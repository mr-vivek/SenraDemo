package com.vivek.demo.home.datamodel.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vivek.demo.home.datamodel.model.ArticleModel;
import com.vivek.demo.home.datamodel.model.NewsDataModel;

import java.util.List;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHomeData(NewsDataModel... dataModels);

    @Query("SELECT * FROM news_data")
    List<NewsDataModel> getAllHomeData();

    @Query("SELECT count(dataId) FROM news_data WHERE dataId = :dataId")
    int countHomeData(int dataId);

    @Query("SELECT dataId FROM news_data WHERE dataId =:dataId")
    int getPrimaryKey(int dataId);

    @Update
    int updateHomeData(NewsDataModel dataModel);

}
