package boxuegu.com.boxuegu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import boxuegu.com.boxuegu.utils.ReadWriteSP;
import boxuegu.com.boxuegu.view.TitleBar;

public class SettingActivity extends AppCompatActivity {
    private LinearLayout ll_title_bar,ll_exit;
    private ImageView iv_modify_psw,iv_security_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        TitleBar titleBar=new TitleBar(this,"设置");
        ll_title_bar.addView(titleBar.getView());
    }
    private void initView(){
         ll_title_bar=findViewById(R.id.ll_title_bar);
         ll_exit=findViewById(R.id.ll_exit);
         iv_modify_psw=findViewById(R.id.iv_modify_psw);
         iv_security_setting=findViewById(R.id.iv_security_setting);
         ll_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出登录，不是退出app
                //1、进入我子界面,销毁当前设置界面，
                //2、sp文件中的登录状态要清除，这一步在这里完成
                //3、我的子界面图标下面的文字要改为“点击登录”，这一步在主界面接收回跳的事件代码中执行
                setResult(6666);//回跳回去到主界面
                ReadWriteSP.clearLoginStatus(SettingActivity.this);
                SettingActivity.this.finish();
            }
        });
        iv_modify_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(SettingActivity.this,"密码修改界面没有设计，提示一下",Toast.LENGTH_LONG).show();
            }
        });
        iv_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"密保设置界面没有设计，提示一下",Toast.LENGTH_LONG).show();
            }
        });
    }
}
