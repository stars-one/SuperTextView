package com.wan.supertextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.wan.supertextview.SuperTextView;

public class MainActivity extends AppCompatActivity {


    private SuperTextView mTextview;
    private LinearLayout mLinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        mTextview = (SuperTextView) findViewById(R.id.textview);
        mTextview.startShow(1000,250);
        mTextview.hide(findViewById(R.id.linearlayout),2000);

    }
}
