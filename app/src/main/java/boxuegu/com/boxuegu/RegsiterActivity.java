package boxuegu.com.boxuegu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.utils.MD5Utils;

public class RegsiterActivity extends AppCompatActivity {
    private TextView tv_back;
    private Button btn_register;
    private TextView tv_main_title;
    private EditText et_user_name, et_psw, et_psw_again;
    private String userName, psw, pswAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegsiterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_user_name.getText().toString();
                psw = et_psw.getText().toString();
                pswAgain = et_psw_again.getText().toString();
                if (userName.equals("")) {
                    Toast.makeText(RegsiterActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                //考虑用户名已经存在
                //第一步：到sp中读取是否有这个用户名，如果有则提示，并return
                SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
                String pswd = sharedPreferences.getString(userName, "");
                //书上这么认为：sp文件中存在该用户名和密码，存在该用户名但是没有密码，根据不存在该用户名
                if (pswd != null && pswd.length() != 0) {
//                    if(!TextUtils.isEmpty(pswd))
                    //区别pswd.equals(""):TextUtils.isEmpty(pswd)为假相当于pswd！=null，同时pswd的长度不为0
                    //提示，
                    Toast.makeText(RegsiterActivity.this, "该用户已经存在", Toast.LENGTH_LONG).show();
                    return;
                }
                if (psw.equals("")) {
                    Toast.makeText(RegsiterActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pswAgain.equals("")) {
                    Toast.makeText(RegsiterActivity.this, "请再输入一次密码", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!psw.equals(pswAgain)) {
                    Toast.makeText(RegsiterActivity.this, "两次密码不相同", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegsiterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                String md5Psw = MD5Utils.md5(psw);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userName, md5Psw);
                editor.commit();
                Intent data = new Intent();
                data.putExtra("userName", userName);
                data.putExtra("psw", psw);
                setResult(999, data);
                RegsiterActivity.this.finish();
            }
        });
    }
    private void init() {
        tv_back = findViewById(R.id.tv_back);
        btn_register = findViewById(R.id.btn_register);
        tv_main_title = findViewById(R.id.tv_main_title);
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_psw_again);
    }
}
