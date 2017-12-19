package com.example.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quarter.R;
import com.example.quarter.bean.PingLun;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/18.
 */

public class PingLunRecycleView extends RecyclerView.Adapter<PingLunRecycleView.MyViewHolder> {
   private ArrayList<PingLun> list;
   private Context context;

    public PingLunRecycleView(ArrayList<PingLun> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PingLunRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pinglun_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PingLunRecycleView.MyViewHolder holder, int position) {
        holder.tv_nickname.setText(list.get(position).getNickname()+":");
        holder.tv_content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_nickname;
        private final TextView tv_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nickname = itemView.findViewById(R.id.tv_nickname);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}
