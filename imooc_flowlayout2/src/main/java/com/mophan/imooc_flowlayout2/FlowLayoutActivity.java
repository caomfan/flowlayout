package com.mophan.imooc_flowlayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mophan.imooc_flowlayout2.view.FlowLayout;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity {

    private FlowLayout mFlowLayout;
    private static final List<String> mDataList =
            Arrays.asList("Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com","Android", "hyman","imooc.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        mFlowLayout = findViewById(R.id.flowlayout);

    }

    private Button generateButton() {
        Button button = new Button(this);
        button.setText("add");

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(lp);
        return button;
    }

    public void addTag(View view) {

        TextView tag = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tag, mFlowLayout, false);

        tag.setText(mDataList.get((int)(Math.random()*(mDataList.size()-1))));
        mFlowLayout.addView(tag);

    }
}
