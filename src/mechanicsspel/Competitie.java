package mechanicsspel;

import interfacegui.GUI;

import java.util.ArrayList;

public class Competitie {

	private ArrayList<Team> clist;

	public Competitie() {
		clist = new ArrayList<Team>();
	}

	/**
	 * methode om een team toe te voegen aan een competitie
	 */
	public void voegTeamToe(Team tm) {
		clist.add(tm);
	}

	/**
	 * de lengte van een competitie wordt teruggeven
	 */
	public int length() {
		return clist.size();
	}

	/**
	 * haalt uit een competitie de team op de meegegeven index
	 */
	public Team getTeam(int x) {
		return clist.get(x);
	}

	/**
	 * aan de hand van de statistieken van een team, wordt voor beide teams de
	 * 'kans' op doelpunten berekent en deze wordt meegeven aan de methode die
	 * hiermee het aantal doelpunten van een team bepaald. de doelpunten worden
	 * in een array geplaatst en gereturned
	 */
	public static int[] uitslag(Team team1, Team team2) {
		int off1 = team1.offensiefTotaal();
		int def1 = team1.defensiefTotaal();
		double sta1 = team1.staminaTotaal();
		int toff1 = (int) (off1 * (sta1 / 100));
		int tdef1 = (int) (def1 * (sta1 / 100));

		int off2 = team2.offensiefTotaal();
		int def2 = team2.defensiefTotaal();
		double sta2 = team2.staminaTotaal();
		int toff2 = (int) (off2 * (sta2 / 100));
		int tdef2 = (int) (def2 * (sta2 / 100));

		int temp1 = tdef1 - toff2 - 100;
		int doelp2 = doelpunten(temp1);
		int temp2 = tdef2 - toff1 - 100;
		int doelp1 = doelpunten(temp2);

		int[] temp = { doelp1, doelp2 };

		return temp;
	}

	/**
	 * aan de hand van een bepaalde waarde wordt het aantal doelpunten random
	 * gekozen binnen bepaalde grenzen
	 */
	public static int doelpunten(int x) {
		int doel = 0;
		if (x >= 150) {
			doel = RNG.randomInt(0, 1);
		}
		if (x < 150 && x >= -50) {
			doel = RNG.randomInt(0, 2);
		}
		if (x < -50 && x >= -200) {
			doel = RNG.randomInt(1, 3);
		}
		if (x < -200 && x >= -500) {
			doel = RNG.randomInt(2, 5);
		}
		if (x < -500) {
			doel = RNG.randomInt(2, 7);
		}
		return doel;
	}

	/**
	 * aan de hand van twee teams en verschillende methodes wordt ervoor gezorgd
	 * dat bij een bepaalde uitslag de nodige statistieken worden aangepast
	 */
	public static void afterMatch(Team t1, Team t2) {
		int[] uitslag = uitslag(t1, t2);
		Uitslag currentUitslag = new Uitslag(t1, t2, uitslag);
		GUI.getSpeelschema().get(Mechanics.getSpeelRonde())
				.addUitslag(currentUitslag);

		int int1 = uitslag[0];
		int int2 = uitslag[1];
		t1.doelSaldoPlus(int1);
		t1.doelV(int1);
		t1.doelSaldoMin(int2);
		t2.doelSaldoPlus(int2);
		t2.doelSaldoMin(int1);
		t2.doelV(int2);

		if (int1 > int2) {
			t1.compPuntenUpdate(1);
			t2.compPuntenUpdate(-1);
			t1.updateStatistieken();
		}
		if (int1 < int2) {
			t1.compPuntenUpdate(-1);
			t2.compPuntenUpdate(1);
			t2.updateStatistieken();
		}
		if (int1 == int2) {
			t1.compPuntenUpdate(0);
			t2.compPuntenUpdate(0);
		}
		t1.statusNaSpeelronde();
		t1.voegKaartenToe();
		t1.voegBlessureToe();
		t2.statusNaSpeelronde();
		t2.voegKaartenToe();
		t2.voegBlessureToe();
	}

	/**
	 * XML representatie van een competitie
	 */
	public String toXML() {
		String res = "<teams>\r\n";
		for (int i = 0; i < clist.size(); i++) {
			res += clist.get(i).toXML() + "\r\n";
		}
		res += "</teams>";
		return res;
	}

	/**
	 * string representatie van een competitie
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i < clist.size(); i++) {
			res += clist.get(i).toString() + "\r";
		}
		return res;
	}

	/**
	 * geeft een team terug aan de hand van een teamnaam
	 */
	public Team getTeam(String a) {
		for (int i = 0; i < clist.size(); i++) {
			if (a.equals(clist.get(i).getTeamname())) {
				return clist.get(i);
			}
		}
		return null;
	}

	/**
	 * equals methode voor een competitie
	 */
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Competitie) {
			Competitie that = (Competitie) obj;
			res = true;
			if (!(this.length() == that.length())) {
				return false;
			}
			for (int i = 0; i < this.length(); i++) {
				if (!this.getTeam(i).equals(that.getTeam(i))) {
					return false;
				}
			}

		}
		return res;
	}

	/*
	 * sorteert de stand op punten, dan verliespunten, dan doelsaldo en
	 * uiteindelijk doelpunten voor.
	 */
	public Competitie sorteer() {
		Competitie comp = this;
		Team tempTeam;

		for (int a = 0; a < comp.length(); a++) {
			for (int i = 0; i < comp.length() - 1; i++) {
				if (comp.getTeam(i).getPunten() == comp.getTeam(i + 1)
						.getPunten()) {

					if (comp.getTeam(i).getVerlies() == comp.getTeam(i + 1)
							.getVerlies()) {

						if (comp.getTeam(i).getDoelsaldo() == comp.getTeam(
								i + 1).getDoelsaldo()) {

							if (comp.getTeam(i).getDoelv() == comp.getTeam(
									i + 1).getDoelv()) {
								

							} else if (comp.getTeam(i).getDoelv() > comp
									.getTeam(i + 1).getDoelv()) {

							} else {
								tempTeam = comp.getTeam(i);
								comp.setTeam(i, comp.getTeam(i + 1));
								comp.setTeam(i + 1, tempTeam);

							}
						}
						if (comp.getTeam(i).getDoelsaldo() == comp.getTeam(
								i + 1).getDoelsaldo()) {

						} else if (comp.getTeam(i).getDoelsaldo() > comp
								.getTeam(i + 1).getDoelsaldo()) {

						} else {
							tempTeam = comp.getTeam(i);
							comp.setTeam(i, comp.getTeam(i + 1));
							comp.setTeam(i + 1, tempTeam);
						}
					}

					else if (comp.getTeam(i).getVerlies() < comp.getTeam(i + 1)
							.getVerlies()) {

					} else {
						tempTeam = comp.getTeam(i);
						comp.setTeam(i, comp.getTeam(i + 1));
						comp.setTeam(i + 1, tempTeam);
					}

				}

				else if (comp.getTeam(i).getPunten() > comp.getTeam(i + 1)
						.getPunten()) {

				} else {
					tempTeam = comp.getTeam(i);
					comp.setTeam(i, comp.getTeam(i + 1));
					comp.setTeam(i + 1, tempTeam);
				}

			}
		}

		return comp;
	}

	public void setTeam(int i, Team team) {
		clist.set(i, team);
	}
}
