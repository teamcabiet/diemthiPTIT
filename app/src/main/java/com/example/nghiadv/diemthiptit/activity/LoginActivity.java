package com.example.nghiadv.diemthiptit.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.common.AlertDialog;

public class LoginActivity extends BaseLayoutActivity {

    EditText editText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = (EditText) findViewById(R.id.et_masv);

        setEvents();


    }

    private void setEvents() {
        View loginForm = findViewById(R.id.lnLogin);
        loginForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard(v);
            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.llLogin);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard(v);
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null || actionId == getResources().getInteger(R.integer.login_id)) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        closeKeyboard(v);
                        login();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected Boolean isAppUseDrawerLayout() {
        return null;
    }

    public void submit(View v) {
        login();
        closeKeyboard(v);
    }


    void login() {
        String masv = editText.getText().toString().trim();
        // check here

        if (validMaSV(masv)) {
            Intent intent = new Intent(LoginActivity.this, ViewDiem.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    boolean validMaSV(String masv) {
        boolean result = false;
        boolean cancel = false;
        View focusView = null;

        if (masv.equals("")) {
            AlertDialog.showAlertWithAction(getString(R.string.null_msv), this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            focusView = editText;
            cancel = true;
        } else {
            if (masv.length() < 2) {
                AlertDialog.showAlertWithAction(getString(R.string.short_msv), this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                focusView = editText;
                cancel = true;
            } else {

                if(masv.equals("123")) result = true;

                // check here
                if (!result) {
                    AlertDialog.showAlertWithAction(getString(R.string.wrong_msv), this, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    focusView = editText;
                    cancel = true;
                }
            }
        }

        if(cancel){
            focusView.requestFocus();
        }

        return result;
    }
}
