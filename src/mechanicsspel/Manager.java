package mechanicsspel;

import interfacegui.GUI;


public class Manager {

	private Team team;
	protected int budget;
	private String naam;
	
	public Manager(Team tm, int bdgt, String nm) {
		team = tm;
		budget = bdgt;
		naam = nm;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public Team getTegenstander() {
		Speelronde tempRonde = GUI.getSpeelschema().get(Mechanics.getSpeelRonde());
		for (int i = 0; i < tempRonde.length(); i++) {
			if (tempRonde.getWedstrijd(i).getTeamThuis() == GUI.getManager().getTeam()) {
				return tempRonde.getWedstrijd(i).getTeamUit();
			} else if (tempRonde.getWedstrijd(i).getTeamUit() == GUI.getManager().getTeam()) {
				return tempRonde.getWedstrijd(i).getTeamThuis();
			}
		}
		return null;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setBudget(int i) {
		budget = i;
	}
	
	public String toString(){
		String res = "Manager: "+this.naam+", team: "+this.team.getTeamname()+", budget: "+this.budget;
		return res;
	}
	
	public String toXML(){
		String res ="<naam>"+this.naam+"</naam>\r\n<team>"+this.team.getTeamname()+"</team>\r\n"+
					"<budget>"+this.budget+"</budget>\r\n";
		return res;
	}
	
	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Manager){
			Manager that = (Manager)obj;
			if(this.getTeam().equals(that.getTeam()) && this.getBudget()==that.getBudget()
					&& this.getNaam().equals(that.getNaam())){
				return true;
			}
		}
		return res;
	}
}
