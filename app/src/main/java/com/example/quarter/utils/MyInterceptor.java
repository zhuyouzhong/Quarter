package com.example.quarter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.Toast;

import com.example.quarter.myapp.MyApp;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;

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
            System.out.println("我是拦截器====="+request.body());
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
            if(request.body() instanceof MultipartBody)
            {
                MultipartBody multipartBody= (MultipartBody) request.body();
                MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("source","android")
                            .addFormDataPart("appVersion",""+versionCode)
                            .addFormDataPart("token",tk);
                List<MultipartBody.Part> parts = multipartBody.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request=request.newBuilder().post(builder.build()).build();
            }
        }

        Response proceed = chain.proceed(request);
        return proceed;
    }
}
