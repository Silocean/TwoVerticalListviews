package com.example.twolistviewvertical;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twolistviewvertical.R;

public class MainActivity extends Activity {

	private ListView listView;
	private ListView headlistView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListViewAdapter adapter = new ListViewAdapter(getApplicationContext());
		ListViewAdapter2 adapter2 = new ListViewAdapter2(
				getApplicationContext());
		listView = (ListView) findViewById(R.id.listView);
		View v = this.getLayoutInflater().inflate(R.layout.footerview, null);
		headlistView = (ListView) v.findViewById(R.id.head_listView);
		headlistView.setAdapter(adapter2);
		setListViewHeightBasedOnChildren(headlistView);
		listView.addFooterView(v);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), arg3 + "",
						Toast.LENGTH_SHORT).show();
			}
		});
		headlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), arg3 + "====",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * 动态设置listview的高度
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter adapter = listView.getAdapter();
		if(adapter != null) {
			int totalHeight = 0;
			System.out.println(adapter.getCount());
			for(int i=0; i<adapter.getCount(); i++) {
				View listItem = adapter.getView(i, null, listView);
				listItem.measure(0, 0);
				totalHeight += listItem.getMeasuredHeight();
			}
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
			((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
			listView.setLayoutParams(params);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class ListViewAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public ListViewAdapter(Context context) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_item, null);
			}
			TextView txt = (TextView) convertView.findViewById(R.id.text);
			txt.setText("第" + getItem(position) + "个item");
			return convertView;
		}

	}

	private class ListViewAdapter2 extends BaseAdapter {

		private LayoutInflater inflater;

		public ListViewAdapter2(Context context) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_item, null);
			}
			TextView txt = (TextView) convertView.findViewById(R.id.text);
			txt.setText("第" + getItem(position) + "个item");
			return convertView;
		}

	}

}
