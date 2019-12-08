package boxuegu.com.boxuegu;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.List;

import boxuegu.com.boxuegu.adapter.VideoListAdapter;
import boxuegu.com.boxuegu.bean.VideoListBean;
import boxuegu.com.boxuegu.utils.DBAccess;
import boxuegu.com.boxuegu.utils.ReadWriteSP;
import boxuegu.com.boxuegu.view.TitleBar;

public class VideoPlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         setContentView(R.layout.activity_video_play);
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
         String path=getIntent().getStringExtra("videoPath");
         VideoView vv_paly=findViewById(R.id.vv_play);
          if(path.equals("a")){
            String uri="android.resource://"+getPackageName()+"/"+R.raw.video11;
            vv_paly.setVideoPath(uri);
            vv_paly.start();
        }
    }
}