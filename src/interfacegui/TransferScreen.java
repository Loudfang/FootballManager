package interfacegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.LineBorder;

import mechanicsspel.*;

public class TransferScreen {

	static int MYwallet = 2500000;
	private static boolean check = true;
	private static JComboBox teams;
	private static JComboBox otherPlayerComboBox;
	private static boolean firstRun = true;
	private static Team selectedTeam;
	private static JLabel offensief, defensief, stamina, prijs, offensief2,
			defensief2, stamina2, prijs2;

	protected static void addTransferM() {
		if (!check) {
			GUI.transferpanel.remove(GUI.addTransferM);
		}

		GUI.addTransferM = new JPanel(new GridBagLayout());
		GridBagConstraints t = new GridBagConstraints();

		GUI.addTransferM.setBorder(new LineBorder(Color.BLACK));

		t.gridx = 0;
		t.gridy = 0;
		GUI.addTransferM.add(GUI.newEmptyLabel(1), t);

		// Comboboxes
		t.gridx = -2;
		t.gridy = 1;
		final JComboBox yourplayer = spelerComboBox();
		GUI.addTransferM.add(yourplayer, t);

		String teamList[] = new String[(GUI.competitie.length() - 1)];

		int j = 0;
		for (int i = 0; i < GUI.competitie.length(); i++) {
			if (!(GUI.competitie.getTeam(i).equals(GUI.manager.getTeam()))) {
				teamList[j] = GUI.competitie.getTeam(i).getTeamname();
				j++;
			}
		}

		teams = new JComboBox(teamList);
		teams.setBackground(Color.WHITE);
		teams.setFont(new Font("Arial", Font.PLAIN, 20));
		t.gridx = 4;
		t.gridy = 0;
		GUI.addTransferM.add(teams, t);

		t.gridx = 2;
		t.gridy = 1;
		GUI.addTransferM.add(GUI.newEmptyLabel(1), t);

		// Labels met gegevens voor eigen spelers
		offensief = new JLabel("Offensief:");
		offensief.setFont(new Font("Arial", Font.PLAIN, 20));
		offensief.setVisible(false);
		t.gridx = -2;
		t.gridy = 3;
		GUI.addTransferM.add(offensief, t);

		defensief = new JLabel("Defensief:");
		defensief.setFont(new Font("Arial", Font.PLAIN, 20));
		defensief.setVisible(false);
		t.gridx = -2;
		t.gridy = 4;
		GUI.addTransferM.add(defensief, t);

		stamina = new JLabel("Stamina:");
		stamina.setFont(new Font("Arial", Font.PLAIN, 20));
		stamina.setVisible(false);
		t.gridx = -2;
		t.gridy = 5;
		GUI.addTransferM.add(stamina, t);

		prijs = new JLabel("Prijs:");
		prijs.setFont(new Font("Arial", Font.PLAIN, 20));
		prijs.setVisible(false);
		t.gridx = -2;
		t.gridy = 6;
		GUI.addTransferM.add(prijs, t);

		// Labels met gegevens voor andere spelers
		offensief2 = new JLabel("Offensief:");
		offensief2.setFont(new Font("Arial", Font.PLAIN, 20));
		offensief2.setVisible(false);
		t.gridx = 4;
		t.gridy = 3;
		GUI.addTransferM.add(offensief2, t);

		defensief2 = new JLabel("Defensief:");
		defensief2.setFont(new Font("Arial", Font.PLAIN, 20));
		defensief2.setVisible(false);
		t.gridx = 4;
		t.gridy = 4;
		GUI.addTransferM.add(defensief2, t);

		stamina2 = new JLabel("Stamina:");
		stamina2.setFont(new Font("Arial", Font.PLAIN, 20));
		stamina2.setVisible(false);
		t.gridx = 4;
		t.gridy = 5;
		GUI.addTransferM.add(stamina2, t);

		prijs2 = new JLabel("Prijs:");
		prijs2.setFont(new Font("Arial", Font.PLAIN, 20));
		prijs2.setVisible(false);
		t.gridx = 4;
		t.gridy = 6;
		GUI.addTransferM.add(prijs2, t);

		// space tussen doelman en button
		t.gridx = 0;
		t.gridy = 8;
		GUI.addTransferM.add(GUI.newEmptyLabel(1), t);

		t.gridx = -2;
		t.gridy = 9;
		t.gridwidth = 3;
		JButton Sell = new JButton("Verkoop speler");
		Sell.setFont(new Font("Arial", Font.PLAIN, 30));
		GUI.addTransferM.add(Sell, t);

		// The button will sell a player but it will not update on the drop down
		// menu
		t.gridx = 4;
		t.gridy = 9;
		t.gridwidth = 3;
		JButton Buy = new JButton("Koop speler");
		Buy.setFont(new Font("Arial", Font.PLAIN, 30));
		GUI.addTransferM.add(Buy, t);

		// space tussen button en errorLabel
		t.gridx = 0;
		t.gridy = 10;
		t.gridwidth = 1;
		GUI.addTransferM.add(GUI.newEmptyLabel(1), t);

		// wallet
		t.gridx = 25;
		t.gridy = 15;
		t.gridwidth = 3;
		final JLabel sign = new JLabel("Uw budget: €" + GUI.manager.getBudget());
		sign.setFont(new Font("Arial", Font.PLAIN, 25));
		sign.setForeground(Color.blue);
		GUI.addTransferM.add(sign, t);

		// errorLabel
		t.gridx = 0;
		t.gridy = 11;
		t.gridwidth = 5;
		final JLabel errorLabel = new JLabel("Error");
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);
		GUI.addTransferM.add(errorLabel, t);

