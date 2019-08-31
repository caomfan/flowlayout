package com.mophan.imooc_flowlayout2.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.ls.LSException;

/**
 * Create by CMF on 2019/9/1.
 */
public abstract class TagAdapter {
    public abstract int getItemCount();

    public abstract View createView(LayoutInflater inflater, ViewGroup parent, int position);

    public abstract void bindView(View view, int position);

    public void onItemViewClick(View v,int position){

    }

    public void tipForSelectMax(View v, int maxSelectCount){

    }

    public  void onItemSelected(View v,int position){

    }

    public  void onItemUnSelected(View v ,int position){

    }

    public static interface OnDataSetChangedListener{
        void onDataChanged();
    }

    private OnDataSetChangedListener mListener;

    public void setOnDataSetChangedListener(OnDataSetChangedListener listener){
        mListener= listener;
    }

    public void notifyDataSetChanged(){
        // TagFlowLayout.onDataChanged
        if(mListener!=null){
            mListener.onDataChanged();
        }
    }
}
