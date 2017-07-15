package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VillagerHome extends Activity {
String user=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_villager_home);
		 Intent i=getIntent();
	        user= i.getExtras().getString("user");
	        
		Button l,r,a;
		 r=(Button)findViewById(R.id.problem);
	        l=(Button)findViewById(R.id.balance);
	        a=(Button)findViewById(R.id.advice);
	        r.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(VillagerHome.this,UploadProblem.class);
					ad.putExtra("user",user);
					startActivity(ad);
				}
	        	 });
	        l.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(VillagerHome.this,Balance.class);
					ad.putExtra("user",user);
					startActivity(ad);
				}
	        	 });
	        a.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(VillagerHome.this,AdviceDetails.class);
					ad.putExtra("user",user);
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
		getMenuInflater().inflate(R.menu.villager_home, menu);
		return true;
	}

}
