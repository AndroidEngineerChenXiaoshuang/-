package com.example.administrator.userwirtemoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.userwirtemoney.Util.NumberThink;
import com.example.administrator.userwirtemoney.Util.openDatepopUpWindow;

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
        backLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.openDate){
            openDatepopUpWindow.openDate(v,this);
        }else{
            number_info = NumberThink.send(v,number_info,numberText);
        }
    }


}
