package com.mingquan.gamevip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingquan.gamevip.R;

/**
 * Created by Administrator on 2019/3/20
 */

public class XMyInfoItem extends RelativeLayout {

    private int iconResourceId;
    private String title;
    private boolean isShowMore;
    private ImageView ivIcon;
    private TextView tvMore;
    private TextView tvtitle;

    public XMyInfoItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_my_info_item, this);
        ivIcon = findViewById(R.id.iv_icon);
        tvtitle = findViewById(R.id.tv_title);
        tvMore = findViewById(R.id.tv_more);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XMyInfoItem, 0, 0);
        try {
            iconResourceId = ta.getResourceId(R.styleable.XMyInfoItem_icon, iconResourceId);
            isShowMore = ta.getBoolean(R.styleable.XMyInfoItem_is_show_more, false);
            title = ta.getString(R.styleable.XMyInfoItem_title);
            ivIcon.setBackgroundResource(iconResourceId);
            tvtitle.setText(title);
            tvMore.setVisibility(isShowMore ? View.VISIBLE : View.GONE);
        } finally {
            ta.recycle();
        }
    }
}
