package com.example.quarter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.adapter.MyUserVideoXRecycleView;
import com.example.quarter.adapter.PingLunRecycleView;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.PingLun;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.presenter.GuanggaoPresent;
import com.example.quarter.presenter.UserVideoPresenter;
import com.example.quarter.presenter.VideoPLPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.GuanggaoView;
import com.example.quarter.view.UserVideoView;
import com.example.quarter.view.VideoPLView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;

import java.util.ArrayList;
import java.util.List;

public class UserVideoActivity extends BaseActivity<UserVideoPresenter> implements UserVideoView,GuanggaoView,VideoPLView{

    private TextView tv_name;
    private CustomShapeImageView csi_iv;
    private TextView iv_guanzhu;
    private Button iv_yiguanzhu;
    private int num=0;
    private ImageView iv_geren_zuojiantou;
    private XRecyclerView xrl_user_video;
    private String uid;
    private int page=1;
    private List<UserVideoBean.DataBean> list;
    private String icon;
    private String name;
    private UserVideoPresenter userVideoPresenter;
    private MyUserVideoXRecycleView myUserVideoXRecycleView;
    private String fans;
    private String follow;
    private TextView tv_fensi;
    private TextView tv_guauzhu;
    private GuanggaoPresent guanggaoPresent;
    // private GuanggaoPresent guanggaoPresent;
    private ArrayList<PingLun> pl_list;

    @Override
    public UserVideoPresenter initPresenter() {
        userVideoPresenter = new UserVideoPresenter(this);
        return userVideoPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_video;
    }

    @Override
    public void initView() {
        inView();
        initOnClick();
    }

