package com.example.quarter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.VersionBean;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.presenter.UpdatePresenter;
import com.example.quarter.utils.ClearCache;
import com.example.quarter.view.UpdateView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class ShezhiActivity extends BaseActivity<UpdatePresenter> implements UpdateView {

    private TextView tv_fanhui;
    private Button bu_tuichu;
    private TextView tv_daxiao;
    private RelativeLayout rl_deleter;
    private RelativeLayout rl_deleter1;
    private RelativeLayout rl_update;
    private ProgressDialog dialog;

    @Override
    public UpdatePresenter initPresenter() {
        UpdatePresenter updatePresenter=new UpdatePresenter(this);
        return updatePresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shezhi;
    }

    @Override
    public void initView() {
        init();
        initonClick();
    }

    private void initonClick() {
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bu_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences token = MyApp.context.getSharedPreferences("token", Context.MODE_PRIVATE);
                token.edit().clear().commit();
                SharedPreferences uid = MyApp.context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                uid.edit().clear().commit();
                SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(ShezhiActivity.this,MainActivity.class));
                finish();
            }
        });
        rl_deleter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearCache.clearAllCache(ShezhiActivity.this);
                AlertDialog.Builder a=new AlertDialog.Builder(ShezhiActivity.this)
                        .setMessage("是否清除?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
                                    tv_daxiao.setText(totalCacheSize);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                a.create().show();
            }
        });

        rl_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.UpdateSuccessPresenter();


            }
        });
    }

    private void init() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        bu_tuichu = findViewById(R.id.bu_tuichu);
        tv_daxiao = findViewById(R.id.tv_daxiao);
        rl_deleter1 = findViewById(R.id.rl_deleter);
        rl_update = findViewById(R.id.rl_update);

        try {
            String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
            tv_daxiao.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateSuccess(final VersionBean versionBean) {
        System.out.println("APK"+versionBean.getData().getApkUrl());
        AlertDialog.Builder a=new AlertDialog.Builder(ShezhiActivity.this)
                .setTitle("是否更新")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //定义保存的文件地址为根目录
                        File path = new File(Environment.getExternalStorageDirectory(),
                                "一刻钟"+ ".apk");
                        httpDownLoad(path.getPath(),versionBean.getData().getApkUrl());
                    }
                })
                .setNegativeButton("稍后", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        a.create().show();
        dialog=new ProgressDialog(ShezhiActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("更新应用");
        dialog.setMax(100);
    }


    private void httpDownLoad(String path, String url) {
        HttpUtils http = new HttpUtils();
        http.download(url, path, true, true, new RequestCallBack<File>() {

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                int index = (int) (current * 100 / total);
                dialog.setProgress(index);
            }


            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                //获取到安装包后，调用系统的android安装apk界面进行安装 这是固定格式
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.fromFile(new File(responseInfo.result.getPath())),
                        "application/vnd.android.package-archive");
                startActivity(intent);
                dialog.dismiss();
                ShezhiActivity.this.finish();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFailure(
                    com.lidroid.xutils.exception.HttpException arg0, String arg1) {
                File path = new File(Environment.getExternalStorageDirectory(),
                        "一刻钟" + ".apk");
                Toast.makeText( ShezhiActivity.this, "下载失败"+arg1, 0).show();
                dialog.dismiss();
                path.delete();
            }
        });
    }



    @Override
    public void UpdateFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(ShezhiActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void UpdateError(Throwable e) {

    }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);

        initView();
        initonClick();
    }

    private void initonClick() {
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bu_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences token = MyApp.context.getSharedPreferences("token", Context.MODE_PRIVATE);
                token.edit().clear().commit();
                SharedPreferences uid = MyApp.context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                uid.edit().clear().commit();
                SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(ShezhiActivity.this,MainActivity.class));
                finish();
            }
        });
        rl_deleter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearCache.clearAllCache(ShezhiActivity.this);
                AlertDialog.Builder a=new AlertDialog.Builder(ShezhiActivity.this)
                        .setMessage("是否清除?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
                                    tv_daxiao.setText(totalCacheSize);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                a.create().show();

            }
        });
    }

    private void initView() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        bu_tuichu = findViewById(R.id.bu_tuichu);
        tv_daxiao = findViewById(R.id.tv_daxiao);
        rl_deleter1 = findViewById(R.id.rl_deleter);
        rl_update = findViewById(R.id.rl_update);
        try {
            String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
            tv_daxiao.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
