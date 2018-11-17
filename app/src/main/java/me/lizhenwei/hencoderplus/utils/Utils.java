package me.lizhenwei.hencoderplus.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import me.lizhenwei.hencoderplus.R;

/**
 * Name : Utils
 * Date :2018/11/5 10:04 AM
 * Author : will
 */
public class Utils {

    /**
     * dp 转 px
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取图片资源
     */
    public static Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.avatar, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.drawable.avatar, options);
    }

    /**
     * 适配 camera 的 Z 坐标轴
     * @return  适配完后的坐标值
     */
    public static float getZForCamera() {
        return -8 * Resources.getSystem().getDisplayMetrics().density;
    }

}
