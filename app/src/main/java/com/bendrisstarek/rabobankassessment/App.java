package com.bendrisstarek.rabobankassessment;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.bendrisstarek.rabobankassessment.datasource.DaggerDataComponent;
import com.bendrisstarek.rabobankassessment.datasource.DataComponent;
import com.bendrisstarek.rabobankassessment.datasource.local.database.StorageModule;
import com.bendrisstarek.rabobankassessment.datasource.remote.service.NetworkModule;
import com.bendrisstarek.rabobankassessment.datasource.remote.service.ServiceEndpoint;
import com.bendrisstarek.rabobankassessment.util.LocalHelper;
import com.bendrisstarek.rabobankassessment.widgets.LoadingDialog;

import java.util.Locale;



public class App extends MultiDexApplication {

    private static DataComponent dataComponent;
    private static LoadingDialog loader;
    private static App instance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static Context getContext(){
        return instance;
    }

    public static void hideLoader() {
        LoadingDialog.dismiss(loader);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loader = LoadingDialog.newInstance();
        initAppComponent();
        instance = this;

        LocalHelper.setLocale(this, Locale.getDefault().getDisplayLanguage());
    }

    public static void showLoader(Context context) {
        LoadingDialog.show(context, loader);
    }

    private void initAppComponent() {

        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ServiceEndpoint.BASE_URL))
                .storageModule(new StorageModule())
                .build();


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
