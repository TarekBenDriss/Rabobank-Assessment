package com.bendrisstarek.rabobankassessment.modules.main

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bendrisstarek.rabobankassessment.R
import com.bendrisstarek.rabobankassessment.base.BaseAdapter
import com.bendrisstarek.rabobankassessment.base.BaseDiffCallback
import com.bendrisstarek.rabobankassessment.databinding.ItemCsvContentBinding
import java.util.*

class CsvContentAdapter(
    private val mListener: OnItemClickListener?,
    private val topList: ArrayList<String>
) : BaseAdapter<StandardModel?, CsvContentAdapter.ViewHolder?>() {

    /**
     * interface used to handle the item click listener
     */
    interface OnItemClickListener {
        fun onContentItemClick(item: StandardModel?)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_csv_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = items[position]!!
        holder.bind(item)
    }

    override fun change(items: List<StandardModel?>) {
        change(NewsDiffCallback(this.items, items))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val context: Context = view.context
        private val binding: ItemCsvContentBinding? = DataBindingUtil.bind(view)
        fun bind(item: StandardModel) {
            binding?.layout?.removeAllViews()
            //binding?.layout?.removeViewAt(0)
            val lst = item.list
            /**
             * this loop will parse the list in the item, and fill the values
             */
            for (i in lst.indices) {
                /**
                 * first, we create a new TextView, and we fill it with the title which is the first line, and then we add the value
                 * I made the title looks bold, the font Comfortaregular, and the size is 5ssp
                 */
                val content = lst[i]

                val txt = TextView(context)
                val htmlContent = HtmlCompat.fromHtml("<b>" + topList[i] + " :</b>   " + content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                txt.text = htmlContent
                txt.textSize = context.resources.getDimension(R.dimen._5ssp)
                val face = Typeface.createFromAsset(context.assets, "fonts/comfortaaregular.ttf")
                txt.typeface = face
                /**
                 * the width and height of the TextView are both Wrap_Content
                 */
                val layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT)
                /**
                 * and finally, the textview is added to the vertical LinearLayout
                 */
                layoutParams.leftToLeft = binding!!.layout.left
                layoutParams.topToTop = binding.layout.top
                txt.layoutParams = layoutParams
                binding.layout.addView(txt)
            }
            /**
             * when the item is clicked, we send the Item so we may use it
             */
            binding!!.cardView.setOnClickListener {
                if (mListener != null) {
                    val position1 = adapterPosition
                    if (position1 != RecyclerView.NO_POSITION) {
                        mListener.onContentItemClick(item)
                    }
                }
            }

            binding.executePendingBindings()
        }

    }

    inner class NewsDiffCallback(oldList: List<StandardModel?>?, newList: List<StandardModel?>?) : BaseDiffCallback<StandardModel?>(oldList!!, newList!!) {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldContent = oldList[oldItemPosition]!!
            val newContent = newList[newItemPosition]!!
            val s1 = oldContent.list.toString()
            val s2 = newContent.list.toString()
            return s1 == s2
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldContent = oldList[oldItemPosition]!!
            val newContent = newList[newItemPosition]!!
            val s1 = oldContent.list.toString()
            val s2 = newContent.list.toString()
            return s1 == s2
        }
    }

}