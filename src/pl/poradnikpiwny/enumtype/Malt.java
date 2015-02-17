package pl.poradnikpiwny.enumtype;

public enum Malt {
	JECZMIENNY("JECZMIENNY"),
	PSZENNY("PSZENNY"),
	INNY("INNY")
	;
    /**
     * @param text
     */
	private Malt(final String text){
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
