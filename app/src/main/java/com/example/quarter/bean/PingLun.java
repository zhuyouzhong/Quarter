package com.example.quarter.bean;

/**
 * Created by 祝文 on 2017/12/18.
 */

public class PingLun {
    private String content;
    private String nickname;

    @Override
    public String toString() {
        return "PingLun{" +
                "content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public PingLun(String content, String nickname) {
        this.content = content;
        this.nickname = nickname;
    }

    public PingLun() {
    }
}
