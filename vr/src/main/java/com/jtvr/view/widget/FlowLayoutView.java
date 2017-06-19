package com.jtvr.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
//流式布局
public class FlowLayoutView extends ViewGroup {
    public FlowLayoutView(Context context) {
        super(context);
    }

    public FlowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 获取当前ViewGroup里所有ChildView的LayoutParams
     * 如果不重写这个方法  返回的是 ViewGroup的参数
     * new MarginLayoutParams(getContext(),attrs)
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //测量当前容器的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        //记录一行的宽和高
        int lineWidth = 0;
        int lineHeight = 0;
        //记录容器的宽和高 内容包裹的
        int width = 0;
        int height = 0;
        //得到子元素的数量
        int cCount = getChildCount();
        //遍历子元素
        for (int i = 0; i < cCount; i++) {
            //获取每一个子元素
            View child = getChildAt(i);
            //测量每一个子元素的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获取子元素的属性
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            //获取子元素的宽和高    需要加上外边距
            int childWidth = child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            //判断当前元素是否换行    如果换行需要重新计算
            if (lineWidth + childWidth > getWidth() - getPaddingLeft() - getPaddingRight()) {
                //换行
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;//记录新的一行的宽度
                height += lineHeight;//获取每一行的高度
                lineHeight = childHeight;//新一行的高度
            } else {
                //不换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);//记录子元素中高度为最大的那个
            }
            //判断当前元素是不是最后一个元素
            if (i == cCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

            //确定最终的容器宽和高
            setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                    (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height + getPaddingBottom() + getPaddingTop());
        }
    }

    //用于存储所有子元素(子View)的集合  按行存  里面存具体的View  外面的存一行的View
    List<List<View>> mAllView = new ArrayList<>();
    //存储每一行最高的值
    List<Integer> mLineHeight = new ArrayList<>();

    /**
     * 摆放子View
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //清除数据  当这个方法多次运行的话 保证数据的准确性
        mAllView.clear();
        mLineHeight.clear();
        //记录一行的宽 和 高
        int lineWidth = 0;
        int lineHeight = 0;

        //存储的是一行中所有的View
        List<View> linesView = new ArrayList<>();

        //获取所有子元素的数量
        int cCount = getChildCount();
        //便利所有子元素
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);//获取子元素
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();//获取子元素的属性
            //获取子元素的宽和高    需要加上外边距
            int childWidth = child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //判断是否换行
            if (lineWidth + childWidth > getWidth() - getPaddingLeft() - getPaddingRight()) {
                //换行
                //记录一行中的高和View
                mLineHeight.add(lineHeight);
                mAllView.add(linesView);
                lineHeight = 0;
                lineWidth = 0;
                linesView = new ArrayList<>();//重新置空
            }
            //换行和没换行都执行
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            //记录一行的子元素
            linesView.add(child);
        }
        //处理最后的元素
        //记录最后一行的行高
        mLineHeight.add(lineHeight);
        //记录最后一行的View
        mAllView.add(linesView);

        int left = getPaddingLeft();
        int top = getPaddingTop();

        //有多少行View
        int lineNum = mAllView.size();
        //遍历每一行的View
        for (int i = 0; i < lineNum; i++) {
            //获取每一行的View
            linesView = mAllView.get(i);
            //获取每一行的最大高度
            lineHeight = mLineHeight.get(i);
            //遍历每一行中的View
            for(int j=0;j<linesView.size();j++){
                //获取这个一行中的子View
                View child = linesView.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();//获取子元素的属性

                int lc = left+lp.leftMargin;
                int tc = top+lp.topMargin;
                int rc = lc+child.getMeasuredWidth();
                int bc = tc+child.getMeasuredHeight();

                //确定子View的位置
                child.layout(lc,tc,rc,bc);
                //叠加  前一个VIew的宽度
                left+= child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            }
            //计算下一行  left重置  高度叠加
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

}
