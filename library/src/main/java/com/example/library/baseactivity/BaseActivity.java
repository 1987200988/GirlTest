package com.example.library.baseactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pools;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.library.OkHttpUtils.OkHttpUtils;
import com.example.library.R;
import com.example.library.utils.T;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Callback;
/**
 * Created by 李韦 on 2016/11/3.
 */
public abstract class BaseActivity extends AppCompatActivity implements IOnCreate{
    public static final int PULL_REFRESH = 1;
    public static final int DROP_DOWN_LOADING = 2;
    public static final int FIRST_REQUEST_NET = 0;

    private View mRootView;
    public final static LinkedList<BaseActivity> mActivities = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(bindLayout()!=0) {
            synchronized(mActivities){
                mActivities.add(this);

            }

            mRootView = View.inflate(this, bindLayout(), null);
            removeTitleBar();
            setContentView(mRootView);
            initToolBar();
            initViews();
            initDatas();
            loadDatas();
            initEvents();
        }else{

            Log.e("Activity", "bindLayout return 0" );
        }
    }
    public View mGetRootView(){
            return mRootView;

    }
    public <T extends View>T findView(int viewId){
       return (T) findViewById(viewId);
    }

    public void mstartActivity(Class<? extends BaseActivity> cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);

    }
    public void mstartActivity(Class<? extends BaseActivity> cls,Bundle bundle){
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    public void mLoadDataGet(String url, Callback callback){
        OkHttpUtils.get(url,callback);


    }
    public String mLoadDataGet(String url){
        String s = OkHttpUtils.get(url);
        return s;

    }


    public void mLoadDataPostMap(String url, HashMap<String,String> map,Callback callback){
        OkHttpUtils.post(url,map,callback);


    }
    public void mLoadDataPostObject(String url,Object object,Callback callback){

        OkHttpUtils.post(url,object,callback);
    }
    public void mLoadDataPostJson(String url,String json,Callback callback){

        OkHttpUtils.post(url,json,callback);
    }
    public void addFragment(int containerviewid, Fragment fragment, String tag){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(containerviewid,fragment,tag);
        fragmentTransaction.commit();
    }



    public void killAll(){

        LinkedList<BaseActivity> baseActivities = new LinkedList<>(mActivities);
        for(BaseActivity base:baseActivities){
            base.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities){

            mActivities.remove(this);
        }


    }
}
