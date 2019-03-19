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
            R.id.ll_my_consortia,
            R.id.ll_my_game,
            R.id.ll_my_friends,
            R.id.ll_notification,
            R.id.ll_manager,
            R.id.ll_game_community,
            R.id.ll_my_vip,
            R.id.ll_account_transactions,
            R.id.ll_check_update,
            R.id.ll_contact_customer,
            R.id.ll_setting,
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back: // 返回按钮
                finish();
                break;
            case R.id.ll_my_consortia: // 我的公会
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_my_game: // 我的游戏

                break;
            case R.id.ll_my_friends: // 我的好友
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_notification: // 通知中心
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_manager: // 我的管家

                break;
            case R.id.ll_game_community: // 游戏社区
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_my_vip: // 我的权益

                break;
            case R.id.ll_account_transactions: // 账号交易

                break;
            case R.id.ll_check_update: // 检查更新
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_contact_customer: // 联系客服
                RxToast.showToast("敬请期待");
                break;
            case R.id.ll_setting: // 设置
                RxToast.showToast("敬请期待");
                break;
        }
    }
}
