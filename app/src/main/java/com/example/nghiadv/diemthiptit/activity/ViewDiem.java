package com.example.nghiadv.diemthiptit.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.base.DataRow;
import com.example.nghiadv.diemthiptit.base.DatabaseHelper;
import com.example.nghiadv.diemthiptit.view.TableRowCustom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ViewDiem extends BaseLayoutActivity {
    private static final String TAG = "LOGDIEM";
    DatabaseHelper mDBHelper;
    EditText ed_masv;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diem);

        ed_masv = (EditText) findViewById(R.id.et_masv);
        mDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        mDBHelper = new DatabaseHelper(this);

    }

    @Override
    protected Boolean isAppUseDrawerLayout() {
        return true;
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void submit(View v) {
        String masv = ed_masv.getText().toString().trim();
        masv = "B14DCCN732";
        List<DataRow> listRow = mDBHelper.getDiem(masv);


        if(listRow.size() == 0){
            Toast.makeText(this,getString(R.string.exist_sv),Toast.LENGTH_SHORT).show();
        }else{
            DataRow d = listRow.get(0);

            TextView tv_tenSV = (TextView) findViewById(R.id.tv_tenSV);
            tv_tenSV.setText(d.getHodem() + " " + d.getTen());

            TableLayout tableView = (TableLayout) findViewById(R.id.tb_diem);
            for (DataRow dataRow : listRow) {
                TableRowCustom row = new TableRowCustom(this);
                row.setData(dataRow);
                tableView.addView(row.getView());
            }
        }


    }
}
