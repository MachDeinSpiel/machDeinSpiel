package de.hsbremen.mds.mdsandroid;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import de.hsbremen.mds.common.valueobjects.MdsItem;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FragmentBackpack extends Fragment {

	ArrayList<MdsItem> itemList = new ArrayList<MdsItem>();
	ArrayList<String> itemAsStringList = new ArrayList<String>();
	BaseAdapter adapter;
	ListView lv;
	
	public FragmentBackpack() {
	}
	
	public FragmentBackpack(ArrayList<MdsItem> itemList) {
		this.itemList=itemList;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		itemList.add(new MdsItem("Rotten-Kiwi","rottenkiwi"));
		itemList.add(new MdsItem("M4A1","m4a1"));
		itemList.add(new MdsItem("Burlap-Sack","burlapsack"));
		itemList.add(new MdsItem("Fire-Axe","fireaxe"));
		itemList.add(new MdsItem("Banana","banana"));
		
		final View view = inflater.inflate(R.layout.fragment_backpack, container,
				false);
		
		for (MdsItem item : itemList) {
			itemAsStringList.add(item.getName());
		}
		
		lv = (ListView) view.findViewById(R.id.itemList);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {

				String currentItem = itemList.get(pos).getImagePath();
				
				int resId = getResources().getIdentifier(currentItem, "drawable", getActivity().getPackageName());
				
				ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageItem);
				imageView.setImageResource(resId);
			}
			
		});
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemAsStringList);
		lv.setAdapter(adapter);
		registerForContextMenu(lv);
		
		return view;
	}
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);

		menu.add("Benutzen");
		menu.add("Ablegen");
		menu.add("Essen");
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item){
		super.onContextItemSelected(item);

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		if(item.getTitle()=="Benutzen"){
			Toast.makeText(getActivity(), itemList.get(info.position).getName()+" benutzt!", Toast.LENGTH_LONG).show();
			itemList.remove(info.position);
			itemAsStringList.remove(info.position);
		}
		
		if(item.getTitle()=="Ablegen"){
			Toast.makeText(getActivity(), "Du hast "+itemList.get(info.position).getName()+" abgelegt!", Toast.LENGTH_LONG).show();
			itemList.remove(info.position);
			itemAsStringList.remove(info.position);
		}

		if(item.getTitle()=="Essen"){
			Toast.makeText(getActivity(), "Du hast "+itemList.get(info.position).getName()+" weggeschmatzt!", Toast.LENGTH_LONG).show();
			itemList.remove(info.position);
			itemAsStringList.remove(info.position);
		}
		
		adapter.notifyDataSetChanged();

		ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageItem);
		imageView.setImageResource(R.drawable.backpack);
		
		return super.onContextItemSelected(item);
	}
	
}
