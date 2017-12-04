package com.example.quarter.service;

import com.example.quarter.bean.ForgeBean;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.LoginBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

  @Multipart
  @POST("quarter/publishJoke")
  Observable<SendBean> getpublishJoke(@Part() List<MultipartBody.Part> file);

  @Multipart
  @POST("file/upload")
  Observable<SendBean> upload(@Part() List<MultipartBody.Part> file);

  @FormUrlEncoded
  @POST("user/updateNickName")
  Observable<SendBean> updateNickName(@Field("uid") String uid,@Field("nickname") String nickname);

  @POST("quarter/getAd")
  Observable<Guanggao> getAd();

  @FormUrlEncoded
  @POST("quarter/getVideos")
  Observable<GroomHotBean> getVideos(@Field("uid") String uid,@Field("type") String type,@Field("page") String page);
}
