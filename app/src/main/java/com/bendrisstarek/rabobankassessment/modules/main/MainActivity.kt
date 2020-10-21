package com.bendrisstarek.rabobankassessment.modules.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.bendrisstarek.rabobankassessment.App
import com.bendrisstarek.rabobankassessment.R
import com.bendrisstarek.rabobankassessment.base.BaseActivity
import com.bendrisstarek.rabobankassessment.databinding.ActivityMainBinding
import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import com.bendrisstarek.rabobankassessment.util.StringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset


class MainActivity : BaseActivity(), CsvContentAdapter.OnItemClickListener,
    SearchView.OnQueryTextListener{

    private lateinit var csvContentAdapter: CsvContentAdapter
    private lateinit var csvContentRecycler: RecyclerView
    private lateinit var emptyAnimationView: LottieAnimationView
    private lateinit var errorMessage: TextView
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var searchBar: SearchView
    private var mModels: java.util.ArrayList<StandardModel>? = null

    private var mDisposable: CompositeDisposable? = null
    private var mPaymentViewModel: PaymentsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initVars()
        ReadCsvAsyncTask(this).execute()
        //doGetPayments("1")
    }

    /**
     * this function inits the vars used in this activity
     */
    private fun initVars()
    {
        csvContentRecycler = mBinding.csvContentRecycler
        emptyAnimationView = mBinding.emptyAnimationView
        errorMessage = mBinding.errorMessage
        swipeContainer = mBinding.swipeContainer
        searchBar = mBinding.searchBar
        swipeContainer.setOnRefreshListener {ReadCsvAsyncTask(this).execute()}
        mModels = java.util.ArrayList()
        searchBar.setOnQueryTextListener(this)
        searchBar.setOnClickListener { searchBar.isIconified = false
            searchBar.requestFocus()
        }
    }

    /**
     * this function inits the recycler view and handles the result of the CSV parsing
     */
    internal fun initRecyclerView(lst:java.util.ArrayList<java.util.ArrayList<String>>) {
        App.hideLoader()
        swipeContainer.isRefreshing = false

        when {
            lst.isEmpty() -> {
                emptyAnimationView.visibility = View.VISIBLE
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = resources.getString(R.string.empty_file)
            }
            lst.size==1 -> {
                emptyAnimationView.visibility = View.VISIBLE
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = resources.getString(R.string.only_titles)
            }
            else -> {
                val listModel = java.util.ArrayList<StandardModel>()
                val topList = lst[0]

                for (i in lst.indices) {
                    if (i != 0)
                        listModel.add(StandardModel(lst[i]))
                }

                csvContentAdapter = CsvContentAdapter(this,topList)

                val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

                csvContentRecycler.layoutManager = layoutManager
                csvContentRecycler.setHasFixedSize(true)
                csvContentRecycler.setItemViewCacheSize(listModel.size+1)
                //csvContentRecycler.setItemViewCacheSize(99999999)

                csvContentRecycler.adapter = csvContentAdapter

                csvContentAdapter.change(listModel)
                mModels = listModel
            }
        }
    }

    /**
     * the implementation of the adapter's item click interface
     */
    override fun onContentItemClick(item: StandardModel?) {
        Toast.makeText(applicationContext,resources.getString(R.string.item_clicked),Toast.LENGTH_SHORT).show()
    }


    /**
     * this function gets the user payments, it is used only to show how I use the MVVM architecture
     */
    private fun doGetPayments(userId:String) {
            mDisposable = CompositeDisposable()
            mPaymentViewModel = ViewModelProviders.of(this).get(PaymentsViewModel::class.java)
            mPaymentViewModel?.getUserPayments(userId)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(mContext) }
                ?.doOnSuccess { App.hideLoader() }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<List<Payment>?>() {
                    override fun onSuccess(response: List<Payment>) {
                        /**
                         * here we handle the successful API call
                         */
                    }

                    override fun onError(error: Throwable) {
                        /**
                         * and here we handle the failure API call
                         */
                        App.hideLoader()
                    }
                })?.let { mDisposable?.add(it) }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false    }

    override fun onQueryTextChange(newText: String?): Boolean {
            val filteredModelList: java.util.ArrayList<StandardModel> = filter(mModels, newText!!)!!
            csvContentAdapter.clear()
            csvContentAdapter.change(filteredModelList)
            csvContentAdapter.notifyDataSetChanged()

        return false
    }

    private fun filter(models: java.util.ArrayList<StandardModel>?, query: String): java.util.ArrayList<StandardModel>? {
        val lowerCaseQuery = query.toLowerCase()
        val filteredModelList: java.util.ArrayList<StandardModel> =
            java.util.ArrayList()
        for (model in models!!) {
            var bool = false
            for(m:String in model.list) {

                val text: String = m.toLowerCase()
                if (text.contains(lowerCaseQuery)) {
                    filteredModelList.add(model)
                    bool = true
                }
                if(bool)
                    break
            }
        }
        return filteredModelList
    }

    override fun onBackPressed() {
        if (!searchBar.isIconified) {
            searchBar.isIconified = true
            searchBar.clearFocus()
            //searchBar.setQuery("", false)
        } else {
            super.onBackPressed()
        }
    }
}

/**
 * the async call
 */
class ReadCsvAsyncTask(private var activity: MainActivity?) : AsyncTask<String, String, java.util.ArrayList<java.util.ArrayList<String>>>() {

    override fun onPreExecute() {
        super.onPreExecute()
        App.showLoader(activity)
    }

    /**
     * in background, we will try to get the CSV file and parse it
     */
    override fun doInBackground(vararg p0: String?): java.util.ArrayList<java.util.ArrayList<String>> {

        val result:java.util.ArrayList<java.util.ArrayList<String>> =  ArrayList()
        try {
        val inputStream: InputStream = activity?.resources?.openRawResource(R.raw.issues)!!
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
        reader.readLines().forEach {
            /**
             * get a string array of all items in this line
             */
            val items = it.split(",")
                val list = java.util.ArrayList<String>()

                for (i in items.indices)
                    list.add(StringUtils.transformToValidString(items[i]))

                result.add(list)
            }
        }catch (e:Exception)
        {
            return result
        }
        return result
    }

    /**
     * when the thread is completed, we return the result to the activity to handle it
     */
    override fun onPostExecute(result: java.util.ArrayList<java.util.ArrayList<String>>?) {
        super.onPostExecute(result)
        activity?.initRecyclerView(result!!)
    }
}
