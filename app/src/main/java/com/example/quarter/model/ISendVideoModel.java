package com.example.quarter.model;

import java.io.File;

/**
 * Created by 祝文 on 2017/12/5.
 */

public interface ISendVideoModel {
    void SendVideo(String uid, File videopath, File coverpath, String videoDesc, String latitude, String longitude);
}
