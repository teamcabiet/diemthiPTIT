package com.example.nghiadv.diemthiptit.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;


import com.example.nghiadv.diemthiptit.R;

public class MainActivity extends BaseLayoutActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected Boolean isAppUseDrawerLayout() {
        return true;
    }

}