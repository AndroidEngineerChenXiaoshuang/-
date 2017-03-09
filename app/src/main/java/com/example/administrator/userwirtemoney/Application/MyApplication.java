package com.example.administrator.userwirtemoney.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }

}
