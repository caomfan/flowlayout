package com.mophan.imooc_flowlayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;

public class FlexBoxLayoutActivity extends AppCompatActivity {

    private FlexboxLayout mFlexBoxLayout;
    private static final List<String> mDataList =
            Arrays.asList("Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout);
        mFlexBoxLayout = findViewById(R.id.flexbox);
    }

    public void addTag(View view) {

        TextView tag = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tag, mFlexBoxLayout, false);

        tag.setText(mDataList.get((int)(Math.random()*(mDataList.size()-1))));
        mFlexBoxLayout.addView(tag);

    }
}
