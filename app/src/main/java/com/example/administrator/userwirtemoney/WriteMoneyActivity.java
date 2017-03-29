package com.example.administrator.userwirtemoney;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Util.NumberThink;
import com.example.administrator.userwirtemoney.Util.openDatepopUpWindow;
import com.example.administrator.userwirtemoney.adapter.ViewPagerAdapter;
import com.example.administrator.userwirtemoney.litepal.saveInfo;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class WriteMoneyActivity extends AppCompatActivity implements View.OnClickListener{

    public List<Button> buttons_number;
    public Button openDate_btn;
    public RelativeLayout backLayout;
    public TextView numberText ;
    public long number_info =0;
    public int count = 0;
    public ViewPager viewPager;
    public ImageView dian_one;
    public ImageView dian_two;
    public ImageView dian_three;
    public ImageView dian_four;
    public ViewPagerAdapter viewPagerAdapter;
    public Button openEditInfo;
    public Button finish_info;
    public static final int OPEND_EDITACTIVITY = 0;
    public static final int COLOR = 0XFF007CA2;
    public static final int TEXTCOLOR = 0XFF757575;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_money);
        buttons_number = new ArrayList<>();
        iniDate();


    }
    public void iniDate(){
        finish_info = (Button) findViewById(R.id.finish);
        openEditInfo = (Button) findViewById(R.id.the_money_info);
        buttons_number.add((Button) findViewById(R.id.number_one));
        buttons_number.add((Button) findViewById(R.id.number_two));
        buttons_number.add((Button) findViewById(R.id.number_three));
        buttons_number.add((Button) findViewById(R.id.number_four));
        buttons_number.add((Button) findViewById(R.id.number_fie));
        buttons_number.add((Button) findViewById(R.id.number_six));
        buttons_number.add((Button) findViewById(R.id.number_seven));
        buttons_number.add((Button) findViewById(R.id.number_eight));
        buttons_number.add((Button) findViewById(R.id.number_line));
        buttons_number.add((Button) findViewById(R.id.number_zero));
        buttons_number.add((Button) findViewById(R.id.number_dian));
        openDate_btn = (Button) findViewById(R.id.openDate);
        openDate_btn.setOnClickListener(this);
        numberText = (TextView) findViewById(R.id.number_text);
        backLayout = (RelativeLayout) findViewById(R.id.back_btn);
        for(Button button:buttons_number){
            button.setOnClickListener(WriteMoneyActivity.this);
        }
        finish_info.setOnClickListener(this);
        dian_one = (ImageView) findViewById(R.id.selected1);
        dian_two = (ImageView) findViewById(R.id.selected2);
        dian_three = (ImageView) findViewById(R.id.selected3);
        dian_four = (ImageView) findViewById(R.id.selected4);
        backLayout.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new onPagerListener());
        openEditInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.openDate){
            openDatepopUpWindow.openDate(v,this);
        }else if(v.getId()==R.id.the_money_info){
            Intent intent = new Intent(this,EditMoenyInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("date_info",openDate_btn.getText().toString());
            bundle.putString("numberInfo",numberText.getText().toString());
            intent.putExtra("data",bundle);
            startActivityForResult(intent,OPEND_EDITACTIVITY);
        }else if(v.getId()==R.id.finish){
               finishInfo();
        }else{
            number_info = NumberThink.send(v,number_info,numberText);
        }
    }

    public void finishInfo(){
        int position = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("position",0);
        if(position==0){
            position=1;
        }
        Cursor cursor = DataSupport.findBySQL("select * from saveInfo where postion="+position);
        if(cursor.getCount()==0){
            saveInfo save = new saveInfo();
            save.setDate(openDate_btn.getText().toString());
            save.setMoneySize(numberText.getText().toString());
            save.setSelectType(getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selectedType",0));
            save.setSelected(getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selected",0));
            save.setPostion(position);
            save.save();
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("moneySize",numberText.getText().toString());
            contentValues.put("date",openDate_btn.getText().toString());
            contentValues.put("selectType",getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selectedType",0));
            contentValues.put("selected",getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selected",0));
            DataSupport.updateAll(saveInfo.class,contentValues,"postion=?",position+"");
        }
        SharedPreferences.Editor editor = getSharedPreferences("user_selected",MODE_PRIVATE).edit();
        editor.putInt("position",position+1);
        editor.apply();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
        int position = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("position",0);
        Log.v("Jam",position+"");
        Cursor cursor = DataSupport.findBySQL("select * from saveInfo where postion="+position);
        if(cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex("date"))==null){
                DataSupport.deleteAll(saveInfo.class,"postion=?",position+"");
            }
        }
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    public class onPagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            dian_one.setImageResource(R.drawable.dian2);
            dian_two.setImageResource(R.drawable.dian2);
            dian_three.setImageResource(R.drawable.dian2);
            dian_four.setImageResource(R.drawable.dian2);
            if(position == 0){
                dian_one.setImageResource(R.drawable.dian);
            }else if(position == 1){
                dian_two.setImageResource(R.drawable.dian);
            }else if(position ==2){
                dian_three.setImageResource(R.drawable.dian);
            }else{
                dian_four.setImageResource(R.drawable.dian);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int position = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("position",0);
        List<saveInfo> lists = DataSupport.where("postion=?",position+"").find(saveInfo.class);
        switch (requestCode){
            case OPEND_EDITACTIVITY:
                if(resultCode==RESULT_OK){
                    if(lists.size()==0){
                        saveInfo save = new saveInfo();
                        save.setPostion(position);
                        save.setDateInfo(data.getStringExtra("result_info"));
                        save.save();

                    }else if(!data.getStringExtra("result_info").equals(lists.get(0).dateInfo)){
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("dateInfo",data.getStringExtra("result_info"));
                        DataSupport.updateAll(saveInfo.class,contentValues,"postion=?",position+"");
                    }
                    openEditInfo.setTextColor(COLOR);
                    openEditInfo.setBackgroundResource(R.drawable.bg_login6);
                }else if(resultCode==RESULT_CANCELED){
                    if(lists.size()>0){
                        DataSupport.deleteAll(saveInfo.class,"postion=?",position+"");
                    }
                    openEditInfo.setTextColor(TEXTCOLOR);
                    openEditInfo.setBackgroundResource(R.drawable.bg_login);
                }
                break;
        }
    }

}
