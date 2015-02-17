package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.BeerImage;
import pl.poradnikpiwny.util.PaginatorResponse;

public class BeerImageResponseList extends PaginatorResponse {
	private List<BeerImage> images;
	
	public List<BeerImage> getImages(){
		return images;
	}
	
	public void setImages(List<BeerImage> list){
		this.images = list;
	}
}
