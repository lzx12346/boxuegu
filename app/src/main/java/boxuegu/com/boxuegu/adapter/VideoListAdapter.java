package boxuegu.com.boxuegu.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.VideoPlayActivity;
import boxuegu.com.boxuegu.bean.VideoListBean;
import boxuegu.com.boxuegu.utils.DBAccess;
import boxuegu.com.boxuegu.utils.ReadWriteSP;

public class VideoListAdapter extends BaseAdapter {
    private List<VideoListBean> beans;
    private AppCompatActivity context;
    private ViewHolder old;
    public VideoListAdapter(AppCompatActivity context,List<VideoListBean> beans) {
        this.context=context;
        this.beans=beans;
        this.old=null;
    }
    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.video_list_item,null);
            viewHolder.iv_icon=convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_video_title=convertView.findViewById(R.id.tv_video_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.tv_video_title.setText(beans.get(position).videoTitle);
        final ViewHolder vh=viewHolder;
        viewHolder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClick(vh,position);
            }
        });
        viewHolder.tv_video_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClick(vh,position);
            }
        });
        return convertView;
    }
    private void listenClick(ViewHolder current,int position){
        if(old!=null) {
            old.iv_icon.setImageResource(R.drawable.course_bar_icon);
            old.tv_video_title.setTextColor(Color.parseColor("#333333"));
        }
        current.iv_icon.setImageResource(R.drawable.course_intro_icon);
        current.tv_video_title.setTextColor(Color.parseColor("#009958"));
        old=current;
        String path=beans.get(position).videoPath;
        if(beans.get(position).videoPath!=null&&!beans.get(position).videoPath.equals("")){
            if(ReadWriteSP.readLoginStatus(context)){//条件成立表示已经登录，播放记录保存到数据库中
                DBAccess dbAccess=DBAccess.getDBAccessObject(context);
                dbAccess.saveVideoPlayHistory(ReadWriteSP.getLoginUserName(context),beans.get(position));
            }
            Intent intent=new Intent(context, VideoPlayActivity.class);
            intent.putExtra("videoPath",beans.get(position).videoPath);
            context.startActivity(intent);
        }else {
            Toast.makeText(context,"视频不存在",Toast.LENGTH_LONG).show();
        }
    }
    class ViewHolder{
        public  ImageView iv_icon;
        public TextView  tv_video_title;
    }
}