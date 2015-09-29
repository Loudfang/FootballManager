package mechanicsspel;


public class Doelman extends Speler{
	
	public Doelman(String nm, int pr, String tp, int off, int def, int uit, String st){
		super(nm,pr,tp,off,def,uit,st);
	}
	
	/**
	 * string representatie van een doelman, gebruikt toString methode van Speler
	 */
	public String toString(){
		return super.toString();
	}
	
	/**
	 * XML representatie van een doelman, gebruikt toXML methode van Speler
	 */
	public String toXML(){
		return super.toXML();
	}
}
