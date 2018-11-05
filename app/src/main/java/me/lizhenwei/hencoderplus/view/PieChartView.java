package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : PieChartView
 * Date :2018/11/5 1:13 PM
 * Author : will
 */
public class PieChartView extends View {

    /**
     * 扇形半径
     */
    private static final float RADIUS = Utils.dp2px(150);

    /**
     * 需要被突出显示的圆弧索引
     */
    private static final int PULL_OUT_INDEX = 2;

    /**
     * 突出显示的扇形和其他扇形之间的间隙距离
     */
    private static final float GAP_DISTANCE = Utils.dp2px(20);

    /**
     * 扇形角度数据
     */
    private int[] angles = {60, 100, 120, 80};

    /**
     * 扇形颜色数据包
     */
    private int[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};

    /**
     * 抗锯齿画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 绘制边界
     */
    private RectF mBounds = new RectF();

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        mBounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            mPaint.setColor(colors[i]);

            canvas.save();
            if (i == PULL_OUT_INDEX) {
                canvas.translate(
                        (float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * GAP_DISTANCE,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * GAP_DISTANCE
                );
            }
            canvas.drawArc(mBounds, currentAngle, angles[i], true, mPaint);
            canvas.restore();

            currentAngle += angles[i];
        }

    }
}
