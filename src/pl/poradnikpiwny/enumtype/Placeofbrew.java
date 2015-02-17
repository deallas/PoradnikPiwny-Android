package pl.poradnikpiwny.enumtype;

public enum Placeofbrew {
	BROWAR("BROWAR"),
	RESTAURACJA("RESTAURACJA"),
	DOM("DOM")
	;
    /**
     * @param text
     */
	private Placeofbrew(final String text){
		this.text = text;
	}
	
	private final String text;
	
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
	@Override
	public String toString(){
		return text;
	}
}
