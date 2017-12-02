package com.example.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quarter.R;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/2.
 */

public class MyTuijianHotXRecycleView extends RecyclerView.Adapter<MyTuijianHotXRecycleView.MyViewHolder> {
    private ArrayList<String> list;
    private Context context;

    public MyTuijianHotXRecycleView(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyTuijianHotXRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijian_hot, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTuijianHotXRecycleView.MyViewHolder holder, int position) {

        holder.tv_tiaomu.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_tiaomu;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_tiaomu = itemView.findViewById(R.id.tv_tiaomu);
        }
    }
}
