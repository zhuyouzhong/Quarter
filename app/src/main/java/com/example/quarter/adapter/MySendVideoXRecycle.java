package com.example.quarter.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.quarter.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/4.
 */

public class MySendVideoXRecycle extends XRecyclerView.Adapter<MySendVideoXRecycle.MyViewHolder> {
   private ArrayList<LocalMedia> list;
   private Context context;

    public MySendVideoXRecycle(ArrayList<LocalMedia> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MySendVideoXRecycle.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sendvideorecycle_item, null);
        return new MySendVideoXRecycle.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySendVideoXRecycle.MyViewHolder holder, final int position) {
        //Glide.with(context).load(list.get(position)).into(holder.iv_send);
        View inflate = LayoutInflater.from(context).inflate(R.layout.simple_player_view_player, holder.player);
        PlayerView playerVie=new PlayerView((Activity) context,inflate)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .hideBack(true)
                .operatorPanl()
                .hideMenu(true)
                .hideRotation(true)
                .hideFullscreen(true)
                .hideCenterPlayer(true)
                .setShowSpeed(false)
                .hideBottonBar(true)
                .hideBottonBar(true)
                .hideControlPanl(true)
                .setPlaySource(list.get(position).getPath());
        playerVie.startPlay();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "条目"+position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ab=new AlertDialog.Builder(context)
                        .setTitle("放弃上传视频吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                ab.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout player;

        public MyViewHolder(View itemView) {
            super(itemView);
            player = itemView.findViewById(R.id.player);
        }
    }
}
