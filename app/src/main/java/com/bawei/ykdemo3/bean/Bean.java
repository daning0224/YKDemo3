package com.bawei.ykdemo3.bean;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class Bean {

    private ArrayList<RS> rs;

    public Bean() {
    }

    public Bean(ArrayList<RS> rs) {
        this.rs = rs;
    }

    public ArrayList<RS> getRs() {
        return rs;
    }

    public void setRs(ArrayList<RS> rs) {
        this.rs = rs;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "rs=" + rs +
                '}';
    }
}
