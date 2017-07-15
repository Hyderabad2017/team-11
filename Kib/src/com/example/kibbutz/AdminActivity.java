package com.example.kibbutz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity {
	EditText user,pass;
	Button ab;


	String u;
		 String p;
		 SQLiteDatabase db;
		 String aa = "admin";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		user = (EditText) findViewById(R.id.user);
		pass = (EditText) findViewById(R.id.password);
		ab=(Button) findViewById(R.id.lbutton1);
		ab.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(user.getText().toString().equals("")||pass.getText().toString().equals("")){
					
					Toast.makeText(AdminActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					
					 u = user.getText().toString();
					 p = pass.getText().toString();
						 if(user.getText().toString().trim().equals("admin")&& pass.getText().toString().trim().equals("admin")){
					
					 	            		Toast.makeText(AdminActivity.this, "Welcome..! "  +  u, Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(AdminActivity.this,AdminHome.class);
						            		startActivity(i);
						            	}else{
						            		 Toast.makeText(AdminActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
						            //	return false;
						           
					       
						 
						}
					 	
				
		
			}

			
		);
		
	}
	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(AdminActivity.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
