package com.example.administrator.userwirtemoney.Util;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;
import com.example.administrator.userwirtemoney.adapter.RecyclerDateAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class openDatepopUpWindow {

    private static PopupWindow open;

    public static String year_text ;
    public static String month_text ;
    public static String day_text ;

    public static void openDate(View parent, final WriteMoneyActivity writeMoneyActivity){
        year_text = "2013";
        month_text = "1";
        day_text = "1";
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.window_root,null,false);
        final TextView date_text = (TextView) root.findViewById(R.id.date_text);
        final RecyclerView recyclerYear = (RecyclerView) root.findViewById(R.id.year);
        final RecyclerView recyclerMonth = (RecyclerView) root.findViewById(R.id.month);
        final RecyclerView recyclerDay = (RecyclerView) root.findViewById(R.id.day);

        final Button parent_btn = (Button) parent;


        final List<String> years = new ArrayList<>();
        years.add("");
        for(int i=2013;i<=2100;i++){
            years.add(i+"");
        }
        years.add("");
        LinearLayoutManager yaerLayoutManger = new LinearLayoutManager(MyApplication.getmContext(), LinearLayoutManager.VERTICAL,false);
        recyclerYear.setLayoutManager(yaerLayoutManger);
        final RecyclerDateAdapter recyclerYearAdapter = new RecyclerDateAdapter(years, new JamInterface.recyclerOnclick() {
            @Override
            public void getInfo(int position, View v) {
                recyclerYear.scrollToPosition(position+1);
                year_text = years.get(position);
                date_text.setText(year_text+"年"+month_text+"月"+day_text+"日");
            }
        });
        recyclerYear.setAdapter(recyclerYearAdapter);

        final List<String> months= new ArrayList<>();
        months.add("");
        for(int i=1;i<=12;i++){
            months.add(i+"");
        }
        months.add("");
        LinearLayoutManager monthLayoutManger = new LinearLayoutManager(MyApplication.getmContext(),LinearLayoutManager.VERTICAL,false);
        recyclerMonth.setLayoutManager(monthLayoutManger);
        RecyclerDateAdapter recyclerMonthAdapter = new RecyclerDateAdapter(months, new JamInterface.recyclerOnclick() {
            @Override
            public void getInfo(int position, View v) {
                recyclerMonth.scrollToPosition(position+1);
                month_text = months.get(position);
                date_text.setText(year_text+"年"+month_text+"月"+day_text+"日");
            }
        });
        recyclerMonth.setAdapter(recyclerMonthAdapter);

        final List<String> days = new ArrayList<>();
        days.add("");
        for(int i=1;i<=31;i++){
            days.add(i+"");
        }
        days.add("");
        RecyclerDateAdapter recyclerDayAdapter = new RecyclerDateAdapter(days,new JamInterface.recyclerOnclick(){
            @Override
            public void getInfo(int position,View v){
                recyclerDay.scrollToPosition(position+1);
                day_text = days.get(position);
                date_text.setText(year_text+"年"+month_text+"月"+day_text+"日");
            }
        });
        LinearLayoutManager dayLayoutManger = new LinearLayoutManager(MyApplication.getmContext(),LinearLayoutManager.VERTICAL,false);
        recyclerDay.setLayoutManager(dayLayoutManger);
        recyclerDay.setAdapter(recyclerDayAdapter);


        open = new PopupWindow(root);
        open.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        open.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        open.setAnimationStyle(R.style.ContentAnimation);
        open.setBackgroundDrawable(new BitmapDrawable());
        open.setOutsideTouchable(true);
        open.setFocusable(true);
        open.showAtLocation(parent, Gravity.CENTER,0,0);

        if(Build.VERSION.SDK_INT>=21){
            writeMoneyActivity.getWindow().setStatusBarColor(MyApplication.TITLE_COLOR);
        }
        root.findViewById(R.id.dissmms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.dismiss();
                if(Build.VERSION.SDK_INT>=21){
                    writeMoneyActivity.getWindow().setStatusBarColor(MyApplication.TITLE_COLOR_HOLT);
                }
            }
        });

        root.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.dismiss();
                parent_btn.setText(year_text+"-"+month_text+"-"+day_text);
            }
        });
    }

}
