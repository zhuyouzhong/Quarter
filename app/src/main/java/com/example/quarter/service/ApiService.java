package com.example.quarter.service;

import com.example.quarter.bean.ForgeBean;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.LoginBean;
import com.example.quarter.bean.MyGuanzhuBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VersionBean;
import com.example.quarter.bean.VideoFuJinBean;
import com.example.quarter.bean.VideoXQBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

  /*@FormUrlEncoded
  @POST("quarter/getJokes")
  Observable<ForgeBean> getJokes(@Field("page") String page);*/
  //20秒缓存
  @Headers("cache:20")
  @GET("quarter/getJokes")
  Observable<ForgeBean> getJokes(@Query("page") String page);

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

  @Multipart
  @POST("quarter/publishVideo")
  Observable<SendBean> publishVideo(@Part() List<MultipartBody.Part> file);


  @FormUrlEncoded
  @POST("quarter/getUserVideos")
  Observable<UserVideoBean> getUserVideos(@Field("uid") String uid, @Field("page") String page);


  @FormUrlEncoded
  @POST("quarter/getHotVideos")
  Observable<UserVideoBean> getHotVideos(@Field("page") String page);


  @FormUrlEncoded
  @POST("quarter/follow")
  Observable<SendBean> getfollow(@Field("uid") String uid,@Field("followId") String followId);


  @POST("quarter/getVersion")
  Observable<VersionBean> getVersion();


  @FormUrlEncoded
  @POST("quarter/getFollowUsers")
  Observable<MyGuanzhuBean> getFollowUsers(@Field("uid") String uid);


  @FormUrlEncoded
  @POST("quarter/getWorkInfo")
  Observable<HQUserBean> getWorkInfo(@Field("uid") String uid);

  @FormUrlEncoded
  @POST("quarter/getVideoDetail")
  Observable<VideoXQBean> getVideoDetail(@Field("wid") String wid);

  @FormUrlEncoded
  @POST("quarter/getNearVideos")
  Observable<VideoFuJinBean> getNearVideos(@Field("page") String page,@Field("latitude") String latitude,@Field("longitude") String longitude);


  @FormUrlEncoded
  @POST("quarter/comment")
  Observable<SendBean> comment(@Field("uid") String uid,@Field("wid") String wid,@Field("content") String content);

}
