package com.example.quarter.model;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/28.
 */

public interface ISendModel {
    void Send(String uid,String content,ArrayList<String> path);
    void SendVideo(String uid, File videopath,File coverpath, String videoDesc, String latitude, String longitude);
}
