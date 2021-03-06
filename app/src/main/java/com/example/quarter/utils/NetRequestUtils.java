package com.example.quarter.utils;

import com.example.quarter.api.ApiUtils;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.service.ApiService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/13.
 * 构建者模式
 */

public class NetRequestUtils {

    public static NetRequestUtils netRequestUtils;
    private ApiService apiService;
    public NetRequestUtils(ApiService apiService) {
        this.apiService = apiService;
    }
    public ApiService getApiService() {
        return apiService;
    }

    public static class Builder
    {
        private List<Converter.Factory> converterFactories=new ArrayList<>();
        private List<CallAdapter.Factory> calladaperFactories=new ArrayList<>();
        public Builder addConverterFactory(Converter.Factory factory)
        {
            converterFactories.add(factory);
            return this;
        }
        public Builder addCalladaperFactory(CallAdapter.Factory factory)
        {
            calladaperFactories.add(factory);
            return this;
        }

        public NetRequestUtils build()
        {

            //缓存容量
            long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
            //缓存路径
            String cacheFile = MyApp.context.getCacheDir()+"/shuju";
            System.out.println("缓存路径+6+++++++++++++++"+cacheFile);
            Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);

            OkHttpClient client=new OkHttpClient.Builder()
                    .addInterceptor(new MyInterceptor())
                    .addNetworkInterceptor(MyGetInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                    .addInterceptor(MyGetInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                    .cache(cache)
                    .build();
            Retrofit.Builder builder=new Retrofit.Builder().client(client).baseUrl(ApiUtils.URL);

            if(converterFactories.size()==0)
            {
                builder.addConverterFactory(GsonConverterFactory.create());
            }
            else
            {
                for (Converter.Factory converterFactory : converterFactories) {
                    builder.addConverterFactory(converterFactory);
                }
            }
            if(calladaperFactories.size()==0)
            {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }
            else
            {
                for (CallAdapter.Factory calladaperFactory : calladaperFactories) {
                    builder.addCallAdapterFactory(calladaperFactory);
                }
            }
            ApiService apiService = builder.build().create(ApiService.class);
            netRequestUtils=new NetRequestUtils(apiService);
            return netRequestUtils;
        }
    }
}
