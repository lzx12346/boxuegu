package boxuegu.com.boxuegu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import boxuegu.com.boxuegu.ExercisesDetailActivity;
import boxuegu.com.boxuegu.bean.ExercisesBean;
import boxuegu.com.boxuegu.R;

public class ExercisesAdapter extends BaseAdapter {
    private List<ExercisesBean> dataSoure;
    private Context context;

    public ExercisesAdapter(List<ExercisesBean> dataSoure,Context context) {
        this.dataSoure=dataSoure;
        this.context=context;
    }

    @Override
    public int getCount() {
        if (dataSoure==null)  return 0;
        return dataSoure.size();
    }

    @Override
    public Object getItem(int position) {
        if (dataSoure==null)  return null;
        return dataSoure.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //作用：将数据源的position序号的数据添加到listview的一行中，这一行的控件就是返回值View
        final ExercisesBean bean=dataSoure.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.layout_line,null);
            TextView tv_content,tv_title,tv_id;
            tv_content=convertView.findViewById(R.id.tv_content);
            tv_title=convertView.findViewById(R.id.tv_title);
            tv_id=convertView.findViewById(R.id.tv_id);
            viewHolder=new ViewHolder();
            viewHolder.tv_id=tv_id;
            viewHolder.tv_title=tv_title;
            viewHolder.tv_content=tv_content;
            convertView.setTag(viewHolder);//压入新生成的行view控件到缓冲区，当下次滚走该行时，再次利用
        }else{
            viewHolder=(ViewHolder) convertView.getTag();//取出缓冲区中滚走的行view控件
        }
        //为行view控件中的元素设置对应的内容
        viewHolder.tv_id.setText(bean.getId()+"");
        viewHolder.tv_title.setText(bean.getTitle());
        viewHolder.tv_content.setText(bean.getContent());
        viewHolder.tv_id.setBackgroundResource(bean.getBackground());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"即将打开第"+bean.getId()+"章习题内容",Toast.LENGTH_LONG).show();
                //下面要跳转到习题内容界面，同时要将章节标题带过去
                Intent intent=new Intent(context, ExercisesDetailActivity.class);
                intent.putExtra("id",bean.getId());
                intent.putExtra("title",bean.getTitle());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHolder{
        public TextView tv_content,tv_title,tv_id;
    }
}