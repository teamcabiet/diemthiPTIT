package com.example.nghiadv.diemthiptit.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.common.AlertDialog;
import com.example.nghiadv.diemthiptit.interfaces.PermissionListener;

import java.util.ArrayList;

/**
 * Created by nghia on 4/22/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private PermissionListener permissionListener;
    View.OnClickListener dialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // show explanations to the user
            if (ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, Manifest.permission.INTERNET)
                    && ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, Manifest.permission.ACCESS_NETWORK_STATE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // ask permission
                askForPermission();
            } else {
                // if can't show request permission then goto application settings
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts(getString(R.string.package_value), getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 0);

            }

        }
    };

    public static boolean hasPermission(Context context, String permission) {
        if (context != null && permission != null) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawablelayout);
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * check again permission
         */
        for (int i = 0; i < permissions.length; i++) {
            String per = permissions[i];
            if (per.equals(Manifest.permission.INTERNET) || per.equals(Manifest.permission.ACCESS_NETWORK_STATE) ||
                    per.equals(Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    per.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.showAlertWithAction(getString(R.string.alert_permission), BaseActivity.this, dialogListener);
                    return;
                }
            }
        }

        permissionListener.onPermissionsSuccess();
    }

    public boolean askForPermission() {
        ArrayList<String> arrPermissions = new ArrayList<>();
        if (!hasPermission(getBaseContext(), Manifest.permission.INTERNET)) {
            arrPermissions.add(Manifest.permission.INTERNET);
        }
        if (!hasPermission(getBaseContext(), Manifest.permission.ACCESS_NETWORK_STATE)) {
            arrPermissions.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }

        if (!hasPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            arrPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!hasPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            arrPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (arrPermissions.size() > 0) {
            // if has a permission is deny then request permission
            String[] permissons = new String[arrPermissions.size()];
            permissons = arrPermissions.toArray(permissons);
            ActivityCompat.requestPermissions(this, permissons, PERMISSION_REQUEST_CODE);
        } else {
            // if do not have permission deny
            permissionListener.onPermissionsSuccess();
        }
        return true;
    }


    public void closeKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected abstract Boolean isAppUseDrawerLayout();
}
