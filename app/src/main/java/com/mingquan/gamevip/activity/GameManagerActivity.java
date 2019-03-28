package com.mingquan.gamevip.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.FragmentViewPagerAdapter;
import com.mingquan.gamevip.fragment.GameManagerFragment;
import com.mingquan.gamevip.utils.TLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/3/27
 * <p>
 * 游戏管家
 */
public class GameManagerActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_vip_level)
    ImageView ivVipLevel;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_indicator)
    RelativeLayout rlIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private int currPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_manager);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GameManagerFragment.getInstance(1));
        fragments.add(GameManagerFragment.getInstance(2));
        fragments.add(GameManagerFragment.getInstance(3));
        viewPager.setCurrentItem(currPageIndex);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments));
        ivVipLevel.setBackgroundResource(R.drawable.icon_06_007);
        ivLeft.setVisibility(View.GONE);
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TLog.info("on page selected %s", position);
                currPageIndex = position;
                if (currPageIndex == 0) {
                    ivLeft.setVisibility(View.GONE);
                    ivVipLevel.setBackgroundResource(R.drawable.icon_06_007);
                } else if (currPageIndex == 2) {
                    ivRight.setVisibility(View.GONE);
                    ivVipLevel.setBackgroundResource(R.drawable.icon_06_005);
                } else {
                    ivLeft.setVisibility(View.VISIBLE);
                    ivRight.setVisibility(View.VISIBLE);
                    ivVipLevel.setBackgroundResource(R.drawable.icon_06_006);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_left, R.id.iv_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_left:
                if (currPageIndex == 0) {
                    TLog.info("已经到第一页了");
                    return;
                }
                viewPager.setCurrentItem(currPageIndex - 1);
                break;
            case R.id.iv_right:
                if (currPageIndex == 2) {
                    TLog.info("已经到最后一页了");
                    return;
                }
                viewPager.setCurrentItem(currPageIndex + 1);
                break;
        }
    }
}
