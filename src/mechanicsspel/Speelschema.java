package mechanicsspel;

import java.util.ArrayList;

public class Speelschema {
	private ArrayList<Speelronde> speelrondes;

	public Speelschema() {
		speelrondes = new ArrayList<Speelronde>();
	}
	/*Komende wedstrijden worden teruggestuurd in een speelronde van een team met een huidig speelchema..*/
	public Speelronde getKomendeWedstrijden(Team team) {
		Speelronde ronde = new Speelronde(0);
		for (int i = 0; i < this.length(); i++) {
			for (int j = 0; j < this.get(i).length(); j++) {
				if (this.get(i).getWedstrijd(j).getTeamThuis().equals(team)
						|| this.get(i).getWedstrijd(j).getTeamUit()
								.equals(team)) {
					ronde.addWedstrijd(this.get(i).getWedstrijd(j));
					break;
				}

			}

		}
		return ronde;
	}
	/*Main methode die een speelschema genereert en gebruik maakt van de methode speelschema 1 en speelschema 2*/
	public static Speelschema SpeelschemaGenereer(Competitie comp) {
		if (comp.length() % 2 == 0) {
			Speelschema een = Speelschema1(comp);
			Speelschema twee = Speelschema2(comp);

			for (int i = 0; i < comp.length() - 1; i++) {
				een.add(twee.get(i));
			}
			een = veranderthuisuit(een);
			return een;
		} else {
			return null;
		}

	}
	/*Eerste helft van het speelschema wordt gegenereerd.*/
	public static Speelschema Speelschema1(Competitie comp) {
		Competitie teams = comp;
		Speelschema speel = new Speelschema();
		Speelronde temp = new Speelronde(0);
		int speelronde = 1;
		Competitie een = new Competitie();
		Competitie twee = new Competitie();


			while (speel.length() != teams.length() - 1) {
				temp = new Speelronde(speelronde);
				if (speelronde == 1) {
					een = splits1(teams);
					twee = splits2(teams);

					for (int i = 0; i < een.length(); i++) {
						temp.addWedstrijd(new Wedstrijd(een.getTeam(i), twee
								.getTeam(i)));
					}
				}

				else {
					teams = rotate(een, twee);
					een = splits1(teams);
					twee = splits2(teams);
					for (int i = 0; i < een.length(); i++) {
						temp.addWedstrijd(new Wedstrijd(een.getTeam(i), twee
								.getTeam(i)));
					}
				}
				speel.add(temp);
				speelronde++;
			

		}

		return speel;

	}
	/*Tweede helft van het speelschema wordt gegenereerd.*/
	public static Speelschema Speelschema2(Competitie comp) {
		Competitie teams = comp;
		Speelschema speel = new Speelschema();
		Speelronde temp = new Speelronde(0);
		int speelronde = comp.length();
		Competitie een = new Competitie();
		Competitie twee = new Competitie();

		

			while (speel.length() != teams.length() - 1) {
				temp = new Speelronde(speelronde);
				if (speelronde == comp.length()) {
					een = splits1(teams);
					twee = splits2(teams);

					for (int i = 0; i < een.length(); i++) {
						temp.addWedstrijd(new Wedstrijd(twee.getTeam(i), een
								.getTeam(i)));
					}
				}

				else {
					teams = rotate(een, twee);
					een = splits1(teams);
					twee = splits2(teams);
					for (int i = 0; i < een.length(); i++) {
						temp.addWedstrijd(new Wedstrijd(twee.getTeam(i), een
								.getTeam(i)));
					}
				}
				speel.add(temp);
				speelronde++;
			

		}

		return speel;
	}

	public void add(Speelronde a) {
		speelrondes.add(a);
	}

	public int length() {
		return speelrondes.size();
	}

	public Speelronde get(int a) {
		return speelrondes.get(a);
	}

	/*
	 * Roteren van twee competities volgens het round robin tournament
	 */
	public static Competitie rotate(Competitie een, Competitie twee) {

		Competitie totaal = new Competitie();
		Competitie a = een;
		Competitie b = twee;
		totaal.voegTeamToe(a.getTeam(0));
		totaal.voegTeamToe(b.getTeam(0));
		for (int i = 2; i < a.length(); i++) {
			totaal.voegTeamToe(a.getTeam(i - 1));
		}

		for (int i = 1; i < a.length(); i++) {
			totaal.voegTeamToe(b.getTeam(i));
		}
		totaal.voegTeamToe(a.getTeam(a.length() - 1));
		return totaal;

	}

	/*
	 * Verandert de volgorde van de thuis en uit teams per speelronde, dit
	 * gebeurt afzonderlijk tussen de helft van het aantal speelrondes in het
	 * speelschema. Bij de ene helft worden de teams verwisseld om de twee
	 * speelrondes vanaf de eerste speelronde tot de helft. En bij de
	 * tweedehelft wordt het vanaf de tweede van de tweedehelft verwisseld tot
	 * het einde van het aantal speelrondes.
	 */
	public static Speelschema veranderthuisuit(Speelschema speelschema) {
		
		Team temp;
				
		for (int i = 0; i < speelschema.length() / 2; i = i + 2) {
			for (int j = 0; j < speelschema.get(i).length(); j++) {
				temp = speelschema.get(i).getWedstrijd(j).getTeamThuis();
				speelschema
						.get(i)
						.getWedstrijd(j)
						.setTeamThuis(
								speelschema.get(i).getWedstrijd(j).getTeamUit());
				speelschema.get(i).getWedstrijd(j).setTeamUit(temp);
			}

		}
		for (int i = speelschema.length() / 2; i < speelschema.length(); i = i + 2) {
			for (int j = 0; j < speelschema.get(i).length(); j++) {
				temp = speelschema.get(i).getWedstrijd(j).getTeamThuis();
				speelschema
						.get(i)
						.getWedstrijd(j)
						.setTeamThuis(
								speelschema.get(i).getWedstrijd(j).getTeamUit());
				speelschema.get(i).getWedstrijd(j).setTeamUit(temp);
			}
		}
		
		return speelschema;
	}

	/* Return de eerste helft van een competitie als competitie.*/
	public static Competitie splits1(Competitie teams) {
		Competitie een = new Competitie();
		for (int i = 0; i < teams.length() / 2; i++) {
			een.voegTeamToe(teams.getTeam(i));
		}
		return een;
	}

	/* Return de tweede helft van een competitie als competitie */
	public static Competitie splits2(Competitie teams) {
		Competitie twee = new Competitie();
		for (int i = teams.length() / 2; i < teams.length(); i++) {
			twee.voegTeamToe(teams.getTeam(i));
		}
		return twee;
	}
	

	public String toString() {
		String a = "Competitie: \n\n";
		for (int i = 0; i < this.length(); i++) {
			a = a + (speelrondes.get(i)).toString() + "\n\n";
		}

		return a;
	}

	public String toXML() {
		String a = "\r\n<huidigspel>";
		for (int i = 0; i < this.length(); i++) {
			a = a + "\r\n" + speelrondes.get(i).toXML();

		}
		a = a + "\r\n" + "</huidigspel>";

		return a;
	}

	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Speelschema) {
			res = true;
			Speelschema that = (Speelschema) obj;
			if (!(this.length() == that.length())) {
				return false;
			}
			for (int i = 0; i < this.length(); i++) {
				if (!this.get(i).equals(that.get(i))) {
					return false;
				}
			}
		}
		return res;
	}

}