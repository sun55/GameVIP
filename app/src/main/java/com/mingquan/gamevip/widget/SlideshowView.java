package com.mingquan.gamevip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.utils.TDevice;
import com.mingquan.gamevip.utils.TLog;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SlideshowView extends FrameLayout {
    private static final String TAG = "SlideshowView----->";

    //轮播图图片数量
    private final static int IMAGE_COUNT = 2;
    //自动轮播的时间间隔
    private final static int INIT_DELAY_TIME = 1;
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private static boolean isAutoPlay = false;

    //自定义轮播图的资源
    private List<String> imageUrls;
    //跳转连接
    private List<String> imageJumps;
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    private List<String> mUrlList;
    //放圆点的View的list
    private List<View> dotViewsList;

    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private Context context;

    private float topLeftRadius = 0;
    private float topRightRadius = 0;
    private float bottomRightRadius = 0;
    private float bottomLeftRadius = 0;
    private int indicatorLayoutHeight;

    // Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }

    };

    public SlideshowView(Context context) {
        this(context, null);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    public SlideshowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlideShowView, 0, 0);
        try {
            topLeftRadius = ta.getDimensionPixelSize(R.styleable.SlideShowView_topLeftRadius, 0);
            topRightRadius = ta.getDimensionPixelSize(R.styleable.SlideShowView_topRightRadius, 0);
            bottomLeftRadius = ta.getDimensionPixelSize(R.styleable.SlideShowView_bottomLeftRadius, 0);
            bottomRightRadius = ta.getDimensionPixelSize(R.styleable.SlideShowView_bottomRightRadius, 0);
        } finally {
            ta.recycle();
        }
    }

    public SlideshowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        initData();
    }

    /**
     * 设置图片源
     */
    public void setImageList(List<String> imageList) {
        imageUrls = imageList;
    }

    /**
     * 设置图片链接元
     */
    public void setUrlList(List<String> urlList) {
        imageJumps = urlList;
    }

    /**
     * 开始轮播图切换
     */
    public void startPlay() {
        stopPlay();
        initUI(context);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(
                new SlideShowTask(), INIT_DELAY_TIME, TIME_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        if (null != scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
    }

    /**
     * 设置自动切换
     *
     * @param isAutoPlay
     */
    public void setIsAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        indicatorLayoutHeight = (int) TDevice.dpToPixel(16);
        imageViewsList = new ArrayList<>();
        dotViewsList = new ArrayList<>();
        startPlay();
    }

    /*dispatchDraw是绘制子控件时的回调，参数Canvas可以对子控件的画布进行处理。*/
    @Override
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        Path path = new Path();
        path.moveTo(0, topLeftRadius);
        path.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2), -180, 90);
        path.lineTo(width - topRightRadius, 0);
        path.arcTo(new RectF(width - 2 * topRightRadius, 0, width, topRightRadius * 2), -90, 90);
        path.lineTo(width, height - bottomRightRadius - indicatorLayoutHeight);
        path.arcTo(new RectF(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius - indicatorLayoutHeight, width, height - indicatorLayoutHeight), 0, 90);
        path.lineTo(width - indicatorLayoutHeight, height);
        path.lineTo(bottomLeftRadius, height);
        path.lineTo(bottomLeftRadius, height - indicatorLayoutHeight);
        path.arcTo(new RectF(0, height - 2 * bottomLeftRadius - indicatorLayoutHeight, bottomLeftRadius * 2, height - indicatorLayoutHeight), 90, 90);
        path.close();
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
    }

    /**
     * 初始化Views等UI
     */
    private void initUI(final Context context) {
//    if (imageUrls == null || imageUrls.size() == 0)
//      return;
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        LinearLayout dotLayout = findViewById(R.id.dot_layout);
        dotLayout.removeAllViews();
        if (dotViewsList != null) {
            dotViewsList.clear();
        }
        if (imageViewsList != null) {
            imageViewsList.clear();
        }
        // 热点个数与图片特殊相等
        for (int i = 0; i < 5; i++) {
            ImageView view = new ImageView(context);
            view.setScaleType(ScaleType.CENTER_CROP);
            imageViewsList.add(view);
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 8;
            params.rightMargin = 8;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }
        viewPager = findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, final int position) {
            ImageView imageView = imageViewsList.get(position);
            TLog.info("banner position:%s", position);
            switch (position) {
                case 0:
                    imageView.setBackgroundResource(R.drawable.icon_01021);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.icon_01022);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.drawable.icon_01023);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.drawable.icon_01024);
                    break;
                case 4:
                    imageView.setBackgroundResource(R.drawable.icon_01025);
                    break;
                default:
                    imageView.setBackgroundResource(R.drawable.icon_01021);
                    break;
            }
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxToast.showToast("敬请期待");
                }
            });
            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1: // 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2: // 界面切换中
                    isAutoPlay = true;
                    break;
                case 0: // 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    dotViewsList.get(pos).setBackgroundResource(R.drawable.banner_indicator_selected);
                } else {
                    dotViewsList.get(i).setBackgroundResource(R.drawable.banner_indicator_normal);
                }
            }
        }
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {
        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    //避免可能引起的内存泄漏
    public void shutDownExecutor() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

}