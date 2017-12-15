package com.example.quarter.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.quarter.R;
import com.example.quarter.UserVideoActivity;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.myapp.MyApp;
import com.meg7.widget.CustomShapeImageView;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/2.
 */

public class MyTuijianHotXRecycleView extends RecyclerView.Adapter<MyTuijianHotXRecycleView.MyViewHolder> {
    private ArrayList<GroomHotBean.DataBean> list;
    private Context context;


    public MyTuijianHotXRecycleView(ArrayList<GroomHotBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyTuijianHotXRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijian_hot, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyTuijianHotXRecycleView.MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        /*Glide.with(context).load(list.get(position).getUser().getIcon())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).dontAnimate().into(holder.forge_item_icon);*/

        RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
        if(list.get(position).getUser().getIcon()!=null)
        {
            Glide.with(context).load(list.get(position).getUser().getIcon())
                    .apply(options).into(holder.forge_item_icon);
        }

        holder.forge_item_nickname.setText(""+list.get(position).getUser().getNickname());
        holder.forge_item_createtime.setText(list.get(position).getCreateTime());


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
                        AnimatorSet animSet = new AnimatorSet();//jia
                        holder.ll_yi.setVisibility(View.VISIBLE);
                        holder.ll_er.setVisibility(View.VISIBLE);
                        holder.ll_san.setVisibility(View.VISIBLE);
                        animSet.play(animator).with(animator1).with(animator2).with(animator3);
                        animSet.play(animator4).with(animator5).with(animator6).with(animator7);
                        animSet.setDuration(500);
                        animSet.start();
                        list.get(position).setIsopen(true);

                }
            });

            holder.iv_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        AnimatorSet animSet1 = new AnimatorSet();//jian
                        animSet1.play(rotation).with(rotation1).with(rotation2).with(rotation3);
                        animSet1.play(rotation4).with(rotation5).with(rotation6).with(rotation7);
                        animSet1.setDuration(500);
                        animSet1.start();
                        list.get(position).setIsopen(false);
                }
            });

       /* if(list.get(position).isIsopen()==true)
        {

            holder.ll_yi.setVisibility(View.VISIBLE);
            holder.ll_er.setVisibility(View.VISIBLE);
            holder.ll_san.setVisibility(View.VISIBLE);
            holder.iv_jia.setVisibility(View.GONE);
            holder.iv_jian.setVisibility(View.VISIBLE);

        }
        if(list.get(position).isIsopen()==false)
        {
            holder.ll_yi.setVisibility(View.GONE);
            holder.ll_er.setVisibility(View.GONE);
            holder.ll_san.setVisibility(View.GONE);
            holder.iv_jia.setVisibility(View.VISIBLE);
            holder.iv_jian.setVisibility(View.GONE);
        }*/

        holder.forge_item_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "条目为+"+list.get(position).getUid(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, UserVideoActivity.class);
                intent.putExtra("icon",list.get(position).getUser().getIcon());
                intent.putExtra("uid",list.get(position).getUid()+"");
                intent.putExtra("name",list.get(position).getUser().getNickname()+"");

                context.startActivity(intent);

            }
        });

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


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CustomShapeImageView forge_item_icon;
        private TextView forge_item_nickname;
        private TextView forge_item_createtime;
        private final ImageView iv_jian;
        private final ImageView iv_jia;
        private final LinearLayout ll_yi;
        private final LinearLayout ll_er;
        private final LinearLayout ll_san;
        private final RelativeLayout rlv_player;


        public MyViewHolder(View itemView) {
            super(itemView);
            forge_item_icon = itemView.findViewById(R.id.forge_item_icon);
            forge_item_nickname=itemView.findViewById(R.id.forge_item_nickname);
            forge_item_createtime=itemView.findViewById(R.id.forge_item_createtime);
            iv_jian = itemView.findViewById(R.id.iv_jian);
            iv_jia = itemView.findViewById(R.id.iv_jia);
            ll_yi = itemView.findViewById(R.id.ll_yi);
            ll_er = itemView.findViewById(R.id.ll_er);
            ll_san = itemView.findViewById(R.id.ll_san);

            rlv_player = itemView.findViewById(R.id.rlv_player);

        }
    }
}
