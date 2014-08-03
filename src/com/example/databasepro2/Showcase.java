package com.example.databasepro2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Showcase extends Activity {

	ImageView iv1,iv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.showcase);
	iv1=(ImageView) findViewById(R.id.imagedown);
	iv2=(ImageView) findViewById(R.id.imagesee);
	iv1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse(
			"market://details?id=sun.way2sms.hyd.com&hl=en"));
			startActivity(i);
		}
	});
iv2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
				PackageManager pm = getPackageManager();
			
			Intent intent = pm.getLaunchIntentForPackage("sun.way2sms.hyd.com");
			startActivity(intent);
			}catch(Exception e)
			{
				Intent i = new Intent(android.content.Intent.ACTION_VIEW);
				i.setData(Uri.parse(
				"market://details?id=sun.way2sms.hyd.com&hl=en"));
				startActivity(i);
			}
		}
	});
	
	
	
	
	}
	
	
}
