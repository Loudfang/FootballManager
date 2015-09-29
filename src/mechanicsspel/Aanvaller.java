package mechanicsspel;


public class Aanvaller extends Speler{
	
	public Aanvaller(String nm, int pr, String tp, int off, int def, int uit, String st){
		super(nm,pr,tp,off,def,uit,st);
	}
	
	/**
	 * geeft een string representatie van een speler
	 */
	public String toString(){
		return super.toString();
	}
	
	/**
	 * geeft een XML representatie van een speler
	 */
	public String toXML(){
		return super.toXML();
	}
}