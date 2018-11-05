package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : DashboardView
 * Date :2018/11/5 10:13 AM
 * Author : will
 */
public class DashboardView extends View {

    /**
     * 圆弧未扫过的角度
     */
    private static final float UN_SWEPT_ANGLE = 120;

    /**
     * 圆弧的半径
     */
    private static final float RADIUS = Utils.dp2px(150);

    /**
     * 刻度（虚线）宽度
     */
    private static final float DASH_WIDTH = Utils.dp2px(2);

    /**
     * 刻度（虚线）高度
     */
    private static final float DASH_HEIGHT = Utils.dp2px(8);

    /**
     * 刻度（虚线）总量
     */
    private static final int DASH_COUNT = 20;

    /**
     * 指针长度
     */
    private static final float POINTER_LENGTH = Utils.dp2px(100);

    /**
     * 抗锯齿画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 圆弧路径
     */
    private Path mArcPath = new Path();

    /**
     * 虚线路径
     */
    private Path mDashPath = new Path();

    /**
     * 虚线效果
     */
    private PathDashPathEffect mDashPathEffect;

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
    }

    {
        //  画笔设置 ：仅画线，不填充
        mPaint.setStyle(Paint.Style.STROKE);

        //  画笔设置 ：画线宽度
        mPaint.setStrokeWidth(Utils.dp2px(2));

        //  圆弧路径总长度计算
        mArcPath.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + UN_SWEPT_ANGLE / 2, 360 - UN_SWEPT_ANGLE);
        PathMeasure pathMeasure = new PathMeasure(mArcPath, false);
        float arcPathMeasureLength = pathMeasure.getLength();

        //  路径效果 ：顺时针方向画矩形
        mDashPath.addRect(0, 0, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW);

        //  路径效果 ：计算每个刻度之间的距离
        float dashDistance = arcPathMeasureLength / DASH_COUNT - DASH_WIDTH / 20;

        //  路径效果 ：每个刻度矩形之间的距离及刻度矩形的风格
        mDashPathEffect = new PathDashPathEffect(mDashPath, dashDistance, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  画圆弧
        drawArcByCanvas(canvas);

        //  画刻度
        mPaint.setPathEffect(mDashPathEffect);
        drawArcByCanvas(canvas);
        mPaint.setPathEffect(null);

        //  画指针
        canvas.drawLine(
                getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromDash(0))) * POINTER_LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromDash(0))) * POINTER_LENGTH + getHeight() / 2,
                mPaint);
    }

    /**
     * 画圆弧
     */
    private void drawArcByCanvas(Canvas canvas) {
        canvas.drawArc(
                getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + UN_SWEPT_ANGLE / 2, 360 - UN_SWEPT_ANGLE, false, mPaint
        );
    }

    /**
     * 根据刻度获取角度
     */
    private double getAngleFromDash(int dash) {
        return (90 + UN_SWEPT_ANGLE / 2) + (360 - UN_SWEPT_ANGLE) / DASH_COUNT * dash;
    }

}
