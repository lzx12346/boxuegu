package boxuegu.com.boxuegu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



import java.util.Timer;
import java.util.TimerTask;
import boxuegu.com.boxuegu.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView textView=(TextView) findViewById(R.id.tv_version);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PackageInfo packageInfo=null;
        PackageManager packageManager =getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo("com.boxuegu", 0);
            textView.setText("V "+packageInfo.versionName);
        }catch (Exception e){
            textView.setText("V");
            e.printStackTrace();
        }
        TimerTask task3=new TimerTask() {
            @Override
            public void run() {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
             SplashActivity.this.finish();
            }
        };
        Timer timer=new Timer();//定时器
        timer.schedule(task3,3000);
    }
}
