package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : CustomView
 * Date :2018/11/6 1:47 PM
 * Author : will
 */
public class CameraView extends View {

    /**
     * 抗锯齿画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.rotateX(30);

        //  这里的 -8 单位是 inch, 系统默认 inch 代表 72 像素。
        camera.setLocation(0, 0, Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
        camera.applyToCanvas(canvas);
        canvas.translate(-(100 + 600 / 2), -(100 + 600 / 2));

        canvas.drawBitmap(Utils.getAvatar(getResources(), 600), 100, 100, mPaint);

    }
}
