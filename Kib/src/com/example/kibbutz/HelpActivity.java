package com.example.kibbutz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HelpActivity extends Activity {
String user=null,userid=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		 Intent i=getIntent();
	        user= i.getExtras().getString("user");
	        userid= i.getExtras().getString("userid");
		Button l,r,a;
		 r=(Button)findViewById(R.id.atransfer);
	       
	        a=(Button)findViewById(R.id.advice);
	        r.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(HelpActivity.this,TransferMoney.class);
					ad.putExtra("user",user);
					ad.putExtra("userid",userid);
					startActivity(ad);
				}
	        	 });
	        
	        a.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(HelpActivity.this,GiveAdvice.class);
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
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
