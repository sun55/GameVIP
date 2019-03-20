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
 * 开服
 */
public class OpenServiceFragment extends BaseFragment {
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_service, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static OpenServiceFragment getInstance() {
        return new OpenServiceFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
