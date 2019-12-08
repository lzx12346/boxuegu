package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import boxuegu.com.boxuegu.R;

public class TitleBar {
     private View view;
     private TextView tv_main_title,tv_back;
     public TitleBar(final Activity context, String name){
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_title_bar,null);
        tv_back=view.findViewById(R.id.tv_back);
        tv_main_title=view.findViewById(R.id.tv_main_title);
        tv_main_title.setText(name);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();//销毁自己，就是返回
            }
        });
    }
    public void setVisibility(int visibility){//设置该控件是否显示还是隐藏
        view.setVisibility(visibility);
    }
    public void setTitleName(String name){
        tv_main_title.setText(name);
    }
    public View getView() {
        return view;
    }
}
