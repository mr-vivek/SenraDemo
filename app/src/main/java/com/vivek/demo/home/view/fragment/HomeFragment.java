package com.vivek.demo.home.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vivek.demo.R;
import com.vivek.demo.databinding.HomeFragmentBinding;
import com.vivek.demo.home.datamodel.model.NewsDataModel;
import com.vivek.demo.home.view.adapter.HomeDataAdapter;
import com.vivek.demo.home.viewmodel.HomeFragmentViewModel;
import com.vivek.demo.utils.ActivityUtil;
import com.vivek.demo.utils.ServiceResponseModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class HomeFragment extends Fragment implements Observer {

    private HomeFragmentBinding homeFragmentBinding;
    private HomeFragmentViewModel homeFragmentViewModel;
    private ArrayList<NewsDataModel> dataModelList;

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        homeFragmentViewModel = new HomeFragmentViewModel();
        homeFragmentBinding.setHomeFragmentView(homeFragmentViewModel);
        homeFragmentViewModel.addObserver(this);
        return homeFragmentBinding.getRoot();
    }


    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initNewsListCall();
    }

    private void initNewsListCall() {
        startShimmerEffect();
        homeFragmentViewModel.getRateListData();
    }


    public void update(Observable observable, Object arg) {

        ServiceResponseModel serviceResponseModel = (ServiceResponseModel) arg;
        if (observable instanceof HomeFragmentViewModel && serviceResponseModel != null) {

            stopShimmerEffect();

            switch (serviceResponseModel.getStatus()){
                case SUCCESSFULLY_FETCHED_DATA:

                    try {
                        dataModelList = (ArrayList<NewsDataModel>) serviceResponseModel.getData();
                        if (dataModelList != null && !dataModelList.isEmpty()) {
                            loadNewsList();
                        }
                    }catch (Exception e){}
                    break;

               case FAILED_TO_FETCH_DATA:
                    showErrorMessage(serviceResponseModel.getData().toString());
                    break;

                case FAILED:
                    showErrorMessage(serviceResponseModel.getData().toString());
                    break;

                default:
                    break;
            }

        }
    }

    private void loadNewsList() {
        HomeDataAdapter homeDataAdapter = new HomeDataAdapter(getActivity(), dataModelList.get(0).getArticles());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeFragmentBinding.countryDataRecyclerView.setLayoutManager(layoutManager);
        homeFragmentBinding.countryDataRecyclerView.setAdapter(homeDataAdapter);
    }

    public void stopShimmerEffect() {
        homeFragmentViewModel.setShowProgress(View.GONE);
        homeFragmentViewModel.setDataListRecyclerViewVisibility(View.VISIBLE);

        if (homeFragmentBinding.shimmerHome.isAnimationStarted()) {
            homeFragmentBinding.shimmerHome.stopShimmerAnimation();
        }
    }

    public void startShimmerEffect(){
        homeFragmentViewModel.setShowProgress(View.VISIBLE);
        homeFragmentViewModel.setDataListRecyclerViewVisibility(View.INVISIBLE);
        homeFragmentBinding.shimmerHome.startShimmerAnimation();
    }

    public void showErrorMessage(String message){
        ActivityUtil.setSnackBar(homeFragmentBinding.homeFragmentContainer, message);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(homeFragmentViewModel != null) {
            homeFragmentViewModel.reset();

        }
    }



}
