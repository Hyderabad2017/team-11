package com.example.kibbutz;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBconnector extends SQLiteOpenHelper {

	public DBconnector(Context context) {
		super(context, "kibbutz", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS villager(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,aadhar TEXT,account TEXT,ifsc TEXT,amount INTEGER,area TEXT,problem TEXT,advice TEXT)";
		db.execSQL(sql);
		String sql1="CREATE TABLE IF NOT EXISTS aadhar_details(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,aadhar TEXT)";
		db.execSQL(sql1);
		String sql2 = "CREATE TABLE IF NOT EXISTS contributor(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,pan TEXT,account TEXT,ifsc TEXT,amount INTEGER)";
		db.execSQL(sql2);
		String sql3="CREATE TABLE IF NOT EXISTS pan_details(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,pan TEXT)";
		db.execSQL(sql3);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE villager IF EXITS");
		db.execSQL("DROP TABLE aadhar_details IF EXITS");
		db.execSQL("DROP TABLE contributor IF EXITS");
		db.execSQL("DROP TABLE pan_details IF EXITS");
		onCreate(db);
	}
	
	
	public void insert_villager(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		cv.put("password",  map.get("password"));
		cv.put("aadhar",  map.get("aadhar"));
		cv.put("account",  map.get("account"));
		cv.put("ifsc",  map.get("ifsc"));
		cv.put("amount",  0);
		cv.put("area",  map.get("area"));
		cv.put("problem",  "NOT UPDATED");
		cv.put("advice",  "No Advice");
		sb.insert("villager", null, cv);
	}
	public void insert_contributor(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		cv.put("password",  map.get("password"));
		cv.put("pan",  map.get("pan"));
		cv.put("account",  map.get("account"));
		cv.put("ifsc",  map.get("ifsc"));
		cv.put("amount", map.get("amount"));
		sb.insert("contributor", null, cv);
	}
	public void insert_aadhar(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		
		cv.put("aadhar",  map.get("aadhar"));
		
		sb.insert("aadhar_details", null, cv);
	}
	
	public void insert_pan(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		
		cv.put("pan",  map.get("pan"));
		
		sb.insert("pan_details", null, cv);
	}
	
	
	
	public void update_problem(String problem,String aadhar){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("problem",problem); //These Fields should be your String values of actual column names

sb.update("villager", cv, "aadhar='"+aadhar+"'", null);
	
	}
	
	public void update_advice(String advice,String user){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("advice",advice); //These Fields should be your String values of actual column names

sb.update("villager", cv, "username='"+user+"'", null);
	
	}
	public void update_amount(int amount,String user,String userid){
		SQLiteDatabase sb = this.getWritableDatabase();
		
		

		sb.execSQL("update villager set amount=amount+"+amount+" where username='"+user+"'");
		sb.execSQL("update contributor set amount=amount-"+amount+" where username='"+userid+"'");
	}
	
public void deleteUser(String value){
	SQLiteDatabase sb = this.getWritableDatabase();

	sb.execSQL("delete from user where username='"+value+"'");
	

}



}
