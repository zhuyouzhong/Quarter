package com.example.quarter.bean;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/6.
 */

public class UserVideoBean {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512461229146a.jpg","createTime":"2017-12-05T16:07:09","favoriteNum":null,"latitude":"40.040465","localUri":null,"longitude":"116.300059","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512461229146PictureSelector_20171205_160650.mp4","wid":36,"workDesc":"zzz"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512464549365mv.jpg","createTime":"2017-12-05T17:02:29","favoriteNum":null,"latitude":"40.040472","localUri":null,"longitude":"116.300066","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512464549365PictureSelector_20171205_170150.mp4","wid":46,"workDesc":"zzz"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512481026818截屏_20171205_183914.png","createTime":"2017-12-05T21:37:06","favoriteNum":null,"latitude":"40.040467","localUri":null,"longitude":"116.300086","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512481026818PictureSelector_20171205_213523.mp4","wid":57,"workDesc":"shp"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512481644662截屏_20171122_121418.png","createTime":"2017-12-05T21:47:24","favoriteNum":null,"latitude":"40.040457","localUri":null,"longitude":"116.300049","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512481644662PictureSelector_20171205_214113.mp4","wid":58,"workDesc":"zz"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512481677256httpsgulfstream.didistatic.comstaticzhuancheimguploadrooster2017112815118704278448172a8e0383462e48e050b8b35f8f11cmaterial_1511870427849_1080_1920_400.png","createTime":"2017-12-05T21:47:57","favoriteNum":null,"latitude":"40.040466","localUri":null,"longitude":"116.300067","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512481677256PictureSelector_20171205_214724.mp4","wid":59,"workDesc":"ggg"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512520984146截屏_20171205_224521.png","createTime":"2017-12-06T08:43:04","favoriteNum":null,"latitude":"40.040464","localUri":null,"longitude":"116.30007","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512520984146PictureSelector_20171206_084227.mp4","wid":60,"workDesc":"视频"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512521662490截屏_20171124_141856.png","createTime":"2017-12-06T08:54:22","favoriteNum":null,"latitude":"40.040477","localUri":null,"longitude":"116.300113","playNum":null,"praiseNum":null,"uid":99,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512521662490PictureSelector_20171206_085149.mp4","wid":62,"workDesc":"预览"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : null
         * cover : https://www.zhaoapi.cn/images/quarter/1512461229146a.jpg
         * createTime : 2017-12-05T16:07:09
         * favoriteNum : null
         * latitude : 40.040465
         * localUri : null
         * longitude : 116.300059
         * playNum : null
         * praiseNum : null
         * uid : 99
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512461229146PictureSelector_20171205_160650.mp4
         * wid : 36
         * workDesc : zzz
         */

        private Object commentNum;
        private String cover;
        private String createTime;
        private Object favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private Object playNum;
        private Object praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private String workDesc;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(Object favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getLocalUri() {
            return localUri;
        }

        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getPlayNum() {
            return playNum;
        }

        public void setPlayNum(Object playNum) {
            this.playNum = playNum;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }
    }
}
