package boxuegu.com.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "bxg.db", null, 1);
        //该构造方法的作用就是创建数据库
        //name:是数据库文件名，存放在手机设备的app安装目录里面
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists videoplaylist (" +
                "_id integer primary key autoincrement," +
                "userName varchar," +
                "id int," +
                "videoId int," +
                "videoPath varchar," +
                "title varchar," +
                "videoTitle varchar" +
                ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
