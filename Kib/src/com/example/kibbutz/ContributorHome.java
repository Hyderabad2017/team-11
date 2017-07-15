package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ContributorHome extends Activity {
String user=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contributor_home);
		 Intent i=getIntent();
	        user= i.getExtras().getString("user");
	        
		Button l,r;
		 r=(Button)findViewById(R.id.problem);
	        l=(Button)findViewById(R.id.balance);
	       
	        r.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(ContributorHome.this,InfoProblem.class);
					ad.putExtra("userid",user);
					startActivity(ad);
				}
	        	 });
	        l.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(ContributorHome.this,BalanceContributor.class);
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
		getMenuInflater().inflate(R.menu.contributor_home, menu);
		return true;
	}

}
