package boxuegu.com.boxuegu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import boxuegu.com.boxuegu.adapter.VideoListAdapter;
import boxuegu.com.boxuegu.bean.VideoListBean;
import boxuegu.com.boxuegu.utils.AnalysisUtils;

public class VideoListActivity extends AppCompatActivity {
    private ListView lv_video_list;
    private TextView tv_intro,tv_video,tv_sv_intro;
    private ScrollView sv_intro;
    private int id;
    private String title,intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        title=intent.getStringExtra("title");
        intro=intent.getStringExtra("intro");
        initView();
    }
    private void initView(){
        lv_video_list=findViewById(R.id.lv_video_list);
        tv_intro=findViewById(R.id.tv_intro);
        tv_sv_intro=findViewById(R.id.tv_sv_intro);
        tv_sv_intro.setText(intro+id);
        tv_video=findViewById(R.id.tv_video);
        sv_intro=findViewById(R.id.sv_intro);
        try {
            InputStream data = getResources().getAssets().open("videoListData.json");
            List<VideoListBean> beans = AnalysisUtils.pasarJson(data);
            for(int i=0;i<beans.size();){
                if(beans.get(i).id!=id) {
                    beans.remove(i);
                }else
                    i++;
            }
            VideoListAdapter adapter=new VideoListAdapter(this,beans);
            lv_video_list.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        tv_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_intro.setBackgroundColor(Color.parseColor("#30b4ff"));
                tv_intro.setTextColor(Color.parseColor("#ffffff"));
                tv_video.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_video.setTextColor(Color.parseColor("#000000"));
                sv_intro.setVisibility(View.VISIBLE);
                lv_video_list.setVisibility(View.GONE);
            }
        });
        tv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_intro.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_intro.setTextColor(Color.parseColor("#000000"));
                tv_video.setBackgroundColor(Color.parseColor("#30b4ff"));
                tv_video.setTextColor(Color.parseColor("#ffffff"));
                sv_intro.setVisibility(View.GONE);
                lv_video_list.setVisibility(View.VISIBLE);
            }
        });
    }
}
