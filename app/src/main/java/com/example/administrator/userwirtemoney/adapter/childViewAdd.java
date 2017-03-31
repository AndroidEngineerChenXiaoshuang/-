package com.example.administrator.userwirtemoney.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.EditMoenyInfoActivity;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.Util.SaxMoney;
import com.example.administrator.userwirtemoney.Util.openDeleteWindow;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import static com.example.administrator.userwirtemoney.EditMoenyInfoActivity.LOOKING_MONEY_INFO;
import static com.example.administrator.userwirtemoney.MainActivity.START_WRITE_MONEYINFO_ACTIVITY;


/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class childViewAdd {

    public static void refershView(final LinearLayout childView, final Activity activity){
        childView.removeAllViews();
        List<LinearLayout> views = new ArrayList<>();
        List<String> infos = new ArrayList<>();
        List<TextView> moneys_text = new ArrayList<>();
        Cursor dates = DataSupport.findBySQL("select date from saveinfo group by date");
        final Cursor datas = DataSupport.findBySQL("select * from saveinfo");
        while(dates.moveToNext()){
            View view = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_item,childView,false);
            TextView dateinfo = (TextView) view.findViewById(R.id.date);
            moneys_text.add((TextView) view.findViewById(R.id.money));
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
            final View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_item,childView,false);
            root.findViewById(R.id.top_info).setVisibility(View.GONE);
            ImageView typeImage = (ImageView) root.findViewById(R.id.grid_img_type);
            final TextView img_name = (TextView) root.findViewById(R.id.info_name);
            TextView money = (TextView) root.findViewById(R.id.money_info);
            switch (datas.getInt(datas.getColumnIndex("selecttype"))){
                case 0:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_one[datas.getInt(datas.getColumnIndex("selected"))]);
                    thinkeSetText(datas,img_name,"one");
                    break;
                case 1:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_two[datas.getInt(datas.getColumnIndex("selected"))]);
                    thinkeSetText(datas,img_name,"one");
                    break;
                case 2:
                    typeImage.setImageResource(MyApplication.selected_imagesArray_three[datas.getInt(datas.getColumnIndex("selected"))]);
                    thinkeSetText(datas,img_name,"one");
                    break;
                case 3:
                    typeImage.setImageResource(R.drawable.custom_categories2);
                    thinkeSetText(datas,img_name,"two");
                    break;
            }

            if(datas.getString(datas.getColumnIndex("moneysize")).length()==4){
                money.setTextSize(14);
            }else if(datas.getString(datas.getColumnIndex("moneysize")).length()>=5){
                money.setTextSize(12);
            }

            money.setText(SaxMoney.sax(datas.getString(datas.getColumnIndex("moneysize"))+""));
            for(int i=0;i<dates.getCount();i++){
                if(datas.getString(datas.getColumnIndex("date")).equals(infos.get(i))){
                    Cursor cursor = DataSupport.findBySQL("select sum(moneysize) from saveinfo where date="+"'"+datas.getString(datas.getColumnIndex("date"))+"'");
                    if(cursor.moveToNext()){
                        moneys_text.get(i).setText("支出:"+cursor.getString(cursor.getColumnIndex("sum(moneysize)"))+" 收入:0.00");
                    }
                    views.get(i).addView(root);
                }
            }
            final int position = datas.getInt(datas.getColumnIndex("postion"));
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity!=null){
                        Intent intent = new Intent(activity, EditMoenyInfoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("type",LOOKING_MONEY_INFO);
                        bundle.putInt("position",position);
                        intent.putExtra("data",bundle);
                        activity.startActivityForResult(intent,START_WRITE_MONEYINFO_ACTIVITY);
                    }

                }
            });

            root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    openDeleteWindow.open(position,root,img_name.getText().toString(),activity,childView);
                    return false;
                }
            });
        }
    }

    private static void thinkeSetText(Cursor datas,TextView img_name,String type){
        String info ;
        if(type.equals("one")){
            info = datas.getString(datas.getColumnIndex("dateinfo"));
            if(info!=null){
                if(info.length()>0){
                    img_name.setText(info);
                }else{
                    img_name.setText(MyApplication.imagesArray_one_name[datas.getInt(datas.getColumnIndex("selected"))]);
                }
            }else {
                img_name.setText(MyApplication.imagesArray_one_name[datas.getInt(datas.getColumnIndex("selected"))]);
            }
        }else if(type.equals("two")){
            info = datas.getString(datas.getColumnIndex("dateinfo"));
            if(info!=null){
                if(info.length()>0){
                    img_name.setText(info);
                }else{
                    Cursor cursor = DataSupport.findBySQL("select * from userClassInfo where position="+(datas.getInt(datas.getColumnIndex("selected"))+1)) ;
                    if(cursor.moveToNext()){
                        String data_name = cursor.getString(cursor.getColumnIndex("name"));
                        img_name.setText(data_name);
                    }
                }
            }else {
                Cursor cursor = DataSupport.findBySQL("select * from userClassInfo where position="+(datas.getInt(datas.getColumnIndex("selected"))+1)) ;
                if(cursor.moveToNext()){
                    String data_name = cursor.getString(cursor.getColumnIndex("name"));
                    img_name.setText(data_name);
                }
            }
        }



    }


}