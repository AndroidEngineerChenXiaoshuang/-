package com.example.administrator.userwirtemoney.Util;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class SaxMoney {

    public static String sax(String number){
        int moneyThink = number.lastIndexOf(".");
        if(moneyThink!=-1){
            String[] info = number.split("\\.");
            if(info.length==1){
                info[0]+=".00";
            }else if(info.length==2){
                if(info[1].length()==1){
                    info[1]+="0";
                }
            }

        }else{
            number+=".00";
        }
        return number;
    }

    public static int sax_remove(String number){
        String[] number2 = number.split("\\.");
        int money = Integer.parseInt(number2[0]);
        return money;
    }

    public static int sax_removeSum(String number){
        String[] info1 = number.split(" ");
        String[] info2 = info1[0].split(":");
        String[] info3 = info2[1].split("\\.");
        int money = Integer.parseInt(info3[0]);
        return money;
    }

}
