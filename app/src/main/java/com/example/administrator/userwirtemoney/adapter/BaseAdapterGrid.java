package com.example.administrator.userwirtemoney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class BaseAdapterGrid extends BaseAdapter {

    public static final int GRID_ONE = 0;
    public static final int GRID_TWO = 1;
    public static final int GRID_THREE = 2;
    public int mtype;


    public BaseAdapterGrid(int type){
        this.mtype = type;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return new Object();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.grid_layout_item,parent,false);
        ImageView imageView = (ImageView) root.findViewById(R.id.grid_img);
        TextView textView = (TextView) root.findViewById(R.id.grid_text);
        switch (mtype){
            case GRID_ONE:
                imageView.setImageResource(MyApplication.imagesArray_one[position]);
                textView.setText(MyApplication.imagesArray_one_name[position]);
                break;
            case GRID_TWO:
                imageView.setImageResource(MyApplication.imagesArray_two[position]);
                textView.setText(MyApplication.imagesArray_two_name[position]);
                break;
            case GRID_THREE:
                imageView.setImageResource(MyApplication.imagesArray_three[position]);
                textView.setText(MyApplication.imagesArray_three_name[position]);
                break;
        }
        return root;
    }
}
