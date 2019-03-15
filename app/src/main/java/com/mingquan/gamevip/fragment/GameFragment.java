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
 * 游戏
 */
public class GameFragment extends BaseFragment implements HeaderScrollHelper.ScrollableContainer {

    @BindView(R.id.scroll_view_game)
    ScrollView scrollViewGame;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static GameFragment getInstance() {
        return new GameFragment();
    }

    @Override
    public View getScrollableView() {
        return scrollViewGame;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
