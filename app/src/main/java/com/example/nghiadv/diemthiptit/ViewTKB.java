package com.example.nghiadv.diemthiptit;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nghiadv.diemthiptit.activity.BaseLayoutActivity;

public class ViewTKB extends BaseLayoutActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tkb);
    }

    @Override
    protected Boolean isAppUseDrawerLayout() {
        return true;
    }
}
