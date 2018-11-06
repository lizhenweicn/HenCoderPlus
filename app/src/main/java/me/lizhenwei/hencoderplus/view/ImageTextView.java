package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.R;
import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : SportsView
 * Date :2018/11/5 3:29 PM
 * Author : will
 */
public class ImageTextView extends View {

    /**
     * 需要绘制的文字信息
     */
    private static final String TEXT = "abab";

    /**
     * 环的宽度
     */
    private static final float RING_WIDTH = Utils.dp2px(20);

    /**
     * 圆的半径
     */
    private static final float RADIUS = Utils.dp2px(150);

    /**
     * 环的颜色
     */
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");

    /**
     * 进度条的高亮颜色
     */
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    /**
     * 抗锯齿画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 绘制的图片
     */
    private Bitmap mBitmap;

    /**
     * 第一行文字被截断时的宽度
     */
    float[] mCutWidth = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mBitmap = Utils.getAvatar(getResources(), ((int) Utils.dp2px(100)));
        mPaint.setTextSize(Utils.dp2px(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, getWidth() - mBitmap.getWidth(), 100, mPaint);

        String text = "使用 Camera 做三维旋转 使用 Camera 做三维旋转 使用 Camera 做三维旋转 " +
                "使用 Camera 做三维旋转 使用 Camera 做三维旋转 使用 Camera 做三维旋转 " +
                "使用 Camera 做三维旋转 使用 Camera 做三维旋转 使用 Camera 做三维旋转 " +
                "使用 Camera 做三维旋转 使用 Camera 做三维旋转";

        int index = mPaint.breakText(text, true, getWidth(), mCutWidth);
        canvas.drawText(text, 0, index, 0, 50, mPaint);
        int oldIndex = index;
        index = mPaint.breakText(text, index, text.length(), true, getWidth(), mCutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + mPaint.getFontSpacing(), mPaint);
        oldIndex = index;
        index = mPaint.breakText(text, index, text.length(), true, getWidth() - mBitmap.getWidth(), mCutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + mPaint.getFontSpacing() * 2, mPaint);

    }

}
