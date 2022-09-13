package com.firebase_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<PeopleModel> contactModelsArrayList;
    public MyBaseAdapter(Context context, ArrayList<PeopleModel> contactModelsArrayList) {
        this.context = context;
        this.contactModelsArrayList=contactModelsArrayList;
    }

    @Override
    public int getCount() {
        return contactModelsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactModelsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.raw_list,null);
        final  PeopleModel contactModel = (PeopleModel) getItem(i);
        TextView tvName =  convertView.findViewById(R.id.edt_fn);
        tvName.setText(contactModelsArrayList.get(i).getFn()+"   "+contactModelsArrayList.get(i).getLn());
        return convertView;
    }
}
