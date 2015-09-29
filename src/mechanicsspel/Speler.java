package mechanicsspel;

public abstract class Speler {
	
	private String naam;
	private int prijs;
	private String type;
	private int offensief;
	private int defensief;
	private int uithoudingsvermogen;
	private int nummer;
	private String status;
	private boolean opgesteld;
	
	public Speler(String nm, int pr, String tp, int off, int def, int uit, String st){
		naam = nm;
		prijs = pr;
		type = tp;
		offensief = off;
		defensief = def;
		uithoudingsvermogen = uit;
		status = st;
		opgesteld = false;
	}
	
	public int getNummer() {
		return nummer;
	}
	
	public void setNummer(int n) {
		nummer = n;
	}
	
	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}

	public String getType() {
		return type;
	}

	/**
	 * zet de waarde van opgesteld van een speler op true
	 */
	public void setOpgesteld(boolean b){
		this.opgesteld = b;
	}
	
	public boolean getOpgesteld() {
		return opgesteld;
	}
	
	public void setStatus(String s){
		this.status = s;
	}

	public String getStatus(){
		return status;
	}
	
	public int getStamina(){
		return uithoudingsvermogen;
	}

	public int getOffensief() {
		return offensief;
	}
	
	public int getDefensief() {
		return defensief;
	}
	
	public void setDefensief(int n) {
		defensief = n;
	}
	
	public void setOffensief(int n) {
		offensief = n;
	}
	
	public void setStamina(int n) {
		uithoudingsvermogen = n;
	}
	public String getNaam() {
		return naam;
	}
	
	/**
	 * XML representatie van een speler
	 */
	public String toXML(){
		String res= "<speler>\r\n<naam>"+naam+"</naam>\r\n<prijs>"+prijs+"</prijs>\r\n"+
					"<type>"+type+"</type>\r\n<offensief>"+offensief+"</offensief>\r\n<defensief>"+defensief+"</defensief>\r\n<uithoudingsvermogen>"+
					uithoudingsvermogen+"</uithoudingsvermogen>\r\n<status>"+status+"</status>\r\n</speler>";
		return res;
	}
	
	/**
	 * string representatie van een speler
	 */
	public String toString(){
		return "naam: " +naam+", prijs: "+prijs+", type: "+type+", offensief: "+offensief+
				", defensief: "+defensief+", uithoudingsvermogen: "+uithoudingsvermogen+", status: "+status;
	}
	
	/**
	 * equals methode voor een speler
	 */
	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Speler){
			Speler that = (Speler)obj;
			if(this.naam.equals(that.naam) && this.prijs == that.prijs
				&& this.type.equals(that.type) && this.offensief == that.offensief
				&& this.defensief == that.defensief && this.uithoudingsvermogen == that.uithoudingsvermogen
				&& this.status.equals(that.status)){
				res = true;
			}
		}
		return res;
	}
}
