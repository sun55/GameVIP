package com.mingquan.gamevip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.utils.TDevice;

/**
 * 自定义评分控件
 */
public class MyRatingBar extends LinearLayout {

    private boolean mClickable;
    private int starCount;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable halfFillDrawable;
    private float marginRightSize;
    private float mStarPadding;

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }


    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        this.starImageSize = mTypedArray.getDimension(R.styleable.RatingBar_starImageSize, 20.0f);
        this.starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5);
        this.starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty);
        this.starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starFill);
        this.halfFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_halfFill);
        this.mClickable = mTypedArray.getBoolean(R.styleable.RatingBar_clickable, false);
        this.marginRightSize = mTypedArray.getDimension(R.styleable.RatingBar_marginRightSize, 1f);
        this.mStarPadding = mTypedArray.getDimension(R.styleable.RatingBar_starPadding, 1f);

        mTypedArray.recycle();

        for (int i = 0; i < this.starCount; i++) {
            ImageView imageView = this.getStarImageView(context, attrs);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MyRatingBar.this.mClickable) {  //判断星星可以点击
                        MyRatingBar.this.setStar(MyRatingBar.this.indexOfChild(v) + 1);  //设置当前评分
                        if (MyRatingBar.this.onRatingChangeListener != null) {
                            MyRatingBar.this.onRatingChangeListener.onRatingChange(MyRatingBar.this.indexOfChild(v) + 1);  //调用监听接口
                        }
                    }
                }
            });

            LayoutParams lp = new LayoutParams((int) starImageSize, (int) starImageSize);
            lp.setMargins(0, 0, (int) marginRightSize, 0);
            addView(imageView, lp);
        }

    }

    //初始化单个星星控件
    private ImageView getStarImageView(Context context, AttributeSet attrs) {
        ImageView imageView = new ImageView(context);
        imageView.setPadding((int) mStarPadding, 0, (int) mStarPadding, 0);
        imageView.setImageDrawable(this.starEmptyDrawable);
        return imageView;
    }

    //设置当前评分
    public void setStar(float mark) {

        //判断评分，不能大于星星总数，不能小于0
        mark = mark > this.starCount ? this.starCount : mark;
        mark = starCount < 0 ? 0 : mark;

//        float xiaoshu = mark - (int) (mark); //计算分数的小数部分
        int zhengshu = (int) mark; //计算分数的整数部分

        //显示整数部分的星星，全部是实心星星
        for (int i = 0; i < zhengshu; ++i) {
            ((ImageView) this.getChildAt(i)).setImageDrawable(this.starFillDrawable);
        }

        //剩余部分用全空星星显示
        for (int j = zhengshu; j < this.starCount; j++) {
            ((ImageView) this.getChildAt(j)).setImageDrawable(this.starEmptyDrawable);
        }

    }

    //定义星星点击的监听接口
    public interface OnRatingChangeListener {
        void onRatingChange(int var1);
    }

}
