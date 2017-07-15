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

public class UploadProblem extends Activity {
	EditText user,aadhar;
	Button add;
	String u;
	 String p;
	 SQLiteDatabase db;
	 String username=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_problem);
		user = (EditText) findViewById(R.id.problem);
		aadhar = (EditText) findViewById(R.id.aadhar);
		add=(Button) findViewById(R.id.lbutton1);
		Intent i=getIntent();
        username= i.getExtras().getString("user");
		add.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				
					
					 u = user.getText().toString();
					 p = aadhar.getText().toString();
					  DBconnector dbc = new DBconnector(UploadProblem.this);
					  
					 
					  
					  dbc.update_problem(u,p); 
					
					 	            		Toast.makeText(UploadProblem.this, "Details added..! " , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(UploadProblem.this,VillagerHome.class);
					            		i.putExtra("user",username);	
					            		startActivity(i);
						            	
						            	}
						            //	return false;
						           
					       
					
					 	
				
		
			}

			
		);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_problem, menu);
		return true;
	}

}
