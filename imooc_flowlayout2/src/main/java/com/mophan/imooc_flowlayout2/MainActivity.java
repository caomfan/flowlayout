package com.mophan.imooc_flowlayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mophan.imooc_flowlayout2.adapter.FlexBoxLayoutManagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goFlowLayout(View view) {
        Intent intent=new Intent(this,FlowLayoutActivity.class);
        startActivity(intent);
    }

    public void goFlexBoxLayout(View view) {
        Intent intent=new Intent(this,FlexBoxLayoutActivity.class);
        startActivity(intent);
    }

    public void goFlexBoxLayoutManager(View view) {
        Intent intent=new Intent(this, FlexBoxLayoutManagerActivity.class);
        startActivity(intent);
    }

    public void goTagFlowLayout(View view) {
        Intent intent=new Intent(this,TagFlowLayoutActivity.class);
        startActivity(intent);
    }
}
