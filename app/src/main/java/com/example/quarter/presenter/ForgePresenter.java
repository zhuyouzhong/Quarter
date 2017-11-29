package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.ForgeBean;
import com.example.quarter.model.ForgeModel;
import com.example.quarter.view.ForgeView;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class ForgePresenter extends BasePresenter<ForgeView> implements ForgeModel.ForgeModelPesponse
{

    private ForgeView forgeView;
    private ForgeModel forgeModel;
    public ForgePresenter(ForgeView mView) {
        super(mView);
        this.forgeView=mView;
        forgeModel=new ForgeModel();
        forgeModel.setForgeModelPesponse(this);
    }

    public void ForgePresenterSuccess(String page)
    {
        forgeModel.Forge(page);
    }
    @Override
    public void ForgeSuccess(ForgeBean forgeBean) {
        forgeView.ForgeSuccess(forgeBean);
    }
}
