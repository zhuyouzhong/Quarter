package com.example.quarter.model;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/28.
 */

public interface ISendModel {
    void Send(String uid,String content,ArrayList<String> path);
}
