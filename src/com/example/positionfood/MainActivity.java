package com.example.positionfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private ImageButton imageButton01,imageButton02;
    private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageButton01 = (ImageButton) findViewById(R.id.imageButton1);
		imageButton02 = (ImageButton) findViewById(R.id.imageButton2);
		button = (Button) findViewById(R.id.button1);
		
		imageButton01.setOnClickListener(mimageButton01);
		imageButton02.setOnClickListener(mimageButton02);
		button.setOnClickListener(mbutton);
	}
	
	//�w��
	private OnClickListener mimageButton01 = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Postion_serch.class);
			startActivity(intent);
			MainActivity.this.finish(); 
		}
	};
	
	//�I�\
	private OnClickListener mimageButton02 = new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, La_carte_menu.class);
				startActivity(intent);
				MainActivity.this.finish(); 
			}
		};
		
	//�n�X
	private OnClickListener mbutton = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, LoginActivty.class);
			startActivity(intent);
			MainActivity.this.finish(); 
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
