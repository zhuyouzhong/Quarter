package com.example.quarter.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.quarter.MainActivity;
import com.example.quarter.R;
import com.example.quarter.adapter.MyVideoFuJinXrecycleView;
import com.example.quarter.adapter.MyVideoHotXrecycleView;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VideoFuJinBean;
import com.example.quarter.presenter.VideoFuJinPresenter;
import com.example.quarter.presenter.VideoHotPresenter;
import com.example.quarter.view.VideoFuJinView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Fujin_Fragment extends Fragment implements VideoFuJinView{

    private VideoFuJinPresenter videoFuJinPresenter;
    private View view;
    private XRecyclerView xrl_video_hot;
    private VideoHotPresenter videoHotPresenter;
    private int page=1;
    private List<VideoFuJinBean.DataBean> list;
    private MyVideoFuJinXrecycleView myVideoFuJinXrecycleView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private String weidu;
    private String jingdu;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fujin_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        list = new ArrayList<>();
        videoFuJinPresenter = new VideoFuJinPresenter(this);

        SharedPreferences dw = getContext().getSharedPreferences("DW", Context.MODE_PRIVATE);
        weidu = dw.getString("weidu", "");
        jingdu = dw.getString("jingdu", "");
        videoFuJinPresenter.VideoFuJinPresenterSuccess(page+"", weidu, jingdu);
    }

    private void initView() {
        xrl_video_hot = view.findViewById(R.id.xrl_video_hot);
        xrl_video_hot.setLoadingMoreEnabled(true);
        xrl_video_hot.setPullRefreshEnabled(true);
    }

    @Override
    public void VideoFuJinSuccess(VideoFuJinBean videoFuJinBean) {
        list.addAll(videoFuJinBean.getData());
        if(myVideoFuJinXrecycleView==null)
        {
            myVideoFuJinXrecycleView = new MyVideoFuJinXrecycleView(list,getContext());
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
            xrl_video_hot.setAdapter(myVideoFuJinXrecycleView);
        }
        else
        {
            myVideoFuJinXrecycleView.notifyDataSetChanged();
        }
        xrl_video_hot.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page=1;

                videoFuJinPresenter.VideoFuJinPresenterSuccess(page+"", weidu, jingdu);
                xrl_video_hot.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;

                videoFuJinPresenter.VideoFuJinPresenterSuccess(page+"", weidu, jingdu);
                xrl_video_hot.loadMoreComplete();
            }
        });
    }

    @Override
    public void VideoFuJinFailue(String msg) {
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
    public void VideoFuJinError(Throwable e) {

    }

}
