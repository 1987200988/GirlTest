package liwei.baway.com.girltest.bitmaputils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.blankj.utilcode.utils.EncryptUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.example.library.utils.L;

/**
 * 本地缓存
 * 
 * @author Kevin
 * 
 */
public class LocalCacheUtils {
    public Context mContext;
	public static final String CACHE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/girltest";

	/**
	 * 从本地sdcard读图片
	 */
	public Bitmap getBitmapFromLocal(String url) {
		try { File externalFilesDir = null;
//            File externalFilesDir = .getExternalFilesDir(Environment.DIRECTORY_DCIM);
//            if(mContext==null){
//                return null;
//            }
//            externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM);
//			String s = EncryptUtils.encryptMD5File2String(url);
//            L.e("1111"+s);
//            String replace = url.replace("/", "");
//            L.e("2222"+replace);
            String s = EncryptUtils.encryptMD5ToString(url);

            File file = new File(CACHE_PATH, s);
			if (file.exists()) {
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						file));
				return bitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 向sdcard写图片
	 *
	 */
	public void setBitmapToLocal(Context context, String url, Bitmap bitmap) {
		try {
//			String fileName = MD5Encoder.encode(url);
//			EncryptUtils.decrypt
//            mContext = context;
//            File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DCIM);

            String s = EncryptUtils.encryptMD5ToString(url);
            L.e(s);
//            String replace = url.replace("/", "");
//            L.e(replace);



            File file = new File(CACHE_PATH,s);

			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {// 如果文件夹不存在, 创建文件夹
				parentFile.mkdirs();
			}
			// 将图片保存在本地
			bitmap.compress(CompressFormat.JPEG, 20,
					new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
