package com.example.administrator.userwirtemoney.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;


/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    public WriteMoneyActivity writeMoneyActivity;

    public ViewPagerAdapter(WriteMoneyActivity writeMoneyActivity){
        this.writeMoneyActivity = writeMoneyActivity;
    }

    @Override
    public int getCount() {
        return 4;
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
        if(position!=3){
            BaseAdapterGrid baseAdapterGrid = new BaseAdapterGrid(position);
            gridView.setAdapter(baseAdapterGrid);
        }else{
            UserAddClassGridAdapter userAddClassGridAdapter = new UserAddClassGridAdapter(writeMoneyActivity);
            gridView.setAdapter(userAddClassGridAdapter);
        }
        container.addView(root);
        return root;
    }
}
