package com.example.administrator.userwirtemoney.adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.Util.SaxMoney;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class childViewAdd {

    public static void refershView(LinearLayout childView){
        childView.removeAllViews();
        List<LinearLayout> views = new ArrayList<>();
        List<String> infos = new ArrayList<>();
        Cursor dates = DataSupport.findBySQL("select date from saveinfo group by date");
        Cursor datas = DataSupport.findBySQL("select * from saveinfo");
        while(dates.moveToNext()){
            View view = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_item,childView,false);
            TextView dateinfo = (TextView) view.findViewById(R.id.date);
            String[] saxdate = dates.getString(dates.getColumnIndex("date")).split("-");
            dateinfo.setText(saxdate[0]+"年"+saxdate[1]+"月"+saxdate[2]+"日");
            view.findViewById(R.id.layout_bottom).setVisibility(View.GONE);
            view.findViewById(R.id.line).setVisibility(View.GONE);
            LinearLayout linearLayout = (LinearLayout) view;
            views.add(linearLayout);
            childView.addView(view);
            infos.add(dates.getString(dates.getColumnIndex("date")));
        }
        while(datas.moveToNext()){
            View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_item,childView,false);
            root.findViewById(R.id.top_info).setVisibility(View.GONE);
            ImageView typeImage = (ImageView) root.findViewById(R.id.grid_img_type);
            TextView img_name = (TextView) root.findViewById(R.id.info_name);
            TextView money = (TextView) root.findViewById(R.id.money_info);
            switch (datas.getInt(datas.getColumnIndex("selecttype"))){
                case 0:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_one[datas.getInt(datas.getColumnIndex("selected"))]);
                    img_name.setText(MyApplication.imagesArray_one_name[datas.getInt(datas.getColumnIndex("selected"))]);
                    break;
                case 1:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_two[datas.getInt(datas.getColumnIndex("selected"))]);
                    img_name.setText(MyApplication.imagesArray_two_name[datas.getInt(datas.getColumnIndex("selected"))]);
                    break;
                case 2:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_three[datas.getInt(datas.getColumnIndex("selected"))]);
                    img_name.setText(MyApplication.imagesArray_three_name[datas.getInt(datas.getColumnIndex("selected"))]);
                    break;
                case 3:
                    typeImage.setImageResource(R.drawable.custom_categories2);
                    Cursor cursor = DataSupport.findBySQL("select * from userClassInfo where position="+(datas.getInt(datas.getColumnIndex("selected"))+1)) ;
                    if(cursor.moveToNext()){
                        String data_name = cursor.getString(cursor.getColumnIndex("name"));
                        img_name.setText(data_name);
                    }
                    break;
            }
            money.setText("-"+ SaxMoney.sax(datas.getString(datas.getColumnIndex("moneysize"))+""));
            for(int i=0;i<dates.getCount();i++){
                if(datas.getString(datas.getColumnIndex("date")).equals(infos.get(i))){
                    views.get(i).addView(root);
                }
            }
        }
    }


}