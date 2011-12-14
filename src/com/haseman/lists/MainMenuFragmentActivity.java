 package com.haseman.lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainMenuFragmentActivity extends FragmentActivity{

	public void onCreate(Bundle data){
		super.onCreate(data);
		setContentView(R.layout.main_menu);
		
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 FragmentTransaction ft = fragmentManager.beginTransaction();
		 Fragment fragment = new MainMenuListFragment();
		 ft.replace(R.id.list_fragment_location, fragment, "demoFragment");
		 ft.commit();
	}
}
