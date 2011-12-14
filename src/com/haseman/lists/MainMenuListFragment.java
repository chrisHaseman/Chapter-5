package com.haseman.lists;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainMenuListFragment extends ListFragment{
	public MainMenuListFragment(){
		super();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list_fragment, null);
		String entries[] = getResources().getStringArray(R.array.menu_entries);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_element, entries);
		setListAdapter(adapter);
		return v;
	}
}