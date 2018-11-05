package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : SportsView
 * Date :2018/11/5 3:29 PM
 * Author : will
 */
public class SportsView extends View {

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

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
//        mPaint.setTextSize(Utils.dp2px(100));
//        mPaint.setTypeface(Typeface.createFromAsset(getContext()));
//        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  绘制环
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(CIRCLE_COLOR);
        mPaint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mPaint);

        //  绘制进度条
        mPaint.setColor(HIGHLIGHT_COLOR);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(
                getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                -90, 225, false, mPaint);

        //  绘制文字
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawText("abab", getWidth() / 2, getHeight() / 2, mPaint);

    }
}
