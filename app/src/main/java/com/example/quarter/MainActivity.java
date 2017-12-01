package com.example.quarter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;;
import android.view.WindowManager;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {

        SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
        boolean zhi = sp.getBoolean("zhi", false);
        if(zhi==false)
        {
            TextView tv_outher= findViewById(R.id.tv_outher);
            tv_outher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.tran_next_jin,R.anim.tran_next_chu);
                    finish();
                }
            });

        }
       else
       {
           Intent intent=new Intent(MainActivity.this,HomeActivity.class);
           startActivity(intent);
           finish();
       }


    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
