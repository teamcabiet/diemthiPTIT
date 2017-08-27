package com.example.nghiadv.diemthiptit.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.ViewTKB;

/**
 * Created by NghiaDV on 20/06/2017.
 */

public abstract class BaseLayoutActivity extends BaseActivity {
    private static final String TAG = "Log";
    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public FrameLayout activityContainer;
    private int navId = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isAppUseDrawerLayout() != null) {
            if (isAppUseDrawerLayout()) {
                DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawablelayout, null);
                activityContainer = (FrameLayout) fullView.findViewById(R.id.content_main);
                getLayoutInflater().inflate(layoutResID, activityContainer, true);
                super.setContentView(fullView);


                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                toolbar = (Toolbar) findViewById(R.id.toolbar);

                TextView txtActivityTitle = (TextView) toolbar.findViewById(R.id.activity_title);
                TextView txtActivitySubTitle = (TextView) toolbar.findViewById(R.id.activity_subtitle);

                txtActivitySubTitle.setText("");
                txtActivityTitle.setText("");
                setSupportActionBar(toolbar);
                toolbar.setBackgroundResource(R.drawable.header);

                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

                drawerLayout.addDrawerListener(toggle);
                toggle.setDrawerIndicatorEnabled(false);
                toggle.syncState();


                NavigationView navigationView = (NavigationView) drawerLayout.findViewById(R.id.nav_view);
                navigationView.setItemIconTintList(null);
                navigationView.setNavigationItemSelectedListener(this);

                //View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, null);
                //navigationView.addHeaderView(headerView);


                ImageView hamburger = (ImageView) toolbar.findViewById(R.id.homeButton);
                hamburger.setImageResource(R.drawable.ic_hamburger);


                // set event for button of toolbar
                hamburger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);
                            closeKeyboard(drawerLayout);
                        }
                        closeKeyboard(drawerLayout);
                    }
                });

            } else {
                CoordinatorLayout fullView = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.activity_basic_toolbarlayout, null);
                activityContainer = (FrameLayout) fullView.findViewById(R.id.content_main);
                getLayoutInflater().inflate(layoutResID, activityContainer, true);
                super.setContentView(fullView);

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                ImageView backButton = (ImageView) toolbar.findViewById(R.id.homeButton);
                backButton.setImageResource(R.drawable.ic_back);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        } else {
            super.setContentView(layoutResID);
        }

    }


    @Override
    public void onBackPressed() {
        if (null != isAppUseDrawerLayout() && isAppUseDrawerLayout()) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } else {
            closeKeyboard(toolbar);
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_tkb: {
                navId = 0;
                break;
            }
            case R.id.nav_lichthi: {
                navId = 1;
                break;
            }
            case R.id.nav_diem: {
                navId = 2;
                break;
            }
            case R.id.nav_about: {
                navId = 3;
                break;
            }
        }


        loadScreen();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadScreen() {
        switch (navId) {
            case 0: {
                Intent intent = new Intent(this, ViewTKB.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(this, ViewDiem.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
        }

    }


}
