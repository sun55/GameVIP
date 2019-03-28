package com.mingquan.gamevip.bean;

/**
 * Created by 公会榜单bean on 2019/3/27
 */

public class SocietyBean {
    private int resourceId;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "SocietyBean{" +
                "resourceId=" + resourceId +
                '}';
    }
}
