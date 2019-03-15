package com.mingquan.gamevip.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.widget.HeaderScrollHelper;
import com.lzy.widget.HeaderViewPager;
import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.FragmentViewPagerAdapter;
import com.mingquan.gamevip.fragment.GameFragment;
import com.mingquan.gamevip.fragment.HomeFragment;
import com.mingquan.gamevip.fragment.MessageFragment;
import com.mingquan.gamevip.fragment.OpenServiceFragment;
import com.mingquan.gamevip.fragment.SocietyFragment;
import com.mingquan.gamevip.widget.MyRatingBar;
import com.mingquan.gamevip.widget.RoundAngleImageView;
import com.mingquan.gamevip.widget.StatusTextView;
import com.mingquan.gamevip.widget.XIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

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
    @BindView(R.id.scrollable_layout)
    HeaderViewPager scrollableLayout;

    private List<Fragment> mFragments;
    private HomeFragment homeFragment;
    private GameFragment gameFragment;
    private OpenServiceFragment openServiceFragment;
    private SocietyFragment societyFragment;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ratingBar.setStar(5);
        initView();
        initEvents();
    }

    private void initView() {
        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);
        indicator.setTitleList(Arrays.asList(tabTitles));
        indicator.setViewPager(viewPager, 0);
        homeFragment = HomeFragment.getInstance();
        gameFragment = GameFragment.getInstance();
        openServiceFragment = OpenServiceFragment.getInstance();
        societyFragment = SocietyFragment.getInstance();
        messageFragment = MessageFragment.getInstance();
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(homeFragment);
            mFragments.add(gameFragment);
            mFragments.add(openServiceFragment);
            mFragments.add(societyFragment);
            mFragments.add(messageFragment);
        }
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments));
        scrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer) mFragments.get(0));
    }

    private void initEvents() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                scrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer) mFragments.get(position));
            }
        });
        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                float alpha = 1.0f * currentY / maxY;
            }
        });
    }
}
