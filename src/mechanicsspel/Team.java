package mechanicsspel;
import java.util.ArrayList;

public class Team {

	private ArrayList<Speler> slist;
	private String teamname;
	private int punten, doelsaldo,winst,verlies,gelijk,doelv;
	private Opstelling opstelling;

	public Team(String tm, int p, int d, int w, int v, int g, int dv){
		slist = new ArrayList<Speler>();
		teamname = tm;
		punten = p;
		doelsaldo = d;
		winst = w;
		verlies = v;
		gelijk = g;
		doelv = dv;
	}
	
	public void setDoelv(int x){
		this.doelv = x;
	}
	
	public int getDoelv(){
		return this.doelv;
	}
	
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public void setPunten(int punten) {
		this.punten = punten;
	}

	public void setDoelsaldo(int doelsaldo) {
		this.doelsaldo = doelsaldo;
	}

	public void setWinst(int winst) {
		this.winst = winst;
	}

	public void setVerlies(int verlies) {
		this.verlies = verlies;
	}

	public void setGelijk(int gelijk) {
		this.gelijk = gelijk;
	}

	public int getAantalSpelers(){
		return slist.size();	
	}
	
	public Speler getSpeler(int x){
		return slist.get(x);
	}
	
	public void verwijderSpeler(Speler s) {
		int i = slist.indexOf(s);		
		slist.remove(s);
		slist.listIterator(i);
	}
	
	/**
	 * geeft de naam van een team
	 */
	public String getTeamname(){
		return this.teamname;
	}
	
	/**
	 * geeft het doelsaldo van een team
	 */
	public int getDoelsaldo(){
		return this.doelsaldo;
	}

	public Opstelling getOpstelling(){
		return this.opstelling;
	}
	
	public void setOpstelling(Opstelling o) {
		for(int i = 0; i < slist.size(); i++) {
			slist.get(i).setOpgesteld(false);
		}
		
		this.opstelling = o;
		
		for(int i = 0; i < o.getAanvallers().size(); i++) {
			o.getAanvallers().get(i).setOpgesteld(true);
		}
		
		for(int i = 0; i < o.getMiddenvelders().size(); i++) {
			o.getMiddenvelders().get(i).setOpgesteld(true);
		}
		
		for(int i = 0; i< o.getVerdedigers().size(); i++) {
			o.getVerdedigers().get(i).setOpgesteld(true);
		}
		
		for(int i = 0; i< o.getDoelman().size(); i++) {
			o.getDoelman().get(i).setOpgesteld(true);
		}
	}

	/**
	 * geeft het aantal winst van een team
	 */
	public int getWinst(){
		return this.winst;
	}
	/**
	 * geeft het aantal gelijk van een team
	 */
	public int getGelijk(){
		return this.gelijk;
	}
	/**
	 * geeft het aantal verlies van een team
	 */
	public int getVerlies(){
		return this.verlies;
	}
	/**
	 * geeft de punten van een team
	 */
	public int getPunten(){
		return this.punten;
	}

	/**
	 * methode om een speler aan een team toe te voegen
	 */
	public void voegSpelerToe(Speler sp){
		int index = -1;
		
		for (int i = 0; i < this.getAantalSpelers(); i++) {
			if(slist.get(i) == null) {
				index = i;
				System.out.println("Empty spot found");
			}
		}
		
		if (index != -1) {
			slist.set(index, sp);
		} else {
			slist.add(sp);	
		}
	}
	
	/**
	 * berekent met een for-loop de totale offensie waarde van een team(totaal ipv gemiddeld want
	 * anders telt het aantal aanvallers,verdedigers niet) als ze opgesteld zijn
	 */
	public int offensiefTotaal() {
		int score = 0;
		Opstelling opstelling = this.opstelling;
		
		if (opstelling != null) {
		for (int i = 0; i < opstelling.getAanvallers().size(); i++) {
			score = score + opstelling.getAanvallers().get(i).getOffensief();
		}
		
		for (int i = 0; i < opstelling.getMiddenvelders().size(); i++) {
			score = score + opstelling.getMiddenvelders().get(i).getOffensief();
		}		
		}
		
		return score;
	}
	
	/**
	 * berekent met een for-loop de totale defensie waarde van een team(totaal ipv gemiddeld want
	 * anders telt het aantal aanvallers,verdedigers niet) als ze opgesteld zijn
	 */	
	public int defensiefTotaal() {
		int score = 0;
		Opstelling opstelling = this.opstelling;
		
		if (opstelling != null) {
		for (int i = 0; i < opstelling.getMiddenvelders().size(); i++) {
			score = score + opstelling.getMiddenvelders().get(i).getDefensief();
		}
		
		for (int i = 0; i < opstelling.getVerdedigers().size(); i++) {
			score = score + opstelling.getVerdedigers().get(i).getDefensief();
		}
		
		for (int i = 0; i < opstelling.getDoelman().size(); i++) {
			score = score + opstelling.getDoelman().get(i).getDefensief();
		}
		}
		
		return score;
	}
	
	/**
	 * berekent met een for-loop het gemiddelde uithoudingsvermogen van een team(gemiddeld omdat
	 * een team altijd hetzelfde aantal spelers heeft) ale ze opgesteld zijn
	 */
	public int staminaTotaal(){
		int score = 0;
		for(int i=0; i<slist.size(); i++){
			if(slist.get(i).getOpgesteld() == true){
				score += slist.get(i).getStamina();
			}			
		}
		score = (int)(score/10);
		return score;		
	}
	
