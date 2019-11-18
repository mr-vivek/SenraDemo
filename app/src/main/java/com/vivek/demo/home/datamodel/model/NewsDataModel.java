package com.vivek.demo.home.datamodel.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vivek.demo.database.DataConverter;

import java.util.List;

@Entity(tableName = "news_data")
public class NewsDataModel {

    public NewsDataModel() {
    }

    @PrimaryKey(autoGenerate = true)
    @SerializedName("dataId")
    @Expose
    private Integer dataId;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @TypeConverters(DataConverter.class)
    @SerializedName("articles")
    @Expose
    private List<ArticleModel> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }
}

