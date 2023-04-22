package com.example.myapplication12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ThucAnAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThucAn> thucAnList;

    public ThucAnAdapter(Context context, int layout, List<ThucAn> thucAnList) {
        this.context = context;
        this.layout = layout;
        this.thucAnList = thucAnList;
    }

    @Override
    public int getCount() {
        return thucAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder1{
        TextView txday,txsang,txtrua,txtoi,txphu;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder1  holder1;
        if (view==null)
        {
            holder1 = new ThucAnAdapter.ViewHolder1();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder1.txday = (TextView) view.findViewById(R.id.ngayan);
            holder1.txsang = (TextView) view.findViewById(R.id.sang);
            holder1.txtrua = (TextView) view.findViewById(R.id.trua);
            holder1.txtoi = (TextView) view.findViewById(R.id.toi);
            holder1.txphu = (TextView) view.findViewById(R.id.phu);
            view.setTag(holder1);
        }
        else
        {
            holder1 = (ViewHolder1) view.getTag();
        }
        ThucAn thucAn = thucAnList.get(i);
        holder1.txday.setText(thucAn.getNgay());
        if(thucAn.getBuasang().trim().equals(""))
        {
            holder1.txsang.setText(context.getString(R.string.Buasang));
        }
        else
        {
            holder1.txsang.setText(thucAn.getBuasang());
        }
        if(thucAn.getBuatrua().trim().equals(""))
        {
            holder1.txtrua.setText(context.getString(R.string.Buatrua));
        }
        else
        {
            holder1.txtrua.setText(thucAn.getBuatrua());
        }
        if(thucAn.getBuatoi().trim().equals(""))
        {
            holder1.txtoi.setText(context.getString(R.string.Buatoi));
        }
        else
        {
            holder1.txtoi.setText(thucAn.getBuatoi());
        }
        if(thucAn.getBuaphu().trim().equals(""))
        {
            holder1.txphu.setText(context.getString(R.string.Buaphu));
        }
        else
        {
            holder1.txphu.setText(thucAn.getBuaphu());
        }
        return view;
    }
}
