package com.bawei.ykdemo3.bean;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class Children1 {

    private String dirName;
    private String imgApp;

    public Children1() {
    }

    public Children1(String dirName, String imgApp) {
        this.dirName = dirName;
        this.imgApp = imgApp;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getImgApp() {
        return imgApp;
    }

    public void setImgApp(String imgApp) {
        this.imgApp = imgApp;
    }

    @Override
    public String toString() {
        return "Children1{" +
                "dirName='" + dirName + '\'' +
                ", imgApp='" + imgApp + '\'' +
                '}';
    }
}
