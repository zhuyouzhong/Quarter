package com.example.quarter.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 祝文 on 2017/12/10.
 */

public class NetUtils {
    private Context context;
    public NetUtils(Context context) {
        this.context = context;
    }

    public static final void viefil(Context context,NetWork netWork)
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info!=null)
        {
            if(info.getType()==ConnectivityManager.TYPE_MOBILE)
            {
                netWork.youNetwork();
            }
        }
        else
        {
            netWork.noNetwork();
        }
    }
    public interface NetWork
    {
        void youNetwork();
        void noNetwork();
    }
}
