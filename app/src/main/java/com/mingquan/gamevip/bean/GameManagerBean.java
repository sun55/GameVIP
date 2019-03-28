package com.mingquan.gamevip.bean;

/**
 * Created by 游戏管家bean on 2019/3/27
 */

public class GameManagerBean {
    private int resourceId;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "GameManagerBean{" +
                "resourceId=" + resourceId +
                '}';
    }
}
