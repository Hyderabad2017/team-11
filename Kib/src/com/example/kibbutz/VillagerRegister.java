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

public class VillagerRegister extends Activity {
EditText user,pass,aadhar,account,ifsc,area;
SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_villager_register);
		user = (EditText) findViewById(R.id.username);
		pass=(EditText) findViewById(R.id.password);
		aadhar=(EditText) findViewById(R.id.aadhar);
		account=(EditText) findViewById(R.id.account);
		ifsc=(EditText) findViewById(R.id.ifsc);
		area=(EditText) findViewById(R.id.area);
		Button		btn = (Button) findViewById(R.id.log);
		
		  btn.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					
					if( user.getText().toString().equals("") && pass.getText().toString().equals("")&& aadhar.getText().toString().equals("")&& account.getText().toString().equals("")&& area.getText().toString().equals(""))
							{
						  Toast.makeText(VillagerRegister.this, "Please fill the blank",Toast.LENGTH_LONG).show();
					  }/*else if(aadhar.getText().toString().length()!=12){
						  Toast.makeText(VillagerRegister.this, "Enter 12 digits",Toast.LENGTH_LONG).show();
					  }*/else{
						  try{

					          db=openOrCreateDatabase("kibbutz",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					         
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }try{
					        	 Cursor cc = db.rawQuery("select * from aadhar_details where aadhar='"+aadhar.getText().toString()+"'", null);
					        	 System.out.println("select * from aadhar_details where aadhar='"+aadhar.getText().toString()+"'");
							     cc.moveToFirst();						            String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            		DBconnector dbc = new DBconnector(VillagerRegister.this);
											  
											  HashMap<String , String> map = new  HashMap<String, String>();

											  map.put("username",user.getText().toString());
											  map.put("password", pass.getText().toString());
											  map.put("account",account.getText().toString());
											  map.put("aadhar", aadhar.getText().toString());
											  map.put("ifsc",ifsc.getText().toString());
										 	  
											  map.put("area",area.getText().toString());
										 	  
											  dbc.insert_villager(map);
											  Toast.makeText(VillagerRegister.this, "Villager Registered ....!", 200).show();				  
											  user.setText("");
											  pass.setText("");
											  aadhar.setText("");
											  area.setText("");
							
											  account.setText("");
											 
											  
											  Intent i = new Intent(VillagerRegister.this, RegisterList.class);
											  startActivity(i);	
						            	}else{
						            		//return true;
						            		Toast.makeText(VillagerRegister.this, "Aadhar No Not valid "   , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(VillagerRegister.this,RegisterList.class);
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
		getMenuInflater().inflate(R.menu.villager_register, menu);
		return true;
	}

}
