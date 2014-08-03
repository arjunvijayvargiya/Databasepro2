package com.example.databasepro2;

import java.io.IOException;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class See extends Activity {
Spinner s2;
String[] employees=new String[2000];
String[] phnx=new String[2000];
String[] mems=new String[2000];
String[] department = new String[2000];
DataBaseHelper myDbHelper2;
String gphn="";
String gname="";
int index;
int auto=1;
String[] presidents = {
		"IT",
		"AGM(O&M)",
		"AUTOBASE",
		"BE",
		"BMD",
		"C&I",
		"C&M",
		"CHD",
		"CHP",
		"CIVIL",
		"COAL COORDINATION","EEMG","COAL COORDINATION","EMD","EMG-AUD"
		,"F&A"
		,"HOSP"
		,"HR"
		,"MPD"
		,"OPN"
		,"P&S"
		,"WTP"
		};
AutoCompleteTextView textView;
DBAdapter db;
int i=0;
int j=0;
EditText et1,et2,et3;
Button b;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.see);
for(int i = auto;i<2000;i++)
{
	employees[i] = "emp" + i; 
}
for(int i=auto;i<2000;i++)
{
	phnx[i]="phn"+i;
}
for(int i=auto;i<2000;i++)
{
	mems[i]="mems"+i;
}
for(int i=auto;i<2000;i++)
{
	department[i]="dept"+i;
}
AdView adView = (AdView)this.findViewById(R.id.adView3);
AdRequest adRequest = new AdRequest.Builder().build();
adView.loadAd(adRequest);
et1=(EditText) findViewById(R.id.grupnumss);
b=(Button) findViewById(R.id.sendgripmessage);
et2=(EditText) findViewById(R.id.messagegrup);
et3=(EditText) findViewById(R.id.cyclops);

b.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(et3.getText().toString(), null,et2.getText().toString() ,
		null, null);
		Toast.makeText(getApplicationContext(), "Message sent successfully",Toast.LENGTH_LONG).show();
	}
});
myDbHelper2 = new DataBaseHelper(this);
try {
	 
	myDbHelper2.createDataBase();
	//Toast.makeText(getBaseContext(), "hey your server has accessed",Toast.LENGTH_LONG).show();

} catch (IOException ioe) {

	throw new Error("Unable to create database");

}
try {
	 
		myDbHelper2.openDataBase();
		//Toast.makeText(getBaseContext(), "superb",Toast.LENGTH_LONG).show();
	}catch(SQLException sqle){
		//Toast.makeText(getBaseContext(), "not working",Toast.LENGTH_LONG).show();
		throw sqle;

	}

db = new DBAdapter(this);
GetContacts();


ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,employees);
textView = (AutoCompleteTextView)findViewById(R.id.txtCountries);
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
			            	  {  
			            		  et1.setText(mems[j]);
			            		  et3.setText(phnx[j]);
			            	  }
			              }
			              
    }
			                });
		textView.setThreshold(0);
		textView.setAdapter(adapter);
		spinnerset();

}
public void GetContacts() {
	//--get all contacts---
	db.open();
	Cursor c = db.getAllContacts();
	if (c.moveToFirst())
	{
	do { //DisplayContact(c);
	     employees[i]=c.getString(1);
	     phnx[i]=c.getString(2);
	     mems[i]=c.getString(3);
		
	} while (c.moveToNext());
	}
	db.close();
	}
public void DisplayContact(Cursor c)
{
Toast.makeText(this,
"id: " + c.getString(0) + "\n" +
"Name: " + c.getString(1) + "\n" +
"Email: " + c.getString(2),
Toast.LENGTH_LONG).show();
}

public void spinnerset(){
	s2 = (Spinner) findViewById(R.id.spinner2);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	this, android.R.layout.simple_spinner_item,presidents);
	s2.setAdapter(adapter);

	s2.setOnItemSelectedListener(new OnItemSelectedListener() {
	public void onItemSelected(AdapterView<?> arg0, View arg1,
	int arg2, long arg3) {
	
	/*Toast.makeText(getBaseContext(),
	"You have selected item : " + phn[index],
	
	Toast.LENGTH_SHORT).show();*/
		index = s2.getSelectedItemPosition();
    	String deptselect=presidents[index];
    	/*Toast.makeText(getBaseContext(),
    			"You have selected item : " + deptselect,
    			
    			Toast.LENGTH_SHORT).show();*/
    	coffee(deptselect);
		et1.setText(gname);
		et3.setText(gphn);
		gphn="";

	}
	public void onNothingSelected(AdapterView<?> arg0) {
	}
	});
	        }  
private static String removeLastChar(String str) {
    return str.substring(0,str.length()-1);
}
public void coffee(String gnamster)
{   try {
	 
	myDbHelper2.createDataBase();
	//Toast.makeText(getBaseContext(), "hey your server has accessed",Toast.LENGTH_LONG).show();

} catch (IOException ioe) {

	throw new Error("Unable to create database");

}
try {
	 
		myDbHelper2.openDataBase();
		//Toast.makeText(getBaseContext(), "superb",Toast.LENGTH_LONG).show();
	}catch(SQLException sqle){
		//Toast.makeText(getBaseContext(), "not working",Toast.LENGTH_LONG).show();
		throw sqle;

	}
	Cursor c = myDbHelper2.getAllContacts();
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
	
	gname=gnamster;
	//Toast.makeText(getApplicationContext(), "Succes coffee script success",Toast.LENGTH_LONG).show();
	//Toast.makeText(getApplicationContext(), gphn,Toast.LENGTH_LONG).show();
	myDbHelper2.close();
	
}

}
