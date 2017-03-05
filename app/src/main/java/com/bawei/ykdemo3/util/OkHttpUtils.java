package com.bawei.ykdemo3.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class OkHttpUtils {

    private Gson gson;
    private Handler handler;
    private CallbackM callbackM;


    /**
     * 单例模式
     */
    //1.声明对象（私有化，静态，加锁）
    private static volatile OkHttpUtils okHttpUtils;

    //2.私有化构造方法
    private OkHttpUtils() {
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();
    }

    //3.提供公共静态的方法
    public static OkHttpUtils getOkHttpUtils() {
        if (null == okHttpUtils) {
            synchronized (OkHttpUtils.class) {
                if (null == okHttpUtils) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    /**
     * 观察者模式
     */
    public interface CallbackM {
        public void onFailure(Request request, IOException e);

        public void onResponse(Object response);
    }

    public void setCallbackM(CallbackM callbackM) {
        this.callbackM = callbackM;
    }

//***********************************************************************************

    //枚举
    public enum Methods {
        GET, PSOT, DOEW
    }

    //拼接url的方法
    public <T> void setUrl(String url, Map<String, String> map, Class<T> cls, Methods m) {
        switch (m) {
            case GET:
                ok_get(url, map, cls);
                break;
            case PSOT:
                ok_post(url, map, cls);
                break;
            case DOEW:
                break;
        }
    }

    //post请求
    private <T> void ok_post(String url, Map<String, String> map, final Class<T> cls) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //得到Body
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //遍历集合
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String values = map.get(key);
            //加入到builder里面
            builder.add(key, values);
        }
        //创建Request
        Request request = new Request.Builder().post(builder.build()).url(url).build();
        //创建Call
        Call call = okHttpClient.newCall(request);
        //接口回调
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (null != callbackM) {
                    callbackM.onFailure(request, e);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String json = response.body().string();
                //解析
                final T t = gson.fromJson(json, cls);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (null != callbackM) {
                            callbackM.onResponse(t);
                        }
                    }
                });
            }
        });
    }

    //get请求
    private <T> void ok_get(String url, Map<String, String> map, final Class<T> cls) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //遍历map集合
        int i = 0;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String values = map.get(key);
            if (i == 0) {
                url += key + "=" + values;
            } else {
                url += "&" + key + "=" + values;
            }
            i++;
        }
        /*请求数据*/
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                /*请求失败*/
                if (null != callbackM) {
                    callbackM.onFailure(request, e);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                /*请求成功*/
                String string = response.body().string();
                //解析数据
                final T t = gson.fromJson(string, cls);
                //这里是运行在主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (null != callbackM) {
                            callbackM.onResponse(t);
                        }
                    }
                });
            }
        });
    }
}
