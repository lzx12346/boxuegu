package boxuegu.com.boxuegu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;

import boxuegu.com.boxuegu.adapter.ExercisesDetailAdapter;
import boxuegu.com.boxuegu.bean.ExercisesDetailBean;
import boxuegu.com.boxuegu.utils.AnalysisUtils;
import boxuegu.com.boxuegu.view.TitleBar;

public class ExercisesDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",-1);
        String title="";
        if(id!=-1){
            title=intent.getStringExtra("title");
        }
        LinearLayout exercises_detail_title_bar;
        exercises_detail_title_bar=findViewById(R.id.exercises_detail_title_bar);
        TitleBar titleBar=new TitleBar(this,title);
        exercises_detail_title_bar.addView(titleBar.getView());
        try{
            InputStream inputStream=getResources().getAssets().open("chapter"+id+".xml");
            List<ExercisesDetailBean> beans=AnalysisUtils.getExercisesInfos(inputStream);
            ExercisesDetailAdapter adapter=new ExercisesDetailAdapter(beans,this);
            ListView listView;
            listView=findViewById(R.id.lv_exercises_detail);
            listView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this,"题目文件打开失败或解析失败！",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
