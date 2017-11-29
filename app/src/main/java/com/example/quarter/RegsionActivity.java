package com.example.quarter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.base.BaseActivity;
import com.example.quarter.presenter.RegPresenter;
import com.example.quarter.view.RegView;

import java.io.IOException;

import okhttp3.ResponseBody;

public class RegsionActivity extends BaseActivity<RegPresenter> implements RegView{
    @Override
    public RegPresenter initPresenter() {
        RegPresenter regPresenter=new RegPresenter(this);
        return regPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regsion;
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView iv_reg_lefts=findViewById(R.id.iv_reg_lefts);
        TextView tv_reg=findViewById(R.id.tv_reg);
        TextView tv_regsion=findViewById(R.id.tv_regsion);
        final EditText et_mobile=findViewById(R.id.et_mobile);
        final EditText et_password=findViewById(R.id.et_password);
        iv_reg_lefts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegsionActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tran_pre_jin,R.anim.tran_pre_chu);
                finish();

            }
        });
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegsionActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tran_pre_jin,R.anim.tran_pre_chu);
                finish();

            }
        });

        tv_regsion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = et_mobile.getText().toString();
                String password = et_password.getText().toString();
                presenter.PresenterSuccess(mobile,password);
                finish();
            }
        });


    }
    @Override
    public void RegSuccess(ResponseBody responseBody) {
        try {
            Toast.makeText(this, "--"+responseBody.string(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
