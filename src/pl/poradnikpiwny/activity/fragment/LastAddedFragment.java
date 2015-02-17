package pl.poradnikpiwny.activity.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import pl.poradnikpiwny.R;
import pl.poradnikpiwny.entity.Beer;
import pl.poradnikpiwny.entity.response.BeerResponseList;
import pl.poradnikpiwny.rest.AbstractHandler;
import pl.poradnikpiwny.rest.RestAction;
import pl.poradnikpiwny.rest.RestService;
import android.os.Bundle;
import android.os.Message;
import android.widget.SimpleAdapter;

public class LastAddedFragment extends BeerFragment 
{
	private RestService rest;
	
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        rest = new RestService(new LastAddedHandler(), getActivity().getBaseContext());
        rest.execute();
    	
    	super.onCreate(savedInstanceState);
    }
	
	private final class LastAddedHandler extends AbstractHandler
	{
		@Override
		public String getServiceUrl() {
			return RestAction.BEER_LASTADDED;
		}	
		
    	@Override
    	public void success(Message msg)
    	{
			Gson gson = new Gson();
			String json = (String) msg.obj;
			BeerResponseList response = gson.fromJson(json ,BeerResponseList.class);
			final List<Beer> beers = response.getBeers();
			int number = beers.size();
			
			if(number==0){
				setError(getString(R.string.beer_search_notfound));
				return;
			}
			
			String[] from = new String[] {"beerName","beerRanking","beerManufacturer","beerStars","beerDateAdded","beerImage"};
			int[] to = {R.id.TextViewBeerName,R.id.TextViewBeerRating,R.id.TextViewManufacturer,R.id.TextViewBeerStars,R.id.TextViewBeerDate,R.id.list_image};
			
			List<Map<String,String>> beerList = new ArrayList<Map<String,String>>();
			for (int i=0;i<number;i++) {
				Beer current = beers.get(i);
				beersId.add(current.getId());
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("beerName", current.getName());
				map.put("beerDateAdded", current.getDateAdded());
				map.put("beerRanking", String.format("%.2f",current.getRankingAvg()));
				if(current.getManufacturer() !=null) map.put("beerManufacturer",current.getManufacturer().getName());
				map.put("beerStars",rankingToStars(current.getRankingAvg()));
				if(current.getImage() !=null) map.put("beerImage",current.getImage().getPath());
				else map.put("beerImage", "DEFAULT");
				beerList.add(map);	
			}
			
			SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),beerList,R.layout.list_row,from,to);
			adapter.setViewBinder(viewBinder);
			setListAdapter(adapter);
		}
    	
    	@Override
    	public void error(Message msg)
    	{
    		setError(msg);
    	}
    }

}
