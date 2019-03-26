package com.mingquan.gamevip.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.GameAdapter;
import com.mingquan.gamevip.bean.GameBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 游戏
 */
public class GameFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.tv_label_1)
    TextView tvLabel1;
    @BindView(R.id.tv_label_2)
    TextView tvLabel2;
    @BindView(R.id.tv_label_3)
    TextView tvLabel3;
    @BindView(R.id.tv_label_4)
    TextView tvLabel4;
    @BindView(R.id.rcv_game_list)
    RecyclerView rcvGameList;
    private ArrayList<GameBean> gameList;
    private GameAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvLabel1.setBackgroundResource(R.drawable.game_indicator_selected);
        initView(view);
        initData();
        return view;
    }

    public static GameFragment getInstance() {
        return new GameFragment();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        tvLabel1.setBackgroundResource(R.drawable.game_indicator_selected);
        tvLabel1.setTextColor(getResources().getColor(R.color.white));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcvGameList.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        super.initData();
        gameList = new ArrayList<>();
        adapter = new GameAdapter(getActivity());
        GameBean bean = new GameBean();
        bean.setIconResourceId(R.drawable.icon_03004);
        bean.setGameName("1.蒸汽都市");
        bean.setDetail("角色扮演");
        bean.setDiscount("4.5折");
        bean.setTag("『平台独家』超人气英雄集结！");
        gameList.add(bean);

        bean = new GameBean();
        bean.setIconResourceId(R.drawable.icon_03005);
        bean.setGameName("2.炎之轨迹");
        bean.setDetail("卡牌");
        bean.setDiscount("6.8折");
        bean.setTag("");
        gameList.add(bean);

        bean = new GameBean();
        bean.setIconResourceId(R.drawable.icon_03006);
        bean.setGameName("3.暗黑魔法使");
        bean.setDetail("模拟 动漫");
        bean.setDiscount("5.4折");
        bean.setTag("");
        gameList.add(bean);

        bean = new GameBean();
        bean.setIconResourceId(R.drawable.icon_03007);
        bean.setGameName("4.忍者大师");
        bean.setDetail("动作");
        bean.setDiscount("6.8折");
        bean.setTag("");
        gameList.add(bean);
        adapter.setList(gameList);
        rcvGameList.setAdapter(adapter);
    }

    @OnClick({R.id.tv_label_1, R.id.tv_label_2, R.id.tv_label_3, R.id.tv_label_4})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_label_1:
                selectLabel(0);
                break;
            case R.id.tv_label_2:
                selectLabel(1);
                break;
            case R.id.tv_label_3:
                selectLabel(2);
                break;
            case R.id.tv_label_4:
                selectLabel(3);
                break;
        }
    }

    private void selectLabel(int index) {
        tvLabel1.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel1.setTextColor(getResources().getColor(R.color.color_9e6e33));
        tvLabel2.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel2.setTextColor(getResources().getColor(R.color.color_9e6e33));
        tvLabel3.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel3.setTextColor(getResources().getColor(R.color.color_9e6e33));
        tvLabel4.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel4.setTextColor(getResources().getColor(R.color.color_9e6e33));
        switch (index) {
            case 0:
                tvLabel1.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel1.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                tvLabel2.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel2.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                tvLabel3.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel3.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                tvLabel4.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel4.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        for (int i = 0; i < gameList.size(); i++) {
            gameList.get(i).setTag(i == index ? "『平台独家』超人气英雄集结！" : "");
        }
        adapter.setList(gameList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
