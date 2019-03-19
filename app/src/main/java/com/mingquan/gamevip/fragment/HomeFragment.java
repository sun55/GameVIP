package com.mingquan.gamevip.fragment;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.lzy.widget.HeaderScrollHelper;
import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.NewGameAdapter;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements HeaderScrollHelper.ScrollableContainer, View.OnClickListener {

    @BindView(R.id.scroll_view_home)
    ScrollView scrollViewHome;
    Unbinder unbinder;
    @BindView(R.id.video_view_1)
    VideoView videoView1;
    @BindView(R.id.rl_video_view_1)
    RelativeLayout rlVideoView1;
    @BindView(R.id.rv_new_games)
    RecyclerView rvNewGames;
    @BindView(R.id.video_view_2)
    VideoView videoView2;
    @BindView(R.id.rl_video_view_2)
    RelativeLayout rlVideoView2;

    private String uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uri = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video1;
        initView(view);
        initEvent();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewGames.setLayoutManager(manager);
        rvNewGames.setAdapter(new NewGameAdapter());
    }

    private void initEvent() {
        rlVideoView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView1.isPlaying()) {
                    videoView1.pause();
                    videoView1.setVisibility(View.GONE);
                    return;
                }
                videoView1.setVisibility(View.VISIBLE);
                videoView1.setVideoURI(Uri.parse(uri));
                videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoView1.setVisibility(View.GONE);
                    }
                });
                videoView1.start();
            }
        });

        rlVideoView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView2.isPlaying()) {
                    videoView2.pause();
                    videoView2.setVisibility(View.GONE);
                    return;
                }
                videoView2.setVisibility(View.VISIBLE);
                videoView2.setVideoURI(Uri.parse(uri));
                videoView2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoView2.setVisibility(View.GONE);
                    }
                });
                videoView2.start();
            }
        });
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View getScrollableView() {
        return scrollViewHome;
    }

    @OnClick({R.id.rl_video_view_1, R.id.rl_video_view_2, R.id.tv_more, R.id.tv_download_1, R.id.tv_download_2, R.id.ll_other})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_video_view_1:
                if (videoView1.isPlaying()) {
                    videoView1.pause();
                    videoView1.setVisibility(View.GONE);
                    return;
                }
                videoView1.setVisibility(View.VISIBLE);
                videoView1.setVideoURI(Uri.parse(uri));
                videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoView1.setVisibility(View.GONE);
                    }
                });
                videoView1.start();
                break;
            case R.id.rl_video_view_2:
                if (videoView2.isPlaying()) {
                    videoView2.pause();
                    videoView2.setVisibility(View.GONE);
                    return;
                }
                videoView2.setVisibility(View.VISIBLE);
                videoView2.setVideoURI(Uri.parse(uri));
                videoView2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoView2.setVisibility(View.GONE);
                    }
                });
                videoView2.start();
                break;
            case R.id.tv_more:
            case R.id.tv_download_1:
            case R.id.tv_download_2:
            case R.id.ll_other:
                RxToast.showToast("敬请期待");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
