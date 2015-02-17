package pl.poradnikpiwny.enumtype;

public enum Filtered {
	NIEWIEM("NIEWIEM"),
	NIE("NIE"),
	TAK("TAK")
	;
    /**
     * @param text
     */
	private Filtered(final String text){
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
