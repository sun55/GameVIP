package com.mingquan.gamevip.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.lzy.widget.HeaderScrollHelper;
import com.mingquan.gamevip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements HeaderScrollHelper.ScrollableContainer {

    @BindView(R.id.scroll_view_home)
    ScrollView scrollViewHome;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View getScrollableView() {
        return scrollViewHome;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
