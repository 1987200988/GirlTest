package com.example.library.baseactivity;

/**
 * Created by 李韦 on 2016/11/8 20:11
 */

public interface IOnCreate {
    int bindLayout();
    void initViews();
    void initEvents();
    void initDatas();
    void loadDatas();
    void removeTitleBar();
    void initToolBar();
}
