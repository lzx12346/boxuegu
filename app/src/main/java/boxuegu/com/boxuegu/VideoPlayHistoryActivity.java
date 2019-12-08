package boxuegu.com.boxuegu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import boxuegu.com.boxuegu.adapter.VideoListAdapter;
import boxuegu.com.boxuegu.bean.VideoListBean;
import boxuegu.com.boxuegu.utils.DBAccess;
import boxuegu.com.boxuegu.utils.ReadWriteSP;
import boxuegu.com.boxuegu.view.TitleBar;

public class VideoPlayHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play_history);
        initView();
    }
    private void initView(){
        FrameLayout fl_title_bar=findViewById(R.id.fl_title_bar);
        TitleBar titleBar=new TitleBar(this,"播放记录");
        fl_title_bar.addView(titleBar.getView());
        ListView lv_video_list=findViewById(R.id.lv_video_list);
        DBAccess dbAccess=DBAccess.getDBAccessObject(this);
        List<VideoListBean> beans=dbAccess.getVideoPlayHistory(ReadWriteSP.getLoginUserName(this));
        int i=beans.size();
        lv_video_list.setAdapter(new VideoListAdapter(VideoPlayHistoryActivity.this,beans));
    }
}
