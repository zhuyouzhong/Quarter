package com.example.quarter.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.R;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/29.
 */

public class MySendRecycleView extends RecyclerView.Adapter<MySendRecycleView.MyViewHolder> {

    private ArrayList<String> path;
    private Context context;

    public MySendRecycleView(ArrayList<String> path, Context context) {
        this.path = path;
        this.context = context;
    }

    @Override
    public MySendRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sendrecycle_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySendRecycleView.MyViewHolder holder, final int position) {

        Glide.with(context).load(path.get(position)).into(holder.iv_send);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "条目"+position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ab=new AlertDialog.Builder(context)
                        .setTitle("放弃上传这张图片吗?")
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               path.remove(position);
                               notifyDataSetChanged();
                               Toast.makeText(context,""+ path.size(), Toast.LENGTH_SHORT).show();
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
        return path.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_send;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_send = itemView.findViewById(R.id.iv_send);
        }
    }
}