    private void initOnClick() {

        iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.GuanzhuPresenterSuccess(MyInterceptor.id,uid);
            }
        });
        iv_yiguanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.GuanzhuPresenterSuccess(MyInterceptor.id,uid);

            }
        });

        iv_geren_zuojiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inView() {
        View view = LayoutInflater.from(UserVideoActivity.this).inflate(R.layout.user_video_item, null);
        iv_guanzhu = view.findViewById(R.id.iv_guanzhu);
        iv_yiguanzhu =view. findViewById(R.id.iv_yiguanzhu);

        tv_fensi = view.findViewById(R.id.tv_fensi);
        tv_guauzhu = view.findViewById(R.id.tv_guauzhu);

        iv_geren_zuojiantou = view.findViewById(R.id.iv_geren_zuojiantou);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        icon = intent.getStringExtra("icon");
        name = intent.getStringExtra("name");

        tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(name);
        csi_iv =view. findViewById(R.id.csi_iv);
        Glide.with(this).load(icon).into(csi_iv);




        xrl_user_video = findViewById(R.id.xrl_user_video);
        xrl_user_video.addHeaderView(view);
        list = new ArrayList<>();

        presenter.UserPresenterSuccess(uid);
        presenter.HQUserPresenterSuccess(uid);

    }

    @Override
    public void UserVideoSuccess(UserVideoBean userVideoBean) {
        System.out.println("+++++++++++++++++"+userVideoBean.getMsg());
        list.addAll(userVideoBean.getData());

        if(myUserVideoXRecycleView==null)
        {
            myUserVideoXRecycleView = new MyUserVideoXRecycleView(list,UserVideoActivity.this,icon,name);
            xrl_user_video.setLayoutManager(new LinearLayoutManager(UserVideoActivity.this));
            xrl_user_video.setAdapter(myUserVideoXRecycleView);
        }
        else
        {
            myUserVideoXRecycleView.notifyDataSetChanged();
        }
        xrl_user_video.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.UserVideoPresenterSuccess(uid,page+"");
                xrl_user_video.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                presenter.UserVideoPresenterSuccess(uid,page+"");
                xrl_user_video.loadMoreComplete();
            }
        });

        guanggaoPresent = new GuanggaoPresent(this);
        guanggaoPresent.GuanggaoPresentSuccess(uid,"1",page+"");
    }

    @Override
    public void UserVideoFailue(String msg) {

        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void UserVideoError(Throwable e) {

    }
    @Override
    public void UserSuccess(UserBean userBean) {
        String s = userBean.getData().getFans() + "";
        String s1 = userBean.getData().getFollow() + "";
        tv_fensi.setText(s);
        tv_guauzhu.setText(s1);
        presenter.UserVideoPresenterSuccess(uid,page+"");
    }

    @Override
    public void UserFailue(String msg) {

    }
    @Override
    public void UserError(Throwable e) {

    }


    //关注
    @Override
    public void GuanzhuSuccess(SendBean sendBean) {
        Toast.makeText(this, sendBean.getMsg(), Toast.LENGTH_SHORT).show();
        iv_guanzhu.setVisibility(View.GONE);
        iv_yiguanzhu.setVisibility(View.VISIBLE);
    }

    @Override
    public void GuanzhuFailue(String msg) {

        if(msg.equals("1"))
        {
            Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show();

        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void GuanzhuError(Throwable e) {

    }



    @Override
    public void HQUserSuccess(HQUserBean sendBean) {
        if(sendBean.getData().getUser().isFollow())
        {
            iv_guanzhu.setVisibility(View.GONE);
            iv_yiguanzhu.setVisibility(View.VISIBLE);
        }
        else
        {
            iv_guanzhu.setVisibility(View.VISIBLE);
            iv_yiguanzhu.setVisibility(View.GONE);
        }
    }

    @Override
    public void HQUserFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show();

        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void HQUserError(Throwable e) {

    }





    @Override
    public void GuanggaoSuccess(Guanggao value) {

    }

    @Override
    public void GuanggaoFailue(String msg) {

    }

    @Override
    public void GuanggaoError(Throwable e) {

    }

    @Override
    public void GroomHotSuccess(final GroomHotBean value) {

        myUserVideoXRecycleView.setJieKou(new MyUserVideoXRecycleView.JieKou() {
            @Override
            public void setRlv_pl(RecyclerView rlv_pl,int xb) {
                pl_list = new ArrayList<>();
                GroomHotBean.DataBean dataBean = value.getData().get(xb);
                List<GroomHotBean.DataBean.CommentsBean> comments = dataBean.getComments();
                for (int i = 0; i < comments.size(); i++) {
                    GroomHotBean.DataBean.CommentsBean commentsBean = comments.get(i);
                    String nickname = commentsBean.getNickname();
                    String content = commentsBean.getContent();
                    pl_list.add(new PingLun(content,nickname));
                }

                PingLunRecycleView pingLunRecycleView= new PingLunRecycleView(pl_list,UserVideoActivity.this);
                rlv_pl.setLayoutManager(new LinearLayoutManager(UserVideoActivity.this));
                rlv_pl.setAdapter(pingLunRecycleView);
                pingLunRecycleView.notifyDataSetChanged();
            }

            @Override
            public void setLl_pingun(LinearLayout ll_pingun, final int xb) {
                ll_pingun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final View view1 = LayoutInflater.from(UserVideoActivity.this).inflate(R.layout.ed_pinglun_item, null);
                        Toast.makeText(UserVideoActivity.this, "评论", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder a=new AlertDialog.Builder(UserVideoActivity.this)
                                .setView(view1)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        VideoPLPresenter videoPLPresenter=new VideoPLPresenter(UserVideoActivity.this);
                                        EditText et_pinglun=view1.findViewById(R.id.et_pinglun);
                                        videoPLPresenter.VidepPLPresenterSuccess(MyInterceptor.id,list.get(xb).getWid()+"",et_pinglun.getText().toString());
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        a.create().show();
                    }
                });
            }
        });
    }

    @Override
    public void GroomHotFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(UserVideoActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(UserVideoActivity.this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp =  getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void GroomHotError(Throwable e) {

    }

    @Override
    public void VideoPLSuccess(SendBean sendBean) {
        System.out.println("个人视频评论+"+sendBean.getMsg());
        pl_list.clear();
        page=1;
        guanggaoPresent.GuanggaoPresentSuccess(uid,"1",page+"");
    }

    @Override
    public void VideoPLFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(UserVideoActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(UserVideoActivity.this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp =  getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
             startActivity(intent);
             finish();
        }
    }

    @Override
    public void VideoPLError(Throwable e) {

    }
}
