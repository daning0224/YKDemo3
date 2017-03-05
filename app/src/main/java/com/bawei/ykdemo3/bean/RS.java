package com.bawei.ykdemo3.bean;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class RS {
    private Boolean check;
    private ArrayList<Children> children;
    private String  dirName;

    public RS() {
    }

    public RS(Boolean check, ArrayList<Children> children, String dirName) {
        this.check = check;
        this.children = children;
        this.dirName = dirName;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public String toString() {
        return "RS{" +
                "check=" + check +
                ", children=" + children +
                ", dirName='" + dirName + '\'' +
                '}';
    }
}
