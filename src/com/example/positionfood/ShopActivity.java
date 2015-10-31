package com.example.positionfood;

import java.util.ArrayList;

import com.example.positionfood.La_carte_menu.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShopActivity extends Activity {
	
	private Bundle bundle;
	private String str;
	ListView listView;
	String[][] aname = new String[][] {
			{"µ·¥Ê¤pÅ¢¥]","1","$120"},
			{"Âû¦×¤pÅ¢¥]","2","$180"},
			{"ÂA½¼¤pÅ¢¥]","3","$200"},
			{"¬õ¿N¤û¦×ÄÑ","4","$180"},
			{"±Æ°©³Jª£¶º","5","$160"}
			};
	String[][] bname = new String[][] {
			{"¯À­¹»]»å","1","$100"},
			{"©ÛµP¯O»æ","2","$80"},
			{"»Èµ·¨÷","3","$40"},
			{"ÂAÛ£Àí¶º","4","$120"},
			{"­»ÝÏª£¶º","5","$120"}
			};
			
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		 listView= (ListView) findViewById(R.id.listView1);
		
		
		
		
		
		bundle = this.getIntent().getExtras();
		str = bundle.getString("demo");
		getmenu(str);
	}
	
	class Todo{
		String name;
		String price;
	}
	
	class TodoListAdapter extends BaseAdapter {
		
		private Context mContext;
		private ArrayList<Todo> mData;
		
		TodoListAdapter(Context context, ArrayList<Todo> data) {
			mContext = context;
			mData = data;
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.menucontent, null);
			}
			
			Todo todo = (Todo) getItem(position);
			
			TextView text1 = (TextView) convertView.findViewById(R.id.textView1);
			text1.setText(todo.name);
			TextView text2 = (TextView) convertView.findViewById(R.id.textView2);
			text2.setText(todo.price);
			
			return convertView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop, menu);
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
	
	
	private void getmenu(String str){
		ArrayList<Todo> datas = new ArrayList<Todo>();
		TodoListAdapter adapter = new TodoListAdapter(ShopActivity.this, datas);
		listView.setOnItemClickListener(lvClick);
		listView.setAdapter(adapter);
		switch (str) {
		case "(A1)":
			for(int i = 0; i < aname.length; i++){
				
					Todo todo = new Todo();
					todo.name = aname[i][0];
					todo.price = aname[i][2];
					datas.add(todo);
			}
			
			break;
		case "(B1)":
			for(int i = 0; i < bname.length; i++){
				
				Todo todo = new Todo();
				todo.name = bname[i][0];
				todo.price = bname[i][2];
				datas.add(todo);
		}
			break;
		}
	}
	
	private OnItemClickListener lvClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			switch (Integer.parseInt(str)) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
			
				break;
			
			}
		}
	};
}
