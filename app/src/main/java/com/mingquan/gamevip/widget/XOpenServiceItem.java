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
 * 开服 item
 */

public class XOpenServiceItem extends RelativeLayout {

    private ImageView ivGameIcon; // 游戏图标
    private TextView tvGameName; // 游戏名字
    private TextView tvGameDetail; // 游戏详情
    private TextView tvGameDiscount; // 折扣
    private TextView tvServiceName; // 服务器名
    private int iconResourceId;
    private String gameName;
    private String gameDetail;
    private String gameDiscount;
    private String serviceName;

    public XOpenServiceItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_open_service_item, this);
        ivGameIcon = findViewById(R.id.iv_game_icon);
        tvGameName = findViewById(R.id.tv_game_name);
        tvGameDetail = findViewById(R.id.tv_game_detail);
        tvGameDiscount = findViewById(R.id.tv_game_discount);
        tvServiceName = findViewById(R.id.tv_service_name);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XOpenServiceItem, 0, 0);
        try {
            iconResourceId = ta.getResourceId(R.styleable.XOpenServiceItem_game_icon, iconResourceId);

            gameName = ta.getString(R.styleable.XOpenServiceItem_game_name);
            gameDetail = ta.getString(R.styleable.XOpenServiceItem_game_detail);
            gameDiscount = ta.getString(R.styleable.XOpenServiceItem_game_discount);
            serviceName = ta.getString(R.styleable.XOpenServiceItem_service_name);
            ivGameIcon.setBackgroundResource(iconResourceId);
            tvGameName.setText(gameName);
            tvGameDetail.setText(gameDetail);
            tvGameDiscount.setText(gameDiscount);
            tvServiceName.setText(serviceName);
        } finally {
            ta.recycle();
        }
    }
}
