package com.example.nghiadv.diemthiptit.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.nghiadv.diemthiptit.R;


/**
 * Created by nghia on 4/22/2017.
 */

public class AlertDialog {
    private static Dialog dialog;

    /**
     * Show alert dialog
     *
     * @param message          : Message show in dialog
     * @param context          : Context for dialog
     * @param mOnClickListener : Action when click dialog
     */
    public static void showAlertWithAction(String message, Context context, final View.OnClickListener mOnClickListener) {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_dialog);
        TextView txtmessage = (TextView) dialog.findViewById(R.id.message);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        // set text message
        txtmessage.setText(message);
        // set action for button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}