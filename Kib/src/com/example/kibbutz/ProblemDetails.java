package com.example.kibbutz;

import java.util.ArrayList;
import java.util.Collections;



import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProblemDetails extends ListActivity {
	 ArrayList<String> results = new ArrayList<String>();
	    String user=null,userid=null;
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_problem_details);
        super.onCreate(savedInstanceState);
        Button b=null;
        Intent i=getIntent();
        user= i.getExtras().getString("user");
        userid= i.getExtras().getString("userid");
        b=(Button)findViewById(R.id.ehelp);
        b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(ProblemDetails.this,HelpActivity.class);
				ad.putExtra("user", user);
				ad.putExtra("userid",userid);
				startActivity(ad);
			}
        	 });
        

        openAndQueryDatabase();
        
        displayResultList();
               
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Problem Details");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        
     final   ListView lv=getListView();
        lv.setTextFilterEnabled(true);
        

      
		
	}
	private void openAndQueryDatabase() {
		try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT problem from villager where username='"+user+"'", null);

	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String Name = c.getString(c.getColumnIndex("problem"));
	    				
	    				results.add(Name);
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
