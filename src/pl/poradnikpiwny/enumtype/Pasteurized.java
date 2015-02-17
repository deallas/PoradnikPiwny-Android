package pl.poradnikpiwny.enumtype;

public enum Pasteurized {
	NIEWIEM("NIEWIEM"),
	NIE("NIE"),
	TAK("TAK")
	;
    /**
     * @param text
     */
	private Pasteurized(final String text){
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
