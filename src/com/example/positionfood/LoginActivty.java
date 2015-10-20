package com.example.positionfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.networkcommunication.volleymgr.NetworkManager;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;

public class LoginActivty extends Activity {
	
	private ProgressDialog mProgressDialog;
	private EditText AccountInput,PasswordInput;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_activty);
		
		AccountInput = (EditText) findViewById(R.id.editText1);
		PasswordInput = (EditText) findViewById(R.id.editText2);
		
		Button loginButton  = (Button) findViewById(R.id.button1);
		Button cancleButton = (Button) findViewById(R.id.button2);
		Button signupButton = (Button) findViewById(R.id.button3);
	
		loginButton.setOnClickListener(mloginListener);
		cancleButton.setOnClickListener(mcancleListener);
		signupButton.setOnClickListener(msignupListener);
		
        mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("½T»{µn¤J¤¤");
	}
	
    private OnClickListener mloginListener = new OnClickListener() {
    	
		public void onClick(View v) {
			
			try {
				String strAccount = URLEncoder.encode(AccountInput.getEditableText().toString(), "UTF-8");
				//String strPassword = URLEncoder.encode(PasswordInput.getEditableText().toString(), "UTF-8");
		
				mProgressDialog.show();
				
				String url = "http://i2015server.herokuapp.com/api/user/query?User=" + strAccount;				
				StringRequest request = new StringRequest(Request.Method.GET, url, LoginSuccessListener, LoginErrorListener);
				NetworkManager.getInstance(LoginActivty.this).request(null, request);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	};
	
	protected Listener<String> LoginSuccessListener = new Listener<String>() {
		private String DataPassword,UID;
		
		@Override
		public void onResponse(String response) {
			try {
				
				JSONArray array = new JSONArray(response);		
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					DataPassword = obj.getString("Password");	
					UID = obj.getString("_id");
				}
				
				
			}catch (JSONException e1) {
				e1.printStackTrace();
			}finally {
				mProgressDialog.dismiss();
			}
						
			String strPassword = (PasswordInput.getEditableText().toString());
			
			if(DataPassword.equals(strPassword)){
				Intent intent = new Intent(LoginActivty.this, MainActivity.class);				 
				startActivity(intent);
				LoginActivty.this.finish();
				
	    
	             
			}else{
				Toast toast = Toast.makeText(LoginActivty.this,"±K½X¿ù»~¡I",Toast.LENGTH_SHORT);
				toast.show();
				PasswordInput.setText("");
			}
			
		}
	};
	
	   public static String MD5(String str)  
	    {  
	        MessageDigest md5 = null;  
	        try  
	        {  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch(Exception e)  
	        {  
	            e.printStackTrace();  
	            return "";  
	        }  
	          
	        char[] charArray = str.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	          
	        for(int i = 0; i < charArray.length; i++)  
	        {  
	            byteArray[i] = (byte)charArray[i];  
	        }  
	        byte[] md5Bytes = md5.digest(byteArray);  
	          
	        StringBuffer hexValue = new StringBuffer();  
	        for( int i = 0; i < md5Bytes.length; i++)  
	        {  
	            int val = ((int)md5Bytes[i])&0xff;  
	            if(val < 16)  
	            {  
	                hexValue.append("0");  
	            }  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	    }  
	      

	
	protected ErrorListener LoginErrorListener = new ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError err) {
			mProgressDialog.dismiss();
		}
	};
	
		//Â÷¶}
	private OnClickListener mcancleListener = new OnClickListener() {
			
			public void onClick(View v) {
				LoginActivty.this.finish(); 
			}
		};
		//µù¥U
	private OnClickListener msignupListener = new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoginActivty.this, SignupActivity.class);
				startActivity(intent);
				LoginActivty.this.finish(); 
			}
		};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_activty, menu);
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
