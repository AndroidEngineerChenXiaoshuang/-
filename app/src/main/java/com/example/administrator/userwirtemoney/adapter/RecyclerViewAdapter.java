package com.example.administrator.userwirtemoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter{


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public View root;

        public MyViewHolder(View itemView) {
            super(itemView);
            root = itemView;
        }
    }

    public RecyclerViewAdapter(){

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_layout,parent,false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if(position==0){
            LinearLayout centerLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.center);
            LinearLayout bottomLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.bottom);
            centerLayout.setVisibility(View.GONE);
            bottomLayout.setVisibility(View.GONE);
        }else if(position==2){
            LinearLayout topLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.top);
            LinearLayout centerLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.center);
            topLayout.setVisibility(View.GONE);
            centerLayout.setVisibility(View.GONE);
        }else{
            LinearLayout topLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.top);
            LinearLayout bottomLayout = (LinearLayout) myViewHolder.root.findViewById(R.id.bottom);
            bottomLayout.setVisibility(View.GONE);
            topLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
