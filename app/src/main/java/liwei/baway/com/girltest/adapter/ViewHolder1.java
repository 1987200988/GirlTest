package liwei.baway.com.girltest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.library.recyclerview.BaseViewHolder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import liwei.baway.com.girltest.R;
import liwei.baway.com.girltest.bean.GirlBean;
import liwei.baway.com.girltest.bitmaputils.MyBitmapUtils;
import liwei.baway.com.girltest.utils.Input2Str;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/10
 */

public class ViewHolder1 extends BaseViewHolder<GirlBean.ResultsBean> {
    private  ImageView iv;
    public ViewHolder1(View itemView) {
        super(itemView);
             iv =  getView(R.id.item_iv);

    }

    @Override
    public void setData(Context context, GirlBean.ResultsBean resultsBean) {
        iv.setMaxHeight(200);
        MyBitmapUtils myBitmapUtils = new MyBitmapUtils(context);
        myBitmapUtils.display(iv,resultsBean.url);
//        Glide.with(context).load(resultsBean.url).into(iv);


//        final String url = resultsBean.url;
//        Log.e("woshishui", "setData: "+url );
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    URL imageurl = new URL(url);
//
//                    HttpURLConnection conn =  (HttpURLConnection) imageurl.openConnection();
//                    int responseCode = conn.getResponseCode();
//                    if(responseCode==200){
//                        Log.e("成功", "run: " );
//                        InputStream inputStream = conn.getInputStream();
//                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        iv.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                   iv.setImageBitmap(bitmap);
//                            }
//                        });
//                    }
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();



    }


}
