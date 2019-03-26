package com.mingquan.gamevip.bean;

/**
 * Created by 收支明细bean on 2019/3/20
 */

public class InOutBean {
    private int resourceId;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "InOutBean{" +
                "resourceId=" + resourceId +
                '}';
    }
}
