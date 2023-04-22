package com.example.myapplication12;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment5 extends Fragment1 {
    private LinearLayout lnchieucao,lntuoi,lngioitinh,lncannang,lnBMI;
    private TextView txchieucao,txtuoi,txcan,txgioitinh,txBMI,txBMR;
    private  DataBase dataBase,dataBasef1;
    private float cannang,chieucao,bmi,bmr,kgthongke;
    private int tuoi;
    private String gioitinh,trangthai,a,a1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment5,container,false);
        anhxa(view);
        dataBasef1 = new DataBase(this.getActivity(),"CanNang.sqlLite",null,1);
        dataBase = new DataBase(this.getActivity(),"thongtin.sqlLite",null,1);
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS ThongTin(Id INTEGER PRIMARY KEY AUTOINCREMENT, ChieuCao float,Tuoi INTEGER,CanNang float,GioiTinh Nvarchar(5))");
           loadulieu();
           lnchieucao.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   showdialogchieucao();
               }
           });
           lntuoi.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   showdialogtuoi();
               }
           });

           lngioitinh.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   showdialoggioitinh();
               }
           });
           lnBMI.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   showdialogBMI();
               }
           });
           lncannang.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  // Fragment5.getTabAt(1).select();
               }
           });
        return view;
    }

    public void anhxa(View view){
        lnchieucao = view.findViewById(R.id.cao);
        lntuoi = view.findViewById(R.id.tuoin);
        lncannang =view.findViewById(R.id.can);
        lngioitinh = view.findViewById(R.id.gioi);
        lnBMI = view.findViewById(R.id.chiso);
        txcan=view.findViewById(R.id.bamcan);
        txchieucao=view.findViewById(R.id.bamcao);
        txBMI=view.findViewById(R.id.banbmi);
        txgioitinh=view.findViewById(R.id.bamgt);
        txtuoi=view.findViewById(R.id.bamtuoi);
        txBMR=view.findViewById(R.id.banbmr);
    }
    public void showdialogBMI(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bmi);
        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams win=window.getAttributes();
        win.gravity=Gravity.CENTER;
        window.setAttributes(win);
        Button btnhuy =dialog.findViewById(R.id.btn_ok);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.show();}



    public void showdialogchieucao(){
        Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chieucao);
        Window window=dialog.getWindow();

        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams win=window.getAttributes();
        win.gravity= Gravity.CENTER;
        window.setAttributes(win);
        int chieucao1 = (int) Math.round(chieucao);
        a =String.valueOf(chieucao1);

        EditText nhap=dialog.findViewById(R.id.nhap);
        Button s7=dialog.findViewById(R.id.s7);
        Button s8=dialog.findViewById(R.id.s8);
        Button s9=dialog.findViewById(R.id.s9);
        Button s4=dialog.findViewById(R.id.s4);
        Button s5=dialog.findViewById(R.id.s5);
        Button s6=dialog.findViewById(R.id.s6);
        Button s1=dialog.findViewById(R.id.s1);
        Button s2=dialog.findViewById(R.id.s2);
        Button s3=dialog.findViewById(R.id.s3);
        Button s0=dialog.findViewById(R.id.s0);
        Button sc=dialog.findViewById(R.id.C);
        Button s00=dialog.findViewById(R.id.s00);
        Button sphay=dialog.findViewById(R.id.sphay);
        Button sbang=dialog.findViewById(R.id.sbang);
        ImageView sxoa=dialog.findViewById(R.id.imagexoa);
        ImageView sxoa1=dialog.findViewById(R.id.image);
        nhap.setText(a);
        sbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a.equals(null)  ||a.equals(""))
                {
                    dialog.dismiss();
                }
                else
                {
                    if(isParsable(a))
                    {
                        float c = Float.parseFloat(a);
                        dataBase.QueryData("Update ThongTin SET ChieuCao="+c+"");
                        loadulieu();
                        dialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),getString(R.string.saidinhdang),Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
        sxoa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a="";
                nhap.setText(a);
            }
        });
        sxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!a.isEmpty()){
                    a=a.substring(0,a.length()-1);
                }
                nhap.setText(a);
            }

        });
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"7";
                nhap.setText(a);
            }
        });
        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"8";
                nhap.setText(a);
            }
        });
        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"9";
                nhap.setText(a);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"4";
                nhap.setText(a);
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"5";
                nhap.setText(a);
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"6";
                nhap.setText(a);
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"1";
                nhap.setText(a);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"2";
                nhap.setText(a);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"3";
                nhap.setText(a);
            }
        });
        s0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"0";
                nhap.setText(a);
            }
        });
        s00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+"00";
                nhap.setText(a);
            }
        });
        sphay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=a+".";
                nhap.setText(a);
            }
        });

        dialog.show();
    }



    public void showdialoggioitinh(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_gioitinh);
        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams win=window.getAttributes();
        win.gravity=Gravity.CENTER;
        window.setAttributes(win);
        Button btnhuy =dialog.findViewById(R.id.btn_huy1);
        TextView nam,nu;
        nam = dialog.findViewById(R.id.nam);
        nu = dialog.findViewById(R.id.nu);
        nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.QueryData("Update ThongTin SET GioiTinh='Nam'");
                loadulieu();
                dialog.dismiss();
            }
        });

        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.QueryData("Update ThongTin SET GioiTinh='Nữ'");
                loadulieu();
                dialog.dismiss();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.show();
    }


    public void showdialogtuoi(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tuoi);
        Window window=dialog.getWindow();

        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams win=window.getAttributes();
        win.gravity=Gravity.CENTER;
        window.setAttributes(win);
        a1=String.valueOf(tuoi);

        EditText nhap=dialog.findViewById(R.id.nhap1);
        Button s7=dialog.findViewById(R.id.s71);
        Button s8=dialog.findViewById(R.id.s81);
        Button s9=dialog.findViewById(R.id.s91);
        Button s4=dialog.findViewById(R.id.s41);
        Button s5=dialog.findViewById(R.id.s51);
        Button s6=dialog.findViewById(R.id.s61);
        Button s1=dialog.findViewById(R.id.s11);
        Button s2=dialog.findViewById(R.id.s21);
        Button s3=dialog.findViewById(R.id.s31);
        Button s0=dialog.findViewById(R.id.s01);
        Button sc=dialog.findViewById(R.id.C1);
        Button s00=dialog.findViewById(R.id.s0011);

        Button sbang=dialog.findViewById(R.id.sbang1);
        ImageView sxoa=dialog.findViewById(R.id.imagexoa1);
        ImageView sxoa1=dialog.findViewById(R.id.image1);
        nhap.setText(a1);
        sbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a1.equals("")||a1.equals(null))
                {
                    dialog.dismiss();
                }
                else
                {
                    int a = Integer.parseInt(a1);
                    dataBase.QueryData("Update ThongTin SET Tuoi="+a1+"");
                    loadulieu();
                    dialog.dismiss();
                }

            }
        });
        sxoa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1="";
                nhap.setText(a1);
            }
        });
        sxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!a1.isEmpty()){
                    a1=a1.substring(0,a1.length()-1);
                }
                nhap.setText(a1);
            }

        });
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"7";
                nhap.setText(a1);
            }
        });
        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"8";
                nhap.setText(a1);
            }
        });
        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"9";
                nhap.setText(a1);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"4";
                nhap.setText(a1);
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"5";
                nhap.setText(a1);
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"6";
                nhap.setText(a1);
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"1";
                nhap.setText(a1);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"2";
                nhap.setText(a1);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"3";
                nhap.setText(a1);
            }
        });
        s0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"0";
                nhap.setText(a1);
            }
        });
        s00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1=a1+"00";
                nhap.setText(a1);
            }
        });
        dialog.show();
    }

    public void ThemData(){
        dataBase.QueryData("INSERT INTO ThongTin VALUES(NULL,180,20,70.0,'Nam')");
    }

    public void loadulieu(){

        Cursor cursor = dataBase.Getdata("Select * from ThongTin");
        if(cursor==null||cursor.getCount()<=0){
            ThemData();
            chieucao = 180;
            tuoi = 20;
            gioitinh = "Nam";
        }
        else
        {
            while (cursor.moveToNext())
            {
                chieucao = cursor.getFloat(1);
                tuoi = cursor.getInt(2);
                gioitinh = cursor.getString(4);
            }
        }
        cursor.close();
        dataBase.close();
        txchieucao.setText(chieucao+"cm");
        txcan.setText(getcannanghientai(dataBasef1)+"kg");
        txtuoi.setText(tuoi+"");
        txgioitinh.setText(gioitinh);
        cannang =getcannanghientai(dataBasef1);
        tinhtoan();
    }

    public  void  tinhtoan(){
            if(gioitinh.equals("Nam"))
            {
                bmi = (float) (cannang/((chieucao*chieucao)/10000));
                bmr = (float) ((66 + (13.7*cannang)+(5*chieucao))-(6.8*tuoi));
            }
            else if (gioitinh.equals("Nữ"))
            {
                bmi = (float) (cannang/((chieucao*chieucao)/10000));
                bmr = (float) ((655 + (9.6*cannang)+(1.8*chieucao))-(4.7*tuoi));
            }
            if(bmi<18.5)
            {
                trangthai=getString(R.string.gay)+"";
            }
            else if(bmi<=24.9&&bmi>=18.5)
            {
                trangthai=getString(R.string.bt)+"";
            }
            else if(bmi<=29.9&&bmi>=25)
            {
                trangthai=getString(R.string.thuacan)+"";
            }
            else if (bmi>=30)
            {
                trangthai=getString(R.string.beophi)+"";
            }
            txBMI.setText(bmi+":"+trangthai);
            txBMR.setText(bmr+"Kcal");
    }
    public  float getcannanghientai( DataBase dataBase){

        Cursor thongkekg = dataBase.Getdata("Select * from CanNang ORDER BY Ngay ASC");
        kgthongke = 0.0F;
        while (thongkekg.moveToNext())
        {
            if(thongkekg.getFloat(2)>1.0)
            {
                kgthongke = thongkekg.getFloat(2);
            }
        }
        thongkekg.close();
        dataBase.close();
        return kgthongke;
    }
    public static boolean isParsable(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
