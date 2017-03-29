package com.example.administrator.userwirtemoney.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class saveInfo extends DataSupport {
    public String dateInfo;
    public String date;
    public int selectType;
    public int selected;
    public String moneySize;
    public int postion;

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSelected() {
        return selected;
    }

    public void setMoneySize(String moneySize) {
        this.moneySize = moneySize;
    }

    public String getMoneySize() {
        return moneySize;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }
}