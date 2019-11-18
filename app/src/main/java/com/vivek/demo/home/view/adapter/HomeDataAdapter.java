package com.vivek.demo.home.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.demo.R;
import com.vivek.demo.databinding.ItemNewsBinding;
import com.vivek.demo.home.datamodel.model.ArticleModel;
import com.vivek.demo.home.view.fragment.HomeFragment;
import com.vivek.demo.home.view.fragment.NewsDetailFragment;
import com.vivek.demo.home.viewmodel.HomeDataListAdapterViewModel;
import com.vivek.demo.utils.FragmentUtil;

import java.util.List;


public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleModel> dataModelArrayList;
    private ItemNewsBinding itemNewsBinding;


    public HomeDataAdapter(Context context, List<ArticleModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @androidx.annotation.NonNull
    @Override
    public HomeDataAdapter.MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        itemNewsBinding =  DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_news, viewGroup, false);


        return new HomeDataAdapter.MyViewHolder(itemNewsBinding);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.bindUser(dataModelArrayList.get(i));

        myViewHolder.itemView.setOnClickListener((View view) -> {

            NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("NEWS_URL", dataModelArrayList.get(i).getUrl());
            newsDetailFragment.setArguments(bundle);

            FragmentUtil.replaceFragment(context, newsDetailFragment, R.id.home_container, 0);

        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemNewsBinding itemNewsListBinding1;

        public MyViewHolder(@androidx.annotation.NonNull ItemNewsBinding homeDataListBinding) {
            super(homeDataListBinding.getRoot());
            this.itemNewsListBinding1 = homeDataListBinding;
        }

        void bindUser(ArticleModel dataModel) {

            if (itemNewsListBinding1.getHomeDataListAdapterViewModel() == null) {
                HomeDataListAdapterViewModel homeDataListAdapterViewModel = new HomeDataListAdapterViewModel(dataModel);
                itemNewsListBinding1.setHomeDataListAdapterViewModel(homeDataListAdapterViewModel);
            }else {
                itemNewsListBinding1.getHomeDataListAdapterViewModel().setData(dataModel);
            }
        }
    }

}

