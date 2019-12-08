package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.adapter.ExercisesAdapter;
import boxuegu.com.boxuegu.bean.ExercisesBean;

public class ExercisesView{
    private View view;
    private List<ExercisesBean> dataSoure;
    private int bid[]={R.drawable.exercises_bg_1,R.drawable.exercises_bg_2,R.drawable.exercises_bg_3,R.drawable.exercises_bg_4};

    public ExercisesView(final Activity context){
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_view_exercies,null);

        newData();
        ExercisesAdapter adapter=new ExercisesAdapter(dataSoure,context);
        ListView listView=view.findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
    }

    private void newData(){//为dataSoure添加数据
        dataSoure=new ArrayList<ExercisesBean>();
        for (int i=0;i<20;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.setId(i+1);
            bean.setTitle("第"+(i+1)+"章");
            bean.setContent("共计5题");
            bean.setBackground(bid[i%4]);
            dataSoure.add(bean);
        }
    }

    public View getView() {
        return view;
    }
}
