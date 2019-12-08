package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.app.ListActivity;
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
import java.util.logging.LogRecord;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.fragment.AdBannerFragment;

public class MyAdBanner {
    private View view;
    private AppCompatActivity context;
    private int count;
    private ViewPager vp_advertBanner;
    private LinearLayout ll_dot;
    private ImageView[] dots;
    private int dotIndex;
    private int index;
    private int preDotIndex;
    private List<AdBannerFragment> fragments;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((msg.what==1)){
                index++;
                vp_advertBanner.setCurrentItem(index);
                autoPlay();
            }
        }
    };

//    public MyAdBanner(Activity context){
//        this.context=context;
//        LayoutInflater layoutInflater=LayoutInflater.from(context);
//        view=layoutInflater.inflate(R.layout.main_adbanner,null);
//    }

    public MyAdBanner(AppCompatActivity context, List<AdBannerFragment> fragmentList){
        this.count=fragmentList.size();
        this.fragments=fragmentList;
        //
        Bundle bundle=new Bundle();
        bundle.putString("ab","banner_3");
        AdBannerFragment fragment=AdBannerFragment.newInstance(bundle);
        fragments.add(0,fragment);
        bundle=new Bundle();
        bundle.putString("ab","banner_1");
        fragment=AdBannerFragment.newInstance(bundle);
        fragments.add(0,fragment);
        this.context=context;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_adbanner,null);
        initView();
    }

    private void initView(){
        vp_advertBanner=view.findViewById(R.id.vp_advertBanner);
        ll_dot=view.findViewById(R.id.ll_dot);
        ll_dot.setGravity(Gravity.CENTER);
        //ll_dot.setPadding(0,10,0,10);
        AdBannerAdapter adapter=new AdBannerAdapter(context.getSupportFragmentManager(),fragments);
        vp_advertBanner.setAdapter(adapter);
        vp_advertBanner.setOffscreenPageLimit(1);
        index=1;
        vp_advertBanner.setCurrentItem(1,false);
        dotIndex=0;
        preDotIndex=0;
        //
        vp_advertBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                if ((i==count+1)){
                    index=1;
                    dotIndex=0;
                    vp_advertBanner.setCurrentItem(index,false);
                }else if(i==0){
                    index=count;
                    dotIndex=count-1;
                    vp_advertBanner.setCurrentItem(index,false);
                }else{
                    index=i;
                    dotIndex=i-1;
                }
                setCurrentDot();
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        initDot();
        setCurrentDot();
        autoPlay();
    }
    private void  autoPlay(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }.start();;
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
    private  void setCurrentDot(){
        dots[preDotIndex].setImageResource(R.drawable.indicator_off);
        dots[dotIndex].setImageResource(R.drawable.indicator_on);
        preDotIndex=dotIndex;
    }
    public View getView() {
        return view;
    }
    class AdBannerAdapter extends FragmentStatePagerAdapter {
        private List<AdBannerFragment> list;
        public AdBannerAdapter(FragmentManager fm,List<AdBannerFragment> list){
            super(fm);
            this.list=list;
        }
        @Override
        public int getCount() {
            if ((list==null)) return 0;
            return list.size();
        }
        @Override
        public Fragment getItem(int i) {
            if(list==null) return null;
            return list.get(i);
        }
    }
}
