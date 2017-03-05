package com.bawei.ykdemo3;

import android.app.Application;

import com.bawei.ykdemo3.util.OkHttpUtils;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class App extends Application {

    public static int LEFT_POSITION = 0;
    public static OkHttpUtils okHttpUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        //网络请求
        okHttpUtils = OkHttpUtils.getOkHttpUtils();
    }
}
