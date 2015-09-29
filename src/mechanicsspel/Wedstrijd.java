package mechanicsspel;

public class Wedstrijd { 
protected Team thuis,uit;

public Wedstrijd(Team thuis, Team uit){
	this.thuis = thuis;
	this.uit = uit;
}

public String toString(){
	return thuis.getTeamname() + " speelt tegen " + uit.getTeamname();
}

public Team getTeamThuis(){
	return thuis;
}

public Team getTeamUit(){
	return uit;
}

public void setTeamThuis(Team team){
	this.thuis = team;
}

public void setTeamUit(Team team){
	this.uit = team;
}
public String toXML(){
	return "<wedstrijd>" + thuis.getTeamname() + "-" + uit.getTeamname() + "</wedstrijd>";
}

	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Wedstrijd){
			Wedstrijd that = (Wedstrijd)obj;
			if(this.getTeamThuis().equals(that.getTeamThuis())
					&& this.getTeamUit().equals(that.getTeamUit())){
				return true;
			}
		}
		return res;
	}
}
