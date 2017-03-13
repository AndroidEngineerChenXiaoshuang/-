package com.example.administrator.userwirtemoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter{


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public RecyclerViewAdapter(){

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.recycler_one,parent,false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
