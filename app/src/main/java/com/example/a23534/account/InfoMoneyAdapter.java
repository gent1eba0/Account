package com.example.a23534.account;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 23534 on 2020/4/28.
 */

public class InfoMoneyAdapter extends ArrayAdapter<Info> {
    private int moneyID;
    public InfoMoneyAdapter(Context context, int resource, List<Info> objects) {
        super(context, resource, objects);
        moneyID = resource;
    }
    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        Info info = getItem(postion);
        View view = LayoutInflater.from(getContext()).inflate(moneyID, parent, false);
        TextView tvdata     = (TextView) view.findViewById(R.id.tv_date);
        TextView tvmoney    = (TextView) view.findViewById(R.id.tv_money);
        TextView tvplace    = (TextView) view.findViewById(R.id.tv_place);
        TextView tvclassify = (TextView) view.findViewById(R.id.tv_classify);
        tvdata.setText(info.date);
        if(info.whichway.equals("收入")){
                tvmoney.setTextColor(Color.parseColor("#FF0000"));
                tvmoney.setText("+" + info.money + "");
        }else{
            tvmoney.setTextColor(Color.parseColor("#00FF00"));
            tvmoney.setText("-" + info.money + "");
        }
        tvplace.setText(info.place);
        tvclassify.setText(info.classify);
        return view;
    }
}
