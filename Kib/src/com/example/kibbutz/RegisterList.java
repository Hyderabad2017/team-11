package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_list);
		Button l,r,b;
		 r=(Button)findViewById(R.id.villager);
	        l=(Button)findViewById(R.id.contributor);
	        b=(Button)findViewById(R.id.back);
	        l.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(RegisterList.this,ContributorRegister.class);
					startActivity(ad);
				}
	        	 });
	        r.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(RegisterList.this,VillagerRegister.class);
					startActivity(ad);
				}
	        	 });
	        b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(RegisterList.this,MainActivity.class);
					startActivity(ad);
				}
	        	 });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_list, menu);
		return true;
	}

}
