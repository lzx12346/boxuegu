package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.adapter.CourseAdapter;
import boxuegu.com.boxuegu.bean.CourseBean;
import boxuegu.com.boxuegu.fragment.AdBannerFragment;

public class CourseView{
    private View view;
    private AppCompatActivity context;

    public CourseView(AppCompatActivity context){
        this.context=context;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_view_course,null);
        //下面可以寻找view里面的控件，设置相关事件代码
        initView();

    }

    private void initView(){
        FrameLayout fl_adbanner=view.findViewById(R.id.fl_adbanner);
        List<AdBannerFragment> fragmentList=new ArrayList<AdBannerFragment>();

        for(int i=0;i<3;i++){
            Bundle bundle=new Bundle();
            bundle.putString("ad","banner_"+(i+1));
            AdBannerFragment fragment=AdBannerFragment.newInstance(bundle);
            fragmentList.add(fragment);
        }

        MyAdBanner myAdBanner=new MyAdBanner(context,fragmentList);
        fl_adbanner.addView(myAdBanner.getView());

        ListView lv_list=view.findViewById(R.id.lv_list);

        List<CourseBean> beans=new ArrayList<>();

        CourseBean bean=new CourseBean();
        bean.id=1;
        bean.imgId=R.drawable.banner_1;
        bean.imgTitle="123";
        bean.title="第1章";
        bean.intro="Android入门";
        beans.add(bean);

        bean=new CourseBean();
        bean.id=2;
        bean.imgId=R.drawable.banner_2;
        bean.imgTitle="223";
        bean.title="第2章";
        bean.intro="Activity";
        beans.add(bean);

        bean=new CourseBean();
        bean.id=3;
        bean.imgId=R.drawable.banner_3;
        bean.imgTitle="323";
        bean.title="第3章";
        bean.intro="Intent";
        beans.add(bean);

        /*bean=new CourseBean();
        bean.id=4;
        bean.imgId=R.drawable.banner_1;
        bean.imgTitle="423";
        bean.title="第4章";
        beans.add(bean);*/

        CourseAdapter adapter=new CourseAdapter(context,beans);
        lv_list.setAdapter(adapter);


    }

    public View getView() {
        return view;
    }
}

