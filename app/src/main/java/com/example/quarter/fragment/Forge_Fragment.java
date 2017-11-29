package com.example.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quarter.R;
import com.example.quarter.adapter.MyXRecyclViewAdapter;
import com.example.quarter.bean.ForgeBean;
import com.example.quarter.presenter.ForgePresenter;
import com.example.quarter.view.ForgeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Forge_Fragment extends Fragment implements ForgeView{

    private View view;
    private int page=1;
    private XRecyclerView xrlv;
    private List<ForgeBean.DataBean> list;
    private ForgePresenter forgePresenter;
    private MyXRecyclViewAdapter myXRecyclViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.forge_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        list=new ArrayList<>();
        forgePresenter = new ForgePresenter(this);
        forgePresenter.ForgePresenterSuccess(""+page);

    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        page=1;
        forgePresenter.ForgePresenterSuccess(""+page);
    }

    private void initView() {
        xrlv = view.findViewById(R.id.xrlv);
        xrlv.setLoadingMoreEnabled(true);
        xrlv.setPullRefreshEnabled(true);
    }

    @Override
    public void ForgeSuccess(final ForgeBean forgeBean) {
        System.out.println("--段子列表成功--"+forgeBean.getMsg());
         list.addAll(forgeBean.getData());
        if(myXRecyclViewAdapter==null)
        {
            myXRecyclViewAdapter = new MyXRecyclViewAdapter(list,getContext());
            xrlv.setAdapter(myXRecyclViewAdapter);
            xrlv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else
        {
            myXRecyclViewAdapter.notifyDataSetChanged();
        }
        xrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "下拉刷新", Toast.LENGTH_SHORT).show();
                list.clear();
                myXRecyclViewAdapter=null;
                page=1;
                forgePresenter.ForgePresenterSuccess(""+page);
                xrlv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                Toast.makeText(getContext(), "上拉加載更多", Toast.LENGTH_SHORT).show();
                page++;
                forgePresenter.ForgePresenterSuccess(""+page);
                xrlv.loadMoreComplete();
                System.out.println("--page--"+page);
            }
        });

    }


}
