package com.example.quarter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quarter.myapp.MyApp;

public class ShezhiActivity extends AppCompatActivity {

    private TextView tv_fanhui;
    private Button bu_tuichu;

    @Override
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
            }
        });
    }

    private void initView() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        bu_tuichu = findViewById(R.id.bu_tuichu);
    }
}
