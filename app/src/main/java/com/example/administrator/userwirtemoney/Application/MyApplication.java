package com.example.administrator.userwirtemoney.Application;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.administrator.userwirtemoney.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class MyApplication extends Application {

    /**
     * 获得全局context
     */
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }

    public static final int[] imagesArray_one = {R.drawable.grid_money,R.drawable.grid_canyin,
            R.drawable.gouwu,R.drawable.grid_tshrit,R.drawable.grid_bigcards,R.drawable.grid_player,
            R.drawable.social_contact,R.drawable.grid_jujia,R.drawable.grid_phone,R.drawable.grid_eat};

    public static final int[] imagesArray_two = {R.drawable.grid_buteful,R.drawable.grid_sprot,
            R.drawable.grid_go,R.drawable.grid_shuma,R.drawable.grid_student,R.drawable.grid_yiliao,
            R.drawable.grid_book,R.drawable.grid_dog,R.drawable.grid_caipiao,R.drawable.grid_smallcard};

    public static final int[] imagesArray_three = {R.drawable.grid_bangong,R.drawable.grid_home,
            R.drawable.grid_weixiu,R.drawable.grid_king,R.drawable.grid_bigpeople,R.drawable.grid_liwu,
            R.drawable.cash_gift,R.drawable.repayment,R.drawable.donate,R.drawable.grid_licai};

    public static final int[] selected_imagesArray_one = {R.drawable.grid2_yiban,R.drawable.grid2_canyin,
            R.drawable.grid2_gouwu,R.drawable.grid2_tshrit,R.drawable.grid2_bigcards,R.drawable.grid2_yule,
            R.drawable.grid2_shejiao,R.drawable.grid2_jujia,R.drawable.grid2_phone,R.drawable.grid2_eat};

    public static final int[] selected_imagesArray_two = {R.drawable.grid2_buteful,R.drawable.grid2_sprot,
            R.drawable.grid2_gouwu,R.drawable.grid2_shuma,R.drawable.grid2_student,R.drawable.grid2_yiliao,
            R.drawable.grid2_book,R.drawable.grid2_dog,R.drawable.grid2_caipiao,R.drawable.grid2_smallcards};

    public static final int[] selected_imagesArray_three = {R.drawable.grid2_bangong,R.drawable.grid2_home,
            R.drawable.grid2_weixiu,R.drawable.grid2_kings,R.drawable.grid2_bigpeople,R.drawable.grid2_liwu,
            R.drawable.grid2_lijin,R.drawable.grid2_huankuan,R.drawable.grid2_juanzeng,R.drawable.grid2_licai};

    public static final String[] imagesArray_one_name = {"一般","餐饮","购物","服饰","交通","娱乐","社交","居家","通讯","零食"};

    public static final String[] imagesArray_two_name = {"美容","运动","旅行","数码","学习","医疗","书籍","宠物","彩票","汽车"};

    public static final String[] imagesArray_three_name = {"办公","住房","维修","孩子","长辈","礼物","礼金","还钱","捐赠","理财"};

    public static View root = null;

    public static void setRoot(View root) {
        MyApplication.root = root;
    }

    public static View getRoot() {
        return root;
    }
}
