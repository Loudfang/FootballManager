package mechanicsspel;
import java.util.ArrayList;


public class Opstelling {

	private ArrayList<Speler> aanvallers, middenvelders, verdedigers, doelman;
	
	public Opstelling() {
		aanvallers = new ArrayList<Speler>();
		middenvelders = new ArrayList<Speler>();
		verdedigers = new ArrayList<Speler>();
		doelman = new ArrayList<Speler>();
	}
	
	public ArrayList<Speler> getAanvallers() {
		return aanvallers;
	}
	
	public ArrayList<Speler> getMiddenvelders() {
		return middenvelders;
	}
	
	public ArrayList<Speler> getVerdedigers() {
		return verdedigers;
	}
	
	public ArrayList<Speler> getDoelman() {
		return doelman;
	}
		
	public void addAanvaller(Speler speler) {
		aanvallers.add(speler);
	}
	
	public void addMiddenvelder(Speler speler) {
		middenvelders.add(speler);
	}
	
	public void addVerdediger(Speler speler) {
		verdedigers.add(speler);
	}
	
	public void addDoelman(Speler speler) {
		doelman.add(speler);
	}
	
	/**
	 * Maakt de best mogelijke 4-4-2 opstelling
	 * @param t Het team om de opstelling voor te maken
	 */
	public static void opstellingNPT(Team t){
		Opstelling opstelling = new Opstelling();
		ArrayList<Aanvaller> aanvallers = new ArrayList<Aanvaller>();
		ArrayList<Middenvelder> middenvelders = new ArrayList<Middenvelder>();
		ArrayList<Verdediger> verdedigers = new ArrayList<Verdediger>();
		ArrayList<Doelman> doelmannen = new ArrayList<Doelman>();
		
		for(int i=0; i<t.getAantalSpelers(); i++){		
			if(t.getSpeler(i) instanceof Aanvaller){
				aanvallers.add((Aanvaller) t.getSpeler(i));
			}
			if(t.getSpeler(i) instanceof Middenvelder){
				middenvelders.add((Middenvelder) t.getSpeler(i));
			}
			if(t.getSpeler(i) instanceof Verdediger){
				verdedigers.add((Verdediger) t.getSpeler(i));
			}
			if(t.getSpeler(i) instanceof Doelman) {
				doelmannen.add((Doelman) t.getSpeler(i));
			}
		}
		
		// Aanvallers opstellen
		for (int i = 0; i < 2; i++) {	
			opstelling.addAanvaller(besteAanvaller(aanvallers));
			aanvallers.remove(besteAanvaller(aanvallers));	
		}
		
		// Middenvelders opstellen
		for (int i = 0; i < 4; i++) {	
			opstelling.addMiddenvelder(besteMiddenvelder(middenvelders));
			middenvelders.remove(besteMiddenvelder(middenvelders));			
		}
		
		// Verdedigers opstellen
		for (int i = 0; i < 4; i++) {	
			opstelling.addVerdediger(besteVerdediger(verdedigers));
			verdedigers.remove(besteVerdediger(verdedigers));			
		}
		
		// Doelman opstellen
		opstelling.addDoelman(besteDoelman(doelmannen));
		
		t.setOpstelling(opstelling);
	}
	
	/**
	 * Selecteert de aanvaller met de beste offensieve kracht uit een arraylist van aanvallers
	 * @param a Arraylist van aanvallers
	 * @return De sterkste aanvaller
	 */
	public static Aanvaller besteAanvaller(ArrayList<Aanvaller> a) {
		Aanvaller aanvaller1 = new Aanvaller(null, 0, null, 0, 0, 0, null);
		
		for(int i = 0; i < a.size(); i++) {		
			if (a.get(i).getOffensief() > aanvaller1.getOffensief()) {
				aanvaller1 = a.get(i);
			}			
		}
		return aanvaller1;
	}
	
	/**
	 * Selecteert de middenvelder met het beste totaal van offensieve en defensieve kracht
	 * @param m Arraylist van middenvelders
	 * @return De sterkste middenvelder
	 */
	public static Middenvelder besteMiddenvelder(ArrayList<Middenvelder> m) {
		Middenvelder middenvelder1 = new Middenvelder(null, 0, null, 0, 0, 0, null);
		
		for(int i = 0; i < m.size(); i++) {		
			if ((m.get(i).getOffensief() + m.get(i).getDefensief()) > (middenvelder1.getOffensief() + middenvelder1.getDefensief())) {
				middenvelder1 = m.get(i);
			}			
		}
		return middenvelder1;
	}
	
	/**
	 * Selecteert de verdediger met de beste defensieve kracht
	 * @param v Arraylist van verdedigers
	 * @return De beste verdediger
	 */
	public static Verdediger besteVerdediger(ArrayList<Verdediger> v) {
		Verdediger verdediger1 = new Verdediger(null, 0, null, 0, 0, 0, null);
		
		for(int i = 0; i < v.size(); i++) {		
			if (v.get(i).getDefensief() > verdediger1.getDefensief()) {
				verdediger1 = v.get(i);
			}			
		}
		return verdediger1;
	}
	
	/**
	 * Selecteert de doelman met de beste defensieve kracht
	 * @param d Arraylist van doelmannen
	 * @return De beste doelman
	 */
	public static Doelman besteDoelman(ArrayList<Doelman> d) {
		Doelman doelman1 = new Doelman(null, 0, null, 0, 0, 0, null);
		
		for(int i = 0; i < d.size(); i++) {		
			if (d.get(i).getDefensief() > doelman1.getDefensief()) {
				doelman1 = d.get(i);
			}			
		}
		return doelman1;
	}
	
	public String toString(){
		String res = "Opstelling: Aanvallers: ";
		for(int i=0; i<this.aanvallers.size(); i++){
			res += aanvallers.get(i).toString();
		}
		res += "Middenvelders: ";
		for(int i=0; i<this.middenvelders.size(); i++){
			res += middenvelders.get(i).toString();
		}
		res += "Verdedigers: ";
		for(int i=0; i<this.verdedigers.size(); i++){
			res += verdedigers.get(i).toString();
		}
		res += "Doelman: " + this.doelman.get(0);
		return res;
	}
	
	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Opstelling){
			Opstelling that = (Opstelling)obj;
			if(this.aanvallers.size()==that.aanvallers.size()
					&& this.middenvelders.size()==that.middenvelders.size()
					&& this.verdedigers.size()==that.verdedigers.size()
					&& this.doelman.size()==that.doelman.size()){
				res = true;
				for(int i=0; i<this.aanvallers.size(); i++){
					if(!this.aanvallers.get(i).equals(that.aanvallers.get(i))){
						return false;
					}
				}
				for(int i=0; i<this.middenvelders.size(); i++){
					if(!this.middenvelders.get(i).equals(that.middenvelders.get(i))){
						return false;
					}
				}
				for(int i=0; i<this.verdedigers.size(); i++){
					if(!this.verdedigers.get(i).equals(that.verdedigers.get(i))){
						return false;
					}
				}
				for(int i=0; i<this.doelman.size(); i++){
					if(!this.doelman.get(i).equals(that.doelman.get(i))){
						return false;
					}
				}
			}
		}
		return res;
	}
}
