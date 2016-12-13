package liwei.baway.com.girltest.bitmaputils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.blankj.utilcode.utils.EncryptUtils;

import static android.content.ContentValues.TAG;

/**
 * 内存缓存
 * 
 * @author Kevin
 * 
 */
public class MemoryCacheUtils {

	// private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new
	// HashMap<String, SoftReference<Bitmap>>();
	private LruCache<String, Bitmap> mMemoryCache;
    private String s;
    public static MemoryCacheUtils memoryCacheUtils;
    public static MemoryCacheUtils getInstance(){

        if(memoryCacheUtils==null){
            synchronized (MemoryCacheUtils.class){
                if(memoryCacheUtils==null){
                    memoryCacheUtils = new MemoryCacheUtils();
                }
            }
        }
        return memoryCacheUtils;
    }



    private MemoryCacheUtils() {
		long maxMemory = Runtime.getRuntime().maxMemory() / 8;// 模拟器默认是16M
		mMemoryCache = new LruCache<String, Bitmap>((int) maxMemory) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				int byteCount = value.getRowBytes()*value.getHeight();// 获取图片占用内存大小
				return byteCount;
			}
		};
	}

	/**
	 * 从内存读
	 * 
	 * @param url
	 */
	public Bitmap getBitmapFromMemory(String url) {
		// SoftReference<Bitmap> softReference = mMemoryCache.get(url);
		// if (softReference != null) {
		// Bitmap bitmap = softReference.get();
		// return bitmap;
		// }
//        String s = EncryptUtils.encryptMD5ToString(url);
//        Log.e("1111111111", "getBitmapFromMemory: "+s+"222"+mMemoryCache.get(s));
        String replace = url.replace("/", "");
        Log.e("", "getBitmapToMemory: "+replace +"22222"+mMemoryCache.get(replace));
        return mMemoryCache.get(replace);
	}

	/**
	 * 写内存
	 * 
	 * @param url
	 * @param bitmap
	 */
	public void setBitmapToMemory(String url, Bitmap bitmap) {
		// SoftReference<Bitmap> softReference = new
		// SoftReference<Bitmap>(bitmap);
		// mMemoryCache.put(url, softReference);
        String replace = url.replace("/", "");
        Log.e("", "setBitmapToMemory: "+replace );
        mMemoryCache.put(replace, bitmap);
	}
}
