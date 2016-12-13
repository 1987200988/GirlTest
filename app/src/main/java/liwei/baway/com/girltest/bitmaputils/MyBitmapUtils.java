package liwei.baway.com.girltest.bitmaputils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


import com.example.library.utils.L;

import liwei.baway.com.girltest.R;

import static okhttp3.internal.Internal.instance;

/**
 * 自定义图片加载工具
 * 
 * @author Kevin
 * 
 */
public class MyBitmapUtils {

	MNetCacheUtils mNetCacheUtils;
	LocalCacheUtils mLocalCacheUtils;
	MemoryCacheUtils mMemoryCacheUtils;

	public MyBitmapUtils(Context context) {
		mLocalCacheUtils = new LocalCacheUtils();
		mNetCacheUtils = new MNetCacheUtils(context);
	}

	public void display(ImageView ivPic, String url) {
		ivPic.setImageResource(R.mipmap.ic_launcher);// 设置默认加载图片

		Bitmap bitmap = null;
		// 从内存读
		mMemoryCacheUtils = MemoryCacheUtils.getInstance();
		 bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);

		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
//			System.out.println("从内存读取图片啦...");
			L.e("从内存读取图片啦...");
			return;
		}
		// 从本地读
		bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
//			System.out.println("从本地读取图片啦...");
			L.e("从本地读取图片啦...");
			mMemoryCacheUtils.setBitmapToMemory(url, bitmap);// 将图片保存在内存
			return;
		}
		// 从网络读
		mNetCacheUtils.getBitmapUtils(ivPic,url);

	}

}
