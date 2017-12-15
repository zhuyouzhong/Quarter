package com.example.quarter.bean;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/3.
 */

public class GroomHotBean {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1512184570725a.jpg","createTime":"2017-12-02T12:01:57","favoriteNum":0,"latitude":"30","localUri":null,"longitude":"30","playNum":0,"praiseNum":0,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512184570725PictureSelector_20171202_111527.mp4","wid":14,"workDesc":"Y"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/e.png","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"1221","localUri":null,"longitude":"4542","playNum":0,"praiseNum":0,"uid":2997,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512108751006e.png","nickname":"李薇","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/wx_camera_1509596718115.mp4","wid":13,"workDesc":"成功成功"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171202_104440.mp4","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"30","localUri":null,"longitude":"30","playNum":0,"praiseNum":0,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/a.jpg","wid":12,"workDesc":"Y"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/oppo3.3gp","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"103","localUri":null,"longitude":"102","playNum":0,"praiseNum":0,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171201_133412.mp4","wid":11,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1511410355963.jpeg","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"45","localUri":null,"longitude":"190","playNum":0,"praiseNum":0,"uid":144,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512101463178cropped_1512101463933.jpg","nickname":"乔君","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171201_212852.mp4","wid":10,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/ic_launcher.png","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"103","localUri":null,"longitude":"102","playNum":0,"praiseNum":0,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/oppo.mp4","wid":9,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":0,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":8,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":0,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":7,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/d.jpg","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":0,"praiseNum":0,"uid":551,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/551.jpg","nickname":"_zhang","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/x.mp4","wid":6,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-02T12:01:32","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":0,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":5,"workDesc":null}]
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
         * commentNum : 0
         * comments : []
         * cover : https://www.zhaoapi.cn/images/quarter/1512184570725a.jpg
         * createTime : 2017-12-02T12:01:57
         * favoriteNum : 0
         * latitude : 30
         * localUri : null
         * longitude : 30
         * playNum : 0
         * praiseNum : 0
         * uid : 145
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512184570725PictureSelector_20171202_111527.mp4
         * wid : 14
         * workDesc : Y
         */

        private int commentNum;
        private String cover;
        private String createTime;
        private int favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private int playNum;
        private int praiseNum;
        private int uid;
        private UserBean user;
        private String videoUrl;
        private int wid;
        private String workDesc;
        private List<?> comments;
        private boolean isopen;

        public void setIsopen(boolean isopen) {
            this.isopen = isopen;
        }

        public boolean isIsopen() {
            return isopen;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
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

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
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

        public int getPlayNum() {
            return playNum;
        }

        public void setPlayNum(int playNum) {
            this.playNum = playNum;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
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

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/145.jpg
             * nickname : null
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private String follow;
            private String icon;
            private Object nickname;
            private String praiseNum;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }
        }
    }
}
