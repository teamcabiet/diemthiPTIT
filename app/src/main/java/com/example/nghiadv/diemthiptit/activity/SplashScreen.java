package com.example.nghiadv.diemthiptit.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.common.AlertDialog;
import com.example.nghiadv.diemthiptit.common.AnimationUtils;
import com.example.nghiadv.diemthiptit.interfaces.PermissionListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SplashScreen extends BaseLayoutActivity implements PermissionListener {
    private static final String TAG = "LogS";
    private final long DELAY_LENGHT = 1010;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPermissionListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Firebase.setAndroidContext(this);

        //getSupportActionBar().hide();
        ImageView iv_loading = (ImageView) findViewById(R.id.ic_loading);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.indicator_rotate);
        iv_loading.startAnimation(rotate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askForPermission();
                } else {
                    onPermissionsSuccess();
                }
            }
        }, DELAY_LENGHT);


    }

    @Override
    protected Boolean isAppUseDrawerLayout() {
        return null;
    }


    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public void onPermissionsSuccess() {
        boolean isConnection = isConnected();
        String statusConnection = "Connection Avail";
        if (!isConnection) {
            statusConnection = "false";
        }

        Toast.makeText(getBaseContext(), statusConnection, Toast.LENGTH_SHORT);
        checkActive();
    }

    @Override
    public void onPermissionsFailure() {

    }


    private void checkActive() {
        Firebase ref = new Firebase(GlobalURL.LINK_FIREBASE).child("active");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isActive = false;
                Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String result = (String) ds.getValue();
                    if(result.equals("true")){
                        isActive = true;
                    }else {
                        isActive = false;
                    }
                }

                if (!isActive) {
                    AlertDialog.showAlertWithAction(getString(R.string.alert_use_version), SplashScreen.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.exit(0);
                        }
                    });
                }else {
                    Intent intent = new Intent(SplashScreen.this,ViewDiem.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
