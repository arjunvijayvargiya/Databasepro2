package com.example.databasepro2;

import java.io.IOException;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;

public class Group extends Activity  {
	
	int i=0;
	int j;
	String showcasename="";
	String phn="";
	public int positionitem;
	AutoCompleteTextView textView;
	DBAdapter db;
	DataBaseHelper myDbHelper1;
	String[] employees= new String[2000];
	String[] phnx=new String[2000];
	int auto = 1;
	Button addb,regb,see;
	String groupname,groupmembers,chknme;
	EditText etname;
	
	String gphn="";

	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group);
		addb=(Button) findViewById(R.id.addb);
		regb=(Button) findViewById(R.id.regb);
		for(int i = auto;i<2000;i++)
        {
        	employees[i] = "emp" + i; 
        }
        for(int i=auto;i<2000;i++)
        {
        	phnx[i]="phn"+i;
        }
        AdView adView = (AdView)this.findViewById(R.id.adView2);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		etname=(EditText) findViewById(R.id.grpnmedtxt);
		db = new DBAdapter(this);
        myDbHelper1 = new DataBaseHelper(this);
        
        try {
        	 
        	myDbHelper1.createDataBase();
        	//Toast.makeText(getBaseContext(), "hey your server has been accessed",Toast.LENGTH_LONG).show();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
        try {
   		 
     		myDbHelper1.openDataBase();
     		//Toast.makeText(getBaseContext(), "superb server has been opened",Toast.LENGTH_LONG).show();
     	}catch(SQLException sqle){
     		//Toast.makeText(getBaseContext(), "not working",Toast.LENGTH_LONG).show();
     		throw sqle;
     
     	}
        GetContactsserver();
        /*coffee("IT");
        initcoffee("IT");
        coffee("AGM(O&M)");
        initcoffee("AGM(O&M)");
        coffee("AUTOBASE");
        initcoffee("AUTOBASE");
        coffee("BE");
        initcoffee("BE");
        coffee("BMD");
        initcoffee("BMD");
        coffee("C&I");
        initcoffee("C&I");
        coffee("C&M");
        initcoffee("C&M");
        coffee("CHD");
        initcoffee("CHD");
        coffee("CHP");
        initcoffee("CHP");
        coffee("CIVIL");
        initcoffee("CIVIL");
        coffee("COAL COORDINATION");
        initcoffee("COAL COORDINATION");
        coffee("EEMG");
        initcoffee("EEMG");
        coffee("EMD");
        initcoffee("EMD");
        coffee("EMG-AUD");
        initcoffee("EMG-AUD");
        coffee("F&A");
        initcoffee("F&A");
        coffee("FQA");
        initcoffee("FQA");
        coffee("HOSP");
        initcoffee("HOSP");
        
        coffee("HR");
        initcoffee("HR");
        coffee("MPD");
        initcoffee("MPD");
        coffee("OPN");
        initcoffee("OPN");
        coffee("P&S");
        initcoffee("P&S");
        coffee("WTP");
        initcoffee("WTP");
        */
       
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,employees);
				textView = (AutoCompleteTextView)
				findViewById(R.id.txtCountries);
			textView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					        // TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(),(CharSequence)arg0.getItemAtPosition(arg2), Toast.LENGTH_LONG).show();
					              //positionitem=arg2+1;
					              String str1 = (String)arg0.getItemAtPosition(arg2);
					             // String str2=Integer.toString(positionitem);
					              //Toast.makeText(getBaseContext(),str1,Toast.LENGTH_LONG).show();
					             // Toast.makeText(getBaseContext(),str2,Toast.LENGTH_LONG).show();
					              for(j=0;j<2000;j++)
					              {
					            	  if(str1.equals(employees[j]))
					            	  {   showcasename=showcasename+employees[j];
					            		  phn=phn+phnx[j];
					            	  }
					              }
	        }
					                });
				textView.setThreshold(2);
				textView.setAdapter(adapter);
				
				addb.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				      	
				       showcasename=showcasename+",";
				       phn=phn+",";
				       Toast.makeText(getApplicationContext(),showcasename+" added successfully to the group",Toast.LENGTH_LONG).show();
				        
					}
				});
				regb.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String grupnametext=etname.getText().toString();
						phn=removeLastChar(phn);
						AddContact(grupnametext,phn,showcasename);
						
						//AddContact(gname, gphn, gname);
						//GetContactoffline();
						Toast.makeText(getApplicationContext(), "Group registered Successfully",Toast.LENGTH_LONG).show();
					}
					});
				
				
	}
	
	   
	    
	  
	    public void GetContactsserver() {
	    	//--get all contacts---
	    	
	    	Cursor c = myDbHelper1.getAllContacts();
	    	if (c.moveToFirst())
	    	{
	    	do {employees[i]=c.getString(1);
	    	    phnx[i]=c.getString(3);
	    	i++;
	    	//DisplayContactserver(c);
	    	
	    	} while (c.moveToNext());
	    	}
	    	myDbHelper1.close();
	    	}
	    public void DisplayContactserver(Cursor c)
	    {
	    Toast.makeText(this,
	    "id: " + c.getString(0) + "\n" +
	    "Name: " + c.getString(1) + "\n" +
	    "Email: " + c.getString(2),
	    Toast.LENGTH_LONG).show();
	    }
	    public void AddContact(String grupname,String grupnumbers,String memberst) {
	    	//---add a contact---
	    	db.open();
	    	
	    	if (db.insertContact(
	    	grupname, grupnumbers,memberst) >= 0){
	    	Toast.makeText(this, "Group Registered successfully !!!", Toast.LENGTH_LONG).show();
	    	}
	    	
	    	db.close();
	    	}
	  
	    public void GetContactoffline() {
	    	//---get a contact---
	    	db.open();
	    	Cursor c = db.getContact(2);
	    	if (c.moveToFirst())
	    	DisplayContactserver(c);
	    	else
	    	Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
	    	db.close();
	    	}
	    private static String removeLastChar(String str) {
	        return str.substring(0,str.length()-1);
		}
	    public void coffee(String gnamster)
	    {    try {
       	 
       	myDbHelper1.createDataBase();
       	//Toast.makeText(getBaseContext(), "hey your server has been accessed",Toast.LENGTH_LONG).show();

	} catch (IOException ioe) {

		throw new Error("Unable to create database");

	}
       try {
  		 
    		myDbHelper1.openDataBase();
    		//Toast.makeText(getBaseContext(), "superb server has been opened",Toast.LENGTH_LONG).show();
    	}catch(SQLException sqle){
    		//Toast.makeText(getBaseContext(), "not working",Toast.LENGTH_LONG).show();
    		throw sqle;
    
    	}
	    	Cursor c = myDbHelper1.getAllContacts();
	    	if (c.moveToFirst())
	    	{
	    	do {if(gnamster.equals(c.getString(2)))
	    	{
	    		gphn=gphn+c.getString(3);
	    		gphn=gphn+",";
	    	}
	    	//DisplayContactserver(c);
	    	
	    	} while (c.moveToNext());
	    	}
	    	removeLastChar(gphn);
	    	
	    	//Toast.makeText(getApplicationContext(), "Succes coffee script success",Toast.LENGTH_LONG).show();
	    	//Toast.makeText(getApplicationContext(), gphn,Toast.LENGTH_LONG).show();
	    	myDbHelper1.close();
	    	
	    }
	    public void initcoffee(String alpha)
	    {
	    	alpha="pre-"+alpha;
	    	//AddContact(alpha, gphn, alpha);
	    	gphn="";
	    	
	    }
		
}


