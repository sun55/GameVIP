package com.mingquan.gamevip.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.FragmentViewPagerAdapter;
import com.mingquan.gamevip.fragment.GameFragment;
import com.mingquan.gamevip.fragment.HomeFragment;
import com.mingquan.gamevip.fragment.CommunityFragment;
import com.mingquan.gamevip.fragment.OpenServiceFragment;
import com.mingquan.gamevip.fragment.SocietyFragment;
import com.mingquan.gamevip.utils.TDevice;
import com.mingquan.gamevip.utils.TLog;
import com.mingquan.gamevip.widget.MyRatingBar;
import com.mingquan.gamevip.widget.RoundAngleImageView;
import com.mingquan.gamevip.widget.StatusTextView;
import com.mingquan.gamevip.widget.XIndicator;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    @BindView(R.id.avatar)
    RoundAngleImageView avatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_vip_level)
    ImageView ivVipLevel;
    @BindView(R.id.stv_status)
    StatusTextView stvStatus;
    @BindView(R.id.rating_bar)
    MyRatingBar ratingBar;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_game)
    ImageView ivGame;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.indicator)
    XIndicator indicator;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_manager)
    LinearLayout llManager;

    private List<Fragment> mFragments;
    private HomeFragment homeFragment;
    private GameFragment gameFragment;
    private OpenServiceFragment openServiceFragment;
    private SocietyFragment societyFragment;
    private CommunityFragment messageFragment;
    private HashMap<View, int[]> mTouchPointMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initEvents();
    }

    private void initView() {
        ratingBar.setStar(5);
        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);
        indicator.setTitleList(Arrays.asList(tabTitles));
        indicator.setViewPager(viewPager, 0);
        float padding = (TDevice.getScreenWidth() - TDevice.dpToPixel(210)) / 10;
        indicator.setStyleLinePadding((int) TDevice.pixelsToDp(padding));
        homeFragment = HomeFragment.getInstance();
        gameFragment = GameFragment.getInstance();
        openServiceFragment = OpenServiceFragment.getInstance();
        societyFragment = SocietyFragment.getInstance();
        messageFragment = CommunityFragment.getInstance();
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(homeFragment);
            mFragments.add(gameFragment);
            mFragments.add(openServiceFragment);
            mFragments.add(societyFragment);
            mFragments.add(messageFragment);
        }
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
    }

    private void initEvents() {
        llManager.setOnTouchListener(this);
    }

    @OnClick({R.id.iv_search, R.id.iv_game, R.id.iv_user})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.iv_game:
                startActivity(new Intent(MainActivity.this, MyGameActivity.class));
                break;
            case R.id.iv_user:
                startActivity(new Intent(MainActivity.this, MyInfoActivity.class));
                break;
        }
    }

    int down_x = 0;
    int down_y = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                down_x = (int) event.getRawX();
                down_y = (int) event.getRawY();
                TLog.info("down %s, %s", down_x, down_y);
                mTouchPointMap.put(v, new int[]{down_x, down_y});
                break;
            case MotionEvent.ACTION_MOVE:
                int[] lastPoint = mTouchPointMap.get(v);
                if (lastPoint != null) {
                    int dx = (int) event.getRawX() - lastPoint[0];
                    int dy = (int) event.getRawY() - lastPoint[1];

                    int left = (int) v.getX() + dx;
                    int top = (int) v.getY() + dy;
                    v.setX(left);
                    v.setY(top);
                    lastPoint[0] = (int) event.getRawX();
                    lastPoint[1] = (int) event.getRawY();

                    mTouchPointMap.put(v, lastPoint);
                    v.getParent().requestLayout();
                }
                break;
            case MotionEvent.ACTION_UP:
                TLog.info("up %s, %s", event.getRawX(), event.getRawY());
                if (Math.abs(event.getRawX() - down_x) < 50 && Math.abs(event.getRawY() - down_y) < 50) {
                    startActivity(new Intent(MainActivity.this, GameManagerActivity.class));
                }
                break;
        }
        return true;
    }
}
