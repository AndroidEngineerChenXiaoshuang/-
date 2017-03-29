package com.example.administrator.userwirtemoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Util.SaxMoney;
import com.example.administrator.userwirtemoney.litepal.saveInfo;
import com.example.administrator.userwirtemoney.litepal.userClassInfo;
import org.litepal.crud.DataSupport;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class EditMoenyInfoActivity extends AppCompatActivity {

    public Intent data;
    public Bundle bundle;
    public ImageView typeImage;
    public TextView img_name;
    public TextView money_info;
    public TextView date;
    public EditText speak_info;
    public int isSelectType;
    public int isSelected;
    public String numberInfo;
    public String dateInfo;
    public String saveDate;
    public int postion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);
        data = getIntent();
        bundle = data.getBundleExtra("data");
        numberInfo = bundle.getString("numberInfo");
        dateInfo = bundle.getString("date_info");
        typeImage = (ImageView) findViewById(R.id.img_type);
        img_name = (TextView) findViewById(R.id.imgName);
        money_info = (TextView) findViewById(R.id.edit_MoneyInfo);
        date = (TextView) findViewById(R.id.date_info);
        speak_info = (EditText) findViewById(R.id.editText);
        isSelectType = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selectedType",0);
        isSelected = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("selected",0);
        postion = getSharedPreferences("user_selected",MODE_PRIVATE).getInt("position",0);
        if(postion==0){
            SharedPreferences.Editor editor = getSharedPreferences("user_selected",MODE_PRIVATE).edit();
            editor.putInt("position",1);
            editor.apply();
        }
        initData();
    }

    public void initData(){
        switch (isSelectType){
            case 0:
                typeImage.setImageResource(MyApplication.selected_imagesArray_one[isSelected]);
                img_name.setText(MyApplication.imagesArray_one_name[isSelected]);
                break;
            case 1:
                typeImage.setImageResource(MyApplication.selected_imagesArray_two[isSelected]);
                img_name.setText(MyApplication.imagesArray_two_name[isSelected]);
                break;
            case 2:
                typeImage.setImageResource(MyApplication.selected_imagesArray_three[isSelected]);
                img_name.setText(MyApplication.imagesArray_three_name[isSelected]);
                break;
            case 3:
                typeImage.setImageResource(R.drawable.custom_categories2);
                typeImage.setBackgroundResource(R.drawable.gridview_back);
                List<userClassInfo> list = DataSupport.select("name").where("position=?",(isSelected+1)+"").find(userClassInfo.class);
                img_name.setText(list.get(0).getName());
                break;
        }
        String[] dates = dateInfo.split("-");
        date.setText(dates[0]+"年"+dates[1]+"月"+dates[2]+"日");
        money_info.setText("-"+SaxMoney.sax(numberInfo));
        typeImage.setBackgroundResource(R.drawable.gridview_back);
        Cursor cursor = DataSupport.findBySQL("select dateInfo from saveInfo where postion="+postion);
        if(cursor.moveToNext()){
            saveDate = cursor.getString(cursor.getColumnIndex("dateinfo"));
            speak_info.setText(saveDate);
        }
    }

    @Override
    public void onBackPressed() {
        String dateInfo = speak_info.getText().toString();
        Intent intent = new Intent();
        if(dateInfo.length()>0){
            intent.putExtra("result_info",dateInfo);
            setResult(RESULT_OK,intent);
        }else if(dateInfo.length()==0){
            setResult(RESULT_CANCELED);
        }
        super.onBackPressed();
    }
}
