package com.example.kibbutz;

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

public class GiveAdvice extends Activity {
String user=null;
SQLiteDatabase db;EditText advice=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_give_advice);
		Intent i=getIntent();
        user= i.getExtras().getString("user");
        Button a;
        advice = (EditText) findViewById(R.id.advice);
	       
	        a=(Button)findViewById(R.id.lbutton1);
	        a.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					String value = advice.getText().toString();
					  DBconnector dbc = new DBconnector(GiveAdvice.this);
					  
					 
					  
					  dbc.update_advice(value,user); 
					
					 	            		Toast.makeText(GiveAdvice.this, "Advice Given..! " , Toast.LENGTH_LONG).show();
					Intent ad= new Intent(GiveAdvice.this,HelpActivity.class);
					ad.putExtra("user",user);
					startActivity(ad);
				}
	        	 });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.give_advice, menu);
		return true;
	}

}
