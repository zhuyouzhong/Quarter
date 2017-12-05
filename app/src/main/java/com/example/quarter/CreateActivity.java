package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity {

    private TextView tv_quxiao;
    private LinearLayout ll_shipin;
    private LinearLayout ll_duanzi;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        SharedPreferences jz = getSharedPreferences("JZ", MODE_PRIVATE);
        edit = jz.edit();
        initView();
        initOnClick();
    }

    private void initOnClick() {
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ll_duanzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateActivity.this,SendActivity.class);
                startActivity(intent);
                edit.putBoolean("jz",true);
                edit.commit();

            }
        });
        ll_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateActivity.this,SendActivity.class);
                startActivity(intent);
                edit.putBoolean("jz",false);
                edit.commit();

            }
        });
    }

    private void initView() {
        tv_quxiao = findViewById(R.id.tv_quxiao);
        ll_shipin = findViewById(R.id.ll_shipin);
        ll_duanzi = findViewById(R.id.ll_duanzi);
    }
}
