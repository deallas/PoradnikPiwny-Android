package pl.poradnikpiwny.entity;

import pl.poradnikpiwny.enumtype.*;

import com.google.gson.annotations.SerializedName;

public class Beer {

	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("alcohol")
	private float alcohol;
	
	@SerializedName("extract")
	private float extract;
	
	@SerializedName("malt")
	private Malt malt;
	
	@SerializedName("type")
	private Type type;
	
	@SerializedName("filtered")
	private Filtered filtered;
	
	@SerializedName("pasteurized")
	private Pasteurized pasteurized;
	
	@SerializedName("flavored")
	private Flavored flavored;
	
	@SerializedName("placeofbrew")
	private Placeofbrew placeofbrew;
	
	@SerializedName("rankingAvg")
	private float rankingAvg;
	
	@SerializedName("rankingWeightedAvg")
	private float rankingWeightedAvg;
	
	@SerializedName("dateAdded")
	private String dateAdded;
	
	private BeerFamily family;
	
	private BeerDistributor distributor;
	
	private BeerManufacturer manufacturer;
	
	private Country country; 
	
	private Region region;
	
	private City city;
	
	private BeerImage image;
	
	/* ------------------------------------------------------------------- */
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public float getAlcohol(){
		return this.alcohol;
	}
	
	public void setAlcohol(float alcohol){
		this.alcohol = alcohol;
	}
	
	public float getExtract(){
		return this.extract;
	}
	
	public void setExtract(float extract){
		this.extract = extract;
	}
	
	public Malt getMalt(){
		return this.malt;
	}
	
	public void setMalt(Malt malt){
		this.malt = malt;
	}
	
	public Type getType(){
		return this.type;
	}
	
	public void setType(Type type){
		this.type = type;
	}
	
	public Filtered getFiltered(){
		return this.filtered;
	}
	
	public void setFiltered(Filtered filtered){
		this.filtered = filtered;
	}
	
	public Pasteurized getPasteurized(){
		return this.pasteurized;
	}
	
	public void setPasteurized(Pasteurized pasteurized){
		this.pasteurized = pasteurized;
	}
	
	public Flavored getFlavored(){
		return this.flavored;
	}
	
	public void setFlavored(Flavored flavored){
		this.flavored = flavored;
	}
	
	public Placeofbrew getPlaceofbrew(){
		return this.placeofbrew;
	}
	
	public void setPlaceofbrew(Placeofbrew place){
		this.placeofbrew = place;
	}
	
	public float getRankingAvg(){
		return this.rankingAvg;
	}
	
	public void setRankingAvg(float ranking){
		this.rankingAvg = ranking;
	}
	
	public float getRankingWeightedAvg(){
		return this.rankingWeightedAvg;
	}
	
	public void setRankingWeightedAvg(float ranking){
		this.rankingWeightedAvg = ranking;
	}
	
	public String getDateAdded(){
		return this.dateAdded;
	}
	
	public void setDateAdded(String date){
		this.dateAdded = date;
	}
	
	public BeerFamily getFamily() {
		return family;
	}

	public void setFamily(BeerFamily family) {
		this.family = family;
	}

	public BeerDistributor getDistributor(){
		return this.distributor;
	}
	
	public void setDistributor(BeerDistributor distributor){
		this.distributor = distributor;
	}
	
	public BeerManufacturer getManufacturer(){
		return this.manufacturer;
	}
	
	public void setManufacturer(BeerManufacturer manufacturer){
		this.manufacturer = manufacturer;
	}
	
	public Country getCountry(){
		return this.country; 
	}
	
	public void setCountry(Country country){
		this.country = country;
	}
	
	public Region getRegion(){
		return this.region;
	}
	
	public void setRegion(Region region){
		this.region = region;
	}
	
	public City getCity(){
		return this.city;
	}
	
	public void setCity(City city){
		this.city = city;
	}
	
	public BeerImage getImage(){
		return this.image;
	}
	
	public void setImage(BeerImage image){
		this.image = image;
	}
	
}
