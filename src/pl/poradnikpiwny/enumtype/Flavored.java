package pl.poradnikpiwny.enumtype;

public enum Flavored {
	NIEWIEM("NIEWIEM"),
	NIE("NIE"),
	TAK("TAK");
	
    /**
     * @param text
     */
	private Flavored(final String text){
		this.text = text;
	}
	
	private final String text;
	
	@Override
	public String toString(){
		return text;
	}
}
