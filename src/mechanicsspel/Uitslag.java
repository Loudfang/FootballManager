package mechanicsspel;


public class Uitslag extends Wedstrijd{
	
	private int[] uitslag;

	public Uitslag(Team thuis, Team uit, int[] stand) {
		super(thuis, uit);
		uitslag = stand;
	}
	
	public int[] getUitslag() {
		return uitslag;
	}
	
	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Uitslag){
			Uitslag that = (Uitslag)obj;
			if(this.getTeamThuis().equals(that.getTeamThuis())
					&& this.getTeamUit().equals(that.getTeamUit())
					&& this.getUitslag().equals(that.getUitslag())){
				return true;
			}
		}
		return res;
	}
}
