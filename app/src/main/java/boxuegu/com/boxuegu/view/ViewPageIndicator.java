package boxuegu.com.boxuegu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import boxuegu.com.boxuegu.R;

public class ViewPageIndicator extends LinearLayout {//自定义控件：图片轮播上面的三个小圆点
    private int mCount;
    private int mIndex;
    private Context context;
    private ImageView[] iv=null;

    public ViewPageIndicator(Context context) {
        super(context,null);
        this.context = context;
    }

    public ViewPageIndicator(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.context=context;
        setGravity(Gravity.CENTER);
    }

    public void setCurrentPosition(int currentIndex){//设置当前三个小圆点的状态
        mIndex=currentIndex;
        for(int i=0;i<mCount;i++){
            if(mIndex==i){
                iv[i].setImageResource(R.drawable.indicator_on);//如果i小圆点为当前小圆点，则更换蓝色图片
            }else {
                iv[i].setImageResource(R.drawable.indicator_off);//如果i小圆点不为当前小圆点，则更换灰色图片
            }
        }

    }

    public void setCount(int count){
        this.mCount=count;
        this.iv=new ImageView[count];
        for(int i=0;i<mCount;i++){
            iv[i].setPadding(5,0,5,0);//边距左边5，右边5
            addView(iv[i]);
        }
    }
}
