package com.bendrisstarek.rabobankassessment.datasource;



import com.bendrisstarek.rabobankassessment.AppModule;
import com.bendrisstarek.rabobankassessment.datasource.local.database.StorageModule;
import com.bendrisstarek.rabobankassessment.datasource.remote.service.NetworkModule;
import com.bendrisstarek.rabobankassessment.datasource.remote.service.RequestInterceptor;
import com.bendrisstarek.rabobankassessment.modules.main.PaymentsViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class})
public interface DataComponent {

    void inject(PaymentsViewModel paymentViewModel);
    void inject(RequestInterceptor resquestInterceptor);
}
