package com.bawei.ykdemo3.bean;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class Children {

    private ArrayList<Children1> children;
    private String dirName;

    public Children() {
    }

    public Children(ArrayList<Children1> children, String dirName) {
        this.children = children;
        this.dirName = dirName;
    }

    public ArrayList<Children1> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children1> children) {
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
        return "Children{" +
                "children=" + children +
                ", dirName='" + dirName + '\'' +
                '}';
    }
}
