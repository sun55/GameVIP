package com.mingquan.gamevip.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class XRoundRectImageView extends AppCompatImageView {
  private static final String TAG = XRoundRectImageView.class.getSimpleName();
  private float mRadius;
  private OnClickListener mOnClickListener;
  private boolean mIsClickEnable = true;
  private long mDownTime = 0;
  private int mBorderWidth = 0;
  private int mBorderColor = Color.TRANSPARENT;

  public XRoundRectImageView(Context context) {
    this(context, null);
  }

  public XRoundRectImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public XRoundRectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    //        this.setPadding(10,10,10,10);
  }

  /**
   * 设置背景色，包括按钮为Enable状态的和Disable状态的
   *
   * @param enableColor
   * @param disableColor
   */
  public void setBgColor(int enableColor, int disableColor) {
    setClickable(mIsClickEnable);
    resetBg();
  }

  public void setRadius(float radius) {
    this.mRadius = radius;
    invalidate();
  }

  /**
   * 获取是否可点击
   * @return
   */
  public boolean getClickEnabled() {
    return this.mIsClickEnable;
  }

  /**
   * 设置点击时候的效果
   *
   * @param enable true 表示当前不可点击，false 表示当前可以点击
   */
  public void setClickEnable(boolean enable) {
    if (enable) {
      setFocusBacground();
      setEnabled(true);
      this.mIsClickEnable = true;
    } else {
      setNormalBacground();
      setEnabled(false);
      this.mIsClickEnable = false;
    }
    invalidate();
  }

  /**
   * 重置bg
   */
  private void resetBg() {
    if (mIsClickEnable) {
      setFocusBacground();
    } else {
      setNormalBacground();
    }
    invalidate();
  }

  public void setBorder(int width, int color) {
    this.mBorderColor = color;
    this.mBorderWidth = width;
    resetBg();
  }

  public void setBorderColor(int color) {
    this.mBorderColor = color;
    resetBg();
  }

  public void setBorderWidth(int width) {
    this.mBorderWidth = width;
    resetBg();
  }

  private void setNormalBacground() {
    GradientDrawable background = new GradientDrawable(); //创建drawable
    background.setStroke(mBorderWidth, mBorderColor);
    background.setCornerRadius(mRadius);
    setBackgroundDrawable(background);
  }

  private void setFocusBacground() {
    GradientDrawable background = new GradientDrawable(); //创建drawable
    background.setCornerRadius(mRadius);
    background.setStroke(mBorderWidth, mBorderColor);
    setBackgroundDrawable(background);
  }

  @Override
  public void setOnClickListener(OnClickListener l) {
    this.mOnClickListener = l;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        mDownTime = System.currentTimeMillis();
        if (this.isEnabled()) {
          setAlpha(0.5f);
        }
        break;
      case MotionEvent.ACTION_MOVE:
        break;
      case MotionEvent.ACTION_CANCEL:
        Log.e(TAG, "ACTION_CANCEL");
        setAlpha(1.0f);
      case MotionEvent.ACTION_UP:
        long upTime = System.currentTimeMillis();
        boolean isClick = (upTime - mDownTime) < 600 ? true : false;
        if (mIsClickEnable && isClick && event.getX() <= getRight() - getLeft() && event.getX() >= 0
            && event.getY() <= getBottom() - getTop() && event.getY() >= 0) {
          dealClickEvent();
        }
        setAlpha(1.0f);
    }
    return true;
  }
  /**上次点击事件的执行时间*/
  private long mLastClickEventTime;

  /**
   * 处理点击事件
   */
  private void dealClickEvent() {
    long time = System.currentTimeMillis();
    if (time - mLastClickEventTime < 800) { //防止连续点击事件
      return;
    }
    mLastClickEventTime = time;
    if (mOnClickListener != null) {
      mOnClickListener.onClick(this);
    }
  }
}
