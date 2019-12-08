package boxuegu.com.boxuegu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.utils.MD5Utils;
import boxuegu.com.boxuegu.view.TitleBar;

public class LoginActivity extends AppCompatActivity {
    private TextView tv_register, tv_find_psw;
    private Button btn_login;
    private EditText et_user_name, et_psw;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==888) {
            if (data != null) {
                et_user_name.setText(data.getStringExtra("userName"));
                et_psw.setText(data.getStringExtra("psw"));
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        TitleBar titleBar=new TitleBar(this,"登录");
        LinearLayout top_title_bar=findViewById(R.id.top_title_bar);
        top_title_bar.addView(titleBar.getView());
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegsiterActivity.class);
                startActivityForResult(intent,888);
            }
        });
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(LoginActivity.this,FindPswActivity.class);
                //startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查文本框是否为空
                //检查这个用户是否存在
                //检查密码是否正确
                //提示登录成功、保存登陆状态、跳转到主界面，
                String userName, psw;
                userName = et_user_name.getText().toString();
                psw = et_psw.getText().toString();
                if (userName.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                //考虑用户名不存在
                //第一步：到sp中读取是否有这个用户名，如果没有则提示，并return
                SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
                String pswd = sharedPreferences.getString(userName, "");//如果sp文件不存在这个用户，则返回为空对象
                //sp文件中对用户名有三种情况：有该用户名和密码，没有该用户名，有该用户名，没有密码
                if ((pswd == null || pswd.length() == 0)) {//区别pswd.equals(""):TextUtils.isEmpty(pswd)为假相当于pswd！=null，同时pswd的长度不为0
                    //提示，
                    Toast.makeText(LoginActivity.this, "你输入的用户名错误，该用户不存在", Toast.LENGTH_LONG).show();
                    return;
                }
                if (psw.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                String md5psw= MD5Utils.md5(psw);
                if(!pswd.equals(md5psw)){
                    Toast.makeText(LoginActivity.this, "请输入的密码不正确", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                //保存登录状态p70:80,p71:107
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("isLogin",true);
                editor.putString("loginUserName",userName);
                editor.commit();
                //回跳到主界面
                Intent data=new Intent();
                data.putExtra("userName",userName);
                setResult(9999,data);//执行完该行，接下来执行的并不是前面起跳的那一行的下面。
                LoginActivity.this.finish();
                // 接下来要返回起跳的这个Activity里面的一个方法里面的代码
            }
        });
    }
    private void init() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
    }
}
