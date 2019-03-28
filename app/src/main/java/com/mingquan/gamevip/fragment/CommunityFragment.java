package com.mingquan.gamevip.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.activity.MyCommunityActivity;
import com.mingquan.gamevip.activity.MyTeamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 社区
 */
public class CommunityFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.ll_my_community)
    LinearLayout llMyCommunity;
    @BindView(R.id.ll_teams)
    LinearLayout llTeams;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @OnClick({R.id.ll_my_community, R.id.ll_teams})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_my_community:
                startActivity(new Intent(getActivity(), MyCommunityActivity.class));
                break;
            case R.id.ll_teams:
                startActivity(new Intent(getActivity(), MyTeamActivity.class));
                break;
        }
    }

    public static CommunityFragment getInstance() {
        return new CommunityFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
