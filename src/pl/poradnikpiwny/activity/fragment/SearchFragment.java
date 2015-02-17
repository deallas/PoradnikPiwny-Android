package pl.poradnikpiwny.activity.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import pl.poradnikpiwny.R;
import pl.poradnikpiwny.entity.Beer;
import pl.poradnikpiwny.entity.response.BeerResponseList;
import pl.poradnikpiwny.entity.response.BeerSearchPostResponse;
import pl.poradnikpiwny.rest.AbstractHandler;
import pl.poradnikpiwny.rest.RestAction;
import pl.poradnikpiwny.rest.RestService;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class SearchFragment extends BeerFragment implements OnScrollListener 
{
	private RestService rest;
	private String query;
	private String search_id;
	
	/*
	 * Minimalna liczba piw poniżej aktualnej pozycji, po której ma nastąpić ładowanie kolejnych piw
	 */
    private int visibleThreshold = 1;
    private int previousTotal = 0;
    private boolean loading = true;
    private int currentPage = 0;
    private int maxPage = 1;
    
    private List<Map<String,String>> beerList = new ArrayList<Map<String,String>>();
    private ListView lv;
	
    @Override
    public void onCreate (Bundle savedInstanceState){
        
    	rest = new RestService(new SearchPostHandler(), getActivity().getBaseContext());
        rest.addParam("query", query);
        rest.execute();
    	
    	super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState){
		
		lv = this.getListView();
		lv.setOnScrollListener(this);
		
		super.onActivityCreated(savedInstanceState);
	} 
    
    public void setQuery(String query){
    	this.query = query;
    }
    
    public String getQuery(){
    	return this.query;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
    	if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) && (currentPage<maxPage)) {
        	loadPage();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) 
    {
    }
    
    public void loadPage()
    {
    	rest = new RestService(new SearchHandler(), getActivity().getBaseContext());
    	rest.addParam("search_id", search_id);
    	rest.addParam("page",String.valueOf(currentPage+1));
    	rest.execute();
    	loading = true;
    }
    
	private final class SearchPostHandler extends AbstractHandler
	{
		@Override
		public int getRequestType() 
		{
			return RestService.POST;
		}
		
		@Override
		public String getServiceUrl() 
		{
			return RestAction.BEER_SEARCH;
		}
		
		@Override
		public void success(Message msg)
		{
			Gson gson = new Gson();
			String json = msg.obj.toString();
			BeerSearchPostResponse response = gson.fromJson(json ,BeerSearchPostResponse.class);
	        if(response.getResultId()!=null){
	        	search_id = response.getResultId();
	        	loadPage();
	        }
		}
		
		public void error(Message msg)
		{
			setError(msg);
		}

	};
	
	private final class SearchHandler extends AbstractHandler
	{
		@Override
		public String getServiceUrl() 
		{
			return RestAction.BEER_SEARCH;
		}
		
    	@Override
    	public void success(Message msg)
    	{
			Gson gson = new Gson();
			String json = msg.obj.toString();
			BeerResponseList response = gson.fromJson(json ,BeerResponseList.class);
			currentPage = response.getCurrentPageNumber();
			maxPage = (int) Math.ceil((float) response.getTotalItemCount() / (float) response.getItemCountPerPage());
			
			//zapisanie aktualnej pozycji scrolla
			int position = lv.getFirstVisiblePosition();
			View v = lv.getChildAt(0);
			int top = (v == null) ? 0 : v.getTop();
			
			final List<Beer> beers = response.getBeers();
			int number = beers.size();
			
			if(number==0){
				setError(getString(R.string.beer_search_notfound));
				return;
			}
			
			String[] from = new String[] {"beerName","beerRanking","beerManufacturer","beerStars","beerImage","beerDistributor"};
			int[] to = {R.id.TextViewBeerName,R.id.TextViewBeerRating,R.id.TextViewManufacturer,R.id.TextViewBeerStars,R.id.list_image,R.id.TextViewBeerDate};
			
			for (int i=0;i<number;i++) {
				Beer current = beers.get(i);
				beersId.add(current.getId());
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("beerName", current.getName());
				map.put("beerRanking", String.format("%.2f",current.getRankingAvg()));
				if(current.getManufacturer() !=null) map.put("beerManufacturer",current.getManufacturer().getName());
				map.put("beerStars",rankingToStars(current.getRankingAvg()));
				if(current.getDistributor() !=null) map.put("beerDistributor", current.getDistributor().getName());
				if(current.getImage() !=null) map.put("beerImage",current.getImage().getPath());
				else map.put("beerImage", "DEFAULT");
				beerList.add(map);	
			}
			SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),beerList,R.layout.list_row,from,to);
			adapter.setViewBinder(viewBinder);
			setListAdapter(adapter);
			
			// przywrócenie poprzedniej pozycji
			lv.setSelectionFromTop(position, top);

		}

		@Override
		public void error(Message msg) 
		{
			setError(msg);
		}
		
    };
    
}
