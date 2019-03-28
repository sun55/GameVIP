package com.mingquan.gamevip.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.widget.XMyInfoItem;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCommunityActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.item_1)
    XMyInfoItem item1;
    @BindView(R.id.item_2)
    XMyInfoItem item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_community);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.item_1, R.id.item_2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.item_1:
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_2:
                RxToast.showToast("敬请期待");
                break;
        }
    }
}
