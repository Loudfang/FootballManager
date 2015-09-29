package mechanicsspel;


public class Mechanics {
	
	private static int speelRonde = 0;
	/**
	 * bepaalt aan de hand van een speelschema en de speelronde alle
	 * uitslagen van de wedstrijden in die speelronde
	 */
	public static void bepaalUitslagenSpeelronde(Speelschema schema){
		Speelronde temp = schema.get(speelRonde);
		for(int j=0; j<temp.length(); j++){
			Wedstrijd wed = temp.getWedstrijd(j);
			Team t1 = wed.getTeamThuis();
			Team t2 = wed.getTeamUit();
			Competitie.afterMatch(t1,t2);
		}
		speelRonde++;
	}
	
	public static int getSpeelRonde() {
		return speelRonde;
	}
	
	public static void setSpeelRonde(int i) {
		speelRonde = i;
	}
}
