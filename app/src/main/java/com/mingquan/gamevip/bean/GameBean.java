package com.mingquan.gamevip.bean;

/**
 * Created by Administrator on 2019/3/20
 */

public class GameBean {
    private int iconResourceId; // 游戏图标资源id
    private String gameName = ""; // 游戏名
    private String detail = ""; // 游戏描述
    private String discount = ""; // 折扣
    private String tag = "";

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GameBean{" +
                "iconResourceId=" + iconResourceId +
                ", gameName='" + gameName + '\'' +
                ", detail='" + detail + '\'' +
                ", discount='" + discount + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
