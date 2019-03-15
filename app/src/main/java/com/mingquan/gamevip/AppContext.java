package com.mingquan.gamevip;

import android.support.multidex.MultiDexApplication;

import com.vondear.rxtool.RxTool;

/**
 * Created by Administrator on 2019/3/15
 */

public class AppContext extends MultiDexApplication {

    private static AppContext instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxTool.init(this);
    }

    public static AppContext getInstance() {
        return instance;
    }
}
