package com.example.administrator.userwirtemoney.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.Util.OpenAddclassName;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;
import com.example.administrator.userwirtemoney.litepal.SQL;
import org.litepal.crud.DataSupport;

/**
 * 该适配器是专用于用户添加类别的
 */

public class UserAddClassGridAdapter extends BaseAdapter{

    public FrameLayout frameLayout;
    public SharedPreferences userAddClassSize = MyApplication.getmContext().getSharedPreferences("user_classSize", Context.MODE_PRIVATE);
    public SharedPreferences.Editor editor = userAddClassSize.edit();
    public int size ;
    public Cursor cursor;
    public LayoutInflater layoutInflater;
    public WriteMoneyActivity writeMoneyActivity;


    public UserAddClassGridAdapter(WriteMoneyActivity writeMoneyActivity){
        size = userAddClassSize.getInt("size",0);
        layoutInflater = LayoutInflater.from(MyApplication.getmContext());
        if(size>0){
            cursor = DataSupport.findBySQL(SQL.SELECT_ALL);
        }
        this.writeMoneyActivity = writeMoneyActivity;
    }

    @Override
    public int getCount() {
        return size==0?1:size+1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = layoutInflater.inflate(R.layout.grid_layout_item,parent,false);
        ImageView imageView = (ImageView) root.findViewById(R.id.grid_img);
        TextView textView = (TextView) root.findViewById(R.id.grid_text);
        if(size==0){
           setAddClass(root,imageView,textView);
        }else{
            if(size==position){
                setAddClass(root,imageView,textView);
            }else{
                String className = cursor.getString(cursor.getColumnIndex("name"));
                imageView.setImageResource(R.drawable.custom_categories);
                textView.setText(className);
                root.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        return false;
                    }
                });
            }
        }
        return root;
    }

    public void setAddClass(View root,ImageView imageView,TextView textView){
        imageView.setImageResource(R.drawable.add_class);
        textView.setText("添加类别");
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAddclassName.start(v,writeMoneyActivity);
            }
        });
    }


}
