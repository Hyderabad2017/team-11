package com.example.sumanth.studentmainpage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sumanth on 15-Jul-17.
 */

public class Upcoming_List extends BaseAdapter {

    LayoutInflater lin;
    Context con;
    public  ArrayList<String> ExamNames=new ArrayList<>();
    public  ArrayList<String> Request_status=new ArrayList<>();

    public Upcoming_List(Context con,ArrayList ExamNames, ArrayList Request_status){
        this.con=con;
        this.ExamNames=ExamNames;
        this.Request_status=Request_status;
    }

    @Override
    public int getCount() {
        return ExamNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(lin==null)
            lin=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
            view=lin.inflate(R.layout.upcoming_data,null);

        CardView cv=(CardView) view.findViewById(R.id.notif_card);
        cv.setCardBackgroundColor(Color.TRANSPARENT);
        cv.setClickable(true);

        TextView description=(TextView) cv.findViewById(R.id.desc_txt);
        TextView status=(TextView)cv.findViewById(R.id.status_txt);

        description.setText(ExamNames.get(i));
        status.setText(Request_status.get(i));
        return null;
    }
}
