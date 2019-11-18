package com.vivek.demo.application;

import android.app.Application;
import android.content.Context;

import com.vivek.demo.application.di.ApiComponents;
import com.vivek.demo.application.di.ApiModule;
import com.vivek.demo.application.di.AppModule;
import com.vivek.demo.application.di.DaggerApiComponents;
import com.vivek.demo.network.Api;
import com.vivek.demo.network.URLConstant;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class AssignmentApp extends Application {

    @Inject
    Api api;
    private Scheduler scheduler;
    private ApiComponents mApiComponents;

    private static Context mContext;

    public static Context getContext() {

        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AssignmentApp.mContext = this.getApplicationContext();
        mApiComponents = DaggerApiComponents.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(URLConstant.BASE_URL))
                .build();

        mApiComponents.inject(this);

    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public Api getNetComponent() {
        return api;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;

    }


}
