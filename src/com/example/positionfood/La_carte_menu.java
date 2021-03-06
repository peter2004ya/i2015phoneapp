package com.example.positionfood;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class La_carte_menu extends Activity {
	
	    private Bundle bundle;
		
		int[] img = new int[]{R.drawable.tw, R.drawable.jp, R.drawable.kr, R.drawable.tl, R.drawable.am, R.drawable.id, R.drawable.vgt};
		String[] Cname = new String[]{"中式","日式","韓式","泰式料理","美式","印度料理","素食"};
		String[] Ename = new String[]{"Chinese","Japan","Koren","Thailand","America","India","Vegetarian food"};
		String[][] nname = new String[][] {
			{"(A1)頂汰峰","(A2)肚小岳","(A3)驚星"},
			{"(C1)臀筋拉麵","(C2)小欣居酒屋","(C3)深夜食堂","(C4)狐童"},
			{"(D1)Honey Dog","(D2)小張炸雞","(D3)韓賀婷"},
			{"(F1)瓦乘","(F2)泰難過"},
			{"(G1)參樓","(G2)Saturday"},
			{"(E1)楷開甩餅","(E2)印度皇宮"},
			{"(B1)咦圓","(B2)輸粿"}
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_la_carte_menu);
		
		
		Button ReturnButton  = (Button) findViewById(R.id.button1);
		ReturnButton.setOnClickListener(ReturnListener);
		
		ExpandableListView listview =(ExpandableListView)findViewById(R.id.list);
		
		MyAdapter adapter = new MyAdapter(this);
		listview.setOnItemClickListener(lvClick);
		listview.setAdapter(adapter);
		listview.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3) {
				Log.d("Click", "onGroupClick");
				return false;
			}
		});
		listview.setOnChildClickListener(new OnChildClickListenerImpl());
	}
	//子項點擊事件
	class OnChildClickListenerImpl implements OnChildClickListener {
		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			Log.d("Clivk", "groupPosition " + groupPosition + ", childPosition " + childPosition);
			TextView tv1 = (TextView) v.findViewById(R.id.textView1);
			final String title = tv1.getText().toString();
			Log.d("click", title.subSequence(1, 2).toString());
			String str = title.subSequence(1, 3).toString();
			
			switch (str) {
			case "A1":
				getDemo("(A1)");
				break;
			case "B1":
				getDemo("(B1)");
				break;
			}
			return true;
		}
		
	}
	//設定Expandable資料
	public class MyAdapter extends BaseExpandableListAdapter{
		private LayoutInflater myInflater;
		public MyAdapter(Context c){
			myInflater = LayoutInflater.from(c);
		}
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return nname[groupPosition][childPosition];
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			convertView = myInflater.inflate(R.layout.mylistvieww,null);
			TextView txtName = ((TextView)convertView.findViewById(R.id.textView1));
			txtName.setText(nname[groupPosition][childPosition]);
			txtName.setOnClickListener(textClick);
			txtName.setTag(groupPosition + "," + childPosition);
			return convertView;
		}
		
		private OnClickListener textClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String positionInfo = (String) v.getTag();
				if (positionInfo != null) {
					String[] strPositions = positionInfo.split(",");
					int groupPosition = Integer.parseInt(strPositions[0]);
					int childPosition = Integer.parseInt(strPositions[1]);
					Log.d("Click", "groupPosition " + groupPosition + ", childPosition " + childPosition);
					// TODO
//					String resturantName = "";
//					Intent intent = new Intent(La_carte_menu.this, MenuActivity.class);
//					intent.putExtra("resName", resturantName);
//					startActivity(intent);
					
					// MenuActivity code snippent
					/*onCreate() {
						String resName = getIntent().getStringExtra("resName");
						// composite api
						// http://XXXXX/api/getMenu?res=resName
					}*/
				}
			}
		};

		@Override
		public int getChildrenCount(int groupPosition) {
			return nname[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return Cname[groupPosition];
		}

		@Override
		public int getGroupCount() {
			return Cname.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

			convertView = myInflater.inflate(R.layout.mylistview,null);
			
			//取得圖片
			ImageView imgLogo = (ImageView)convertView.findViewById(R.id.imageView1);
			//取得文字
			TextView txtName = ((TextView)convertView.findViewById(R.id.textView1));
			TextView txtengName = ((TextView)convertView.findViewById(R.id.textView2));
			//設定內容
			imgLogo.setImageResource(img[groupPosition]);
			txtName.setText(Cname[groupPosition]);
			txtengName.setText(Ename[groupPosition]);
			
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	//返回
	private OnClickListener ReturnListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(La_carte_menu.this, MainActivity.class);
			startActivity(intent);
			La_carte_menu.this.finish(); 
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.la_carte_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private OnItemClickListener lvClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			TextView tv1 = (TextView) view.findViewById(R.id.textView1);
			final String title = tv1.getText().toString();
			Log.d("click", title.subSequence(1, 2).toString());
			String str = title.subSequence(1, 3).toString();
			
			switch (str) {
			case "(A1)":
				getDemo("(A1)");
				break;
			case "(B1)":
				getDemo("(B1)");
				break;
			}
		}
	};
	    private void getDemo(String i){
		    //Intent intent;
	    	
	    	Intent intent = new Intent(La_carte_menu.this,ShopActivity.class);
		    startActivity(intent);
			La_carte_menu.this.finish();
		    bundle = new Bundle();
		    bundle.putString("demo", i); 
		    intent.putExtras(bundle);
		    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		    startActivity(intent);
	      }
	 }




     