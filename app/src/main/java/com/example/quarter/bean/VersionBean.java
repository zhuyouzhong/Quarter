package com.example.quarter.bean;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class VersionBean {

    /**
     * msg : 获取版本信息成功
     * code : 0
     * data : {"apkUrl":"https://www.zhaoapi.cn/version/app.apk","type":2,"vId":3,"versionCode":"300","versionName":"3.0"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkUrl : https://www.zhaoapi.cn/version/app.apk
         * type : 2
         * vId : 3
         * versionCode : 300
         * versionName : 3.0
         */

        private String apkUrl;
        private int type;
        private int vId;
        private String versionCode;
        private String versionName;

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVId() {
            return vId;
        }

        public void setVId(int vId) {
            this.vId = vId;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
