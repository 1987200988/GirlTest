package liwei.baway.com.girltest.bitmaputils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import com.example.library.utils.L;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存
 * 
 * @author Kevin
 * 
 */
public class NetCacheUtils {

	private LocalCacheUtils mLocalCacheUtils;
	private MemoryCacheUtils mMemoryCacheUtils;
	public Context mContext;

    public NetCacheUtils(Context context,LocalCacheUtils localCacheUtils,
						 MemoryCacheUtils memoryCacheUtils) {
		mLocalCacheUtils = localCacheUtils;
		mMemoryCacheUtils = memoryCacheUtils;
		mContext = context;
	}

	/**
	 * 从网络下载图片
	 * 
	 * @param ivPic
	 * @param url
	 */
	public void getBitmapFromNet(ImageView ivPic, String url) {
		new BitmapTask().execute(ivPic, url);// 启动AsyncTask,



        // 参数会在doInbackground中获取
	}

	/**
	 * Handler和线程池的封装
	 * 
	 * 第一个泛型: 参数类型 第二个泛型: 更新进度的泛型, 第三个泛型是onPostExecute的返回结果
	 * 
	 * @author Kevin
	 * 128线程
	 */
	class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

		private ImageView ivPic;
		private String url;

       Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ivPic.setTag(url);


            }
        };

		/**
		 * 后台耗时方法在此执行, 子线程
		 */
		@Override
		protected Bitmap doInBackground(Object... params) {
			ivPic = (ImageView) params[0];
			url = (String) params[1];
//            handler.sendEmptyMessage(0);
//            ivPic.setTag(url);


            return downloadBitmap(url);
		}

		/**
		 * 更新进度, 主线程
		 */
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		/**
		 * 耗时方法结束后,执行该方法, 主线程
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
            if (result != null) {
				String bindUrl = (String) ivPic.getTag();

				if (url.equals(bindUrl)) {// 确保图片设定给了正确的imageview
					ivPic.setImageBitmap(result);
//					mLocalCacheUtils.setBitmapToLocal(context,url, result);// 将图片保存在本地
//                    String s = EncryptUtils.encryptMD5ToString(url);
//                    mMemoryCacheUtils.setBitmapToMemory(s, result);// 将图片保存在内存
                    L.e("从网络缓存读取图片啦...");
//                    System.out.println("从网络缓存读取图片啦...");


                }
			}


		}
	}

	/**
	 * 下载图片
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap downloadBitmap(String url) {

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
				
				//图片压缩处理
				BitmapFactory.Options option = new BitmapFactory.Options();
//                option.inJustDecodeBounds = true;  //设置为true，只读尺寸信息，不加载像素信息到内存
//                Bitmap mbitmap = BitmapFactory.decodeStream(inputStream,null, option);  //此时bitmap为空

				option.inSampleSize = 2;//宽高都压缩为原来的二分之一, 此参数需要根据图片要展示的大小来确定
//                option.inJustDecodeBounds = false;
//                int bWidth = option.outWidth;
//                int bHeight= option.outHeight;
//                int toWidth = 400;
//                int toHeight = 800;
//                int be = 1;  //be = 1代表不缩放
//                if(bWidth/toWidth>bHeight/toHeight&&bWidth>toWidth){
//                    be = (int)bWidth/toWidth;
//                }else if(bWidth/toWidth<bHeight/toHeight&&bHeight>toHeight){
//                    be = (int)bHeight/toHeight;
//                }
//                option.inSampleSize = be; //设置缩放比例


                option.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片格式

//				option.inJustDecodeBounds = true;

				Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, option);
				return bitmap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return null;
	}

}
