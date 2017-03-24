package com.example.administrator.userwirtemoney.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.Util.OpenAddclassName;
import com.example.administrator.userwirtemoney.Util.OpenEditClass;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;
import com.example.administrator.userwirtemoney.litepal.SQL;
import com.example.administrator.userwirtemoney.litepal.userClassInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *  public boolean /TRUE = false;
 *  只有上帝和我才知道里面的意思!
 * 该适配器是专用于用户添加类别的
 */

public class UserAddClassGridAdapter extends BaseAdapter{

    public int size ;
    public Cursor cursor;
    public LayoutInflater layoutInflater;
    public WriteMoneyActivity writeMoneyActivity;
    public List<String> datas = new ArrayList<>();
    public  SharedPreferences sharedPreferences = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE);
    public SharedPreferences.Editor editor = sharedPreferences.edit();
    public List<Boolean> isSelecteds = new ArrayList<>();
    public JamInterface.Refresh refresh;

    public UserAddClassGridAdapter(WriteMoneyActivity writeMoneyActivity,JamInterface.Refresh refresh){
        this.refresh = refresh;
        cursor = DataSupport.findBySQL(SQL.SELECT_ALL);
        size = cursor.getCount();
        layoutInflater = LayoutInflater.from(MyApplication.getmContext());
        this.writeMoneyActivity = writeMoneyActivity;
        while(cursor.moveToNext()){
            datas.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        if(size==0){
            isSelecteds.add(false);
        }else{
            for(int i=0;i<=size;i++){
                if(i==size){
                    isSelecteds.add(false);
                }else{
                    isSelecteds.add(true);
                }
            }
        }
        cursor.close();

    }

    @Override
    public int getCount() {
        return size==10?size:size+1;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View root = layoutInflater.inflate(R.layout.grid_layout_item,parent,false);
        final ImageView imageView = (ImageView) root.findViewById(R.id.grid_img);
        final TextView textView = (TextView) root.findViewById(R.id.grid_text);
        final int is_seleced = sharedPreferences.getInt("selected",0);
        final int selected_pager = sharedPreferences.getInt("selectedType",0);
        if(is_seleced==position&&selected_pager==3&&size>0){
            setSelected(root,position,imageView,textView);
        }else if(size==0){
            setAddClass(root,imageView,textView);
        }else if(size==position){
            setAddClass(root,imageView,textView);
        }else{
            String className = datas.get(position);
            imageView.setImageResource(R.drawable.custom_categories);
            textView.setText(className);
        }
        if(isSelecteds.get(position)){
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelected(root,position,imageView,textView);
                }
            });
            root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String info = null;
                    int whereId = position + 1;
                    final Cursor cursor = DataSupport.findBySQL("select name from userClassInfo where position="+whereId+";");
                    if(cursor.moveToNext()){
                        Log.v("Jam",cursor.getString(cursor.getColumnIndex("name")));
                        info = cursor.getString(cursor.getColumnIndex("name"));
                    }
                    OpenEditClass openEditClass = new OpenEditClass(new JamInterface.Refresh() {
                        @Override
                        public void start() {
                            UserAddClassGridAdapter.this.cursor = DataSupport.findBySQL(SQL.SELECT_ALL);
                            size = UserAddClassGridAdapter.this.cursor.getCount();
                            datas.clear();
                            while(UserAddClassGridAdapter.this.cursor.moveToNext()){
                                datas.add(UserAddClassGridAdapter.this.cursor.getString(UserAddClassGridAdapter.this.cursor.getColumnIndex("name")));
                            }
                            isSelecteds.clear();
                            if(size==0){
                                isSelecteds.add(false);
                            }else{
                                for(int i=0;i<=size;i++){
                                    if(i==size){
                                        isSelecteds.add(false);
                                    }else{
                                        isSelecteds.add(true);
                                    }
                                }
                            }
                            notifyDataSetChanged();
                        }

                    });
                    openEditClass.openEdit(v,position,info,writeMoneyActivity);
                    cursor.close();
                    return false;
                }
            });

        }
        return root;
    }

    public void setSelected(View root,int position,ImageView imageView,TextView textView){
        sharedPreferences = MyApplication.getmContext().getSharedPreferences("user_selected", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int lastPager = sharedPreferences.getInt("selectedType",0);
        int last_position = sharedPreferences.getInt("selected",0);
        View last_selected;
        if(MyApplication.getRoot()!=null){
            last_selected = MyApplication.getRoot();
            ImageView img = (ImageView) last_selected.findViewById(R.id.grid_img);
            TextView text = (TextView) last_selected.findViewById(R.id.grid_text);
            switch (lastPager){
                case 0:
                    img.setImageResource(MyApplication.imagesArray_one[last_position]);
                    text.setText(MyApplication.imagesArray_one_name[last_position]);
                    img.setBackgroundResource(0);
                    break;
                case 1:
                    img.setImageResource(MyApplication.imagesArray_two[last_position]);
                    text.setText(MyApplication.imagesArray_two_name[last_position]);
                    img.setBackgroundResource(0);
                    break;
                case 2:
                    img.setImageResource(MyApplication.imagesArray_three[last_position]);
                    text.setText(MyApplication.imagesArray_three_name[last_position]);
                    img.setBackgroundResource(0);
                    break;
                case 3:
                    img.setImageResource(R.drawable.custom_categories);
                    text.setText(datas.get(last_position));
                    img.setBackgroundResource(0);
                    break;
            }
        }
        MyApplication.setRoot(root);
        imageView.setImageResource(R.drawable.custom_categories2);
        imageView.setBackgroundResource(R.drawable.gridview_back);
        textView.setText(datas.get(position));
        editor.putInt("selectedType",3);
        editor.putInt("selected",position);
        editor.apply();
        notifyDataSetChanged();
    }


    public void setAddClass(View root,ImageView imageView,TextView textView){
        imageView.setImageResource(R.drawable.add_class);
        textView.setText("添加类别");
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAddclassName.start(v,writeMoneyActivity,new JamInterface.getClassNameInfo(){
                    @Override
                    public void getInfo(String info) {
                        if(info.length()>0){
                            Log.v("Jam",size+"");
                            if(size+1<=10){
                                userClassInfo classInfo = new userClassInfo();
                                classInfo.setPosition(size+1);
                                classInfo.setName(info);
                                classInfo.save();
                                cursor = DataSupport.findBySQL(SQL.SELECT_ALL);
                                size = cursor.getCount();
                                datas.clear();
                                while(cursor.moveToNext()){
                                    datas.add(cursor.getString(cursor.getColumnIndex("name")));
                                }
                                isSelecteds.clear();
                                for(int i=0;i<=size;i++){
                                    if(i==size){
                                        isSelecteds.add(false);
                                    }else{
                                        isSelecteds.add(true);
                                    }
                                }
                                editor.putInt("selected",size-1);
                                editor.putInt("selectedType",3);
                                editor.apply();
                                cursor.close();
                                notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(MyApplication.getmContext(),"类别的名称不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}
