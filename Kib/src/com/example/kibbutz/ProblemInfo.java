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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProblemInfo extends ListActivity {
	 ArrayList<String> results = new ArrayList<String>();
	    String area=null,userid=null;
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        area= i.getExtras().getString("area");
        userid= i.getExtras().getString("userid");
        openAndQueryDatabase();
        
        displayResultList();
       
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Villager Details");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        
     final   ListView lv=getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

               //If you wanna send any data to nextActicity.class you can use
               
            	
                 Intent anotherActivityIntent = new Intent(getApplicationContext(), ProblemDetails.class);
            
                 System.out.println(results.size());
               String na=(String)(lv.getItemAtPosition(position));
                 anotherActivityIntent.putExtra("user",na);
                 anotherActivityIntent.putExtra("userid",userid);
                 i++;
                
                 startActivity(anotherActivityIntent); 
            
            }
          });

      
		
	}
	private void openAndQueryDatabase() {
		try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT username from villager where area='"+area+"'", null);

	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String Name = c.getString(c.getColumnIndex("username"));
	    				
	    				results.add(Name);
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
