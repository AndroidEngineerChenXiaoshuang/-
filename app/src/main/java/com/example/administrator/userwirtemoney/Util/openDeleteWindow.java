package com.example.administrator.userwirtemoney.Util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.ApplicationTest.test;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.adapter.childViewAdd;
import com.example.administrator.userwirtemoney.litepal.saveInfo;
import com.example.administrator.userwirtemoney.litepal.userClassInfo;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class openDeleteWindow {

    public static final int STATUS_COLOR = 0X96000000;

    public static void open(final int position, View parent, String name, final Activity activity, final LinearLayout childView){
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.info_delete_layout,null,false);
        TextView title_name = (TextView) root.findViewById(R.id.edit_name);
        TextView sure = (TextView) root.findViewById(R.id.delete_sure);
        title_name.setText(name);
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(root);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.ContentAnimation);
        popupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(saveInfo.class,"postion=?",position+"");
                Cursor cursor = DataSupport.findBySQL("select * from saveinfo where postion>"+position);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        int Id = cursor.getInt(cursor.getColumnIndex("postion"));
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("postion",Id-1);
                        DataSupport.updateAll(saveInfo.class,contentValues,"postion=?",Id+"");
                    }
                }
                int position = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE).getInt("position",0);
                SharedPreferences.Editor editor = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE).edit();
                editor.putInt("position",position-1);
                editor.apply();
                childViewAdd.refershView(childView,activity);
                if(Build.VERSION.SDK_INT>=21){
                    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                popupWindow.dismiss();

            }
        });

        if(Build.VERSION.SDK_INT>=21){
            activity.getWindow().setStatusBarColor(STATUS_COLOR);
        }

    }



}
