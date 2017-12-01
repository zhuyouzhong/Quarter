package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.LoginBean;
import com.example.quarter.presenter.LoginPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.LoginView;

import java.util.List;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private SharedPreferences.Editor edit;
    private SharedPreferences.Editor edit_uid;
    private SharedPreferences.Editor edit3;
    private SharedPreferences.Editor edit2;

    @Override
    public LoginPresenter initPresenter() {
        LoginPresenter loginPresenter=new LoginPresenter(this);
        return loginPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView iv_login_lefts= findViewById(R.id.iv_login_lefts);
        TextView tv_login=findViewById(R.id.tv_login);
        TextView yk=findViewById(R.id.yk);
        final EditText et_login_mobile=findViewById(R.id.et_login_mobile);
        final EditText et_login_password=findViewById(R.id.et_login_password);
        TextView tv_dl=findViewById(R.id.tv_dl);

        iv_login_lefts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tran_pre_jin,R.anim.tran_pre_chu);
                finish();

            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegsionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tran_next_jin,R.anim.tran_next_chu);
                finish();
            }
        });

        tv_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String login_mobile= et_login_mobile.getText().toString();
                    String login_password= et_login_password.getText().toString();
                    if(TextUtils.isEmpty(login_mobile))
                    {
                        Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(login_password))
                    {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    presenter.LoginPresenterSuccess(login_mobile,login_password);

            }
        });

        yk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(MyInterceptor.tk)||TextUtils.isEmpty(MyInterceptor.id))
                {
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.tran_next_jin,R.anim.tran_next_chu);
                    finish();
                }

            }
        });
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        this.edit = token.edit();
        SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
        edit_uid = uid.edit();

        SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
        edit2 = sp.edit();
    }

    @Override
    public void LoginSuccess(LoginBean loginBean) {
        System.out.println("------"+loginBean.getMsg());
        if(loginBean.getCode().equals("0"))
        {
            edit.putString("tk",loginBean.getData().getToken());
            edit.commit();
            edit_uid.putString("id",""+loginBean.getData().getUid());
            edit_uid.commit();
            edit2.putBoolean("zhi",true);
            edit2.commit();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.tran_next_jin,R.anim.tran_next_chu);
            finish();

        }
        if(loginBean.getCode().equals("1"))
        {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
