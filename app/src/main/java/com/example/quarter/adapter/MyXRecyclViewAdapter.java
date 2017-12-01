package com.example.quarter.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.quarter.HomeActivity;
import com.example.quarter.R;
import com.example.quarter.bean.ForgeBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class MyXRecyclViewAdapter extends RecyclerView.Adapter<MyXRecyclViewAdapter.MyViewHolder> {

    private List<ForgeBean.DataBean> list;
    private Context context;
    private int width;
    private int heightPixels;
    public MyXRecyclViewAdapter(List<ForgeBean.DataBean> list, Context context,int width,int heightPixels) {

        this.list = list;
        this.context = context;
        this.width=width;
        this.heightPixels=heightPixels;
    }

    @Override
    public MyXRecyclViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forge_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyXRecyclViewAdapter.MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        Glide.with(context).load(list.get(position).getUser().getIcon())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).dontAnimate().into(holder.forge_item_icon);

        holder.forge_item_nickname.setText(list.get(position).getUser().getNickname());
        holder.forge_item_createtime.setText(list.get(position).getCreateTime());
        holder.forge_item_content.setText(list.get(position).getContent());

        final ObjectAnimator animator = ObjectAnimator.ofFloat(holder.iv_jia, "rotation", 0f, 180f);
        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(holder.ll_yi, "translationX", 0f, -80f);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(holder.ll_er, "translationX", 0f,-160f);
        final ObjectAnimator animator3= ObjectAnimator.ofFloat(holder.ll_san, "translationX", 0f, -240f);

        final ObjectAnimator animator4 = ObjectAnimator.ofFloat(holder.iv_jia, "rotation", 0f, 180f);
        final ObjectAnimator animator5 = ObjectAnimator.ofFloat(holder.ll_yi, "alpha", 0f,1f);
        final ObjectAnimator animator6 = ObjectAnimator.ofFloat(holder.ll_er, "alpha", 0f,1f);
        final ObjectAnimator animator7= ObjectAnimator.ofFloat(holder.ll_san, "alpha", 0f,1f);


        final ObjectAnimator rotation = ObjectAnimator.ofFloat(holder.iv_jian, "rotation", 0f, -180f);
        final ObjectAnimator rotation1 = ObjectAnimator.ofFloat(holder.ll_yi, "translationX", -80f,0f);
        final ObjectAnimator rotation2 = ObjectAnimator.ofFloat(holder.ll_er, "translationX", -160f,0f);
        final ObjectAnimator rotation3= ObjectAnimator.ofFloat(holder.ll_san, "translationX", -240f,0f);

        final ObjectAnimator rotation4 = ObjectAnimator.ofFloat(holder.iv_jian, "rotation", 0f, -180f);
        final ObjectAnimator rotation5 = ObjectAnimator.ofFloat(holder.ll_yi, "alpha", 1f,0f);
        final ObjectAnimator rotation6 = ObjectAnimator.ofFloat(holder.ll_er, "alpha", 1f,0f);
        final ObjectAnimator rotation7= ObjectAnimator.ofFloat(holder.ll_san, "alpha", 1f,0f);


        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                holder.iv_jia.setVisibility(View.GONE);
                holder.iv_jian.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        rotation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                holder.iv_jia.setVisibility(View.VISIBLE);
                holder.iv_jian.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        holder.iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimatorSet animSet = new AnimatorSet();//动画集合
                holder.ll_yi.setVisibility(View.VISIBLE);
                holder.ll_er.setVisibility(View.VISIBLE);
                holder.ll_san.setVisibility(View.VISIBLE);
                animSet.play(animator).with(animator1).with(animator2).with(animator3);
                animSet.play(animator4).with(animator5).with(animator6).with(animator7);
                animSet.setDuration(500);
                animSet.start();
            }
        });
        holder.iv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimatorSet animSet1 = new AnimatorSet();//动画集合
                animSet1.play(rotation).with(rotation1).with(rotation2).with(rotation3);
                animSet1.play(rotation4).with(rotation5).with(rotation6).with(rotation7);
                animSet1.setDuration(500);
                animSet1.start();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "条目为+"+position, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> iv_list=new ArrayList<>();
        String imgUrls = list.get(position).getImgUrls();
         if(imgUrls!=null)
         {
             String[] split = imgUrls.split("\\|");
             for (int i = 0; i < split.length; i++) {
                 iv_list.add(split[i]);
             }
             if(iv_list.size()==1)
             {
                 MyItemRecycleView myItemRecycleView=new MyItemRecycleView(iv_list,context,width,heightPixels);
                 holder.iv_rlv.setLayoutManager(new GridLayoutManager(context,1));
                 holder.iv_rlv.setAdapter(myItemRecycleView);
             }
             else if(iv_list.size()==2)
             {
                 MyItemRecycleView myItemRecycleView=new MyItemRecycleView(iv_list,context,width,heightPixels);
                 holder.iv_rlv.setLayoutManager(new GridLayoutManager(context,2));
                 holder.iv_rlv.setAdapter(myItemRecycleView);
             }
             else
             {
                 MyItemRecycleView myItemRecycleView=new MyItemRecycleView(iv_list,context,width,heightPixels);
                 holder.iv_rlv.setLayoutManager(new GridLayoutManager(context,3));
                 holder.iv_rlv.setAdapter(myItemRecycleView);
             }


         }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CustomShapeImageView forge_item_icon;
        private TextView forge_item_nickname;
        private TextView forge_item_createtime;
        private TextView forge_item_content;
        private final ImageView iv_jian;
        private final ImageView iv_jia;
        private final LinearLayout ll_yi;
        private final LinearLayout ll_er;
        private final LinearLayout ll_san;
        private final RecyclerView iv_rlv;

        public MyViewHolder(View itemView) {
            super(itemView);
            forge_item_icon = itemView.findViewById(R.id.forge_item_icon);
            forge_item_nickname=itemView.findViewById(R.id.forge_item_nickname);
            forge_item_createtime=itemView.findViewById(R.id.forge_item_createtime);
            forge_item_content=itemView.findViewById(R.id.forge_item_content);

            iv_jian = itemView.findViewById(R.id.iv_jian);
            iv_jia = itemView.findViewById(R.id.iv_jia);
            ll_yi = itemView.findViewById(R.id.ll_yi);
            ll_er = itemView.findViewById(R.id.ll_er);
            ll_san = itemView.findViewById(R.id.ll_san);

            iv_rlv = itemView.findViewById(R.id.iv_rlv);
        }


    }
}
