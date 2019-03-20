package com.mingquan.gamevip.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.widget.MyRatingBar;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.carbs.android.avatarimageview.library.AvatarImageView;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.avatar)
    AvatarImageView avatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rating_bar)
    MyRatingBar ratingBar;
    @BindView(R.id.iv_vip_level)
    ImageView ivVipLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ratingBar.setStar(5);
    }

    @OnClick({
            R.id.iv_back,
            R.id.iv_edit,
            R.id.iv_query,
            R.id.fl_detail,
            R.id.fl_recharge,
            R.id.item_notification,
            R.id.item_manager,
            R.id.item_game_community,
            R.id.item_my_vip,
            R.id.item_account_transactions,
            R.id.item_share,
            R.id.item_contact_customer,
            R.id.item_setting
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back: // 返回按钮
                finish();
                break;
            case R.id.iv_edit: // 编辑按钮
                RxToast.showToast("敬请期待");
                break;
            case R.id.iv_query: // 问号按钮
                RxToast.showToast("敬请期待");
                break;
            case R.id.fl_detail: // 收支明细
                RxToast.showToast("敬请期待");
                break;
            case R.id.fl_recharge: // 充值
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_notification: // 通知中心
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_manager: // 我的管家
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_game_community: // 游戏社区
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_my_vip: // 我的权益
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_account_transactions: // 账号交易
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_share: // 推广分享
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_contact_customer: // 联系客服
                RxToast.showToast("敬请期待");
                break;
            case R.id.item_setting: // 设置
                RxToast.showToast("敬请期待");
                break;
            default:
                RxToast.showToast("敬请期待");
                break;
        }
    }
}
