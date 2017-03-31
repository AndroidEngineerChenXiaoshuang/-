package com.example.administrator.userwirtemoney.ApplicationTest;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.administrator.userwirtemoney.Application.MyApplication;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class test {
    public test()  {
    }

    public static void deletePosition(int position){
        SharedPreferences.Editor editor = MyApplication.getmContext().getSharedPreferences("", Context.MODE_PRIVATE).edit();
        editor.putInt("position",position);
        editor.apply();
    }

    public static void refreshPosition(){
        SharedPreferences.Editor editor = MyApplication.getmContext().getSharedPreferences("user_selected",Context.MODE_PRIVATE).edit();
        editor.putInt("position",1);
        editor.apply();
    }

    public static void testSelect() throws MalformedURLException {
        Cursor cursor =  DataSupport.findBySQL("select name from userclassinfo group by name");
        while(cursor.moveToNext()){
            Log.v("Jam",cursor.getString(cursor.getColumnIndex("name")));
        }

    }


}
