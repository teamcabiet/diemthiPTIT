package com.example.nghiadv.diemthiptit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.nghiadv.diemthiptit.R;
import com.example.nghiadv.diemthiptit.base.DataRow;

/**
 * Created by NghiaDV on 19/06/2017.
 */

public class TableRowCustom extends TableRow {
    TextView tv_tenMon, tv_diem_tb;
    TextView tv_diem_ck, tv_diem_chu;
    View view;

    public TableRowCustom(Context context) {
        super(context);
        init(context);
    }

    public TableRowCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    void init(Context context) {
        view = inflate(context, R.layout.table_row_layout, null);
        tv_tenMon = (TextView) view.findViewById(R.id.tv_tenMon);
        tv_diem_tb = (TextView) view.findViewById(R.id.tv_diemTB);
        tv_diem_ck = (TextView) view.findViewById(R.id.tv_diemCK);
        tv_diem_chu = (TextView) view.findViewById(R.id.tv_diemChu);

    }

    public void setTextTenMon(String tenMon) {
        tv_tenMon.setText(tenMon);
    }

    public void setTextDiemCK(float diemCK) {
        tv_diem_ck.setText(String.valueOf(diemCK));
    }

    public void setTextDiemTB(float diemTB) {
        tv_diem_tb.setText(String.valueOf(diemTB));
    }

    public void setTextDiemChu(String diemChu) {
        tv_diem_chu.setText(diemChu);
    }

    public void setData(DataRow data) {
        setTextTenMon(data.getTenmon());
        setTextDiemCK(data.getDiem_ck());
        setTextDiemTB(data.getDiem_tb());
        setTextDiemChu(data.getDiemchu());
    }

    public View getView() {
        return view;
    }
}
