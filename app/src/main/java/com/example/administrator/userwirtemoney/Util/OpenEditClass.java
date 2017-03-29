package com.example.administrator.userwirtemoney.Util;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;
import com.example.administrator.userwirtemoney.litepal.userClassInfo;

import org.litepal.crud.DataSupport;

/**
 *  这是一个用来打开windowPopUpwindow的工具类
 *  只有上帝和我才知道里面的意思!
 */
public class OpenEditClass {

    public  JamInterface.Refresh refresh;

    public OpenEditClass(JamInterface.Refresh refresh){
        this.refresh = refresh;
    }

    public void openEdit(final View view, final int position, final String lastClassName, WriteMoneyActivity writeMoneyActivity){
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.edit_class,null,false);
        TextView item1 = (TextView) root.findViewById(R.id.edit_name);
        TextView item2 = (TextView) root.findViewById(R.id.delete_class);
        final PopupWindow popupWindow = new PopupWindow(root);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.ContentAnimation);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.edit_class_name,null,false);
                popupWindow.setContentView(root);
                final TextView textView = (TextView) root.findViewById(R.id.edit_info);
                textView.setText(lastClassName);
                TextView sure = (TextView) root.findViewById(R.id.sure);
                TextView dismms = (TextView) root.findViewById(R.id.dissmms);
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(textView.getText().toString().length()>0){
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name",textView.getText().toString());
                            DataSupport.updateAll(userClassInfo.class,contentValues,"position=?",position+1+"");
                            refresh.start();
                        }else{
                            Toast.makeText(view.getContext(),"类别名称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        popupWindow.dismiss();
                    }
                });
                dismms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setAnimationStyle(R.style.ContentAnimation);
                popupWindow.showAtLocation(view,Gravity.CENTER,0,0);

            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.edit_class_delete,null,false);
                TextView sure = (TextView) root.findViewById(R.id.delete_sure);
                TextView dissms = (TextView) root.findViewById(R.id.delete_dissms);
                TextView delete_name = (TextView) root.findViewById(R.id.edit_name);
                delete_name.setText("确定要删除["+lastClassName+"]类别");
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int whereId = position+1;
                        DataSupport.deleteAll(userClassInfo.class,"position=?",whereId+"");
                        Cursor cursor = DataSupport.findBySQL("select * from userclassinfo where position>"+whereId);
                        if(cursor!=null){
                            while(cursor.moveToNext()){
                                int Id = cursor.getInt(cursor.getColumnIndex("position"));
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("position",Id-1);
                                DataSupport.updateAll(userClassInfo.class,contentValues,"position=?",Id+"");
                            }
                        }
                        SharedPreferences sharedPreferences = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int is_seleced = sharedPreferences.getInt("selected",0);
                        int selected_pager = sharedPreferences.getInt("selectedType",0);
                        if(selected_pager==3){
                            if(is_seleced>position){
                                editor.putInt("selected",is_seleced-1);
                                editor.apply();
                            }else if(is_seleced==position){
                                editor.putInt("selectedType",0);
                                editor.putInt("selected",0);
                                editor.apply();
                            }
                        }
                        //通知刷新
                        refresh.start();
                        popupWindow.dismiss();


                    }
                });
                dissms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setContentView(root);
                popupWindow.setAnimationStyle(R.style.ContentAnimation);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(view,Gravity.CENTER,0,0);
            }
        });
    }


}
