package com.example.myapplication12;

public class ThucAn {
  private   int Id;
   private  String ngay;
   private String buasang;
   private String buatrua;
   private String buatoi;
   private String buaphu;

    public ThucAn(int id, String ngay, String buasang, String buatrua, String buatoi, String buaphu) {
        Id = id;
        this.ngay = ngay;
        this.buasang = buasang;
        this.buatrua = buatrua;
        this.buatoi = buatoi;
        this.buaphu = buaphu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getBuasang() {
        return buasang;
    }

    public void setBuasang(String buasang) {
        this.buasang = buasang;
    }

    public String getBuatrua() {
        return buatrua;
    }

    public void setBuatrua(String buatrua) {
        this.buatrua = buatrua;
    }

    public String getBuatoi() {
        return buatoi;
    }

    public void setBuatoi(String buatoi) {
        this.buatoi = buatoi;
    }

    public String getBuaphu() {
        return buaphu;
    }

    public void setBuaphu(String buaphu) {
        this.buaphu = buaphu;
    }
}
