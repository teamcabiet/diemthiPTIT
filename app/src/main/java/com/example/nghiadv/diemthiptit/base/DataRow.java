package com.example.nghiadv.diemthiptit.base;

/**
 * Created by NghiaDV on 19/06/2017.
 */

public class DataRow {
    private String tenmon;
    private float diem_tb, diem_ck;
    private String diemchu;
    private String hodem, ten;

    public DataRow(String tenmon, float diem_ck, float diem_tb, String diemchu) {
        this.tenmon = tenmon;
        this.diem_tb = diem_tb;
        this.diem_ck = diem_ck;
        this.diemchu = diemchu;
    }

    public DataRow(String tenmon, float diem_tb, float diem_ck, String diemchu, String hodem, String ten) {
        this.tenmon = tenmon;
        this.diem_tb = diem_tb;
        this.diem_ck = diem_ck;
        this.diemchu = diemchu;
        this.hodem = hodem;
        this.ten = ten;
    }

    public DataRow() {

    }

    public String getHodem() {
        return hodem;
    }

    public void setHodem(String hodem) {
        this.hodem = hodem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public float getDiem_tb() {
        return diem_tb;
    }

    public void setDiem_tb(float diem_tb) {
        this.diem_tb = diem_tb;
    }

    public float getDiem_ck() {
        return diem_ck;
    }

    public void setDiem_ck(float diem_ck) {
        this.diem_ck = diem_ck;
    }

    public String getDiemchu() {
        return diemchu;
    }

    public void setDiemchu(String diemchu) {
        this.diemchu = diemchu;
    }
}
