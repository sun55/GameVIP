package com.mingquan.gamevip;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2019/3/15
 */

public class Contacts {

    // 默认存放图片的路径
    public final static String APP_ROOT_DIR =
            Environment.getExternalStorageDirectory() + File.separator + "game_vip" + File.separator;

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = APP_ROOT_DIR + "image" + File.separator;
    /**
     * 默认缓存图片的路径
     */
    public final static String DEFAULT_CACHE_IMAGE_PATH = APP_ROOT_DIR + "cache_image";

    public final static String DEFAULT_CACHE_PATH = APP_ROOT_DIR + "cache_data";

    // 默认存放文件下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH = APP_ROOT_DIR + "download" + File.separator;
}
