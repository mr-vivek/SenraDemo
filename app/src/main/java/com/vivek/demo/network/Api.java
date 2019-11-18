package com.vivek.demo.network;

import com.vivek.demo.home.datamodel.model.NewsDataModel;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Api {

    @GET(URLConstant.NEWS_DATA)
    Single<Response<NewsDataModel>> getCountryDataList();



}