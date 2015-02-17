package pl.poradnikpiwny.activity;

import java.lang.reflect.Field;
import java.net.URL;

import pl.poradnikpiwny.R;
import pl.poradnikpiwny.activity.fragment.BeerFragment;
import pl.poradnikpiwny.entity.Beer;
import pl.poradnikpiwny.entity.BeerFamily;
import pl.poradnikpiwny.entity.BeerManufacturer;
import pl.poradnikpiwny.entity.BeerTranslation;
import pl.poradnikpiwny.entity.City;
import pl.poradnikpiwny.entity.Country;
import pl.poradnikpiwny.entity.Region;
import pl.poradnikpiwny.entity.response.BeerResponseSingle;
import pl.poradnikpiwny.entity.response.BeerTranslationResponseSingle;
import pl.poradnikpiwny.enumtype.Filtered;
import pl.poradnikpiwny.enumtype.Pasteurized;
import pl.poradnikpiwny.enumtype.Flavored;
import pl.poradnikpiwny.enumtype.Placeofbrew;
import pl.poradnikpiwny.enumtype.Malt;
import pl.poradnikpiwny.enumtype.Type;
import pl.poradnikpiwny.rest.AbstractHandler;
import pl.poradnikpiwny.rest.RestAction;
import pl.poradnikpiwny.rest.RestService;

import com.google.gson.Gson;

