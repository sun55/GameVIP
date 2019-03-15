package com.mingquan.gamevip.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;

import com.mingquan.gamevip.utils.TDevice;

public class StatusTextView extends android.support.v7.widget.AppCompatTextView {

    private String defaultColor = "#ece2c3";

    public StatusTextView(Context context) {
        super(context);
        init();
    }

    public StatusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
//        setCompoundDrawablePadding((int) TDevice.dpToPixel(2));
        setPadding((int) TDevice.dpToPixel(4), 0, (int) TDevice.dpToPixel(4), (int) TDevice.dpToPixel(0.5f));
        setBackground();
        setTextColor(Color.parseColor(defaultColor));
    }

    public void setStatus(String value) {
        setText(value);
    }

    /**
     * 设置背景颜色
     */
    public void setBackground() {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setStroke((int) TDevice.dpToPixel(1f), Color.parseColor(defaultColor));
        gd.setCornerRadius(TDevice.dpToPixel(6));
        setBackgroundDrawable(gd);
        /*GradientDrawable dot = new GradientDrawable();
        dot.setCornerRadius(TDevice.dpToPixel(1.5f));
        dot.setColor(Color.parseColor(defaultColor));
        dot.setSize((int) TDevice.dpToPixel(3), (int) TDevice.dpToPixel(3));
        dot.setBounds(0, 0, dot.getMinimumWidth(), dot.getMinimumHeight());
        setCompoundDrawables(dot, null, null, null);*/
    }
}
