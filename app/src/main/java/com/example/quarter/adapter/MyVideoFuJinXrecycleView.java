package com.example.quarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quarter.R;
import com.example.quarter.Video_XQ_Activity;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VideoFuJinBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 祝文 on 2017/12/7.
 */

public class MyVideoFuJinXrecycleView extends XRecyclerView.Adapter<MyVideoFuJinXrecycleView.MyViewHoolder> {
   private List<VideoFuJinBean.DataBean> list;
   private Context context;
   private List<Integer> heightList;

    public MyVideoFuJinXrecycleView(List<VideoFuJinBean.DataBean> list, Context context ) {
        this.list = list;
        this.context = context;

        heightList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int height=new Random().nextInt(3)+2;
            heightList.add(height);
        }

    }

    @Override
    public MyViewHoolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.video_hot_item, null);
        return new MyViewHoolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHoolder holder, final int position) {

        float scale =  context.getResources().getDisplayMetrics().heightPixels;
        System.out.println("scale+++++++++"+scale);
        try {
            holder.iv_hot.getLayoutParams().height=(int)scale/heightList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Glide.with(context).load(list.get(position).getCover()).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into( holder.iv_hot);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Video_XQ_Activity.class);
                intent.putExtra("wid",list.get(position).getWid()+"");
                Toast.makeText(context, list.get(position).getWid()+"", Toast.LENGTH_SHORT).show();

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoolder extends RecyclerView.ViewHolder {

        private final ImageView iv_hot;

        public MyViewHoolder(View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
        }
    }



}
