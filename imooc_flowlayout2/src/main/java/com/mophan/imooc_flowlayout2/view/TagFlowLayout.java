package com.mophan.imooc_flowlayout2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by CMF on 2019/9/1.
 */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataSetChangedListener {

    private TagAdapter mAdapter;
    private int mMaxSelectCount;

    public void setMaxSelectCount(int count) {
        mMaxSelectCount = count;
    }

    //测量，布局，layout，params
    public TagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(TagAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setOnDataSetChangedListener(this);
        onDataChanged();
    }

    @Override
    public void onDataChanged() {
        removeAllViews();
        TagAdapter adapter = mAdapter;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            View view = adapter.createView(LayoutInflater.from(getContext()), this, i);
            adapter.bindView(view, i);
            addView(view);

            if(view.isSelected()){
                adapter.onItemSelected(view,i);
            }else{
                adapter.onItemUnSelected(view,i);
            }

            bindViewMethod(i, view);
        }
    }

    /**
     * 单选：可以直接选择一个，当选择下一个，上一个选择效果自动取消;
     * 多选：用户需要手动反选。
     *
     * @param position
     * @param view
     */
    private void bindViewMethod(final int position, final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.onItemViewClick(v, position);
                if (mMaxSelectCount <= 0) {
                    return;
                }

                //view 状态，selected
                //特殊case
                if (!v.isSelected()) {
                    //view 没有被选中
                    if (getSelectViewCount() >= mMaxSelectCount) {
                        //单选情况
                        if(getSelectViewCount()==1){
                            View selectView=getSelectedView();
                            if(selectView!=null){
                                selectView.setSelected(false);
                                mAdapter.onItemUnSelected(selectView,getPositionForChild(selectView));
                            }
                        }else{
                            //多选
                            mAdapter.tipForSelectMax(v,mMaxSelectCount);
                            return;
                        }
                    }
                }

                if(v.isSelected()){
                    v.setSelected(false);
                    mAdapter.onItemUnSelected(v,position);

                }else {
                    v.setSelected(true);
                    mAdapter.onItemSelected(v,position);

                }
            }
        });
    }

    public List<Integer> getSelectedItemPositionList(){
        List<Integer> selectList=new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if(view.isSelected()){
                selectList.add(i);
            }
        }
        return selectList;
    }

    private int getPositionForChild(View selectView) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view==selectView) {
                return i;
            }
        }
        return 0;
    }

    private View getSelectedView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isSelected()) {
                return view;
            }
        }
        return null;
    }

    private int getSelectViewCount() {
        int result = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isSelected()) {
                result++;
            }
        }
        return result;
    }
}
