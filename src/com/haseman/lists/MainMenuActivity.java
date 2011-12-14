package com.haseman.lists;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends ListActivity{
	
	//String entries[] = getResources().getStringArray(R.array.menu_entries);
	
	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element, entries);
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.list_activity);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.menu_entries, R.layout.list_element);
		setListAdapter(adapter);	
	}
	@Override
	public void onListItemClick(ListView lv, View clickedView, int position, long id){
		super.onListItemClick(lv, clickedView, position, id);
		
		if(position == 0){
			Intent i = new Intent(getApplicationContext(), DemoListActivity.class);
			startActivity(i);
		}else if (position == 1){
			Intent i = new Intent(getApplicationContext(), MainMenuFragmentActivity.class);
			startActivity(i);
		}
		else{
			TextView tv = (TextView)clickedView;
			Toast.makeText(getApplicationContext(), "List Item "+tv.getText()+" was clicked!", Toast.LENGTH_SHORT).show();
		}
		
	}
}
