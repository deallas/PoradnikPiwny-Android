package pl.poradnikpiwny.enumtype;

public enum Type {
	BEZALKOHOLOWE("BEZALKOHOLOWE"),
	LEKKIE("LEKKIE"),
	PELNE("PELNE"),
	MOCNE("MOCNE")
	;
    /**
     * @param text
     */
	private Type(final String text){
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
