package com.example.quarter.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.MainActivity;
import com.example.quarter.R;
import com.example.quarter.adapter.MyTuijianHotXRecycleView;
import com.example.quarter.adapter.PingLunRecycleView;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.PingLun;
import com.example.quarter.bean.SendBean;
import com.example.quarter.presenter.GuanggaoPresent;
import com.example.quarter.presenter.VideoPLPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.GuanggaoView;
import com.example.quarter.view.VideoPLView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Hot_Fragment extends Fragment implements GuanggaoView,VideoPLView{

    private View view;
    private XBanner xbanner;
    private ArrayList<String> list;
    private XRecyclerView tj_hot_xrl;
    private View inflate;
    private ArrayList<GroomHotBean.DataBean> ls;
    private MyTuijianHotXRecycleView myTuijianHotXRecycleView;
    private int page=1;
    private GuanggaoPresent guanggaoPresent;
    private VideoPLPresenter videoPLPresenter;

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
        videoPLPresenter = new VideoPLPresenter(this);
       guanggaoPresent.GuanggaoPresentSuccess(MyInterceptor.id,"1",page+"");


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

        intanfce();
    }

    private void intanfce() {
        myTuijianHotXRecycleView.setPlJieKou(new MyTuijianHotXRecycleView.PLJieKou() {
            @Override
            public void setrl_plJieKou(RecyclerView rlv_pl,int p) {

                ArrayList<PingLun> pl_list=new ArrayList<>();
                List<GroomHotBean.DataBean.CommentsBean> comments = ls.get(p).getComments();
                for (int i = 0; i < comments.size(); i++) {
                    GroomHotBean.DataBean.CommentsBean commentsBean = comments.get(i);
                    String content = commentsBean.getContent();
                    String nickname = commentsBean.getNickname();
                    pl_list.add(new PingLun(content,nickname));
                }
                    PingLunRecycleView pingLunRecycleView = new PingLunRecycleView(pl_list,getContext());
                    rlv_pl.setLayoutManager(new LinearLayoutManager(getContext()));
                    rlv_pl.setAdapter(pingLunRecycleView);
                    pingLunRecycleView.notifyDataSetChanged();

            }

            @Override
            public void setll_plJieKou(LinearLayout ll_pingun, final int p) {
                ll_pingun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final View view1 = LayoutInflater.from(getContext()).inflate(R.layout.ed_pinglun_item, null);
                        Toast.makeText(getContext(), "评论", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder a=new AlertDialog.Builder(getContext())
                                .setView(view1)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        EditText et_pinglun=view1.findViewById(R.id.et_pinglun);
                                        videoPLPresenter.VidepPLPresenterSuccess(MyInterceptor.id,ls.get(p).getWid()+"",et_pinglun.getText().toString());
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        a.create().show();
                    }
                });
            }
        });
    }

    @Override
    public void GroomHotFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        }
        if(msg.equals("2"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getContext().getSharedPreferences("ZHI",getContext().MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(getContext(),MainActivity.class);
            getContext().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void GroomHotError(Throwable e) {

    }

    @Override
    public void VideoPLSuccess(SendBean sendBean) {
        System.out.println("视频评论+"+sendBean.getMsg());
        ls.clear();
        page=1;
        guanggaoPresent.GuanggaoPresentSuccess(MyInterceptor.id,"1",page+"");

    }

    @Override
    public void VideoPLFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getContext().getSharedPreferences("ZHI",getContext().MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(getContext(),MainActivity.class);
            getContext().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void VideoPLError(Throwable e) {

    }
}
