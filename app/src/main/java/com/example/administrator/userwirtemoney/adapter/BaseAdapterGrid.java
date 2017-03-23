package com.example.administrator.userwirtemoney.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class BaseAdapterGrid extends BaseAdapter {

    public static final int GRID_ONE = 0;
    public static final int GRID_TWO = 1;
    public static final int GRID_THREE = 2;
    public static final int GRID_FOUR = 3;
    public int mtype;
    public int isSelected ;
    public int isSelectedType ;

    public SharedPreferences sharedPreferences = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE);
    public SharedPreferences.Editor editor = sharedPreferences.edit();


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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.grid_layout_item,parent,false);
        final ImageView imageView = (ImageView) root.findViewById(R.id.grid_img);
        final TextView textView = (TextView) root.findViewById(R.id.grid_text);
        isSelected = sharedPreferences.getInt("selected",0);
        isSelectedType = sharedPreferences.getInt("selectedType",0);
        if(position==isSelected&&mtype==isSelectedType){
            setSelectInfo(root,position,imageView,textView);
        }else{
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
        }
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectInfo(root,position,imageView,textView);
            }
        });

        return root;
    }

    public void setSelectInfo(View root,int position,ImageView imageView,TextView textView){
        if(MyApplication.getRoot()!=null){
            ImageView img = (ImageView) MyApplication.root.findViewById(R.id.grid_img);
            TextView img_name = (TextView) MyApplication.root.findViewById(R.id.grid_text);
            switch (sharedPreferences.getInt("selectedType",0)){
                case GRID_ONE:
                    img.setImageResource(MyApplication.imagesArray_one[sharedPreferences.getInt("selected",0)]);
                    img_name.setText(MyApplication.imagesArray_one_name[sharedPreferences.getInt("selected",0)]);
                    img.setBackgroundResource(0);
                    break;
                case GRID_TWO:
                    img.setImageResource(MyApplication.imagesArray_two[sharedPreferences.getInt("selected",0)]);
                    img_name.setText(MyApplication.imagesArray_two_name[sharedPreferences.getInt("selected",0)]);
                    img.setBackgroundResource(0);
                    break;
                case GRID_THREE:
                    img.setImageResource(MyApplication.imagesArray_three[sharedPreferences.getInt("selected",0)]);
                    img_name.setText(MyApplication.imagesArray_three_name[sharedPreferences.getInt("selected",0)]);
                    img.setBackgroundResource(0);
                    break;
                case GRID_FOUR:
//                  Log.v("Jam",sharedPreferences.getInt("selectedType",0)+" "+sharedPreferences.getInt("selected",0));
                    Log.v("Jam",img_name.getText().toString());
                    img.setImageResource(R.drawable.custom_categories);
                    img.setBackgroundResource(0);
                    break;
            }
        }
        switch (mtype){
            case GRID_ONE:
                imageView.setImageResource(MyApplication.selected_imagesArray_one[position]);
                imageView.setBackgroundResource(R.drawable.gridview_back);
                textView.setText(MyApplication.imagesArray_one_name[position]);
                break;
            case GRID_TWO:
                imageView.setImageResource(MyApplication.selected_imagesArray_two[position]);
                imageView.setBackgroundResource(R.drawable.gridview_back);
                textView.setText(MyApplication.imagesArray_two_name[position]);
                break;
            case GRID_THREE:
                imageView.setImageResource(MyApplication.selected_imagesArray_three[position]);
                imageView.setBackgroundResource(R.drawable.gridview_back);
                textView.setText(MyApplication.imagesArray_three_name[position]);
                break;

        }

        MyApplication.setRoot(root);
        isSelected = position;
        //用与保存选中的第几个
        editor.putInt("selected",isSelected);
        //用于保存在几页
        editor.putInt("selectedType",mtype);
        editor.apply();
        notifyDataSetChanged();
    }
}
