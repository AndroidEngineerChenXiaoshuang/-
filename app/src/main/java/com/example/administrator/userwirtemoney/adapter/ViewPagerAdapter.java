package com.example.administrator.userwirtemoney.adapter;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.grid_layout,container,false);
        GridView gridView = (GridView) root.findViewById(R.id.grid);
        BaseAdapterGrid baseAdapterGrid = new BaseAdapterGrid(position);
        gridView.setAdapter(baseAdapterGrid);
        container.addView(root);
        return root;
    }
}
