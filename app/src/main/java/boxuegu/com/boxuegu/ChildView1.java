package boxuegu.com.boxuegu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import boxuegu.com.boxuegu.R;

public class ChildView1 {
    private View view;
    private Activity context;
    private TextView tv_title_sy;
    public ChildView1(Activity context,String title){
        LayoutInflater layoutInflater=LayoutInflater.from((context));
        view=layoutInflater.inflate(R.layout.layout,null);
        tv_title_sy=view.findViewById(R.id.tv_title_sy);
        if (title.equals(null)){
            tv_title_sy.setVisibility(View.GONE);
        }else if (title.equals("博学谷习题")){
            tv_title_sy.setText("博学谷习题");
        }else if (title.equals("博学谷课程")){
            tv_title_sy.setText("博学谷课程");
        }
    }
    public View getView(){
        return view;
    }
}
