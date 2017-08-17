package com.example.dell.finalfirstproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by owner on 13-Jul-16.
 */
public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> name;

    public CustomListAdapter(Activity context, List<String> name) {
        super(context, R.layout.mylist,name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=name;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);


        txtTitle.setText(name.get(position));

        return rowView;

    };
}
