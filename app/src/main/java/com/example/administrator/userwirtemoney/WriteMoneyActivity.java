package com.example.administrator.userwirtemoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.Util.NumberThink;
import com.example.administrator.userwirtemoney.Util.openDatepopUpWindow;
import com.example.administrator.userwirtemoney.adapter.ViewPagerAdapter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_money);
        buttons_number = new ArrayList<>();
        iniDate();


    }
    public void iniDate(){
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
        dian_one = (ImageView) findViewById(R.id.selected1);
        dian_two = (ImageView) findViewById(R.id.selected2);
        dian_three = (ImageView) findViewById(R.id.selected3);
        dian_four = (ImageView) findViewById(R.id.selected4);
        backLayout.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new onPagerListener());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.openDate){
            openDatepopUpWindow.openDate(v,this);
        }else{
            number_info = NumberThink.send(v,number_info,numberText);
        }
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


}
