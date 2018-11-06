package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.R;
import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : AvatarView
 * Date :2018/11/5 1:59 PM
 * Author : will
 */
public class AvatarView extends View {

    /**
     * 需绘制的头像宽度
     */
    private static final int AVATAR_WIDTH = (int) Utils.dp2px(300);

    /**
     * 图片显示的偏移量
     */
    private static final float PADDING = Utils.dp2px(50);

    /**
     * 头像的黑边框厚度
     */
    private static final float EDGE_PADDING = Utils.dp2px(10);

    /**
     * 抗锯齿画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private Bitmap mBitmap;

    private RectF savedArea = new RectF();

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mBitmap = Utils.getAvatar(getResources(), AVATAR_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        savedArea.set(PADDING, PADDING, PADDING + AVATAR_WIDTH, PADDING + AVATAR_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(PADDING, PADDING, PADDING + AVATAR_WIDTH, PADDING + AVATAR_WIDTH, mPaint);

        int saved = canvas.saveLayer(savedArea, mPaint);
        canvas.drawOval(PADDING + EDGE_PADDING, PADDING + EDGE_PADDING,
                PADDING + AVATAR_WIDTH - EDGE_PADDING, PADDING + AVATAR_WIDTH - EDGE_PADDING, mPaint);
        mPaint.setXfermode(xfermode);
        canvas.drawBitmap(mBitmap, PADDING, PADDING, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

}
