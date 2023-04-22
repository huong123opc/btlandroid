package com.example.myapplication12;

public class CangNang {
    private float CanNang;
    private  int Id;
    private  String date;
    private  boolean tap;
    private boolean uong;
    private   String note;

    public CangNang(float canNang, int id, String date, boolean tap, boolean uong, String note) {
        CanNang = canNang;
        Id = id;
        this.date = date;
        this.tap = tap;
        this.uong = uong;
        this.note = note;
    }

    public float getCanNang() {
        return CanNang;
    }

    public void setCanNang(float canNang) {
        CanNang = canNang;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTap() {
        return tap;
    }

    public void setTap(boolean tap) {
        this.tap = tap;
    }

    public boolean isUong() {
        return uong;
    }

    public void setUong(boolean uong) {
        this.uong = uong;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
