package com.example.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quarter.R;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/30.
 */

public class MyItemRecycleView extends RecyclerView.Adapter<MyItemRecycleView.MyViewHolder> {
   private ArrayList<String> iv_list;
   private Context context;
    private int kuan;
    private int heightPixels;
    public MyItemRecycleView(ArrayList<String> iv_list, Context context,int kuan,int heightPixels) {
        this.iv_list = iv_list;
        this.context = context;
        this.kuan=kuan;
        this.heightPixels=heightPixels;
    }

    @Override
    public MyItemRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forge_item_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyItemRecycleView.MyViewHolder holder, int position) {
        if(iv_list.size()==1)
        {
            LinearLayout.LayoutParams params =  (LinearLayout.LayoutParams)
                    holder.iv_item_item.getLayoutParams();
            params.width=kuan;
            int i = heightPixels / 2;
            params.height=i;
            holder.iv_item_item.setLayoutParams(params);

           // RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
            Glide.with(context).load(iv_list.get(position)).into(holder.iv_item_item);
        }
        else if(iv_list.size()==2)
        {
            LinearLayout.LayoutParams params =  (LinearLayout.LayoutParams)
                    holder.iv_item_item.getLayoutParams();
            int i = kuan / 2;
            params.width=i;
            holder.iv_item_item.setLayoutParams(params);
            //RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
            Glide.with(context).load(iv_list.get(position)).into(holder.iv_item_item);

        }
        else
        {
            //RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
            Glide.with(context).load(iv_list.get(position)).into(holder.iv_item_item);
        }

    }

    @Override
    public int getItemCount() {
        return iv_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_item_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_item_item = itemView.findViewById(R.id.iv_item_item);
        }
    }
}
