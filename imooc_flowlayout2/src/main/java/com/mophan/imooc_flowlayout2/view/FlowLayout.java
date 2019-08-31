package com.mophan.imooc_flowlayout2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mophan.imooc_flowlayout2.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Create by CMF on 2019/8/29.
 */
public class FlowLayout extends ViewGroup {

    private List<List<View>> mAllView = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();

    private static final int[] LL=new int[]{android.R.attr.maxLines};
    private  int mMaxLines;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a=context.obtainStyledAttributes(attrs,LL);

        mMaxLines = a.getInt(0, Integer.MAX_VALUE);
        a.recycle();

        Log.d("hyman","maxLine = "+mMaxLines);

    }

    /**
     * 1.flowlayout
     * 宽度：一定是确定的。
     * 高度：wrapcontent,exactly,unspe
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mAllView.clear();
        mLineHeight.clear();

        // widthMeasureSpec
        // 建议宽度 + model
        // 1.300dp + exactly
        // 2.parent width , at_most 建议值，不能超过
        // 3.  + unspecified
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modelHeight = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;

        int cCount = getChildCount();

        List<View> lineViews = new ArrayList<>();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            if(child.getVisibility()== View.GONE){
                continue;
            }
            //child 也要确定宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int cWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int cHeight = child.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;

            if (lineWidth + cWidth > sizeWidth-(getPaddingLeft()+getPaddingRight())) {
                //换行处理
                height += lineHeight;

                mLineHeight.add(lineHeight);
                mAllView.add((lineViews));
                lineViews = new ArrayList<>();
                lineViews.add(child);

                //重置
                lineWidth = cWidth;
                lineHeight = cHeight;

            } else {
                //未换行
                lineWidth += cWidth;
                lineHeight = Math.max(lineHeight, cHeight);
                lineViews.add(child);
            }

            if (i == cCount - 1) {
                height += lineHeight;
                mLineHeight.add(lineHeight);
                mAllView.add(lineViews);
            }
        }

        //maxLines 校正
        if(mMaxLines<mLineHeight.size()){
            height=getMaxLinesHeight();
        }

        //可以前移优化
        if (modelHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else if (modelHeight == MeasureSpec.AT_MOST) {
            height = Math.min(sizeHeight, height);
            height=height+getPaddingTop()+getPaddingBottom();
        }else if(modelHeight==MeasureSpec.UNSPECIFIED){
            height=height+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(sizeWidth, height);
    }

    private int getMaxLinesHeight() {

        int height=0;
        for (int i=0;i<mMaxLines;i++){
            height+=mLineHeight.get(i);
        }
        return  height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //摆放 view

        int left = getPaddingLeft();
        int top =getPaddingTop();

        int lineNums = mAllView.size();
        for (int i = 0; i < lineNums; i++) {
            List<View> lineViews = mAllView.get(i);
            int lineHeight = mLineHeight.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }

            left=getPaddingLeft();
            top+=lineHeight;
        }
    }

    // 未添加layoutParam
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    // inflater
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    //addView
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    // addView
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof  MarginLayoutParams;
    }
}
