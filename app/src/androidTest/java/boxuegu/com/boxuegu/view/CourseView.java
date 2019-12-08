package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.fragment.AdBannerFragment;

public class CourseView{
    private View view;
    private AppCompatActivity context;
    public CourseView(AppCompatActivity context){
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_view_course,null);
        initView();
    }
    private void initView(){
        FrameLayout fl_adbanner=view.findViewById(R.id.fl_adbanner);
        List<AdBannerFragment> fragmentList=new ArrayList<AdBannerFragment>();
        for (int i=0;i<3;i++){
            Bundle bundle=new Bundle();
            bundle.putString("ad","banner_"+(i+1));
            AdBannerFragment fragment=AdBannerFragment.newInstance(bundle);
            fragmentList.add(fragment);
        }
        MyAdBanner myAdBanner=new MyAdBanner(context,fragmentList);
        fl_adbanner.addView(myAdBanner.getView());
    }
    public View getView() {
        return view;
    }
}
