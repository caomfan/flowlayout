package com.mophan.imooc_flowlayout2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.mophan.imooc_flowlayout2.adapter.FlexBoxLayoutManagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlexBoxLayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas=new ArrayList<>();
    private FlexBoxLayoutManagerAdapter mAdapter;

    private static final List<String> mDataList =
            Arrays.asList("Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout_manager);

        mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new FlexboxLayoutManager(this));

        mDatas.clear();
        for (int i=0;i<400;i++){
            mDatas.add(addTag());
        }
        mAdapter=new FlexBoxLayoutManagerAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    public String addTag() {
        return mDataList.get((int)(Math.random()*(mDataList.size()-1)));
    }
}
