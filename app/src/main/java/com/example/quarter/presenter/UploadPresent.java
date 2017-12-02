package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.SendBean;
import com.example.quarter.model.UploadModel;
import com.example.quarter.view.UploadView;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/1.
 */

public class UploadPresent extends BasePresenter<UploadView> implements UploadModel.UploadModelResponse {

    private UploadView uploadView;
    private UploadModel uploadModel;
    public UploadPresent(UploadView mView) {
        super(mView);
        this.uploadView=mView;
        uploadModel=new UploadModel();
        uploadModel.setUploadModelResponse(this);
    }

    public void UploadPresentSuccess(String uid, ArrayList<String> list)
    {
        uploadModel.Upload(uid,list);
    }
    public void UpdateNickNamePresentSuccess(String uid,String nickname)
    {
        uploadModel.UpdateNickName(uid,nickname);
    }


    @Override
    public void UploadSuccess(SendBean value) {
        uploadView.UploadSuccess(value);
    }

    @Override
    public void UploadFailue(String msg) {
        uploadView.UploadFailue(msg);
    }

    @Override
    public void UploadError(Throwable e) {
        uploadView.UploadError(e);
    }
}
