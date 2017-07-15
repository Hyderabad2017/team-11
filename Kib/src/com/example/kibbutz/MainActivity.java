package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
	        Button a,l,r;
	        a=(Button)findViewById(R.id.admin);
	        l=(Button)findViewById(R.id.login);
	        r=(Button)findViewById(R.id.register);
	       
	        a.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(MainActivity.this,AdminActivity.class);
					startActivity(ad);
				}
	        	 });
	        r.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(MainActivity.this,RegisterList.class);
					startActivity(ad);
				}
	        	 });
	        l.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(MainActivity.this,LoginList.class);
					startActivity(ad);
				}
	        	 });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
