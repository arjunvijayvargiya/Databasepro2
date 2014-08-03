package com.example.databasepro2;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView.OnItemSelectedListener;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	int index;
	DataBaseHelper myDbHelper;
int j;
	String[] presidents = {
	"Dwight D. Eisenhower",
	"John F. Kennedy",
	"Lyndon B. Johnson",
	"Richard Nixon",
	"Gerald Ford",
	"Jimmy Carter",
	"Ronald Reagan",
	"George H. W. Bush",
	"Bill Clinton",
	"George W. Bush",
	"Barack Obama"
	};
	AutoCompleteTextView textView;
	String[] employees = new String[2000];
	int auto = 1;
	//String[] employees={"emp1","emp2","emp3","emp4","emp5","emp6","emp7"};
    
	String[] phn=new String[2000];
    int i=0;
    EditText name,phone,message,email;
    Button send,creategrp,seegrp,freesms,caller,emailb;
    String messagetexter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = auto;i<2000;i++)
        {
        	employees[i] = "emp" + i; 
        }
        for(int i=auto;i<2000;i++)
        {
        	phn[i]="phn"+i;
        }
        AdView adView = (AdView)this.findViewById(R.id.adView1);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
        myDbHelper = new DataBaseHelper(this);
        name=(EditText) findViewById(R.id.name);
        phone=(EditText) findViewById(R.id.phone);
        message=(EditText) findViewById(R.id.message);
        email=(EditText) findViewById(R.id.emailedittext);
        send=(Button) findViewById(R.id.sendbut);
        creategrp=(Button) findViewById(R.id.crtgrp);
        seegrp=(Button) findViewById(R.id.grpmsnger);
        emailb=(Button)findViewById(R.id.emailbutton);
        emailb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String emailtext=email.getText().toString();
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setData(Uri.parse("mailto:"));
			String to = "";
			String cc = emailtext;
			i.putExtra(Intent.EXTRA_EMAIL, to);
			i.putExtra(Intent.EXTRA_CC, cc);
			i.putExtra(Intent.EXTRA_SUBJECT, "Subject here...");
			i.putExtra(Intent.EXTRA_TEXT, "Message here...");
			i.setType("message/rfc822");
			startActivity(Intent.createChooser(i, "Email"));
			}
		});
        caller=(Button) findViewById(R.id.call);
        freesms=(Button)findViewById(R.id.freesms);
        // Font path
        String fontPath = "fonts/future.ttf";
 
        // text view label
        TextView txtGhost = (TextView) findViewById(R.id.headerx);
 
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
 
        // Applying font
        txtGhost.setTypeface(tf);
        freesms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,Showcase.class);
			startActivity(i);
				
				
				
				
				
				
			}
		});
 caller.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				makeCall();
				
				
				
				
				
			}
		});
       seegrp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,See.class);
			startActivity(i);
			}
		});
        creategrp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,Group.class);
			startActivity(i);
			}
		});
        messagetexter=message.getText().toString();
        send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(phn[index], null,messagetexter ,
				null, null);
				Toast.makeText(getApplicationContext(), "Message sent successfully",Toast.LENGTH_LONG).show();
			}
		});
        try {
 
        	myDbHelper.createDataBase();
        	//Toast.makeText(getBaseContext(), "hey your server has accessed",Toast.LENGTH_LONG).show();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
        try {
   		 
     		myDbHelper.openDataBase();
     		//Toast.makeText(getBaseContext(), "superb",Toast.LENGTH_LONG).show();
     	}catch(SQLException sqle){
     		//Toast.makeText(getBaseContext(), "not working",Toast.LENGTH_LONG).show();
     		throw sqle;
     
     	}
     
 //GetContact();
        GetContacts();
        
       // spinnerset();
//---Spinner View---
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,employees);
				textView = (AutoCompleteTextView)
				findViewById(R.id.txtcountriesx);
			textView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					        // TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(),(CharSequence)arg0.getItemAtPosition(arg2), Toast.LENGTH_LONG).show();
					              //positionitem=arg2+1;
					              String str1 = (String)arg0.getItemAtPosition(arg2);
					              //String str2=Integer.toString(arg2);
					              //Toast.makeText(getBaseContext(),str1,Toast.LENGTH_LONG).show();
					              name.setText(str1);
					              for(j=0;j<2000;j++)
					              {
					            	  if(str1.equals(employees[j]))
					            	  {   
					            		  phone.setText(phn[j]);
					            	  }
					              }
					              //Toast.makeText(getBaseContext(),str2,Toast.LENGTH_LONG).show();
					          	  
					              // Toast.makeText(getBaseContext(),str2,Toast.LENGTH_LONG).show();
					             
	        }
					                });
				textView.setThreshold(2);
				textView.setAdapter(adapter);
          
}
    
    public void GetContact() {
    	//---get a contact---
    	
        Cursor c = myDbHelper.getContact(2);
    	if (c.moveToFirst())
    	   DisplayContact(c);
    	else
    	   Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
    	myDbHelper.close();
    	}
    public void DisplayContact(Cursor c)
    {
    Toast.makeText(this,
    "id: " + c.getString(0) + "\n" +
    "Name: " + c.getString(1) + "\n" +
    "Email: " + c.getString(2),
    Toast.LENGTH_LONG).show();
    }
    public void GetContacts() {
    	//--get all contacts---
    	
    	Cursor c = myDbHelper.getAllContacts();
    	if (c.moveToFirst())
    	{
    	do {employees[i]=c.getString(1);
    	    phn[i]=c.getString(3);
    	i++;
    	//DisplayContact(c);
    	
    	} while (c.moveToNext());
    	}
    	myDbHelper.close();
    	}
    public void makeCall() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+phn[index]));

        try {
           startActivity(phoneIntent);
           finish();
           Log.i("Finished making a call...", "");
        } catch (android.content.ActivityNotFoundException ex) {
           Toast.makeText(MainActivity.this, 
           "Call failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}