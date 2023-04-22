package com.example.myapplication12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CannangAdapter extends BaseAdapter {
    private Context context;
    private List<CangNang> cangNangList;
    private int layout;

    public CannangAdapter(Context context, List<CangNang> cangNangList, int layout) {
        this.context = context;
        this.cangNangList = cangNangList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return cangNangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    //tạo holder để để tránh ánh xạ lại dữ liệu khi gọi lại gàm getView(mất thời gian)
    public  class ViewHolder{
        TextView tx1,tx2,tx3,tx4,tx5;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tx1 = (TextView) view.findViewById(R.id.ngay);
            holder.tx2 = (TextView) view.findViewById(R.id.cannang);
            holder.tx3 = (TextView) view.findViewById(R.id.tap);
            holder.tx4 = (TextView) view.findViewById(R.id.uong);
            holder.tx5 = (TextView) view.findViewById(R.id.note);
            view.setTag(holder);
        }
        else
        {
        holder = (ViewHolder) view.getTag();
        }
        CangNang cangNang = cangNangList.get(i);
        holder.tx1.setText(cangNang.getDate());
        if(cangNang.getCanNang()<=0.0F)
        {
            holder.tx2.setText("Null");
        }
        else
        {
            holder.tx2.setText(String.valueOf(cangNang.getCanNang()));
        }
        if(cangNang.isTap())
        {
            holder.tx3.setText("O");
        }
        else
        {
            holder.tx3.setText("X");
        }
        if(cangNang.isUong())
        {
            holder.tx4.setText("O");
        }
        else
        {
            holder.tx4.setText("X");
        }
        holder.tx5.setText(cangNang.getNote());
        return view;
    }
}
