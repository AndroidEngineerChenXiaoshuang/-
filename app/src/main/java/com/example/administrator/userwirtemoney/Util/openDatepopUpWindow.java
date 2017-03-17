package com.example.administrator.userwirtemoney.Util;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class openDatepopUpWindow {

    private static PopupWindow open;
    private static final int TITLE_COLOR = 0x96000000;
    private static final int TITLE_COLOR_HOLT = 0xFF000000;
    private static final List<String> year = new ArrayList<>();
    private static final List<String> month = new ArrayList<>();
    private static final List<String> day = new ArrayList<>();

    public static void openDate(View parent, final WriteMoneyActivity writeMoneyActivity){
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.window_root,null,false);
        ListView list_one = (ListView) root.findViewById(R.id.year);
        ListView list_two = (ListView) root.findViewById(R.id.month);
        ListView list_three = (ListView) root.findViewById(R.id.day);

        year.add("");
        for(int i=2013;i<=2100;i++){
            year.add(i+"");
        }
        year.add("");
        month.add("");
        for(int i=1;i<=12;i++){
            month.add(i+"");
        }
        month.add("");
        day.add("");
        for(int i=1;i<=31;i++){
            day.add(i+"");
        }
        day.add("");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(MyApplication.getmContext(),R.layout.list_item,R.id.text_item,year);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(MyApplication.getmContext(),R.layout.list_item,R.id.text_item,month);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(MyApplication.getmContext(),R.layout.list_item,R.id.text_item,day);
        list_one.setAdapter(yearAdapter);
        list_two.setAdapter(monthAdapter);
        list_three.setAdapter(dayAdapter);
        open = new PopupWindow(root);
        open.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        open.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        open.setAnimationStyle(R.style.ContentAnimation);
        open.setBackgroundDrawable(new BitmapDrawable());
        open.setOutsideTouchable(false);
        open.showAtLocation(parent, Gravity.CENTER,0,0);
       
        if(Build.VERSION.SDK_INT>=21){
            writeMoneyActivity.getWindow().setStatusBarColor(TITLE_COLOR);
        }
        root.findViewById(R.id.dissmms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.dismiss();
                if(Build.VERSION.SDK_INT>=21){
                    writeMoneyActivity.getWindow().setStatusBarColor(TITLE_COLOR_HOLT);
                }
            }
        });

    }

}
