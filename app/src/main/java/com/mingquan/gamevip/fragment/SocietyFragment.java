package com.mingquan.gamevip.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.BaseRecyclerMutilAdapter;
import com.mingquan.gamevip.adapter.SocietyAdapter;
import com.mingquan.gamevip.bean.SocietyBean;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 公会
 */
public class SocietyFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.iv_indicator_1)
    ImageView ivIndicator1;
    @BindView(R.id.rl_indicator_1)
    RelativeLayout rlIndicator1;
    @BindView(R.id.iv_indicator_2)
    ImageView ivIndicator2;
    @BindView(R.id.rl_indicator_2)
    RelativeLayout rlIndicator2;
    @BindView(R.id.rcv_society_list)
    RecyclerView rcvSocietyList;
    @BindView(R.id.tv_indicator_1)
    TextView tvIndicator1;
    @BindView(R.id.tv_indicator_2)
    TextView tvIndicator2;

    private List<SocietyBean> powerList = new ArrayList<>();
    private List<SocietyBean> activeList = new ArrayList<>();
    private int[] powers = {
            R.drawable.icon_05_001,
            R.drawable.icon_05_002,
            R.drawable.icon_05_003,
            R.drawable.icon_05_004};
    private int[] actives = {
            R.drawable.icon_05_005,
            R.drawable.icon_05_006,
            R.drawable.icon_05_007,
            R.drawable.icon_05_008};
    private SocietyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_society, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        selectIndicator(0);
        adapter = new SocietyAdapter(getActivity());
        adapter.setOnItemClickListener(new BaseRecyclerMutilAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                RxToast.showToast("敬请期待");
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvSocietyList.setLayoutManager(linearLayoutManager);
    }

    private void selectIndicator(int index) {
        switch (index) {
            case 0:
                ivIndicator1.setVisibility(View.GONE);
                ivIndicator2.setVisibility(View.VISIBLE);
                tvIndicator1.setTextColor(Color.WHITE);
                tvIndicator2.setTextColor(getResources().getColor(R.color.color_cfae76));
                break;
            case 1:
                ivIndicator1.setVisibility(View.VISIBLE);
                ivIndicator2.setVisibility(View.GONE);
                tvIndicator1.setTextColor(getResources().getColor(R.color.color_cfae76));
                tvIndicator2.setTextColor(Color.WHITE);
                break;
            default:
                ivIndicator1.setVisibility(View.GONE);
                ivIndicator2.setVisibility(View.VISIBLE);
                tvIndicator1.setTextColor(Color.WHITE);
                tvIndicator2.setTextColor(getResources().getColor(R.color.color_cfae76));
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
        SocietyBean bean;
        for (int img : powers) {
            bean = new SocietyBean();
            bean.setResourceId(img);
            powerList.add(bean);
        }

        for (int img : actives) {
            bean = new SocietyBean();
            bean.setResourceId(img);
            activeList.add(bean);
        }
        rcvSocietyList.setAdapter(adapter);
        adapter.setList(powerList);
    }

    @OnClick({R.id.rl_indicator_1, R.id.rl_indicator_2})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_indicator_1:
                selectIndicator(0);
                adapter.setList(powerList);
                break;
            case R.id.rl_indicator_2:
                selectIndicator(1);
                adapter.setList(activeList);
                break;
            default:
                selectIndicator(0);
                adapter.setList(activeList);
                break;
        }
    }

    public static SocietyFragment getInstance() {
        return new SocietyFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
