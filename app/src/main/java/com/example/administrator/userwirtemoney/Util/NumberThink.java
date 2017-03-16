package com.example.administrator.userwirtemoney.Util;

import android.support.v4.widget.PopupWindowCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.MainActivity;
import com.example.administrator.userwirtemoney.R;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class NumberThink {

    public static final long MAX_INFO = 99999999;
    public static boolean DIAN_FALG = true;
    public static int index = 1;
    public static String date_info;

    public static long send(View v, long number_info, TextView numberText){
        long result = 0;
        switch (v.getId()){
            case R.id.number_one:
                if(DIAN_FALG){
                    result = setNumber(1,number_info,numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(1,number_info,numberText);
                }
                break;
            case R.id.number_two:
                if(DIAN_FALG) {
                    result = setNumber(2, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(2,number_info,numberText);
                }
                break;
            case R.id.number_three:
                if(DIAN_FALG) {
                    result = setNumber(3, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(3,number_info,numberText);
                }
                break;
            case R.id.number_four:
                if(DIAN_FALG) {
                    result = setNumber(4, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(4,number_info,numberText);
                }
                break;
            case R.id.number_fie:
                if(DIAN_FALG) {
                    result = setNumber(5, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(5,number_info,numberText);
                }
                break;
            case R.id.number_six:
                if(DIAN_FALG) {
                    result = setNumber(6, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(6,number_info,numberText);
                }
                break;
            case R.id.number_seven:
                if(DIAN_FALG) {
                    result = setNumber(7, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(7,number_info,numberText);
                }
                break;
            case R.id.number_eight:
                if(DIAN_FALG) {
                    result = setNumber(8, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(8,number_info,numberText);
                }
                break;
            case R.id.number_line:
                if(DIAN_FALG) {
                    result = setNumber(9, number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(9,number_info,numberText);
                }
                break;
            case R.id.number_zero:
                if(DIAN_FALG) {
                    result = onClickToZero(number_info, numberText);
                }else if(!DIAN_FALG&&index<=2){
                    result = onClickToDian(0,number_info,numberText);
                }
                break;
            case R.id.number_dian:
                if(DIAN_FALG){
                    DIAN_FALG = false;
                    date_info = number_info+".";
                    numberText.setText(date_info);
                }else if(!DIAN_FALG){
                    result = number_info;
                }
                break;
            case R.id.back_btn:
                result = delNumber(numberText);
                break;

        }
        return result;
    }

    public static long setNumber(long info,long number_info,TextView numberText){

        if(number_info==0){
            number_info+=info;
            numberText.setText(number_info+"");
        }else{
            number_info*=10;
            number_info+=info;
            if(number_info<=MAX_INFO){
                numberText.setText(number_info+"");
            }else{
                number_info-=info;
                number_info/=10;
            }
        }
        return number_info;
    }

    public static long onClickToZero(long number_info,TextView numberText){
        if(number_info*10<=MAX_INFO){
            numberText.setText(number_info*10+"");
            return number_info*10;
        }
        return number_info;
    }

    public static long onClickToDian(long info,long number_info,TextView numberText){
        if(info==0){
            if(index==1){
                date_info+="0";
                numberText.setText(date_info);
                index++;
            }else if(index==2){
                if(number_info==0){
                    date_info+="0";
                    numberText.setText(date_info);
                    index++;
                }else if(number_info>0){
                    number_info*=10;
                    numberText.setText(date_info+number_info);
                    index++;
                }
            }
        }else if(number_info==0){
            number_info+=info;
            numberText.setText(date_info+number_info);
            index++;
        }else{
            number_info*=10;
            number_info+=info;
            numberText.setText(date_info+number_info);
            index++;
        }
        return number_info;
    }

    public static long delNumber(TextView numberText){
        long numberInfo = 0;
        float f = 0;
        if(DIAN_FALG){
            numberInfo = Long.parseLong(numberText.getText().toString());
            numberInfo/=10;
            numberText.setText(numberInfo+"");
        }else{
            //表示此时的数字后面是两位小数
            if(index==3){
                f = Float.parseFloat(numberText.getText().toString());
                f*=100;
                numberInfo = (long) f;
                numberInfo/=10;
                f = numberInfo;
                f/=10;
                numberText.setText(f+"");
                date_info = numberText.getText().toString();
                index--;
                numberInfo = 0;
            }else if(index==2){
                f = Float.parseFloat(numberText.getText().toString());
                f*=10;
                numberInfo = (long) f;
                numberInfo/=10;
                date_info = numberInfo+".";
                numberInfo = 0;
                numberText.setText(date_info);
                index--;
            }else if(index == 1){
                numberInfo = Long.parseLong(numberText.getText().toString().replace(".",""));
                numberText.setText(numberInfo+"");
                DIAN_FALG = true;

            }
        }
        return numberInfo;
    }



}