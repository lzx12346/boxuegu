package boxuegu.com.boxuegu.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import boxuegu.com.boxuegu.R;
import boxuegu.com.boxuegu.VideoListActivity;
import boxuegu.com.boxuegu.bean.CourseBean;

public class CourseAdapter extends BaseAdapter {
     private AppCompatActivity context;
     private List<CourseBean> beans;

     public CourseAdapter(AppCompatActivity context, List<CourseBean> beans) {
        this.context=context;
        this.beans=beans;
    }

    @Override
     public int getCount() {//返回的值是listview中的行数
        return (int)Math.rint(beans.size()/2.0);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.course_list_item,null);
            viewHolder.iv_left_img=convertView.findViewById(R.id.iv_left_img);
            viewHolder.tv_left_img_title=convertView.findViewById(R.id.tv_left_img_title);
            viewHolder.tv_left_title=convertView.findViewById(R.id.tv_left_title);
            viewHolder.iv_right_img=convertView.findViewById(R.id.iv_right_img);
            viewHolder.tv_right_img_title=convertView.findViewById(R.id.tv_right_img_title);
            viewHolder.tv_right_title=convertView.findViewById(R.id.tv_right_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.iv_left_img.setImageResource(beans.get(position*2).imgId);
        viewHolder.tv_left_img_title.setText(beans.get(position*2).imgTitle);
        viewHolder.tv_left_title.setText(beans.get(position*2).title);
        if((position*2+1)<beans.size()){
            viewHolder.iv_right_img.setVisibility(View.VISIBLE);
            viewHolder.tv_right_img_title.setVisibility(View.VISIBLE);
            viewHolder.tv_right_title.setVisibility(View.VISIBLE);
            viewHolder.iv_right_img.setImageResource(beans.get(position*2+1).imgId);
            viewHolder.tv_right_img_title.setText(beans.get(position*2+1).imgTitle);
            viewHolder.tv_right_title.setText(beans.get(position*2+1).title);
        }
        else{
            viewHolder.iv_right_img.setVisibility(View.GONE);
            viewHolder.tv_right_img_title.setVisibility(View.GONE);
            viewHolder.tv_right_title.setVisibility(View.GONE);
        }
        viewHolder.iv_left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击左边图片，打开新的acitvity
                Intent intent=new Intent(context, VideoListActivity.class);
                intent.putExtra("id",beans.get(2*position).id);
                intent.putExtra("title",beans.get(2*position).title);
                intent.putExtra("intro",beans.get(2*position).intro);
                context.startActivity(intent);
            }
        });
        viewHolder.iv_right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击右边图片,打开新的acitvity
              Intent intent=new Intent(context, VideoListActivity.class);
              intent.putExtra("id",beans.get(2*position+1).id);
              intent.putExtra("title",beans.get(2*position+1).title);
              intent.putExtra("intro",beans.get(2*position+1).intro);
              context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        public ImageView iv_left_img;
        public TextView tv_left_img_title;
        public TextView tv_left_title;
        public ImageView iv_right_img;
        public TextView tv_right_img_title;
        public TextView tv_right_title;
    }
}

