package com.example.administrator.userwirtemoney.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.R;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class RecyclerDateAdapter extends RecyclerView.Adapter {

    public List<String> dateList;

    public JamInterface.recyclerOnclick recyclerOnclick;

    public final int YEAR_SIZE = 90;

    public final int MONTH_SIZE = 14;

    public final int DAY_SIZE = 33;

    public int isposition =1;

    public static final int TEXT_COLOR = 0xFFB9B9B9;

    public RecyclerDateAdapter(List<String> dateList,JamInterface.recyclerOnclick recyclerOnclick){
        this.dateList = dateList;
        this.recyclerOnclick = recyclerOnclick;
    }

    public class MyViewHodler extends RecyclerView.ViewHolder{
        public View root;
        public MyViewHodler(View itemView) {
            super(itemView);
            root = itemView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHodler(LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final MyViewHodler myViewHodler = (MyViewHodler) holder;
        TextView info = (TextView) myViewHodler.root.findViewById(R.id.text_item);
        myViewHodler.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dateList.size()){
                    case YEAR_SIZE:
                        if(position!=0&&position!=89){
                            setBind(position,myViewHodler.root);
                        }
                        break;
                    case MONTH_SIZE:
                        if(position!=0&&position!=13){
                            setBind(position,myViewHodler.root);
                        }
                        break;
                    case DAY_SIZE:
                        if(position!=0&&position!=32){
                            setBind(position,myViewHodler.root);
                        }
                        break;
                }
            }
        });
        info.setText(dateList.get(position));
        if(position==isposition){
            info.setTextColor(Color.RED);
        }else{
            info.setTextColor(TEXT_COLOR);
        }
    }

    public void setBind(int position,View root){
        this.isposition = position;
        recyclerOnclick.getInfo(position,root);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
