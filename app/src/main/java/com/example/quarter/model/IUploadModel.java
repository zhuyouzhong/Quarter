package com.example.quarter.model;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/1.
 */

public interface IUploadModel {
    void Upload(String uid, ArrayList<String> list);
    void UpdateNickName(String uid,String nickname);
}
