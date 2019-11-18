package com.vivek.demo.home.viewmodel;


import android.content.Context;

import androidx.databinding.ObservableField;

import com.vivek.demo.application.AssignmentApp;
import com.vivek.demo.home.datamodel.model.ArticleModel;

import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class HomeDataListAdapterViewModel extends Observable {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;
    private Context context;

    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> detail = new ObservableField<>();
    private ObservableField<String> logoImage = new ObservableField<>();

    ArticleModel dataModel;

    public HomeDataListAdapterViewModel(ArticleModel dataModel) {
        this.dataModel = dataModel;
        this.context = AssignmentApp.getContext();
        setData(dataModel);

    }

    public void setData(ArticleModel dataModel) {
        if(dataModel!=null){

            if(dataModel.getTitle()== null || dataModel.getTitle().isEmpty()){
                setTitle("No data from API");
            }else{
                setTitle(dataModel.getTitle());
            }

            if(dataModel.getDescription()== null || dataModel.getDescription().isEmpty()){
                setDetail("No data from API");
            }else{
                setDetail(dataModel.getDescription());
            }

            setLogoImage(dataModel.getUrlToImage());
        }
    }

    public ObservableField<String> detail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }

    public ObservableField<String> title() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public ObservableField<String> getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage.set(logoImage);
    }



    public void reset() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
        context = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

    }
}
