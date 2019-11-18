package com.vivek.demo.database;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vivek.demo.home.datamodel.model.ArticleModel;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromBranch(List<ArticleModel> branchTimes) {
        if (branchTimes == null || branchTimes.isEmpty()) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ArticleModel>>() {
        }.getType();
        return gson.toJson(branchTimes, type);
    }

    @TypeConverter
    public List<ArticleModel> toBranch(String branchTimes) {
        if (branchTimes == null || TextUtils.isEmpty(branchTimes)) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ArticleModel>>() {
        }.getType();
        return gson.fromJson(branchTimes, type);
    }
}
