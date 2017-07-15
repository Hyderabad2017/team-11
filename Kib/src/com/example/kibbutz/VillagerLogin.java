package com.example.kibbutz;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VillagerLogin extends Activity {
	EditText user, pass;
	Button a;
	 String u;
	 String p;
	 SQLiteDatabase db;
	 String aa = "admin";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_villager_login);
		user = (EditText) findViewById(R.id.user);
		pass = (EditText) findViewById(R.id.password);
		a=(Button) findViewById(R.id.lbutton1);
		a.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(user.getText().toString().equals("")||pass.getText().toString().equals("")){
					
					Toast.makeText(VillagerLogin.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					
					 u = user.getText().toString();
					 p = pass.getText().toString();
						if(user.getText().toString().equals(aa)&& pass.getText().toString().equals(aa)){
							
							
						}else{
					
					 try{

					          db=openOrCreateDatabase("kibbutz",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					         
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }try{
					        	 Cursor cc = db.rawQuery("select * from villager where username = '"+u+"' and password = '"+p+"' ", null);
							     cc.moveToFirst();						            String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            	//return true;
						            scan g=new scan();
						            g.execute();
						            
						            		Toast.makeText(VillagerLogin.this, "Welcome Villager "  + u , Toast.LENGTH_LONG).show();
					            		Intent i = new Intent(VillagerLogin.this,VillagerHome.class);
					            		i.putExtra("user",user.getText().toString());	
					            		startActivity(i);
						            	}else{
						            		 Toast.makeText(VillagerLogin.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
						            //	return false;
						           
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }
						
						 
						}
					 	
				}
				
			}

			
		});
	}
	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(VillagerLogin.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.villager_login, menu);
		return true;
	}

}
