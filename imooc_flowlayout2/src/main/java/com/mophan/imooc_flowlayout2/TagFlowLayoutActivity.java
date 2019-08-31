package com.mophan.imooc_flowlayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.mophan.imooc_flowlayout2.view.TagAdapter;
import com.mophan.imooc_flowlayout2.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagFlowLayoutActivity extends AppCompatActivity {

    private TagFlowLayout mTagFlowLayout;
    private static final List<String> mDatas =
            new ArrayList<>(Arrays.asList("The first one is FlexboxLayout that extends the ViewGroup like LinearLayout and RelativeLayout You can specify the attributes from a layout XML like".split(" ")));
    private  TagAdapter  mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_flow_layout);
        mTagFlowLayout=findViewById(R.id.tag_flow_layout);
        mTagFlowLayout.setMaxSelectCount(3);
        mTagFlowLayout.setAdapter(mAdapter= new TagAdapter() {
            @Override
            public int getItemCount() {
                return mDatas.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.item_select_tag,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView tvTag= view.findViewById(R.id.tv);
                tvTag.setText(mDatas.get(position));
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onItemViewClick(View v, int position) {
                Toast.makeText(v.getContext(),mTagFlowLayout.getSelectedItemPositionList().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void tipForSelectMax(View v, int maxSelectCount) {
                super.tipForSelectMax(v, maxSelectCount);
                Toast.makeText(v.getContext(),"最多选择"+maxSelectCount+"个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSelected(View v, int position) {
                TextView tvTag= v.findViewById(R.id.tv);
                tvTag.setTextColor(Color.RED);
            }

            @Override
            public void onItemUnSelected(View v, int position) {
                TextView tvTag= v.findViewById(R.id.tv);
                tvTag.setTextColor(Color.BLACK);
            }
        });
    }

    public void changeDatas(View view) {
        mDatas.clear();
        mDatas.addAll( new ArrayList<>(Arrays.asList("here are two ways of using Flexbox in your layout".split(" "))));
        mTagFlowLayout.setMaxSelectCount(1);
        mAdapter.notifyDataSetChanged();
    }
}
