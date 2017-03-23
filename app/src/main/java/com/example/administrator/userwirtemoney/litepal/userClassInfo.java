package com.example.administrator.userwirtemoney.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class userClassInfo extends DataSupport {
    public int position;
    public String name;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
