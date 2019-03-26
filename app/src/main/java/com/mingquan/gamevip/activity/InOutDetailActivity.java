package com.mingquan.gamevip.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.BaseRecyclerMutilAdapter;
import com.mingquan.gamevip.adapter.InOutAdapter;
import com.mingquan.gamevip.bean.InOutBean;
import com.mingquan.gamevip.manage.FullyLinearLayoutManager;
import com.mingquan.gamevip.utils.TLog;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收支明细
 */
public class InOutDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_label_1)
    TextView tvLabel1;
    @BindView(R.id.tv_label_2)
    TextView tvLabel2;
    @BindView(R.id.tv_label_3)
    TextView tvLabel3;
    @BindView(R.id.rcv_in_out_list)
    RecyclerView rcvInOutList;

    private int currIndex;

    private List<InOutBean> timeList = new ArrayList<>();
    private List<InOutBean> gameList = new ArrayList<>();
    private List<InOutBean> userList = new ArrayList<>();
    private int[] orderTime = {
            R.drawable.icon_04_007,
            R.drawable.icon_04_006,
            R.drawable.icon_04_005,
            R.drawable.icon_04_004,
            R.drawable.icon_04_003,
            R.drawable.icon_04_001,
            R.drawable.icon_04_002};
    private int[] orderGame = {
            R.drawable.icon_04_004,
            R.drawable.icon_04_007,
            R.drawable.icon_04_002,
            R.drawable.icon_04_003,
            R.drawable.icon_04_001,
            R.drawable.icon_04_005,
            R.drawable.icon_04_006};
    private int[] orderUser = {
            R.drawable.icon_04_002,
            R.drawable.icon_04_001,
            R.drawable.icon_04_005,
            R.drawable.icon_04_006,
            R.drawable.icon_04_003,
            R.drawable.icon_04_007,
            R.drawable.icon_04_004};
    private InOutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        adapter = new InOutAdapter(this);
        adapter.setOnItemClickListener(new BaseRecyclerMutilAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                RxToast.showToast("敬请期待");
            }
        });
        currIndex = 0;
        tvLabel1.setBackgroundResource(R.drawable.game_indicator_selected);
        tvLabel1.setTextColor(getResources().getColor(R.color.white));
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvInOutList.setLayoutManager(manager);
    }

    private void initData() {
        for (int resourceId : orderTime) {
            InOutBean bean = new InOutBean();
            bean.setResourceId(resourceId);
            timeList.add(bean);
        }

        for (int resourceId : orderGame) {
            InOutBean bean = new InOutBean();
            bean.setResourceId(resourceId);
            gameList.add(bean);
        }

        for (int resourceId : orderUser) {
            InOutBean bean = new InOutBean();
            bean.setResourceId(resourceId);
            userList.add(bean);
        }
        rcvInOutList.setAdapter(adapter);
        adapter.setList(timeList);
    }

    @OnClick({R.id.iv_back, R.id.tv_label_1, R.id.tv_label_2, R.id.tv_label_3})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_label_1:
                selectLabel(0);
                break;
            case R.id.tv_label_2:
                selectLabel(1);
                break;
            case R.id.tv_label_3:
                selectLabel(2);
                break;
        }
    }

    private void selectLabel(int index) {
        if (index == currIndex) {
            TLog.debug("显示当前列表，不用刷新");
            return;
        }
        currIndex = index;
        tvLabel1.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel1.setTextColor(getResources().getColor(R.color.color_9e6e33));
        tvLabel2.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel2.setTextColor(getResources().getColor(R.color.color_9e6e33));
        tvLabel3.setBackgroundResource(R.drawable.game_indicator_normal);
        tvLabel3.setTextColor(getResources().getColor(R.color.color_9e6e33));
        switch (index) {
            case 0:
                tvLabel1.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel1.setTextColor(getResources().getColor(R.color.white));
                adapter.setList(timeList);
                break;
            case 1:
                tvLabel2.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel2.setTextColor(getResources().getColor(R.color.white));
                adapter.setList(gameList);
                break;
            case 2:
                tvLabel3.setBackgroundResource(R.drawable.game_indicator_selected);
                tvLabel3.setTextColor(getResources().getColor(R.color.white));
                adapter.setList(userList);
                break;
        }
    }
}
