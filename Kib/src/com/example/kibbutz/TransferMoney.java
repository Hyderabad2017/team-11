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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TransferMoney extends ListActivity {
	 ArrayList<String> results = new ArrayList<String>();
	    String user=null,userid=null;
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
	EditText amount=null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_transfer_money);
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        user= i.getExtras().getString("user");
        userid= i.getExtras().getString("userid");
    	 Button a;
        amount = (EditText) findViewById(R.id.amount);
	       
	        a=(Button)findViewById(R.id.lbutton1);
	        a.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					String value = amount.getText().toString();
					  DBconnector dbc = new DBconnector(TransferMoney.this);
					  
					 int amount=Integer.parseInt(value);
					  
					  dbc.update_amount(amount,user,userid); 
					
					 	            		Toast.makeText(TransferMoney.this, "Amount Transfered..! " , Toast.LENGTH_LONG).show();
					Intent ad= new Intent(TransferMoney.this,HelpActivity.class);
					ad.putExtra("user",user);
					ad.putExtra("userid",userid);
					startActivity(ad);
				}
	        	 });
        openAndQueryDatabase();
        
        displayResultList();
       
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Tranfer Amount");
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
			Cursor c = newDB.rawQuery("SELECT * from villager where username='"+user+"'", null);

	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String amount = c.getString(c.getColumnIndex("amount"));
	    				String username=c.getString(c.getColumnIndex("username"));
	    				String account=c.getString(c.getColumnIndex("account"));
	    				String ifsc=c.getString(c.getColumnIndex("ifsc"));
	    				String name="Name :"+username+"\n Account :"+account+"\n Ifsc :"+ifsc+"\n Amount :"+amount;
	    				results.add(name);
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
