package com.example.quarter.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quarter.MainActivity;
import com.example.quarter.R;
import com.example.quarter.UserVideoActivity;
import com.example.quarter.adapter.MyVideoHotXrecycleView;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VideoHotBean;
import com.example.quarter.presenter.VideoHotPresenter;
import com.example.quarter.view.VideoHotView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Hot_Video_Fragment extends Fragment implements VideoHotView{

    private View view;
    private XRecyclerView xrl_video_hot;
    private VideoHotPresenter videoHotPresenter;
    private int page=1;
    private List<UserVideoBean.DataBean> list;
    private MyVideoHotXrecycleView myVideoHotXrecycleView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.hot_video_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        list = new ArrayList<>();
        videoHotPresenter = new VideoHotPresenter(this);
        videoHotPresenter.VideoHotPresenterSuccess(page+"");
    }
    private void initView() {
        xrl_video_hot = view.findViewById(R.id.xrl_video_hot);
        xrl_video_hot.setLoadingMoreEnabled(true);
        xrl_video_hot.setPullRefreshEnabled(true);
    }
    @Override
    public void VideoHotSuccess(UserVideoBean userVideoBean) {


        list.addAll(userVideoBean.getData());
        if(myVideoHotXrecycleView==null)
        {
            myVideoHotXrecycleView = new MyVideoHotXrecycleView(list,getContext());
           staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            //顶部错位解决
            staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
           //防止第一行有白处
            xrl_video_hot.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    staggeredGridLayoutManager.invalidateSpanAssignments();
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            xrl_video_hot.setLayoutManager(staggeredGridLayoutManager);
            xrl_video_hot.setAdapter(myVideoHotXrecycleView);
        }
        else
        {
             myVideoHotXrecycleView.notifyDataSetChanged();
        }
        xrl_video_hot.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page=1;
                videoHotPresenter.VideoHotPresenterSuccess(page+"");
                xrl_video_hot.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                videoHotPresenter.VideoHotPresenterSuccess(page+"");
                xrl_video_hot.loadMoreComplete();
            }
        });



    }

    @Override
    public void VideoHotFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getContext().getSharedPreferences("ZHI", getContext().MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(getContext(),MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void VideoHotError(Throwable e) {

    }
}