		// Listeners

		Sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Speler speler = null;
				for (int i = 0; i < GUI.manager.getTeam().getAantalSpelers(); i++) {
					if (yourplayer.getSelectedItem().equals(GUI.manager.getTeam().getSpeler(i).getNaam())) {
						speler = GUI.manager.getTeam().getSpeler(i);
						break;
					}
				}

				// Controleren of er wel een speler geselecteerd is
				if (speler != null) {
					Opstelling opstelling = GUI.manager.getTeam().getOpstelling();
					// Controleren of speler staat opgesteld
						if ((opstelling != null) && (opstelling.getAanvallers().contains(speler) || opstelling.getMiddenvelders().contains(speler) || opstelling.getVerdedigers().contains(speler) || opstelling.getDoelman().contains(speler))) {
							errorLabel.setText("De selecteerde speler is nog opgesteld");
							errorLabel.setVisible(true);
						} else {
							// Controleren of er nog wel achttien spelers zijn
							if (GUI.manager.getTeam().getAantalSpelers() > 18) {
								if (speler instanceof Doelman) {
									// Controleren of er nog wel twee doelmannen zijn
									int doelmanCount = 0;
									for (int i = 0; i < GUI.manager.getTeam().getAantalSpelers(); i++) {
										if (GUI.manager.getTeam().getSpeler(i) instanceof Doelman) {
											doelmanCount++;
										}
									}
									
									if (doelmanCount > 2) {
										errorLabel.setText("Uw speler is verkocht aan " + verkoopSpeler(speler));
										errorLabel.setVisible(true);
										sign.setText("Uw budget: €" + GUI.manager.getBudget());
									} else {
										errorLabel.setText("Uw team moet minstens 2 doelmannen bevatten");
										errorLabel.setVisible(true);
									}
								} else {
									errorLabel.setText("Uw speler is verkocht aan " + verkoopSpeler(speler));
									errorLabel.setVisible(true);
									sign.setText("Uw budget: €" + GUI.manager.getBudget());
								}
							} else {
								errorLabel.setText("Uw team moet minstens 18 spelers bevatten");
								errorLabel.setVisible(true);
							}										
						}
				} else {
					errorLabel.setText("Er is geen speler geslecteerd!");
					errorLabel.setVisible(true);
				}
			}
		});

		Buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Speler speler = null;
				for (int i = 0; i < selectedTeam.getAantalSpelers(); i++) {
					if (otherPlayerComboBox.getSelectedItem().equals(selectedTeam.getSpeler(i).getNaam())) {
						speler = selectedTeam.getSpeler(i);
						break;
					}
				}

				// Controleren of er wel een speler geselecteerd is
				if (speler != null) {
					// Controleren of er genoeg budget is
					if (speler.getPrijs() <= GUI.manager.getBudget()) {
					
						Opstelling opstelling = selectedTeam.getOpstelling();				
						// Controleren of speler staat opgesteld zoja, dan vervangen in de opstelling
							if (opstelling.getAanvallers().contains(speler)) {
								System.out.println(selectedTeam.getOpstelling().getAanvallers());
								int i = opstelling.getAanvallers().indexOf(speler);
								opstelling.getAanvallers().remove(speler);
								opstelling.getAanvallers().listIterator(i);
								
								for(int j = 0; j < selectedTeam.getAantalSpelers(); j++) {
									if (!(opstelling.getAanvallers().contains(selectedTeam.getSpeler(j)) || opstelling.getMiddenvelders().contains(selectedTeam.getSpeler(j)) || opstelling.getVerdedigers().contains(selectedTeam.getSpeler(j)) || opstelling.getDoelman().contains(selectedTeam.getSpeler(j)) || selectedTeam.getSpeler(j).equals(speler))) {
										opstelling.getAanvallers().add(selectedTeam.getSpeler(j));
										break;
									}
								}
								
								System.out.println(selectedTeam.getOpstelling().getAanvallers());
								
							} else if (opstelling.getMiddenvelders().contains(speler)){
								int i = opstelling.getMiddenvelders().indexOf(speler);
								opstelling.getMiddenvelders().remove(speler);
								opstelling.getMiddenvelders().listIterator(i);
								
								for(int j = 0; j < selectedTeam.getAantalSpelers(); j++) {
									if (!(opstelling.getAanvallers().contains(selectedTeam.getSpeler(j)) || opstelling.getMiddenvelders().contains(selectedTeam.getSpeler(j)) || opstelling.getVerdedigers().contains(selectedTeam.getSpeler(j)) || opstelling.getDoelman().contains(selectedTeam.getSpeler(j)) || selectedTeam.getSpeler(j).equals(speler))) {
										opstelling.getMiddenvelders().add(selectedTeam.getSpeler(j));
										break;
									}
								}
								
							} else if (opstelling.getVerdedigers().contains(speler)){
								int i = opstelling.getVerdedigers().indexOf(speler);
								opstelling.getVerdedigers().remove(speler);
								opstelling.getVerdedigers().listIterator(i);
								
								for(int j = 0; j < selectedTeam.getAantalSpelers(); j++) {
									if (!(opstelling.getAanvallers().contains(selectedTeam.getSpeler(j)) || opstelling.getMiddenvelders().contains(selectedTeam.getSpeler(j)) || opstelling.getVerdedigers().contains(selectedTeam.getSpeler(j)) || opstelling.getDoelman().contains(selectedTeam.getSpeler(j)) || selectedTeam.getSpeler(j).equals(speler))) {
										opstelling.getVerdedigers().add(selectedTeam.getSpeler(j));
										break;
									}
								}
								
							} else if (opstelling.getDoelman().contains(speler)){
								int i = opstelling.getDoelman().indexOf(speler);
								opstelling.getDoelman().remove(speler);
								opstelling.getDoelman().listIterator(i);
								
								for(int j = 0; j < selectedTeam.getAantalSpelers(); j++) {
									if (!(opstelling.getAanvallers().contains(selectedTeam.getSpeler(j)) || opstelling.getMiddenvelders().contains(selectedTeam.getSpeler(j)) || opstelling.getVerdedigers().contains(selectedTeam.getSpeler(j)) || opstelling.getDoelman().contains(selectedTeam.getSpeler(j)) || selectedTeam.getSpeler(j).equals(speler))) {
										opstelling.getDoelman().add(selectedTeam.getSpeler(j));
										break;
									}
								}
								
							} else {							
								// Controleren of er nog wel achttien spelers zijn
								if (selectedTeam.getAantalSpelers() > 18) {
									if (speler instanceof Doelman) {
										// Controleren of er nog wel twee doelmannen zijn
										int doelmanCount = 0;
										for (int i = 0; i < GUI.manager.getTeam().getAantalSpelers(); i++) {
										if (GUI.manager.getTeam().getSpeler(i) instanceof Doelman) {
											doelmanCount++;
										}
									}
									
									if (doelmanCount > 2) {
										koopSpeler(speler, selectedTeam);
										errorLabel.setVisible(false);
										sign.setText("Uw budget: €" + GUI.manager.getBudget());
									} else {
										errorLabel.setText("Het team moet minstens 2 doelmannen blijven bevatten");
										errorLabel.setVisible(true);
									}
								} else {
									koopSpeler(speler, selectedTeam);
									errorLabel.setVisible(false);
									sign.setText("Uw budget: €" + GUI.manager.getBudget());
								}
							} else {
								errorLabel.setText("Het team moet minstens 18 spelers blijven bevatten");
								errorLabel.setVisible(true);
							}										
						}
					} else {
						errorLabel.setText("Er is geen budget beschikbaar");
						errorLabel.setVisible(true);
					}
				} else {
					errorLabel.setText("Er is geen speler geslecteerd!");
					errorLabel.setVisible(true);
				}
			}
		});

		yourplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < GUI.manager.getTeam().getAantalSpelers(); i++) {
					if (yourplayer.getSelectedItem().equals(
						GUI.manager.getTeam().getSpeler(i).getNaam())) {
						offensief.setText("Offensief: "+ GUI.manager.getTeam().getSpeler(i).getOffensief());
						offensief.setVisible(true);
						defensief.setText("Defensief: "+ GUI.manager.getTeam().getSpeler(i).getDefensief());
						defensief.setVisible(true);
						stamina.setText("Stamina: "+ GUI.manager.getTeam().getSpeler(i).getStamina());
						stamina.setVisible(true);
						prijs.setText("Prijs: "+ GUI.manager.getTeam().getSpeler(i).getPrijs());
						prijs.setVisible(true);
						break;
					}
				}
			}
		});

		teams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otherPlayerComboBox();
			}
		});

		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 3;

		GUI.transferpanel.add(GUI.addTransferM, g);

		GUI.addTransferM.setVisible(true);
	}

	public static JComboBox spelerComboBox() {
		String playerList[] = new String[GUI.getManager().getTeam()
				.getAantalSpelers()];

		for (int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i++) {
			playerList[i] = GUI.getManager().getTeam().getSpeler(i).getNaam();
		}

		JComboBox comboBox = new JComboBox(playerList);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		return comboBox;
	}

	public static void otherPlayerComboBox() {
		if (!firstRun) {
			GUI.addTransferM.remove(otherPlayerComboBox);
		}

		selectedTeam = null;

		for (int i = 0; i < GUI.competitie.length(); i++) {
			if (GUI.competitie.getTeam(i).getTeamname()
					.equals(teams.getSelectedItem())) {
				selectedTeam = GUI.competitie.getTeam(i);
			}
		}

		String OtherplayerList[] = new String[selectedTeam.getAantalSpelers()];

		for (int i = 0; i < selectedTeam.getAantalSpelers(); i++) {
			OtherplayerList[i] = selectedTeam.getSpeler(i).getNaam();
		}

		otherPlayerComboBox = new JComboBox(OtherplayerList);
		otherPlayerComboBox.setBackground(Color.WHITE);
		otherPlayerComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		otherPlayerComboBox.setVisible(true);

		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 4;
		g.gridy = 1;
		GUI.addTransferM.add(otherPlayerComboBox, g);
		GUI.addTransferM.validate();

		// listener
		otherPlayerComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < selectedTeam.getAantalSpelers(); i++) {
					if (otherPlayerComboBox.getSelectedItem().equals(
							selectedTeam.getSpeler(i).getNaam())) {
						offensief2.setText("Offensief: "
								+ selectedTeam.getSpeler(i).getOffensief());
						offensief2.setVisible(true);
						defensief2.setText("Defensief: "
								+ selectedTeam.getSpeler(i).getDefensief());
						defensief2.setVisible(true);
						stamina2.setText("Stamina: "
								+ selectedTeam.getSpeler(i).getStamina());
						stamina2.setVisible(true);
						prijs2.setText("Prijs: "
								+ selectedTeam.getSpeler(i).getPrijs());
						prijs2.setVisible(true);
						break;
					}
				}
			}
		});

		firstRun = false;
	}

	public static void SetCheck(boolean c) {
		check = c;
	}
	
	public static String verkoopSpeler(Speler speler) {
		int oldBudget = GUI.getManager().getBudget();
		int newBudget = oldBudget + speler.getPrijs();
		
		GUI.getManager().setBudget(newBudget);
		GUI.getManager().getTeam().verwijderSpeler(speler);
		
		// Speler aan nieuw team toevoegen		
		int randomInt = RNG.randomInt(0, (GUI.getCompetitie().length() - 1));
		
		if (!(GUI.getCompetitie().getTeam(randomInt).equals(GUI.getManager().getTeam()))) {
			GUI.getCompetitie().getTeam(randomInt).voegSpelerToe(speler);
		} else {
			if (randomInt == 0) {
				randomInt++;
			} else if (randomInt == (GUI.getCompetitie().length() - 1)) {
				randomInt--;
			}
			GUI.getCompetitie().getTeam(randomInt).voegSpelerToe(speler);
		}
		return(GUI.getCompetitie().getTeam(randomInt).getTeamname());
		}
		
	
	public static void koopSpeler(Speler speler, Team oldTeam) {
		int oldBudget = GUI.getManager().getBudget();
		int newBudget = oldBudget - speler.getPrijs();
		
		GUI.getManager().setBudget(newBudget);
		oldTeam.verwijderSpeler(speler);
		GUI.getManager().getTeam().voegSpelerToe(speler);
	}

}
