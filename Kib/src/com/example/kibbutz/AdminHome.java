package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AdminHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);
		Button aadhar,pan;
		aadhar=(Button)findViewById(R.id.aadhar);
		aadhar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,AddAadhar.class);
				startActivity(ad);
			}
        	 });
		pan=(Button)findViewById(R.id.pan);
		pan.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,AddPan.class);
				startActivity(ad);
			}
        	 });
		 Button logout=(Button)findViewById(R.id.logout);
		  logout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "logging out...!!", Toast.LENGTH_LONG).show();
					Intent ad= new Intent(getApplicationContext(),MainActivity.class);
					startActivity(ad);
				} 
	        	 }); 


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_home, menu);
		return true;
	}

}
