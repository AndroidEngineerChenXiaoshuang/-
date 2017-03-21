package com.example.administrator.userwirtemoney.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class user_class extends DataSupport {
    public int id;
    public String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
