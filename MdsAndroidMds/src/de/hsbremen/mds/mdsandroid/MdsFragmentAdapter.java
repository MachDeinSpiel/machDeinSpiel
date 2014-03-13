package de.hsbremen.mds.mdsandroid;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MdsFragmentAdapter extends FragmentPagerAdapter{
	
	private List<String> fragmentList = new ArrayList<String>();
	
	public MdsFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public void addFragment(String fragmentString){
		this.fragmentList.add(fragmentString);
		this.notifyDataSetChanged();
	}
	
	public void removeFragment(int number){
		this.fragmentList.remove(number);
		this.notifyDataSetChanged();
	}
	
	@Override
	public Fragment getItem(int pos) {

		Fragment fragment = null;

		try {
		
		for (int i = 0 ; i < this.fragmentList.size() ; i++){
			
			if(i == pos){
				fragment = (Fragment) Class.forName("de.example.android.gui."+fragmentList.get(i)).newInstance();
				break;
			}
			
		}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}

}
