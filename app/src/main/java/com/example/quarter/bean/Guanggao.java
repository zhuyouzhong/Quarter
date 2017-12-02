package com.example.quarter.bean;

import java.util.List;

/**
 * Created by 祝文 on 2017/12/1.
 */

public class Guanggao {

    /**
     * msg :
     * code : 0
     * data : [{"aid":1,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad1.png","productId":null,"title":"花生油","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":2,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad2.png","productId":null,"title":"京东女人节","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":3,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad3.png","productId":null,"title":"国庆大惠战","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":4,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad4.png","productId":"1","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g","type":1,"url":""}]
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
         * aid : 1
         * createtime : 2017-11-28T19:35:04
         * icon : https://www.zhaoapi.cn/images/quarter/ad1.png
         * productId : null
         * title : 花生油
         * type : 0
         * url : http://m.mv14449315.icoc.bz/index.jsp
         */

        private int aid;
        private String createtime;
        private String icon;
        private Object productId;
        private String title;
        private int type;
        private String url;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
