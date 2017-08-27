package com.example.nghiadv.diemthiptit.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NghiaDV.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "dbapp.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.nghiadv.diemthiptit/databases/";
    private static final String TAG = "LOG_DB";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<DataRow> getDiem(String ma_sv) {
        DataRow row = null;
        List<DataRow> dsDiem = new ArrayList<>();
        openDatabase();

        String querry = "SELECT tenmon, hodem, ten, diem_ck,diem_tb,diem_chu FROM bangdiem, monhoc WHERE bangdiem.idmon = monhoc.id and bangdiem.masv = '" + ma_sv + "'";
        //Log.d(TAG, "getDiem: "+querry);
        Cursor cursor = mDatabase.rawQuery(querry, null);
        cursor.moveToFirst();
        String hodem, ten, tenMon;

        while (!cursor.isAfterLast()) {
//            int id = cursor.getInt(cursor.getColumnIndex("id"));
//            int idMon = cursor.getInt(cursor.getColumnIndex("idmon"));
//
//            String masv = cursor.getString(cursor.getColumnIndex("masv"));
            hodem = cursor.getString(cursor.getColumnIndex("hodem"));
            ten = cursor.getString(cursor.getColumnIndex("ten"));
            tenMon = cursor.getString(cursor.getColumnIndex("tenmon"));

//            String ngaysinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
//            String lop = cursor.getString(cursor.getColumnIndex("lop"));

//            float diem_cc = cursor.getFloat(cursor.getColumnIndex("diem_cc"));
//            float diem_kt = cursor.getFloat(cursor.getColumnIndex("diem_kt"));
//            float diem_th = cursor.getFloat(cursor.getColumnIndex("diem_th"));
//            float diem_btl = cursor.getFloat(cursor.getColumnIndex("diem_btl"));
            float diem_ck = cursor.getFloat(cursor.getColumnIndex("diem_ck"));
            float diem_tb = cursor.getFloat(cursor.getColumnIndex("diem_tb"));

            String diem_chu = cursor.getString(cursor.getColumnIndex("diem_chu"));

            row = new DataRow(tenMon, diem_ck, diem_tb, diem_chu,hodem,ten);
            dsDiem.add(row);

            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return dsDiem;
    }
}
