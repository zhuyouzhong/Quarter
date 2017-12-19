package com.example.quarter.bean;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class MyGuanzhuBean {

    /**
     * msg : 获取关注用户列表成功
     * code : 0
     * data : [{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T15:36:44","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","latitude":null,"longitude":null,"mobile":"18410261121","money":0,"nickname":"nnnnnn","password":"111111","praiseNum":null,"token":"C5F1896E305FA85630AA9BB54A621BC3","uid":195,"userId":null,"username":"18410261121"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T11:27:47","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/15132490600371513178815814.jpg","latitude":null,"longitude":null,"mobile":"15011217423","money":0,"nickname":"Mo","password":"971121","praiseNum":null,"token":"328E60FC7E24996E96A80D99F6311F01","uid":98,"userId":null,"username":"15011217423"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-14T16:06:26","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/154.jpg","latitude":null,"longitude":null,"mobile":"13773359134","money":0,"nickname":"笑出腹肌的男人","password":"654321","praiseNum":null,"token":"B77920CD2D38574F335E2C137F821D61","uid":154,"userId":null,"username":"13773359134"},{"age":null,"appkey":"ca37aa09e5365f3e","appsecret":"0B24FB0C1AE2494A5EB9643D293FBDD5","createtime":"2017-12-14T11:55:59","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1512457258990image.jpg","latitude":null,"longitude":null,"mobile":"13051089902","money":null,"nickname":"soul","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"E3498601C8D3A37869C60836790A63A1","uid":2956,"userId":null,"username":"13051089902"},{"age":null,"appkey":"20e2032e7281061c","appsecret":"6F4B84F2A709D497D64396A668722E10","createtime":"2017-12-15T14:58:51","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1513148630412image.jpg","latitude":null,"longitude":null,"mobile":"15302569391","money":null,"nickname":"14","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"941B303925422F66C10C53494EA7C64D","uid":3028,"userId":null,"username":"15302569391"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-14T20:06:23","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512203571490dsf.jpg","latitude":null,"longitude":null,"mobile":"18339901531","money":0,"nickname":"若水","password":"123456","praiseNum":null,"token":"A315BB69D6F474CB1057185A8432A2B7","uid":77,"userId":null,"username":"18339901531"},{"age":null,"appkey":"09def8c5a2a076eb","appsecret":"7D3C81A20B6AA74836128F75BCE92211","createtime":"2017-12-14T11:40:07","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1512385050787image2017120418572919644.png","latitude":null,"longitude":null,"mobile":"18513200461","money":null,"nickname":"123","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"9C0712BF2619D189819DB15E69D0F31D","uid":2719,"userId":null,"username":"18513200461"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T15:06:18","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512306825287image2017120311306.png","latitude":null,"longitude":null,"mobile":"15011419039","money":0,"nickname":"zyk","password":"123456","praiseNum":null,"token":"D102FF36F5D9C74ABBB59C041DEA0181","uid":675,"userId":null,"username":"15011419039"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T15:29:40","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512179089975avator_thump.jpg","latitude":null,"longitude":null,"mobile":"15201392236","money":0,"nickname":"李灿灿","password":"111111","praiseNum":null,"token":"3E030858FEEED1026B1A1FB5CD5EF9ED","uid":148,"userId":null,"username":"15201392236"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-14T20:06:02","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","latitude":null,"longitude":null,"mobile":"15810672623","money":0,"nickname":"小狼","password":"123456","praiseNum":null,"token":"28C3793F9AB8E555D523C46D8D867998","uid":150,"userId":null,"username":"15810672623"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-14T20:55:22","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/151263717022520171207165918.jpg","latitude":null,"longitude":null,"mobile":"15176046561","money":0,"nickname":"%E9%87%91%E9%B3%9E","password":"666666","praiseNum":null,"token":"DED0233723EC79592080E46729E8F905","uid":92,"userId":null,"username":"15176046561"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-14T21:13:33","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513248407209qqqqq.jpg","latitude":null,"longitude":null,"mobile":"15652760856","money":0,"nickname":"吴少","password":"123123","praiseNum":null,"token":"289E3B56E177F8AAA762432E71ADE4A3","uid":93,"userId":null,"username":"15652760856"},{"age":null,"appkey":"189c30ed03e42ce2","appsecret":"CEED8D7186304845324E99355CE38891","createtime":"2017-12-14T16:06:08","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1512973175662aa.jpg","latitude":null,"longitude":null,"mobile":"17601605523","money":null,"nickname":"Bare","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"B2EAE7B295A1BF373ED01121D93B57E5","uid":3296,"userId":null,"username":"17601605523"},{"age":null,"appkey":"d720415dada82e08","appsecret":"D06D8C948844FF8525D9BC9DA1D52B03","createtime":"2017-12-15T16:25:58","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1512108751006e.png","latitude":null,"longitude":null,"mobile":"18810431730","money":null,"nickname":"李薇","password":"30D0BA0744A36CFD7EFF8869E8B09A0E","praiseNum":null,"token":"4860E93D56838926DD61E21884D6939D","uid":2997,"userId":null,"username":"18810431730"}]
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
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-12-15T15:36:44
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/1512470827115mo.jpg
         * latitude : null
         * longitude : null
         * mobile : 18410261121
         * money : 0
         * nickname : nnnnnn
         * password : 111111
         * praiseNum : null
         * token : C5F1896E305FA85630AA9BB54A621BC3
         * uid : 195
         * userId : null
         * username : 18410261121
         */

        private Object age;
        private Object appkey;
        private Object appsecret;
        private String createtime;
        private Object email;
        private Object fans;
        private Object follow;
        private int gender;
        private String icon;
        private Object latitude;
        private Object longitude;
        private String mobile;
        private int money;
        private String nickname;
        private String password;
        private Object praiseNum;
        private String token;
        private int uid;
        private Object userId;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getAppkey() {
            return appkey;
        }

        public void setAppkey(Object appkey) {
            this.appkey = appkey;
        }

        public Object getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(Object appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getFans() {
            return fans;
        }

        public void setFans(Object fans) {
            this.fans = fans;
        }

        public Object getFollow() {
            return follow;
        }

        public void setFollow(Object follow) {
            this.follow = follow;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
