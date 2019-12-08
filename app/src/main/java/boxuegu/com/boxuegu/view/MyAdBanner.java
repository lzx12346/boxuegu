package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.fragment.AdBannerFragment;

public class MyAdBanner {

    private View view;
    private AppCompatActivity context;
    private int count;
    private ViewPager vp_advertBanner;
    private LinearLayout ll_dot;
    private ImageView[] dots;
    private int dotIndex;//当前小圆点序号
    private int index;//轮播的当前图片的序号
    private int preDotIndex;//前一个蓝色小圆点
    private List<AdBannerFragment> fragments;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {//这里是主线程，修改UI，切换页面
            super.handleMessage(msg);
            if(msg.what==1){
                index++;
                vp_advertBanner.setCurrentItem(index);
                autoPlay();
            }
        }
    };

    public MyAdBanner(AppCompatActivity context, List<AdBannerFragment> fragmentList) {
        this.count=fragmentList.size();
        this.fragments=fragmentList;
        //为广告集合添加两张缓冲图片
        Bundle bundle=new Bundle();
        bundle.putString("ad","banner_3");
        AdBannerFragment fragment=AdBannerFragment.newInstance(bundle);
        fragments.add(0,fragment);

        bundle=new Bundle();
        bundle.putString("ad","banner_1");
        fragment=AdBannerFragment.newInstance(bundle);
        fragments.add(fragment);

        this.context = context;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_adbanner,null);
        initView();

    }

    private void initView(){
        vp_advertBanner=view.findViewById(R.id.vp_advertBanner);
        ll_dot=view.findViewById(R.id.ll_dot);
        ll_dot.setGravity(Gravity.CENTER);
//        ll_dot.setPadding(0,10,0,10);

        AdBannerAdapter adapter=new AdBannerAdapter(context.getSupportFragmentManager(),fragments);
        vp_advertBanner.setAdapter(adapter);


        vp_advertBanner.setOffscreenPageLimit(1);
        index=1;
        vp_advertBanner.setCurrentItem(1);//将viewpage的指针定位

        dotIndex=0;
        preDotIndex=0;

        //为viewpager设置监听用户侧滑事件，当用户侧滑时执行里面的代码
        vp_advertBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {//手按下去在侧滑移动中，该方法会在侧滑过程中多次执行

            }

            @Override
            public void onPageSelected(int i) {//表示侧滑已经完成，页面已经侧滑超过50%，到了新的页面
                if(i==count+1){
                    index=1;
                    dotIndex=0;
                    vp_advertBanner.setCurrentItem(index);
                }else if(i==0){
                    index=count;
                    dotIndex=count-1;
                    vp_advertBanner.setCurrentItem(index);//强制将当前页面指针变为index
                }else {
                    index=i;
                    dotIndex=i-1;
                }
                setCurrentDot();
            }

            @Override
            public void onPageScrollStateChanged(int i) {//侧滑过程中会多次执行，传入的参数是当前侧滑状态，例如手已经松开，侧滑已经完成

            }
        });

        initDot();
        setCurrentDot();
        autoPlay();
    }

    private void autoPlay(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }.start();
    }

    private void initDot(){
        dots=new ImageView[count];
        for(int i=0;i<count;i++){
            dots[i]=new ImageView(context);
            dots[i].setImageResource(R.drawable.indicator_off);
            dots[i].setPadding(5,0,5,5);
            ll_dot.addView(dots[i]);
        }
    }

    private void setCurrentDot(){
        dots[preDotIndex].setImageResource(R.drawable.indicator_off);
        dots[dotIndex].setImageResource(R.drawable.indicator_on);
        preDotIndex=dotIndex;
    }

    public View getView() {
        return view;
    }


    class AdBannerAdapter extends FragmentStatePagerAdapter {
        private List<AdBannerFragment> list;

        public AdBannerAdapter(FragmentManager fm, List<AdBannerFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list == null) return 0;
            return list.size();
        }

        @Override
        public Fragment getItem(int i) {
            if (list == null) return null;
            return list.get(i);
        }
    }
}
