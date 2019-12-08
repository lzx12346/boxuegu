package boxuegu.com.boxuegu.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

public class ViewPageIndicator extends LinearLayout{//自定义控件
    private int mCount;
    private int mIndex;
    private Context context;
    public ViewPageIndicator(Context context){
        super(context);
        this.context=context;
        setGravity(Gravity.CENTER);
    }
    public void setCurrentPosition(int currentIndex){//设置小圆点的状态
        mIndex=currentIndex;
    }
}
