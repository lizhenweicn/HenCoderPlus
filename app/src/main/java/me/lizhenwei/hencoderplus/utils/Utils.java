package me.lizhenwei.hencoderplus.utils;

import android.content.res.Resources;
import android.util.TypedValue;

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

}
