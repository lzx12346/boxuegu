package boxuegu.com.boxuegu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ReadWriteSP {
    public static void clearLoginStatus(Activity context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }
    public static boolean readLoginStatus(Activity context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin",false);
    }
    public static String getLoginUserName(Activity context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("loginUserName","");
    }
}
