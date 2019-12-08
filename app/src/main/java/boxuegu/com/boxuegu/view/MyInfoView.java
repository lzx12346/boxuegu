package boxuegu.com.boxuegu.view;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.com.boxuegu.LoginActivity;
import boxuegu.com.boxuegu.MainActivity;
import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.SettingActivity;
import boxuegu.com.boxuegu.VideoPlayHistoryActivity;
import boxuegu.com.boxuegu.utils.ReadWriteSP;

public class MyInfoView{

    private View view;
    private Activity context;
    private ImageView iv_head_icon,iv_history,iv_setting;
    private TextView tv_user_name;

    public void setLoninStatus(String name){
        tv_user_name.setText(name);
    }

    public MyInfoView(final Activity context){
        this.context=context;//表示当前这个子布局界面依附的主界面就是这个参数
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.main_view_myinfo,null);
        //下面可以寻找view里面的控件，设置相关事件代码
        findView();

        iv_head_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginUser();
            }
        });

        iv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否已经登录，如果没有登录，则提示没有登录，请登录，如果已经登录，则跳转到播放历史记录界面
                //因为播放历史记录界面没有设计，则也提示一下
                if(ReadWriteSP.readLoginStatus(context)){
                    //Toast.makeText(context,"因为播放历史记录界面没有设计，则也提示一下",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(context, VideoPlayHistoryActivity.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context,"你还没有登录，请点击上面登录",Toast.LENGTH_LONG).show();
                }
            }
        });

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否已经登录，如果没有登录，则提示没有登录，请登录，如果已经登录，则跳转到设置界面
                if(ReadWriteSP.readLoginStatus(context)){
                    //要进入设置界面,用回跳机制
                    Intent intent=new Intent(context, SettingActivity.class);
                    context.startActivityForResult(intent,666);
                }else{
                    Toast.makeText(context,"你还没有登录，请点击上面登录",Toast.LENGTH_LONG).show();
                }
            }
        });

        tv_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginUser();
            }
        });


    }

    private void setLoginUser(){
        //检查是否已经登录，如果没有登录，则跳转到登录界面，如果已经登录，则跳转个人资料界面
        if(ReadWriteSP.readLoginStatus(context)){
            Toast.makeText(context,"个人资料界面没有设计，提示一下",Toast.LENGTH_LONG).show();
        }else{
            Intent intent=new Intent(context, LoginActivity.class);
            context.startActivityForResult(intent,8888);
        }
    }

    private void findView(){
        iv_head_icon=view.findViewById(R.id.iv_head_icon);
        iv_history=view.findViewById(R.id.iv_history);
        iv_setting=view.findViewById(R.id.iv_setting);
        tv_user_name=view.findViewById(R.id.tv_user_name);
        if(ReadWriteSP.readLoginStatus(context)){
            tv_user_name.setText(ReadWriteSP.getLoginUserName(context));
        }else {
            tv_user_name.setText("点击登录");
        }
    }

    public View getView() {
        return view;
    }

}
