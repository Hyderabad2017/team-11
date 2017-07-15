package com.example.kibbutz;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAadhar extends Activity {
	EditText user,aadhar;
	Button add;
	String u;
	 String p;
	 SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_aadhar);
		user = (EditText) findViewById(R.id.user);
		aadhar = (EditText) findViewById(R.id.aadhar);
		add=(Button) findViewById(R.id.lbutton1);
		add.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(user.getText().toString().equals("")||aadhar.getText().toString().equals("")){
					
					Toast.makeText(AddAadhar.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					
					 u = user.getText().toString();
					 p = aadhar.getText().toString();
					  DBconnector dbc = new DBconnector(AddAadhar.this);
					  
					  HashMap<String , String> map = new  HashMap<String, String>();

					  map.put("username",user.getText().toString());
					  map.put("aadhar", aadhar.getText().toString());
					  
					  dbc.insert_aadhar(map); 
					
					 	            		Toast.makeText(AddAadhar.this, "Aadhar Details added..! " , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(AddAadhar.this,AdminHome.class);
						            		startActivity(i);
						            	
						            	}
						            //	return false;
						           
					       
						 
						}
					 	
				
		
			}

			
		);
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_aadhar, menu);
		return true;
	}

}
