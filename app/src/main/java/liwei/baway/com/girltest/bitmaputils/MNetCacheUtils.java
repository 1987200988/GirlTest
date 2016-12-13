package liwei.baway.com.girltest.bitmaputils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.example.library.utils.L;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.R.attr.bitmap;

/**
 * 1.类的用途
 * 2.@author:liwei
 * 3.@2016/12/12
 */

public class MNetCacheUtils {

    private final LocalCacheUtils localCacheUtils;
    private Context mContext;
    private ImageView mIv;
    private String mUrl;
    public MNetCacheUtils(Context context){
        localCacheUtils = new LocalCacheUtils();
        mContext = context;
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mIv.getTag().equals(mUrl)){
                Bitmap bitmap = (Bitmap) msg.obj;
                mIv.setImageBitmap(bitmap);
            }
        }
    };
    public void getBitmapUtils(final ImageView iv, final String url){
        mIv = iv;
        mUrl = url;
        mIv.setTag(mUrl);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1000l, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        ThreadPoolUtils threadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread,100);
        threadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();

//                        Bitmap bitmap = ImageCompressUtils.compress(inputStream, mIv);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //图片压缩处理
                        BitmapFactory.Options option = new BitmapFactory.Options();
                        option.inSampleSize = 2;//宽高都压缩为原来的二分之一, 此参数需要根据图片要展示的大小来确定
                        option.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片格式
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, option);
                        localCacheUtils.setBitmapToLocal(mContext,url,bitmap);
                        MemoryCacheUtils instance = MemoryCacheUtils.getInstance();
                        instance.setBitmapToMemory(url, bitmap);
                        Message obtain = Message.obtain();
                        obtain.obj = bitmap;
                        handler.sendMessage(obtain);
                        L.e("从网络获取图片");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }

            }
        });

    }


}
