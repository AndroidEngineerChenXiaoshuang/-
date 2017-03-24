package com.example.administrator.userwirtemoney.Myinterface;

import android.view.View;

/**
 * Created by Administrator on 2017/3/10 0010.
 */




public class JamInterface {
    /**
     * 该接口是用于回调图片uri解析完毕
     */
    public interface photoInterface{
        void finshed(String filePath);
    }

    /**
     * 该接口是用于回调给外部传递数据给Button
     */
    public interface recyclerOnclick{
        void getInfo(int position,View v);
    }

    /**
     *  该接口是用于通知给UserAddClassAdapter向数据库中插入一条数据
     */
    public interface getClassNameInfo {
        void getInfo(String info);
    }

    /**
     * 该接口用于通知gridView进行刷新
     */
    public interface Refresh{
        void start();
    }
}