import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ShowBeerActivity extends Activity 
{
	private TextView tBeerName,tBeerAlcohol,tBeerExtract,
			 tBeerMalt,tBeerType,tBeerFiltered,
			 tBeerPasteurized,tBeerFlavored,tBeerPlaceofbrew,
			 tBeerRating,tBeerDate,tBeerStars,tBeerManufacturer,
			 tCountry, tRegion, tCity, tFamily, tBeerDesciprition;
	
	private LinearLayout lBeerAlcohol,lBeerExtract,
				 lBeerMalt,lBeerType,lBeerFiltered,
				 lBeerPasteurized,lBeerFlavored,
				 lBeerPlaceofbrew, lCountry, lRegion,
				 lCity, lFamily;
	
	private RelativeLayout lBeerInfoLoader;
	
	private ImageView ivBeerImage;
	
	private boolean beerInfoLoaded = false, 
					  beerDescriptionLoaded = false;
	
	/* ----------------------------------------------------------------------- */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_beer);
		
		String beerId = String.valueOf(getIntent().getExtras().getInt("beerId"));
		
        RestService restInfo = new RestService(new HandlerBeerInfo(), this);
        restInfo.addParam("beer_id", beerId);
        restInfo.execute();
        
        RestService restTranslation = new RestService(new HandlerBeerTranslation(), this);
        restTranslation.addParam("beer_id", beerId);
        restTranslation.execute();
		
		ivBeerImage = (ImageView) findViewById(R.id.ShowBeerImage);
		
		tBeerName = (TextView) findViewById(R.id.tBeerName);
		tBeerAlcohol = (TextView) findViewById(R.id.tBeerAlcoholValue);
		tBeerExtract = (TextView) findViewById(R.id.tBeerExtractValue);
		tBeerMalt = (TextView) findViewById(R.id.tBeerMaltValue);
		tBeerType = (TextView) findViewById(R.id.tBeerTypeValue);
		tBeerFiltered = (TextView) findViewById(R.id.tBeerFilteredValue);
		tBeerPasteurized = (TextView) findViewById(R.id.tBeerPasteurizedValue);
		tBeerFlavored = (TextView) findViewById(R.id.tBeerFlavoredValue);
		tBeerPlaceofbrew = (TextView) findViewById(R.id.tBeerPlaceofbrewValue);
		tBeerRating = (TextView) findViewById(R.id.tBeerRating);
		tBeerDate = (TextView) findViewById(R.id.tBeerDate);
		tBeerStars = (TextView) findViewById(R.id.tBeerStars);
		tBeerManufacturer = (TextView) findViewById(R.id.tBeerManufacturer);
		tBeerDesciprition = (TextView) findViewById(R.id.tBeerDescription);
		tCountry = (TextView) findViewById(R.id.tCountryValue);
		tRegion = (TextView) findViewById(R.id.tRegionValue);
		tCity = (TextView) findViewById(R.id.tCityValue);
		tFamily = (TextView) findViewById(R.id.tBeerFamilyValue);
		
		lBeerAlcohol = (LinearLayout) findViewById(R.id.lBeerAlcohol);
		lBeerExtract = (LinearLayout) findViewById(R.id.lBeerExtract);
		lBeerMalt = (LinearLayout) findViewById(R.id.lBeerMalt);
		lBeerType = (LinearLayout) findViewById(R.id.lBeerType);
		lBeerFiltered = (LinearLayout) findViewById(R.id.lBeerFiltered);
		lBeerPasteurized = (LinearLayout) findViewById(R.id.lBeerPasteurized);
		lBeerFlavored = (LinearLayout) findViewById(R.id.lBeerFlavored);
		lBeerPlaceofbrew = (LinearLayout) findViewById(R.id.lBeerPlaceofbrew);
		lCountry = (LinearLayout) findViewById(R.id.lCountry);
		lRegion = (LinearLayout) findViewById(R.id.lRegion);
		lCity = (LinearLayout) findViewById(R.id.lCity);
		lFamily = (LinearLayout) findViewById(R.id.lBeerFamily);
		
		lBeerInfoLoader = (RelativeLayout) findViewById(R.id.BeerInfoLoader);
		lBeerInfoLoader.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.activity_show_beer, menu);
		return true;
	}

	private String getTranslatedEnum(Enum e) 
	{
		return getTranslatedEnum(e, "");
	}
	
	private String getTranslatedEnum(Enum e, String enumName) 
	{
		Context context = getBaseContext();
		String v = "";
		
		if(!enumName.isEmpty()) {
			v += enumName + "_";
		}
		
		v += e.toString().toLowerCase();
		String result;
		
		try {
			Class<?> class1 =  Class.forName("pl.poradnikpiwny.R$string");
		    Field field = class1.getField(v);
			result = context.getString(field.getInt(class1));	
		} catch(Exception exc) {
			return "";
		}
		
		return result;
	}
	
	private void hideLoader()
	{
		if(beerInfoLoaded && beerDescriptionLoaded) {
			lBeerInfoLoader.setVisibility(View.GONE);
		}
	}
	
	/* ----------------------------------------------------------------------- */
	
	private final class HandlerBeerTranslation extends AbstractHandler
	{

		@Override
		public String getServiceUrl() 
		{
			return RestAction.BEER_TRANSLATION;
		}

		@Override
		public void success(Message msg) 
		{
			Gson gson = new Gson();
			String json = (String) msg.obj;
			BeerTranslationResponseSingle response = gson.fromJson(json, BeerTranslationResponseSingle.class);
			BeerTranslation beerTranslation = response.getTranslation();
			
			if(beerTranslation != null) {
				tBeerDesciprition.setText(beerTranslation.getDescription());
			} else {
				tBeerDesciprition.setVisibility(View.GONE);
			}
			
			beerDescriptionLoaded = true;
			hideLoader();
		}
		
		@Override
		public void error(Message msg) 
		{
			// TODO Auto-generated method stub
		}
	}
	
	/* ----------------------------------------------------------------------- */
	
	private final class HandlerBeerInfo extends AbstractHandler
	{
		@Override
		public String getServiceUrl() {
			return RestAction.BEER_INFO;
		}
		
    	@Override
    	public void success(Message msg) 
    	{
			try {
				Gson gson = new Gson();
				String json = (String) msg.obj;
				BeerResponseSingle response = gson.fromJson(json, BeerResponseSingle.class);
				Beer beer = response.getBeer();
				
				tBeerName.setText(beer.getName());
				
				Float alcohol = beer.getAlcohol();
				if(alcohol != null && alcohol != 0) {
					tBeerAlcohol.setText(String.format("%.2f", alcohol) + " %");
				} else {
					lBeerAlcohol.setVisibility(View.GONE);
				}
				
				Float extract = beer.getExtract();
				if(extract != null && extract != 0) {
					tBeerExtract.setText(String.format("%.2f", extract) + " %");
				} else {
					lBeerExtract.setVisibility(View.GONE);
				}
				
				Malt malt = beer.getMalt();
				if(malt != null) {
					tBeerMalt.setText(getTranslatedEnum(malt, "beer_malt"));
				} else {
					lBeerMalt.setVisibility(View.GONE);
				}
				
				Type type = beer.getType();
				if(type != null) {
					tBeerType.setText(getTranslatedEnum(type, "beer_type"));
				} else {
					lBeerType.setVisibility(View.GONE);
				}
				
				Filtered filtered = beer.getFiltered();
				if(filtered != null && filtered != Filtered.NIEWIEM) {
					tBeerFiltered.setText(getTranslatedEnum(filtered));
				} else {
					lBeerFiltered.setVisibility(View.GONE);
				}
				
				Pasteurized pasteurized = beer.getPasteurized();
				if(pasteurized != null && pasteurized != Pasteurized.NIEWIEM) {
					tBeerPasteurized.setText(getTranslatedEnum(pasteurized));
				} else {
					lBeerPasteurized.setVisibility(View.GONE);
				}
				
				Flavored flavored = beer.getFlavored();
				if(flavored != null && flavored != Flavored.NIEWIEM) {
					tBeerFlavored.setText(getTranslatedEnum(flavored));
				} else {
					lBeerFlavored.setVisibility(View.GONE);
				}
				
				Placeofbrew placeofbrew = beer.getPlaceofbrew();
				if(placeofbrew != null) {
					tBeerPlaceofbrew.setText(getTranslatedEnum(placeofbrew, "beer_placeofbrew"));
				} else {
					lBeerPlaceofbrew.setVisibility(View.GONE);
				}
				
				tBeerRating.setText(String.format("%.2f", beer.getRankingAvg()));
				tBeerDate.setText(beer.getDateAdded());
				tBeerStars.setText(BeerFragment.rankingToStars(beer.getRankingAvg()));
				
				BeerManufacturer manufacturer = beer.getManufacturer();
				if(manufacturer != null) {
					tBeerManufacturer.setText(manufacturer.getName());
				} else {
					tBeerManufacturer.setVisibility(View.INVISIBLE);
				}
				
				Country country = beer.getCountry();
				if(country != null) {
					tCountry.setText(country.getName());
				} else {
					lCountry.setVisibility(View.GONE);
				}
				
				Region region = beer.getRegion();
				if(region != null) {
					tRegion.setText(region.getName());
				} else {
					lRegion.setVisibility(View.GONE);
				}
				
				City city = beer.getCity();
				if(city != null) {
					tCity.setText(city.getName());
				} else {
					lCity.setVisibility(View.GONE);
				}
				
				BeerFamily family = beer.getFamily();
				if(family != null) {
					tFamily.setText(family.getName());
				} else {
					lFamily.setVisibility(View.GONE);
				}
				
    			if(beer.getImage()!=null) {
    				URL url=new URL(beer.getImage().getPath());
    				Bitmap bmp; 
    				bmp=BitmapFactory.decodeStream(url.openConnection().getInputStream());
    				ivBeerImage.setImageBitmap(bmp);
    			} else {
    				Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.photo);
    				ivBeerImage.setImageBitmap(bmp);
    			}
    			
    			beerInfoLoaded = true;
    			hideLoader();
			} catch(Exception e) {
				e.printStackTrace();
				finish();
			}
    	}

		@Override
		public void error(Message msg) 
		{
			// TODO Auto-generated method stub
		}		
    }

}