	/**
	 * als team heeft gewonnen(winst=true) dan worden alle statistieken verhoogd
	 * de prijs wordt verhoogd met een factor 1.05 en daarna naar een int gecast
	 * oftewel 52.1 wordt 52 en 52.8 wordt ook 52
	 */
	public void updateStatistieken() {
		if(RNG.randomInt(1,100) > 50){
			for(int i=0; i<slist.size(); i++){
				Speler temp = slist.get(i);
				double p = temp.getPrijs() *1.05;
				temp.setPrijs((int) p);
				
				if(temp instanceof Doelman){
					temp.setDefensief((temp.getDefensief() + 2));
				}
				if(temp instanceof Verdediger){
					temp.setDefensief((temp.getDefensief() + 2));
					temp.setStamina((temp.getStamina() + 1));
				}
				if(temp instanceof Middenvelder){
					temp.setDefensief((temp.getDefensief() + 1));
					temp.setOffensief((temp.getOffensief() + 1));
					temp.setStamina((temp.getStamina() + 1));
				}
				if(temp instanceof Aanvaller){
					temp.setOffensief((temp.getOffensief() + 2));
					temp.setStamina((temp.getStamina() + 1));
				}
			}
		}
	}
	
	public void voegKaartenToe(){
		for(int i=0; i<slist.size(); i++){
			if(slist.get(i).getOpgesteld()==true){
				int k = RNG.randomInt(0, 100);
				if(k<10){
					if(slist.get(i).getStatus().equals("geel")){
						slist.get(i).setStatus("rood-3");
					}
					else{
						slist.get(i).setStatus("geel");
					}
				}
				else if(k>97){
					slist.get(i).setStatus("rood-2");
				}
			}
		}		
	}
	
	public void voegBlessureToe(){
		for(int i=0; i<slist.size(); i++){
			if(RNG.randomInt(0,slist.get(i).getStamina())<5
				&& slist.get(i).getOpgesteld()==true
				&& !slist.get(i).getStatus().equals("geel")
				&& !(slist.get(i) instanceof Doelman)){
					slist.get(i).setStatus("blessure-1");
			}	
		}
	}
	
	public void statusNaSpeelronde(){
		for(int i=0; i<slist.size(); i++){
			if(slist.get(i).getOpgesteld()==false){
				String temp = slist.get(i).getStatus();			
				switch(temp){
					case "blessure-1":
						slist.get(i).setStatus("");
						break;
					case "rood-3":
						slist.get(i).setStatus("rood-2");
						break;
					case "rood-2":
						slist.get(i).setStatus("rood-1");
						break;
					case "rood-1":
						slist.get(i).setStatus("");
						break;
				}
			}
		}
	}

	/**
	 * methode om competitiepunten toe te voegen, waar x=-1 voor verlies
	 * x=0 voor gelijkspel en x=1 voor winst
	 */
	public void compPuntenUpdate(int x){
		if(x==0){
			this.punten +=1;
			this.gelijk +=1;
		}
		if(x==1){
			this.punten +=3;
			this.winst +=1;
		}
		if(x==-1){
			this.verlies +=1;
		}
	}
	
	/**
	 * verhoogt het doelsaldo van een team met meegegeven waarde
	 */
	public void doelSaldoPlus(int x){
		this.doelsaldo +=x;
	}
	
	/**
	 * verlaagt het doelsaldo van een team met meegegeven waarde
	 */
	public void doelSaldoMin(int x){
		this.doelsaldo -= x;
	}
	
	public void doelV(int x){
		this.doelv += x;
	}
	
	/**
	 * XML representatie van een team, maakt gebruik van toXML van een speler
	 */
	public String toXML(){
		String res = "<teamname id=\""+this.teamname+"\" p=\""+this.punten+"\" d=\""+this.doelsaldo+"\" w=\""+
				this.winst+"\" v=\""+this.verlies+"\" g=\""+this.gelijk+"\" dv=\""+this.doelv+"\">\r\n";
		for(int i=0; i<slist.size(); i++){
			res += slist.get(i).toXML()+ "\r\n";
		}
		res += "</teamname>";
		return res;
	}
	
	/**
	 * string representatie van een team, maakt gebruik van toString van een speler
	 */
	public String toString(){
		String res = this.teamname+" p="+this.punten+" d="+this.doelsaldo+" w="+this.winst+" v="+this.verlies+
				" g="+this.gelijk+" dv="+this.doelv+"\r";
		for(int i=0; i<slist.size(); i++){
			res += slist.get(i).toString()+ "\r";
		}
		return res;
	}
	
	/**
	 * equals methode voor een team
	 */
	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Team){
			Team that = (Team)obj;
			res = true;
			if(!this.getTeamname().equals(that.getTeamname())){
				return false;
			}
			if(!(this.getAantalSpelers()==that.getAantalSpelers())){
				return false;
			}
			for(int i=0; i<this.getAantalSpelers(); i++){
				if(!this.getSpeler(i).equals(that.getSpeler(i))){
					return false;
				}
			}
		}
		return res;
	}
}
