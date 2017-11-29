package com.example.quarter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.Toast;

import com.example.quarter.myapp.MyApp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.FormUrlEncoded;

/**
 * Created by 祝文 on 2017/11/27.
 */

public class MyInterceptor implements Interceptor{

    private int versionCode;
    public static  String id;
    public static String tk;

    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferences token = MyApp.context.getSharedPreferences("token", Context.MODE_PRIVATE);
        tk = token.getString("tk", "");
        SharedPreferences uid = MyApp.context.getSharedPreferences("uid", Context.MODE_PRIVATE);
        id = uid.getString("id", "");

        //獲取版本號
        PackageManager pm = MyApp.context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(MyApp.context.getPackageName(), 0);
            versionCode = pi.versionCode;
            System.out.println("版本號++++"+versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Request request = chain.request();
        if(request.method().equals("POST"))
        {
            if(request.body() instanceof FormBody)
            {
                FormBody.Builder bodyBuilder=new FormBody.Builder();
                FormBody formBody= (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i),formBody.encodedValue(i));
                }
                formBody=bodyBuilder
                        .addEncoded("source","android")
                        .addEncoded("appVersion",""+versionCode)
                        .addEncoded("token",tk)
                        .build();
                request=request.newBuilder().post(formBody).build();
            }
        }
        Response proceed = chain.proceed(request);
        return proceed;
    }
}
