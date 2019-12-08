package boxuegu.com.boxuegu.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import boxuegu.com.boxuegu.R;

public class BottomBar {

    private View view;
    private AppCompatActivity context;
    private LinearLayout top_title_bar;
    private FrameLayout main_boy;

    private TitleBar titleBar;
    private CourseView courseView;
    private ExercisesView exercisesView;
    private MyInfoView myInfoView;

    private ImageView imageViews[]=new ImageView[3];//保存底部导航栏中三个图片按钮对象,里面内容暂时为空对象，在代码赋值
    private TextView textViews[]=new TextView[3];//保存底部导航栏中三个文字对象,里面内容暂时为空对象，在代码赋值
    private View bodyViews[]=new View[3];//保存中间三个页面view对象,里面内容暂时为空对象，在代码赋值
    private int unSelected[]={R.drawable.main_course_icon,R.drawable.main_exercises_icon,R.drawable.main_my_icon};
    private int selected[]={R.drawable.main_course_icon_selected,R.drawable.main_exercises_icon_selected,R.drawable.main_my_icon_selected};

    private int currentSelected;//用户选中某个选项之前选中的项目

    public void setLoginStatus(String name){
        myInfoView.setLoninStatus(name);
    }

    private void setDefaultView(){
        //显示加载默认的标题栏和中间部分
        titleBar=new TitleBar(context,"博学谷课程");
        top_title_bar.addView(titleBar.getView());

        if(bodyViews[0]==null){
            courseView=new CourseView(context);
            bodyViews[0]=courseView.getView();
            main_boy.addView(bodyViews[0]);
        }

        //要将底部导航栏的第一个按钮设置为选中
        imageViews[0].setImageResource(R.drawable.main_course_icon_selected);//设置课程的图片为选中图片
        textViews[0].setTextColor(Color.parseColor("#0097F7"));  //设置底部导航栏的选中文字颜色
        currentSelected=0;//表示课程选中状态

    }



    private void findBottomView(){
        //下面可以寻找导航栏view里面的控件，设置相关事件代码
        imageViews[0]=view.findViewById(R.id.bottom_bar_image_course);
        imageViews[1]=view.findViewById(R.id.bottom_bar_image_exercises);
        imageViews[2]=view.findViewById(R.id.bottom_bar_image_myinfo);

        textViews[0]=view.findViewById(R.id.bottom_bar_text_course);
        textViews[1]=view.findViewById(R.id.bottom_bar_text_exercises);
        textViews[2]=view.findViewById(R.id.bottom_bar_text_myinfo);
    }

    private void switchPage(int newPage){//用户选择底部导航栏按钮时，调用该方法来完成切换工作
        //干如下几件事
        //0、标题栏要更换：
        //          如果原来已经存在标题栏，则更换标题内容并显示，
        //          如果原来标题栏不存在，则生成标题栏，添加到主界面适当位置并显示
//                  如果当前情况不需要标题栏，则隐藏
        switch (newPage){
            case 0:{//表示用户选中了课程
                if(titleBar==null){
                    titleBar=new TitleBar(context,"博学谷课程");
                    top_title_bar.addView(titleBar.getView());
                }else {
                    titleBar.setTitleName("博学谷课程");
                    titleBar.getView().setVisibility(View.VISIBLE);
                }
                break;
            }
            case 1:{//表示用户选中了习题
                if(titleBar==null){
                    titleBar=new TitleBar(context,"博学谷习题");
                    top_title_bar.addView(titleBar.getView());
                }else {
                    titleBar.setTitleName("博学谷习题");
                    titleBar.getView().setVisibility(View.VISIBLE);
                }
                break;
            }
            case 2:{//表示用户选中了我
                if(titleBar!=null){ //如果原来存在标题栏，则隐藏就行
                    titleBar.getView().setVisibility(View.GONE);
                }
                break;
            }
        }
        //1、导航栏前面一个选中的设置成未选中
        //2、设置当前的为选中状态
        //3、前面一个选中显示中间页面设置为隐藏
        //4、把当前的页面设置为显示
        imageViews[currentSelected].setImageResource(unSelected[currentSelected]);
        textViews[currentSelected].setTextColor(Color.parseColor("#666666"));
        //上面两行是设置前面选中的为未选中

        imageViews[newPage].setImageResource(selected[newPage]);
        textViews[newPage].setTextColor(Color.parseColor("#0097F7"));
        //上面两行是设置当前选中的为选中

        bodyViews[currentSelected].setVisibility(View.GONE);
        createBodyView(newPage);
        bodyViews[newPage].setVisibility(View.VISIBLE);

        //5、将currentSelected状态的值设置为newPage
        currentSelected=newPage;
    }

    private void createBodyView(int newPage){
        switch (newPage){
            case 0:{
                if (bodyViews[0]==null){
                    courseView=new CourseView(context);
                    bodyViews[0]=courseView.getView();
                    main_boy.addView(bodyViews[0]);
                }
                break;
            }
            case 1:{
                if (bodyViews[1]==null){
                    exercisesView=new ExercisesView(context);
                    bodyViews[1]=exercisesView.getView();
                    main_boy.addView(bodyViews[1]);
                }
                break;
            }
            case 2:{
                if (bodyViews[2]==null){
                    myInfoView=new MyInfoView(context);
                    bodyViews[2]=myInfoView.getView();
                    main_boy.addView(bodyViews[2]);
                }
                break;
            }
        }
    }

    public BottomBar(final AppCompatActivity context){
        this.context=context;

        top_title_bar=context.findViewById(R.id.top_title_bar);
        main_boy=context.findViewById(R.id.main_boy);

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.bottom_bar,null);

        //下面可以寻找导航栏view里面的控件
        findBottomView();

        //显示加载默认的标题栏和中间部分
        setDefaultView();

        //new出三个中间的页面，添加到中间位置，全部设置为隐藏，课程设置为显示

        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPage(0);
            }
        });

        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPage(1);
            }
        });

        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPage(2);
            }
        });
    }

    public View getView() {
        return view;
    }
}