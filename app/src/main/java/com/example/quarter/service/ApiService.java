package com.example.quarter.service;

import com.example.quarter.bean.ForgeBean;
import com.example.quarter.bean.LoginBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 祝文 on 2017/11/13.
 */

public interface ApiService {
  @FormUrlEncoded
  @POST("user/reg")
  Observable<ResponseBody> getUser(@Field("mobile") String mobile,@Field("password") String password);

  @FormUrlEncoded
  @POST("user/login")
  Observable<LoginBean> getLogin(@Field("mobile") String mobile, @Field("password") String password);

  @FormUrlEncoded
  @POST("user/getUserInfo")
  Observable<UserBean> getUserInfo(@Field("uid") String uid);

  @FormUrlEncoded
  @POST("quarter/getJokes")
  Observable<ForgeBean> getJokes(@Field("page") String page);

  @FormUrlEncoded
  @POST("quarter/publishJoke")
  Observable<SendBean> getpublishJoke(@Field("uid") String uid, @Field("content") String content);
}
