package pl.poradnikpiwny.activity;

import pl.poradnikpiwny.R;
import pl.poradnikpiwny.activity.fragment.LastAddedFragment;
import pl.poradnikpiwny.activity.fragment.SearchFragment;
import pl.poradnikpiwny.activity.fragment.TopRankingFragment;
import pl.poradnikpiwny.util.DummyTabContent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity implements OnTabChangeListener 
{
	private EditText eBeerSearch;
	private TabHost tabHost;
	String query;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		eBeerSearch = (EditText) findViewById(R.id.eBeerSearch);
				
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		tabHost.setOnTabChangedListener(this);
		
		TabHost.TabSpec tSpecTopRanking = tabHost.newTabSpec("topranking");
		tSpecTopRanking.setIndicator(getString(R.string.topranking_tab),getResources().getDrawable(R.drawable.star_tab));
		tSpecTopRanking.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecTopRanking);
		
		TabHost.TabSpec tSpecLastAdded = tabHost.newTabSpec("lastadded");
		tSpecLastAdded.setIndicator(getString(R.string.lastadded_tab),getResources().getDrawable(R.drawable.plus_tab));
		tSpecLastAdded.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecLastAdded);
		
		eBeerSearch.setOnKeyListener(new OnKeyListener() {
		    @Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
		        if (((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER) || event.getAction() == EditorInfo.IME_ACTION_DONE)) {
		        	
		        	query = eBeerSearch.getText().toString();
		        	
		        	if(tabHost.getTabWidget().getChildCount()==2){
			        	TabHost.TabSpec tSpecSearch = tabHost.newTabSpec("search");
			    		tSpecSearch.setIndicator(getString(R.string.search_tab),getResources().getDrawable(R.drawable.search_tab));
			    		tSpecSearch.setContent(new DummyTabContent(getBaseContext()));
			    		tabHost.addTab(tSpecSearch);
		        	}
		        	
		        	if(tabHost.getCurrentTab()==2){
		        		onTabChanged("search");
		        	} else {
		        		tabHost.setCurrentTab(2);
		        	}
		        	
		        	// Ukrycie wirtualnej klawiatury
		            InputMethodManager imm = 
		                    (InputMethodManager)getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		            imm.hideSoftInputFromWindow(eBeerSearch.getWindowToken(), 0);

		          return true;
		        }
		        return false;
		    }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onTabChanged(String tabId) {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		TopRankingFragment topRankingFragment = (TopRankingFragment) fm.findFragmentByTag("topranking");
        LastAddedFragment lastAddedFragment = (LastAddedFragment) fm.findFragmentByTag("lastadded");
        SearchFragment searchFragment = (SearchFragment) fm.findFragmentByTag("search");
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
		
        if(topRankingFragment!=null)
        	ft.detach(topRankingFragment);
        if(lastAddedFragment!=null)
        	ft.detach(lastAddedFragment);
        if(searchFragment!=null)
        	ft.detach(searchFragment);
        
        if(tabId.equalsIgnoreCase("topranking")){
        	if(topRankingFragment==null){
        		ft.add(R.id.realtabcontent, new TopRankingFragment(), "topranking");
        	}else{
        		ft.remove(topRankingFragment);
        		ft.add(R.id.realtabcontent, new TopRankingFragment(), "topranking");
        	}
        }else if(tabId.equalsIgnoreCase("lastadded")){
        	if(lastAddedFragment==null){
        		ft.add(R.id.realtabcontent, new LastAddedFragment(),"lastadded");
        	}else{
        		ft.remove(lastAddedFragment);
        		ft.add(R.id.realtabcontent, new LastAddedFragment(),"lastadded");
        	}
        }else if(tabId.equalsIgnoreCase("search")){
        	SearchFragment sf = new SearchFragment();
    		sf.setQuery(query);
        	if(searchFragment==null){
        		ft.add(R.id.realtabcontent, sf,"search");
        	}else{
        		ft.remove(searchFragment);
        		ft.add(R.id.realtabcontent, sf,"search");
        	}
        }
        ft.commit();
        
	}

}
