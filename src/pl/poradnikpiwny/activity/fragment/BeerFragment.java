package pl.poradnikpiwny.activity.fragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import pl.poradnikpiwny.R;
import pl.poradnikpiwny.activity.ShowBeerActivity;
import pl.poradnikpiwny.entity.response.ErrorResponse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BeerFragment extends ListFragment {
	
	//lista przechowuje id piw
	protected List<Integer> beersId = new ArrayList<Integer>();
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        
		//załadowanie własnego layout'a dla ListFragment zamiast domyślnego R.layout.list_content
        View view = inflater.inflate(R.layout.beerlist, container, false);
        return view;

	}
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState){
		
		ListView lv = this.getListView();
		
		// naciśnięcia na wybrane piwo znajdujące się na liście powoduje otwarcie aktywności ze szczegółami na temat piwa
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int pos, long arg) {
					Intent showBeer = new Intent(getActivity().getBaseContext(), ShowBeerActivity.class);
					showBeer.putExtra("beerId", beersId.get(pos));
					startActivity(showBeer);
			}
		});
		
		super.onActivityCreated(savedInstanceState);
	}
	
	//ustawienie treści błędu i ukrycie animacji (spinner)
    public void setError(String msg){
    	TextView tHTTPError = (TextView) getActivity().findViewById(R.id.tHTTPError);
    	
    	ProgressBar pb = (ProgressBar) getActivity().findViewById(android.R.id.empty);
    	pb.setVisibility(ProgressBar.INVISIBLE);
    	
    	tHTTPError.setText(msg);
    	tHTTPError.setVisibility(TextView.VISIBLE);
    }
    
    public void setError(ErrorResponse response){
    	String error = response.getMsg();
    	if(error.equalsIgnoreCase("null_pointer")){
		setError(getString(R.string.beer_search_nullpointer));
		}else if(error.equalsIgnoreCase("invalid_form_values")){
			setError(getString(R.string.beer_search_invalidformvalues));
		}else if(error.equalsIgnoreCase("beer_not_found")){
			setError(getString(R.string.beer_notfound));
		}else if(error.equalsIgnoreCase("beer_image_not_found")){
			setError(getString(R.string.beer_image_notfound));
		}else if(error.equalsIgnoreCase("beer_image_neightbor_not_found")){
			setError(getString(R.string.beer_imageneightbornotfound));
		}else if(error.equalsIgnoreCase("beer_search_not_found")){
			setError(getString(R.string.beer_search_notfound));
		}else if(error.equalsIgnoreCase("beer_search_result_not_found")){
			setError(getString(R.string.beer_search_resultnotfound));
		}else if(error.equalsIgnoreCase("beer_family_not_found")) {
			setError(getString(R.string.beer_family_notfound));
		}else if(error.equalsIgnoreCase("beer_distributor_not_found")){
			setError(getString(R.string.beer_distributor_notfound));
		}else if(error.equalsIgnoreCase("beer_manufacturer_not_found")) {
			setError(getString(R.string.beer_manufacturer_notfound));
		}else if(error.equalsIgnoreCase("country_not_found")){
			setError(getString(R.string.country_notfound));
		}else if(error.equalsIgnoreCase("region_not_found")){
			setError(getString(R.string.region_notfound));
		}else if(error.equalsIgnoreCase("city_not_found")){
			setError(getString(R.string.city_notfound));
		}else{
			setError(error);
		}
    }
    
    public void setError(Message msg){
    	switch(msg.what){
    		case 400:
    		case 404:
				Gson gson = new Gson();
				String json = msg.obj.toString();
				ErrorResponse response = gson.fromJson(json ,ErrorResponse.class);
				setError(response);
				break;
    		case 444:
    			setError(getString(R.string.beer_error_noresponse));
    			break;
    		case 500:
    			setError(getString(R.string.beer_error_internalservererror));
    			break;
    		default:
    			setError(msg.what + " - " + msg.toString());
    	}
    }
    
    //funkcja zamienia postać liczbową rankingu piwa na odpowiadający tekst dla czcionki FontAwesome
    //ranking jest zaokrąglany w dół z dokładnością do połowy
    public static String rankingToStars(float ranking){
    	String rank="";
    	int stars = (int) Math.floor(ranking);
    	for(int i=0; i<stars;i++){
    		rank += (char) 61445; //kod całej gwiazdki
    	}
    	if(ranking-Math.floor(ranking)>=0.5)
    		rank += (char) 61577; //kod połowy gwiazdki
    	
    	return rank;
    }

    //pobranie i ustawienie miniaturki na liście piw
	protected final SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder(){
		@Override	
		public boolean setViewValue(View view, Object data, String textRepresentation) {
        	if (view.getId()==R.id.list_image) { //jeśli jest to ImageView 
        		try{
        			if(data.toString()!="DEFAULT"){
        				//pobranie miniaturki z adresu url
        				URL url=new URL(data.toString());
        				Bitmap bmp; 
        				bmp=BitmapFactory.decodeStream(url.openConnection().getInputStream());
        				ImageView iv = (ImageView) view;
        				iv.setImageBitmap(bmp);
        			}else{ 			
        				//ustawienie domyślnegej miniaturki
        				Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.photo);
        				ImageView iv = (ImageView) view;
        				iv.setImageBitmap(bmp);
        			}
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
        		return true;
        	} else {
        		return false; //dla TextView domyślnie  
        	}
		}
	};
}
