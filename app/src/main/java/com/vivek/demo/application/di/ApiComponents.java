package com.vivek.demo.application.di;

import com.vivek.demo.application.AssignmentApp;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface ApiComponents {
    void inject(AssignmentApp application);
}
