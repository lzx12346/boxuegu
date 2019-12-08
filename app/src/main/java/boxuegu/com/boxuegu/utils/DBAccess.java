package boxuegu.com.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxuegu.bean.VideoListBean;
import boxuegu.com.boxuegu.sqlite.SQLiteHelper;

public class DBAccess {
    private SQLiteDatabase readDB;
    private SQLiteDatabase writeDB;
    private static DBAccess dbAccess=null;
    private DBAccess(Context context) {
        SQLiteHelper helper=new SQLiteHelper(context);
        readDB=helper.getReadableDatabase();
        writeDB=helper.getWritableDatabase();
    }
    public static DBAccess getDBAccessObject(Context context){
        if(dbAccess==null)
            dbAccess=new DBAccess(context);
        return dbAccess;
    }
    public void saveVideoPlayHistory(String name, VideoListBean bean){
        if(isvideoPlay(name,bean)){
            delVideoPlay(name,bean);
        }
        ContentValues cv=new ContentValues();
        cv.put("username",name);
        cv.put("id",bean.id);
        cv.put("videoId",bean.videoId);
        cv.put("title",bean.title);
        cv.put("videoTitle",bean.videoTitle);
        cv.put("videoPath",bean.videoPath);
        writeDB.insert("videoplaylist",null,cv);
    }
    public List<VideoListBean> getVideoPlayHistory(String name){
        List<VideoListBean> beans=new ArrayList<>();
        String sql="select * from videoplaylist where userName=?";
        Cursor cursor=readDB.rawQuery(sql,new String[]{name});
        while (cursor.moveToNext()){
            VideoListBean bean=new VideoListBean();
            bean.id=cursor.getInt(cursor.getColumnIndex("id"));
            bean.videoId=cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.title=cursor.getString(cursor.getColumnIndex("title"));
            bean.videoTitle=cursor.getString(cursor.getColumnIndex("videoTitle"));
            bean.videoPath=cursor.getString(cursor.getColumnIndex("videoPath"));
            beans.add(bean);
        }
        return beans;
    }
    public boolean delVideoPlay(String name, VideoListBean bean){
        boolean ret=false;
        int row=writeDB.delete("videoplaylist","userName=? and videoId=?",new String[]{name,bean.videoId+""});
        if(row>=0){
            ret=true;
        }
        return ret;
    }
    public boolean isvideoPlay(String name, VideoListBean bean){
        boolean is=false;
        String sql="select * from videoplaylist where userName=? and videoId=?";
        Cursor cursor=readDB.rawQuery(sql,new String[]{name,bean.videoId+""});
        if(cursor.moveToFirst())
            is=true;
        return is;
    }
}
