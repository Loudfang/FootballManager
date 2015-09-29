package mechanicsspel;

public class Verdediger extends Speler{
	
	public Verdediger(String nm, int pr, String tp, int off, int def, int uit, String st){
		super(nm,pr,tp,off,def,uit,st);
	}
	
	/**
	 * string representatie van een verdediger, maakt gebruik van toString van Speler
	 */
	public String toString(){
		return super.toString();
	}
	
	/**
	 * XML representatie van een verdediger, maakt gebruik van toXML van Speler
	 */
	public String toXML(){
		return super.toXML();
	}
}
