package com.example.administrator.userwirtemoney.Util;


import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;
import com.example.administrator.userwirtemoney.R;
import com.example.administrator.userwirtemoney.WriteMoneyActivity;

/**
 * 用于打开用户添加类型的名称弹出的窗口
 */

public class OpenAddclassName {

    public static void start(View parent, final WriteMoneyActivity writeMoneyActivity, final JamInterface.getClassNameInfo getClassNameInfo){
        final View root = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.write_classname,null,false);
        final PopupWindow popupWindow = new PopupWindow(root);
        popupWindow.setAnimationStyle(R.style.TestAnimation);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
        Button btn_dismiss = (Button) root.findViewById(R.id.dissmms);
        Button btn_sure = (Button) root.findViewById(R.id.sure);
        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=21){
                    writeMoneyActivity.getWindow().setStatusBarColor(MyApplication.TITLE_COLOR_HOLT);
                }
                popupWindow.dismiss();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) root.findViewById(R.id.edit_info);
                String info = editText.getText().toString();
                getClassNameInfo.getInfo(info);
                popupWindow.dismiss();
            }
        });
        if(Build.VERSION.SDK_INT>=21){
            writeMoneyActivity.getWindow().setStatusBarColor(MyApplication.TITLE_COLOR);
        }
    }


}
