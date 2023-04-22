package com.example.myapplication12;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Fragment1 extends Fragment {
    private ArrayList<CangNang> cangNangArrayList;
    private CannangAdapter cannangAdapter;
    private DataBase dataBase;
    private ListView lt1;
    private List<String> fullday ;
    private List<String> dayexits,daytontai ;
    private boolean checkshow = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment1,container,false);
        lt1 = (ListView) view.findViewById(R.id.list1);
        cangNangArrayList = new ArrayList<>();
        cannangAdapter = new CannangAdapter(getActivity(),cangNangArrayList,R.layout.apdapter_tab1);
        lt1.setAdapter(cannangAdapter);

        //khai báo csdl
        dataBase = new DataBase(this.getActivity(),"CanNang.sqlLite",null,1);
        //thực hiện tạo bảng
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS CanNang(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ngay date not null UNIQUE,CanNang float,TapTheDuc bit,UongNuoc bit , Note Nvarchar(200)) ");
        insertfullday();//kiểm tra ngày để thêm mới
        SelectSQL();//tải lại dữ liệu
        if(checkshow)//hàm kiểm tra nếu ngày mới thì hiện showdialog sửa
        {
            ShowDialogUpdate(cangNangArrayList.get(0));
            checkshow=false;
        }

        //sự kiện click fab (ở góc màn hình lông)
        FloatingActionButton fab = view.findViewById(R.id.fab);
        lt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showdialog(cangNangArrayList.get(i));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogUpdate(cangNangArrayList.get(0));
            }
        });


        //sự kiện khi giữ lâu item trong listView
        lt1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                resetdataitem(cangNangArrayList.get(i));
                return true;
            }
        });
        return view;
    }

    // show dialog xem can nang
    private void showdialog(CangNang cangNang){
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_viewcannang);
        TextView txday = (TextView) dialog.findViewById(R.id.ngayview);
        TextView txcan = dialog.findViewById(R.id.cannangview);
        TextView txtaptheduc = dialog.findViewById(R.id.taptheducview);
        TextView txuog = dialog.findViewById(R.id.uongnuocview);
        TextView txgichu = dialog.findViewById(R.id.ghichuview);
        Button bthuy = dialog.findViewById(R.id.huyview);
        Button btsua = dialog.findViewById(R.id.Suaview);
        txday.setText(cangNang.getDate());
        txcan.setText(cangNang.getCanNang()+"Kg");
        if(cangNang.isTap())
        {
            txtaptheduc.setText(getString(R.string.updatetap));
        }
        else{
            txtaptheduc.setText(getString(R.string.nottap));
        }
        if(cangNang.isUong())
        {
            txuog.setText(getString(R.string.upda));
        }
        else{
            txuog.setText(getString(R.string.notuong));
        }
        txgichu.setText(cangNang.getNote());
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ShowDialogUpdate(cangNang);
            }
        });
    dialog.show();
    }

    //show dialog khi an vao sua
    private void ShowDialogUpdate(CangNang cangNang){
        Dialog dialog1 = new Dialog(getActivity());
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dialog_suacannang);
        EditText editcan,editghichu;
        TextView editDay;
        CheckBox cbtap,cbuong;
        Button btupdate,bthuyupdate;
        editDay = dialog1.findViewById(R.id.editTextDate);
        editcan = dialog1.findViewById(R.id.updateCanang);
        editghichu = dialog1.findViewById(R.id.updateghichu);
        cbtap = dialog1.findViewById(R.id.checktap);
        cbuong = dialog1.findViewById(R.id.checkuong);
        btupdate = dialog1.findViewById(R.id.updateluu);
        bthuyupdate = dialog1.findViewById(R.id.updatehuy);
        editcan.setText(cangNang.getCanNang()+"");
        editDay.setText(cangNang.getDate()+"");
        editghichu.setText(cangNang.getNote()+"");
        if (cangNang.isTap()) {
            cbtap.setChecked(true);
        } else {
            cbtap.setChecked(false);
        }
        if(cangNang.isUong())
        {
            cbuong.setChecked(true);
        }
        else
        {
            cbuong.setChecked(false);
        }
        bthuyupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        //sự kiện khi click vào chon ngày
        editDay.setOnClickListener(new View.OnClickListener() {
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
                        String a = simpleDateFormat.format(calendar.getTime());
                        //tạo mảng để thêm ngày tồn tại
                        daytontai = new ArrayList<>();
                        Cursor trocannang = dataBase.Getdata("Select * from CanNang ORDER BY Ngay DESC ");
                        while (trocannang.moveToNext())
                        {
                            daytontai.add(trocannang.getString(1));
                        }
                        trocannang.close();
                        dataBase.close();
                        //kiểm tra xem ngày nhập có trong phạm vi cho phép ?
                        if(daytontai.contains(a)==false){
                            Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi),Toast.LENGTH_LONG).show();
                            a=daytontai.get(0);//nếu không tồn tại thì gán = ngày hôm nay
                            daytontai.clear();
                        }

                        //chạy con trỏ để gán dữ liệu vào các ô textview
                        Cursor cannanga = dataBase.Getdata("Select * from CanNang where Ngay = '"+a+"'");
                        float can = 0.0F;
                        boolean tap =false;
                        boolean uong = false;
                        String note = "";
                        while (cannanga.moveToNext())
                        {
                             can = cannanga.getFloat(2);
                             tap = cannanga.getInt(3) > 0;
                             uong = cannanga.getInt(4) > 0;
                             note = cannanga.getString(5);
                        }
                        cannanga.close();
                        dataBase.close();

                        editcan.setText(can+"");
                        editghichu.setText(note);
                        if (tap) {
                            cbtap.setChecked(true);
                        } else {
                            cbtap.setChecked(false);
                        }
                        if(uong)
                        {
                            cbuong.setChecked(true);
                        }
                        else
                        {
                            cbuong.setChecked(false);
                        }
                        editDay.setText(a);
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });

        // sự kiện khi click thêm
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String datehandle = editDay.getText().toString().trim();
            String editcan1=editcan.getText().toString().trim();
            if(editcan1.equals("")||editcan1.equals(null)||editcan1.equals("."))
            {
                Toast.makeText(getActivity(),getString(R.string.nhapdu)+"",Toast.LENGTH_LONG).show();
            }
            else
            {
                double cannang1 = Double.parseDouble(editcan.getText().toString().trim());
                if(cannang1<=0)
                {
                    Toast.makeText(getActivity(),getString(R.string.saican)+"",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int uongnc=0,taptt = 0;
                    if(cbtap.isChecked())
                    {
                        taptt=1;
                    }
                    if(cbuong.isChecked())
                    {
                        uongnc = 1;
                    }

                    String noteupdate = editghichu.getText().toString().trim();
                    dataBase.QueryData("Update CanNang SET CanNang = "+cannang1+", UongNuoc="+uongnc+" ,TapTheDuc="+taptt+", Note='"+noteupdate+"' where Ngay='"+datehandle+"' ");
                    Toast.makeText(getActivity(),getString(R.string.thanhcong)+"",Toast.LENGTH_SHORT).show();
                    dialog1.dismiss();
                    SelectSQL();
            }
            }
            }
        });
        dialog1.show();
    }


    //cập nhật lại listview khi adepter thay đổi
    public  void SelectSQL(){
        Cursor cannang = dataBase.Getdata("Select * from CanNang ORDER BY Ngay DESC");
        cangNangArrayList.clear();
        while (cannang.moveToNext())
        {

            String ngay = cannang.getString(1);
            int id = cannang.getInt(0);
            float can = cannang.getFloat(2);
            boolean tap = cannang.getInt(3) > 0;
            boolean uong = cannang.getInt(4) > 0;
            String note = cannang.getString(5);
            cangNangArrayList.add(new CangNang(can,id,ngay,tap,uong,note));
        }
        cannang.close();
        dataBase.close();
        cannangAdapter.notifyDataSetChanged();
    }

    //kiểm tra ngày để thêm
    private void  insertfullday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        fullday = new ArrayList<>();
        dayexits = new ArrayList<>();
        Cursor cannanga = dataBase.Getdata("Select * from CanNang ");

        while (cannanga.moveToNext())
        {
            dayexits.add(cannanga.getString(1));
        }
        cannanga.close();
        dataBase.close();

    for (int i = 0;i<=100;i++)
    {
        fullday.add(sdf.format(c.getTime()));
        if(dayexits.contains(sdf.format(c.getTime())))
        {
        }
        else
        {
            dataBase.QueryData("INSERT INTO CanNang VALUES(NULL,'"+fullday.get(i)+"',0.0,0,0,'') ");
            checkshow=true;
        }
        c.add(Calendar.DATE, -1);
    }
    fullday.clear();
    dayexits.clear();
    }

//Xóa item trong listview
    private  void resetdataitem(CangNang cangNang){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this.getActivity());
        aBuilder.setTitle(getString(R.string.thongbao));
        aBuilder.setIcon(R.mipmap.ic_launcher);
        aBuilder.setMessage(getString(R.string.xacnhanxoa)+cangNang.getDate()+"?");
        aBuilder.setPositiveButton(getString(R.string.co), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBase.QueryData("Update CanNang SET CanNang =0.0 , UongNuoc=0 ,TapTheDuc=0, Note='' where Ngay='"+cangNang.getDate()+"' ");
               Toast.makeText(getActivity(),getString(R.string.xoathanhcong),Toast.LENGTH_LONG).show();
                SelectSQL();
            }
        });
        aBuilder.setNegativeButton(getString(R.string.khong), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        aBuilder.show();
    }
}
