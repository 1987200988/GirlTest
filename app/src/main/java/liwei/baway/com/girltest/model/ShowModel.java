package liwei.baway.com.girltest.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.security.auth.login.LoginException;

import liwei.baway.com.girltest.bean.GirlBean;
import liwei.baway.com.girltest.utils.Input2Str;

import static android.content.ContentValues.TAG;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class ShowModel implements IShowModel {

//    public String url = "http://gank.io/api/data/福利/10/2";
    public String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/30/";
    public int num = 1;
    @Override
    public void addData(final List<GirlBean.ResultsBean> list, final ICallBack iCallBack) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                   URL mUrl = new URL(url+num);
                    HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String s = Input2Str.input2Str(inputStream);
                        Gson gson = new Gson();
//                        Log.e("成功model", "run: "+s);
                        GirlBean girlBean = gson.fromJson(s, GirlBean.class);
                        List<GirlBean.ResultsBean> results = girlBean.results;
                        list.addAll(results);
//                        Log.e("showModel", results.size()+"" );
                        iCallBack.success();

                    } else {
                        iCallBack.failure();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }

    @Override
    public void addMoreData(final List<GirlBean.ResultsBean> list, final ICallBack iCallBack) {
        num++;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    URL mUrl = new URL(url+num);
                    HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String s = Input2Str.input2Str(inputStream);
                        Gson gson = new Gson();
//                        Log.e("成功model", "run: "+s);
                        GirlBean girlBean = gson.fromJson(s, GirlBean.class);
                        List<GirlBean.ResultsBean> results = girlBean.results;
                        list.addAll(results);
//                        Log.e("showModel", results.size()+"" );
                        iCallBack.success();

                    } else {
                        iCallBack.failure();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();




    }
}
