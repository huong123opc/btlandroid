package com.example.myapplication12;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment2 extends Fragment {
    private ArrayList<ThucAn> thucAnArrayList;
    private ThucAnAdapter thucAnAdapter;
    private DataBase dataBaseThucAn;
    private ListView lt2;
    private  List<String> fulldaythucan ;
    private List<String> dayexitsthucan ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment2,container,false);
       lt2 = (ListView)view.findViewById(R.id.list2);
       thucAnArrayList = new ArrayList<>();
        thucAnAdapter = new ThucAnAdapter(getActivity(),R.layout.apdapter_tab2,thucAnArrayList);
        lt2.setAdapter(thucAnAdapter);
        dataBaseThucAn = new DataBase(this.getActivity(),"Thucan.sqlLite",null,1);
        dataBaseThucAn.QueryData("CREATE TABLE IF NOT EXISTS ThucAn(ID INTEGER PRIMARY KEY AUTOINCREMENT,Ngay date not null UNIQUE,BuaSang Nvarchar(200),BuaTrua Nvarchar(200),BuaToi Nvarchar(200),BuaPhu Nvarchar(200)) ");
        loaddata();
        Update();
        FloatingActionButton fab1 = view.findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogthucan(thucAnArrayList.get(0));
            }
        });
        lt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogthucan(thucAnArrayList.get(i));
            }
        });
        lt2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                resetdataitem(thucAnArrayList.get(i));
                return true;
            }
        });
        return view;
    }

    private void showDialogthucan(ThucAn thucAn){
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_formthucan);
        TextView txday  = dialog.findViewById(R.id.date);
        EditText edsang = dialog.findViewById(R.id.buasang);
        EditText edtrua = dialog.findViewById(R.id.buatrua);
        EditText edtoi = dialog.findViewById(R.id.buatoi);
        EditText edphu = dialog.findViewById(R.id.buaphu);
        Button btluu  = dialog.findViewById(R.id.luuthucan);
        Button bthuy  = dialog.findViewById(R.id.huythucan);
        txday.setText(thucAn.getNgay());
        edsang.setText(thucAn.getBuasang().trim());
        edtrua.setText(thucAn.getBuatrua().trim());
        edtoi.setText(thucAn.getBuatoi().trim());
        edphu.setText(thucAn.getBuaphu().trim());
        txday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dayexitsthucan = new ArrayList<>();
                        Cursor trothucan = dataBaseThucAn.Getdata("Select * from ThucAn ORDER BY Ngay DESC ");
                        while (trothucan.moveToNext())
                        {
                            dayexitsthucan.add(trothucan.getString(1));
                        }
                        trothucan.close();
                        dataBaseThucAn.close();
                        String daythucan = simpleDateFormat.format(calendar.getTime());
                        if(dayexitsthucan.contains(daythucan)==false){
                            Toast.makeText(getActivity(),getString(R.string.ngoaiphamvi),Toast.LENGTH_LONG).show();
                            daythucan=dayexitsthucan.get(0);
                            dayexitsthucan.clear();
                        }
                        Cursor thucan = dataBaseThucAn.Getdata("Select * from ThucAn where Ngay = '"+daythucan+"'");
                        String sang="",trua="",toi="",phu="";
                        while (thucan.moveToNext())
                        {
                            sang = thucan.getString(2).trim();
                            trua = thucan.getString(3).trim();
                            toi = thucan.getString(4).trim();
                            phu = thucan.getString(5).trim();
                        }
                        thucan.close();
                        dataBaseThucAn.close();
                        txday.setText(daythucan);
                        edsang.setText(sang);
                        edtrua.setText(trua);
                        edtoi.setText(toi);
                        edphu.setText(phu);
                    }
                },nam,thang,ngay) ;
            datePickerDialog1.show();
            }
        });
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dayan = txday.getText().toString().trim();
                String buasang = edsang.getText().toString().trim();
                String buatrua = edtrua.getText().toString().trim();
                String buatoi = edtoi.getText().toString().trim();
                String buaphu = edphu.getText().toString().trim();
                dataBaseThucAn.QueryData("Update ThucAn SET BuaSang = '"+buasang+"' ,BuaTrua = '"+buatrua+"',BuaToi = '"+buatoi+"',BuaPhu='"+buaphu+"' where Ngay = '"+dayan+"'");
                Toast.makeText(getActivity(),getString(R.string.thanhcong),Toast.LENGTH_LONG).show();
                Update();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //cap nhat lai listview va sql
    private void Update(){
        Cursor thucan = dataBaseThucAn.Getdata("Select * from ThucAn ORDER BY Ngay DESC ");
        thucAnArrayList.clear();
        while (thucan.moveToNext())
        {
            int ID  = thucan.getInt(0);
            String ngayan = thucan.getString(1);
            String sang = thucan.getString(2);
            String trua = thucan.getString(3);
            String toi = thucan.getString(4);
            String phu = thucan.getString(5);
            thucAnArrayList.add(new ThucAn(ID,ngayan,sang,trua,toi,phu));
        }
        thucan.close();
        dataBaseThucAn.close();
        thucAnAdapter.notifyDataSetChanged();
    }


    //kiểm tra để lưu thêm ngày
    private void loaddata(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        fulldaythucan = new ArrayList<>();
        dayexitsthucan = new ArrayList<>();
        Cursor cursor = dataBaseThucAn.Getdata("Select * from ThucAn");
        while (cursor.moveToNext())
        {
            //ngày tồn tại trong csdl
             dayexitsthucan.add(cursor.getString(1));
        }
        cursor.close();
        dataBaseThucAn.close();
        for (int i = 0;i<=100;i++)
        {
            fulldaythucan.add(sdf.format(c.getTime()));//thêm ngày
            if(dayexitsthucan.contains(sdf.format(c.getTime())))//kiểm tra tồn tại của ngày (nếu k trùng thì thêm dữ liệu)
            {
            }
            else
            {
                dataBaseThucAn.QueryData("INSERT INTO ThucAn VALUES(NULL,'"+fulldaythucan.get(i)+"','','','','') ");
            }
            c.add(Calendar.DATE, -1);
        }
        fulldaythucan.clear();
        dayexitsthucan.clear();
    }


    //xoa 1 item trong listView
    private  void resetdataitem(ThucAn thucAn){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this.getActivity());
        aBuilder.setTitle(getString(R.string.thongbao));
        aBuilder.setIcon(R.mipmap.ic_launcher);
        aBuilder.setMessage(getString(R.string.xacnhanxoa)+thucAn.getNgay()+"?");
        aBuilder.setPositiveButton(getString(R.string.co), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBaseThucAn.QueryData("Update ThucAn SET BuaSang ='' ,BuaTrua ='' ,BuaToi ='' ,BuaPhu='' where Ngay ='"+thucAn.getNgay()+"' ");
                Toast.makeText(getActivity(),getString(R.string.xoathanhcong),Toast.LENGTH_LONG).show();
                Update();
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
