package com.mingquan.gamevip.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.BaseRecyclerMutilAdapter;
import com.mingquan.gamevip.adapter.GameManagerAdapter;
import com.mingquan.gamevip.bean.GameManagerBean;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/3/27
 * 游戏管家
 */

public class GameManagerFragment extends BaseFragment {

    @BindView(R.id.rcv_game_manager)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private int level;
    private int[] level1 = {
            R.drawable.icon_06_021,
            R.drawable.icon_06_023,
            R.drawable.icon_06_024,
            R.drawable.icon_06_025,
            R.drawable.icon_06_022,
            R.drawable.icon_06_020
    };
    private int[] level2 = {
            R.drawable.icon_06_014,
            R.drawable.icon_06_016,
            R.drawable.icon_06_018,
            R.drawable.icon_06_019,
            R.drawable.icon_06_017,
            R.drawable.icon_06_015
    };
    private int[] level3 = {
            R.drawable.icon_06_011,
            R.drawable.icon_06_013,
            R.drawable.icon_06_008,
            R.drawable.icon_06_009,
            R.drawable.icon_06_010,
            R.drawable.icon_06_012
    };

    public static GameManagerFragment getInstance(int level) {
        GameManagerFragment instance = new GameManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("LEVEL", level);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getArguments().getInt("LEVEL");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_manager, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        super.initData();
        List<GameManagerBean> beans = new ArrayList<>();
        GameManagerBean bean;
        switch (level) {
            case 1:
                for (int img : level1) {
                    bean = new GameManagerBean();
                    bean.setResourceId(img);
                    beans.add(bean);
                }
                break;
            case 2:
                for (int img : level2) {
                    bean = new GameManagerBean();
                    bean.setResourceId(img);
                    beans.add(bean);
                }
                break;
            case 3:
                for (int img : level3) {
                    bean = new GameManagerBean();
                    bean.setResourceId(img);
                    beans.add(bean);
                }
                break;
        }
        GameManagerAdapter adapter = new GameManagerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerMutilAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                RxToast.showToast("敬请期待");
            }
        });
        adapter.setList(beans);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
