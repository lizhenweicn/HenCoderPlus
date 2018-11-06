package me.lizhenwei.hencoderplus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.lizhenwei.hencoderplus.utils.Utils;

/**
 * Name : CustomView
 * Date :2018/11/6 1:47 PM
 * Author : will
 */
public class CustomView extends View {

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(0,0,200,200);

        Utils.getAvatar(getResources(), 400);

    }
}
