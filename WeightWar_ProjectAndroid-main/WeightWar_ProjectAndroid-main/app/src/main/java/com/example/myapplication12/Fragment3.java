package com.example.myapplication12;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fragment3 extends Fragment {
   private DataBase dataBase1;
   private LinearLayout lnstart,lnend;
    private List<String> dayexitstcannang;
    private Date datestart,dateend;
    private List<CangNang> cangNangs;
    private Float Cannangmax,Cangnangmin,Cannangstart,Cannangend;
    private int timeuong,timetap,timetong;
    private String datemax,datemin;
    private TextView txkgstart,txkgend,txdaystart,txdayend,txratiotap,txratiouong,txtimetap,txtimeuong,txkgmax,txkgmin,txdatemax,txdatemin,txnhanxet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view3 = inflater.inflate(R.layout.fragment3,container,false);
        anhxa(view3);
        dataBase1 = new DataBase(this.getActivity(),"CanNang.sqlLite",null,1);
       //tải dữ liệu
        loadData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //tính toán dữ liệu
        thongkedata(dateFormat.format(datestart),dateFormat.format(dateend));
        lnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dayexitstcannang = new ArrayList<>();
                        Cursor trocannang1 = dataBase1.Getdata("Select * from CanNang ORDER BY Ngay DESC ");
                        while (trocannang1.moveToNext())
                        {
                            dayexitstcannang.add(trocannang1.getString(1));
                        }
                        trocannang1.close();
                        dataBase1.close();
                        String daythucan = simpleDateFormat.format(calendar.getTime());
                        datestart = new Date();
                        try {
                            datestart = simpleDateFormat.parse(daythucan);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(daythucan.equals(null)||dateend.equals(null))
                        {
                            if(dayexitstcannang.contains(daythucan)==false ){
                                Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi)+"",Toast.LENGTH_LONG).show();
                                daythucan=dayexitstcannang.get(dayexitstcannang.size()-5);
                                try {
                                    datestart = simpleDateFormat.parse(daythucan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                dayexitstcannang.clear();
                            }
                        }
                        else
                        {
                            if(dayexitstcannang.contains(daythucan)==false ||(dateend.compareTo(datestart)!=1)){
                                Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi)+"",Toast.LENGTH_LONG).show();
                                daythucan=dayexitstcannang.get(dayexitstcannang.size()-5);
                                try {
                                    datestart = simpleDateFormat.parse(daythucan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                dayexitstcannang.clear();
                            }
                        }
                     getdata(txkgstart,txdaystart,daythucan,1);
                        thongkedata(dateFormat.format(datestart),dateFormat.format(dateend));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
        lnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dayexitstcannang = new ArrayList<>();
                        Cursor trocannang1 = dataBase1.Getdata("Select * from CanNang ORDER BY Ngay DESC ");
                        while (trocannang1.moveToNext())
                        {
                            dayexitstcannang.add(trocannang1.getString(1));
                        }
                        trocannang1.close();
                        dataBase1.close();
                        String daythucanend = simpleDateFormat.format(calendar.getTime());
                        dateend = new Date();
                        try {
                            dateend = simpleDateFormat.parse(daythucanend);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(daythucanend.equals(null)||dateend.equals(null))
                        {
                            if(dayexitstcannang.contains(daythucanend)==false ){
                                Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi)+"",Toast.LENGTH_LONG).show();
                                daythucanend=dayexitstcannang.get(0);
                                try {
                                    dateend = simpleDateFormat.parse(daythucanend);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                dayexitstcannang.clear();
                            }
                        }
                        else
                        {
                            if(dayexitstcannang.contains(daythucanend)==false ||dateend.compareTo(datestart)!=1 ){
                                Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi)+"",Toast.LENGTH_LONG).show();
                                daythucanend=dayexitstcannang.get(0);
                                try {
                                    dateend = simpleDateFormat.parse(daythucanend);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                dayexitstcannang.clear();
                            }
                        }
                        getdata(txkgend,txdayend,daythucanend,-1);
                        thongkedata(dateFormat.format(datestart),dateFormat.format(dateend));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
        return view3;

    }

    private void anhxa(View view)
    {
    lnstart = view.findViewById(R.id.batdau);
    lnend = view.findViewById(R.id.ketthuc);
    txkgstart = view.findViewById(R.id.canbatdau);
    txkgend = view.findViewById(R.id.canketthuc);
    txdaystart = view.findViewById(R.id.datebatdau);
    txdayend = view.findViewById(R.id.dateketthuc);
    txratiotap = view.findViewById(R.id.ratiotheduc);
    txratiouong = view.findViewById(R.id.ratiouong);
    txtimetap = view.findViewById(R.id.timetheduc);
    txtimeuong = view.findViewById(R.id.timeuong);
    txkgmax = view.findViewById(R.id.maxkg);
    txkgmin = view.findViewById(R.id.minkg);
    txdatemax = view.findViewById(R.id.datemax);
    txdatemin = view.findViewById(R.id.datemin);
    txnhanxet = view.findViewById(R.id.nhanxet);
    }


    //gán dữ lệu ngày và cân bắt đầu và ngày kết thúc ,
    private void getdata(TextView txkg,TextView txdate,String date,int i){
        Cursor getdata= dataBase1.Getdata("Select * from CanNang where Ngay = '"+date+"'");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngay="";
        float can = 0.0F;
        if (getdata != null && getdata.getCount() > 0)
        {
            while (getdata.moveToNext())
            {
                ngay = getdata.getString(1);
                can = getdata.getFloat(2);
            }
            getdata.close();
            dataBase1.close();
        }

        if(i==1)
        {
            if(can<=1.0F)
            {
                can = getkgstart(date);
                Cannangstart =can;
            }
            else
            {
                Cannangstart =can;
            }

        }
        if (i!=1)
        {
            if(can<=1.0F)
            {
                can = getkgend(date);
                Cannangend =can;
            }
            else
            {
                Cannangend =can;
            }
        }
        txkg.setText(can+"KG");
        txdate.setText(ngay);
    }

    //xử lý và gán dữ liệu ngày bắt đầu , ngày bắt đầu và ngày kết thúc
    private void loadData() {
        dateend = new Date();
        datestart = new Date();
        Cursor cannang1 = dataBase1.Getdata("Select * from CanNang ORDER BY Ngay DESC");
        cangNangs  = new ArrayList<>();
        cangNangs.clear();
        while (cannang1.moveToNext())
        {
            String ngay = cannang1.getString(1);
            int id = cannang1.getInt(0);
            float can = cannang1.getFloat(2);
            boolean tap = cannang1.getInt(3) > 0;
            boolean uong = cannang1.getInt(4) > 0;
            String note = cannang1.getString(5);
            cangNangs.add(new CangNang(can,id,ngay,tap,uong,note));
        }
        cannang1.close();
        dataBase1.close();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        getdata(txkgstart,txdaystart,cangNangs.get(10).getDate(),1);
        getdata(txkgend,txdayend,cangNangs.get(1).getDate(),0);
        try {
            dateend =dateFormat.parse(cangNangs.get(1).getDate()) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            datestart = dateFormat.parse(cangNangs.get(10).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //tinh toán và gán dữ liệu (ngày bắt đầu và ngày kết thúc)
    private void thongkedata(String start,String end){
        Cursor thongke = dataBase1.Getdata("Select * from CanNang where  Ngay between '"+start+"'and '"+end+"'  ORDER BY Ngay DESC");
        timetong = 0;
        datemax="";
        datemin="";
        timetap=0;
        timeuong=0;
        Cannangmax = 0.0F;
        Cangnangmin=10000.0F;
        while (thongke.moveToNext())
        {
            if(thongke.getInt(3)>0){
                timetap++;
            }
            if(thongke.getInt(4)>0)
            {
                timeuong++;
            }
            if(thongke.getFloat(2)>Cannangmax)
            {
                Cannangmax=thongke.getFloat(2);
                datemax = thongke.getString(1);
            }
            if(thongke.getFloat(2)<Cangnangmin&&thongke.getFloat(2)!=0.0F)
            {
                Cangnangmin=thongke.getFloat(2);
                datemin = thongke.getString(1);
            }
            timetong++;
        }
        thongke.close();
        dataBase1.close();
        Thongke();
    }

    private void Thongke(){

        txkgstart.setText(Cannangstart+" kg");
        txkgend.setText(Cannangend+"kg");
        float z = (float)((timetap*1.0/timetong)*100);
        float y = (float)((timeuong*1.0/timetong)*100);
        txratiotap.setText( z+"%");
        txratiouong.setText(y+"%");
        txtimetap.setText(timetap+"/"+timetong+"ngày");
        txtimeuong.setText(timeuong+"/"+timetong+"ngày");
        txkgmax.setText(Cannangmax+"kg");
        txkgmin.setText(Cangnangmin+"kg");
        txdatemax.setText(datemax);
        txdatemin.setText(datemin);
        if (Cannangmax==0.0F ||Cangnangmin==10000.0F)
        {
            txratiotap.setText( 0+"%");
            txratiouong.setText(0+"%");
            txtimetap.setText(timetap+"/"+timetong+getString(R.string.date));
            txtimeuong.setText(timeuong+"/"+timetong+getString(R.string.date));
            txkgmax.setText(0.0+"kg");
            txkgmin.setText(0.0+"kg");
            txdatemax.setText("");
            txdatemin.setText("");
            txkgstart.setText(0.0+" kg");
            txkgend.setText(0.0+"kg");
            txnhanxet.setText(getString(R.string.kthay));
        }
        else
        {
            if(Cannangstart>Cannangend)
            {
                txnhanxet.setText(getString(R.string.down)+" "+(Cannangstart-Cannangend)+"kg");
                txnhanxet.setTextColor(Color.rgb(0,250,0));
            }
            else if(Cannangstart<Cannangend)
            {
                txnhanxet.setText(getString(R.string.up)+" "+(Cannangend-Cannangstart)+"kg");
                txnhanxet.setTextColor(Color.rgb(250,0,0));
            }
            else
            {
                txnhanxet.setText(getString(R.string.kthay));
            }
        }
    }
    //lấy ra cân nặng gần với ngày bắt đầu theo hướng lên trên
    private  float getkgstart(String date){
        Cursor thongkestart = dataBase1.Getdata("Select * from CanNang where  Ngay >'"+date+"'  ORDER BY Ngay DESC");
        Cannangstart=0.0F;
        while (thongkestart.moveToNext())
        {
            if(thongkestart.getFloat(2)>1)
            {
                Cannangstart = thongkestart.getFloat(2);
            }
        }
        thongkestart.close();
        dataBase1.close();
        return Cannangstart;
    }

    //lấy ra cân nặng gần với ngày bắt đầu theo hướng xuống dưới
    private  float getkgend(String date){
        Cursor thongkeend = dataBase1.Getdata("Select * from CanNang where  Ngay <'"+date+"'  ORDER BY Ngay ASC");
        Cannangend = 0.0F;
        while (thongkeend.moveToNext())
        {
            if(thongkeend.getFloat(2)>1)
            {
                Cannangend = thongkeend.getFloat(2);
            }
        }
        thongkeend.close();
        dataBase1.close();
        return Cannangend;
    }
}
