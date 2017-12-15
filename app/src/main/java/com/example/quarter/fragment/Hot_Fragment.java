package com.example.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quarter.R;
import com.example.quarter.adapter.MyTuijianHotXRecycleView;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.presenter.GuanggaoPresent;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.GuanggaoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Hot_Fragment extends Fragment implements GuanggaoView{

    private View view;
    private XBanner xbanner;
    private ArrayList<String> list;
    private XRecyclerView tj_hot_xrl;
    private View inflate;
    private ArrayList<GroomHotBean.DataBean> ls;
    private MyTuijianHotXRecycleView myTuijianHotXRecycleView;
    private int page=1;
    private GuanggaoPresent guanggaoPresent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.hot_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        guanggaoPresent = new GuanggaoPresent(this);
        guanggaoPresent.GuanggaoPresentSuccess(MyInterceptor.id,"1","1");

    }

    private void initData() {
        list = new ArrayList<>();
        ls = new ArrayList<>();
    }

    private void initView() {
        tj_hot_xrl = view.findViewById(R.id.tj_hot_xrl);
        tj_hot_xrl.setLoadingMoreEnabled(true);
        tj_hot_xrl.setPullRefreshEnabled(true);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.top_xbanner, null);
        xbanner = inflate.findViewById(R.id.xbanner);
        tj_hot_xrl.addHeaderView(inflate);
    }

    @Override
    public void GuanggaoSuccess(Guanggao value) {
        List<Guanggao.DataBean> data = value.getData();
        for (int i = 0; i < data.size(); i++) {
            Guanggao.DataBean dataBean = data.get(i);
            String icon = dataBean.getIcon();
            list.add(icon);
        }
        xbanner.removeAllViews();
        xbanner.setData(list,null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getContext()).load(list.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void GuanggaoFailue(String msg) {

    }

    @Override
    public void GuanggaoError(Throwable e) {

    }

    @Override
    public void GroomHotSuccess(GroomHotBean value) {
        System.out.println("视频列表+"+value.getMsg());
        ls.addAll(value.getData());
        if(myTuijianHotXRecycleView==null)
        {
            myTuijianHotXRecycleView = new MyTuijianHotXRecycleView(ls,getContext());
            tj_hot_xrl.setLayoutManager(new LinearLayoutManager(getContext()));
            tj_hot_xrl.setAdapter(myTuijianHotXRecycleView);
        }
     else
        {
            myTuijianHotXRecycleView.notifyDataSetChanged();
        }
        tj_hot_xrl.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                System.out.println("刷新");
                ls.clear();
                page=1;
                guanggaoPresent.GuanggaoPresentSuccess(MyInterceptor.id,"1",page+"");
                tj_hot_xrl.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                System.out.println("加载更多");
                page++;
                guanggaoPresent.GuanggaoPresentSuccess(MyInterceptor.id,"1",page+"");
                tj_hot_xrl.loadMoreComplete();
            }
        });

    }

    @Override
    public void GroomHotFailue(String msg) {
        System.out.println("视频列表+"+msg);
    }

    @Override
    public void GroomHotError(Throwable e) {

    }
}
