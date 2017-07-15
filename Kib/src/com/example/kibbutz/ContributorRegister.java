package com.example.kibbutz;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContributorRegister extends Activity {
	EditText user,pass,pan,account,ifsc,amount;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contributor_register);
		user = (EditText) findViewById(R.id.username);
		pass=(EditText) findViewById(R.id.password);
		pan=(EditText) findViewById(R.id.pan);
		account=(EditText) findViewById(R.id.account);
		ifsc=(EditText) findViewById(R.id.ifsc);
		amount=(EditText) findViewById(R.id.amount);
		Button		btn = (Button) findViewById(R.id.log);
		
		  btn.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					
					if( user.getText().toString().equals("") && pass.getText().toString().equals("")&& pan.getText().toString().equals("")&& account.getText().toString().equals("")&& amount.getText().toString().equals(""))
							{
						  Toast.makeText(ContributorRegister.this, "Please fill the blank",Toast.LENGTH_LONG).show();
					  }/*else if(pan.getText().toString().length()!=12){
						  Toast.makeText(ContributorRegister.this, "Enter 12 digits",Toast.LENGTH_LONG).show();
					  }*/else{
						  try{

					          db=openOrCreateDatabase("kibbutz",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					         
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }try{
					        	 Cursor cc = db.rawQuery("select * from pan_details where pan='"+pan.getText().toString()+"'", null);
					        	 System.out.println("select * from pan_details where pan='"+pan.getText().toString()+"'");
							     cc.moveToFirst();						            String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            		DBconnector dbc = new DBconnector(ContributorRegister.this);
											  
											  HashMap<String , String> map = new  HashMap<String, String>();

											  map.put("username",user.getText().toString());
											  map.put("password", pass.getText().toString());
											  map.put("account",account.getText().toString());
											  map.put("pan", pan.getText().toString());
											  map.put("ifsc",ifsc.getText().toString());
										 	  
											  map.put("amount",amount.getText().toString());
										 	  
											  dbc.insert_contributor(map);
											  Toast.makeText(ContributorRegister.this, "Contributor Registered ....!", 200).show();				  
											  user.setText("");
											  pass.setText("");
											  pan.setText("");
											  amount.setText("");
							
											  account.setText("");
											 
											  
											  Intent i = new Intent(ContributorRegister.this, RegisterList.class);
											  startActivity(i);	
						            	}else{
						            		//return true;
						            		Toast.makeText(ContributorRegister.this, "pan No Not valid "   , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(ContributorRegister.this,RegisterList.class);
						            		startActivity(i);
						            	
						            	}
						            	}
						            //	return false;
						           
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }
						
						  
					  }
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contributor_register, menu);
		return true;
	}

}
