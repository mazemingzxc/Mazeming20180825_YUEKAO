package com.example.lenovo.mazeming20180825_yuekao.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Frawlayout extends ViewGroup {
    public Frawlayout(Context context) {
        super(context);
    }

    public Frawlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Frawlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addchild(View view){
        if(view!=null){
            addView(view);
            invalidate();
            postInvalidate();

        }
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //当前控件最终需要的高度
        int measureWidth = 0;
        int measureHeight = 0;

        //获取侧栏模式的大小和宽度
        int measureWidthsize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightsize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthmode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightmode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

             MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

             int cwidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
             int cheight = child.getMeasuredWidth()+layoutParams.topMargin+layoutParams.bottomMargin;


          if(measureWidth+cwidth>this.getMeasuredWidth()){
              measureWidth=Math.max(measureWidth,cwidth);
              measureHeight+=cheight;
          }else {
              measureHeight=Math.max(measureHeight,cheight);
              measureWidth+=cwidth;
          }

          //把测量的子view设置给当前控件
            setMeasuredDimension(measureWidthmode==MeasureSpec.EXACTLY ? measureWidthsize:measureWidth,
            measureHeightmode==MeasureSpec.EXACTLY? measureHeightsize:measureHeight
            );

        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int childCount = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int left = 0;
        int top = 0;

        for (int a = 0; a < childCount; a++) {
            View child = getChildAt(a);

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            int cwidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int cheight = child.getMeasuredWidth()+layoutParams.topMargin+layoutParams.bottomMargin;



            //换行
            if(cwidth+lineWidth>getMeasuredWidth()){

                left=0;
                top =lineHeight;

                child.layout(left+layoutParams.leftMargin,top+layoutParams.topMargin,
                       left+layoutParams.leftMargin+child.getMeasuredWidth(),
                        top+layoutParams.topMargin+child.getMeasuredHeight()
                        );

                left+=layoutParams.leftMargin+child.getMeasuredWidth();

                lineWidth=cwidth;
                lineHeight+=cheight;

            }else {

                lineWidth+=cwidth;
                lineHeight=Math.max(lineHeight,cheight);

                child.layout(left+layoutParams.leftMargin,top+layoutParams.topMargin,
                        left+layoutParams.leftMargin+child.getMeasuredWidth(),
                        top+layoutParams.topMargin+child.getMeasuredHeight()
                );

                left+=layoutParams.leftMargin+child.getMeasuredWidth();


            }


        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return  new MarginLayoutParams(getContext(),attrs);
    }
}
