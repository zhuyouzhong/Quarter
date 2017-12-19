package com.example.quarter.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.quarter.R;
import com.example.quarter.UserVideoActivity;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.MyGuanzhuBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class MyZuoPinRecycler extends XRecyclerView.Adapter<MyZuoPinRecycler.MyViewHolder> {
    private List<HQUserBean.DataBean.WorksEntitiesBean> list;
    private Context context;
    public MyZuoPinRecycler(List<HQUserBean.DataBean.WorksEntitiesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myzuopin_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        SharedPreferences fl = context.getSharedPreferences("FL", Context.MODE_PRIVATE);
        boolean fenlei = fl.getBoolean("fenlei", true);
        if(fenlei)
        {
            holder.iv_zuopin.setVisibility(View.GONE);
            holder.rlv_player.setVisibility(View.VISIBLE);
            View inflate = LayoutInflater.from(context).inflate(R.layout.simple_player_view_player, holder.rlv_player);
            String videoUrl = list.get(position).getVideoUrl();
            String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
            System.out.println("视频路径+++++"+replace);
            PlayerView playerVie=new PlayerView((Activity) context,inflate)
                    .setScaleType(PlayStateParams.fitparent)
                    .hideMenu(true)
                    .forbidTouch(false)
                    .showThumbnail(new OnShowThumbnailListener() {
                        @Override
                        public void onShowThumbnail(ImageView ivThumbnail) {
                            /**加载前显示的缩略图*/
                            Glide.with(context)
                                    .load(list.get(position).getCover())
                                    .into(ivThumbnail);
                        }
                    })
                    .hideBack(true)
                    .hideCenterPlayer(false)
                    .setPlaySource(replace);
        }
       else
        {
            holder.iv_zuopin.setVisibility(View.VISIBLE);
            holder.rlv_player.setVisibility(View.GONE);
            Glide.with(context).load(list.get(position).getCover()).into(holder.iv_zuopin);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_zuopin;
        private final RelativeLayout rlv_player;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_zuopin = itemView.findViewById(R.id.iv_zuopin);
            rlv_player = itemView.findViewById(R.id.rlv_player);
        }
    }
}
