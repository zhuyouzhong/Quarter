package com.example.quarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quarter.R;
import com.example.quarter.UserVideoActivity;
import com.example.quarter.bean.MyGuanzhuBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class MyGuanzhuRecycler extends XRecyclerView.Adapter<MyGuanzhuRecycler.MyViewHolder> {
    private List<MyGuanzhuBean.DataBean> list;
    private Context context;
    public MyGuanzhuRecycler(List<MyGuanzhuBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myguanzhu_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getIcon()).into(holder.mygz_csiv);
        holder.mygz_tv_name.setText(list.get(position).getNickname());
        holder.mygz_tv_time.setText(list.get(position).getCreatetime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserVideoActivity.class);
                intent.putExtra("icon",list.get(position).getIcon());
                intent.putExtra("uid",list.get(position).getUid()+"");
                intent.putExtra("name",list.get(position).getNickname()+"");

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CustomShapeImageView mygz_csiv;
        private final TextView mygz_tv_name;
        private final TextView mygz_tv_time;


        public MyViewHolder(View itemView) {
            super(itemView);
            mygz_csiv = itemView.findViewById(R.id.mygz_csiv);
            mygz_tv_name = itemView.findViewById(R.id.mygz_tv_name);
            mygz_tv_time = itemView.findViewById(R.id.mygz_tv_time);

        }
    }
}
