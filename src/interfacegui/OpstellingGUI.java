package interfacegui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import mechanicsspel.*;

public class OpstellingGUI {
	
	private static boolean firstRun = true;
	
	protected static void addOpstelling442() {
		if (!firstRun) {
		GUI.opstellingPanel.remove(GUI.opstelling442Panel);
		}
		
		try {
			GUI.opstelling442Panel = new JPanel(new GridBagLayout()){
				private Image img = ImageIO.read(new File(
						"src//Afbeeldingen/veld.png"));
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
				
				
			};
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		GridBagConstraints c = new GridBagConstraints();
		
		GUI.opstelling442Panel.setBorder(new LineBorder(Color.BLACK));
		
		// Scheiding tussen combobox opstelling selecteren de opstelling zelf
		c.gridx = 0;
		c.gridy = 0;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Aanvallers
		c.gridx = 1;
		c.gridy = 1;
		final JComboBox aanvaller1 = spelerComboBox();
		GUI.opstelling442Panel.add(aanvaller1, c);
		
		c.gridx = 3;
		c.gridy = 1;
		final JComboBox aanvaller2 = spelerComboBox();
		GUI.opstelling442Panel.add(aanvaller2, c);
		
		// Scheiding tussen aanvallers en middenvelders
		c.gridx = 0;
		c.gridy = 2;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Middenvelders
		c.gridx = 0;
		c.gridy = 3;
		final JComboBox middenvelder1 = spelerComboBox();
		GUI.opstelling442Panel.add(middenvelder1);
		GUI.opstelling442Panel.add(middenvelder1, c);
		
		c.gridx = 1;
		c.gridy = 3;
		final JComboBox middenvelder2 = spelerComboBox();
		GUI.opstelling442Panel.add(middenvelder2);
		GUI.opstelling442Panel.add(middenvelder2, c);
		
		c.gridx = 3;
		c.gridy = 3;
		final JComboBox middenvelder3 = spelerComboBox();
		GUI.opstelling442Panel.add(middenvelder3);
		GUI.opstelling442Panel.add(middenvelder3, c);
		
		c.gridx = 4;
		c.gridy = 3;
		final JComboBox middenvelder4 = spelerComboBox();
		GUI.opstelling442Panel.add(middenvelder4);
		GUI.opstelling442Panel.add(middenvelder4, c);
		
		// Scheiding tussen middenvelders en verdedigers
		c.gridx = 0;
		c.gridy = 4;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Verdedigers
		c.gridx = 0;
		c.gridy = 5;
		final JComboBox verdediger1 = spelerComboBox();
		GUI.opstelling442Panel.add(verdediger1);
		GUI.opstelling442Panel.add(verdediger1, c);
		
		c.gridx = 1;
		c.gridy = 5;
		final JComboBox verdediger2 = spelerComboBox();
		GUI.opstelling442Panel.add(verdediger2);
		GUI.opstelling442Panel.add(verdediger2, c);
		
		c.gridx = 3;
		c.gridy = 5;
		final JComboBox verdediger3 = spelerComboBox();
		GUI.opstelling442Panel.add(verdediger3);
		GUI.opstelling442Panel.add(verdediger3, c);
		
		c.gridx = 4;
		c.gridy = 5;
		final JComboBox verdediger4 = spelerComboBox();
		GUI.opstelling442Panel.add(verdediger4);
		GUI.opstelling442Panel.add(verdediger4, c);
		
		// Scheiding tussen verdedigers en doelman
		c.gridx = 0;
		c.gridy = 6;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Doelman
		c.gridx = 2;
		c.gridy = 7;
		final JComboBox doelman1 = doelmanComboBox();
		GUI.opstelling442Panel.add(doelman1, c);
		
		// Scheiding tussen doelman en button
		c.gridx = 0;
		c.gridy = 8;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Selecteer opstelling button
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		JButton selecteerOpstellingButton = new JButton("Selecteer deze opstelling");
		selecteerOpstellingButton.setFont(new Font("Arial", Font.PLAIN, 30));
		GUI.opstelling442Panel.add(selecteerOpstellingButton, c);
		
		// Scheiding tussen button en errorLabel
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		//  errorLabel
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 3;
		final JLabel errorLabel = new JLabel(" ");
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		errorLabel.setForeground(Color.BLACK);
		errorLabel.setVisible(true);
		GUI.opstelling442Panel.add(errorLabel, c);
		
		// Ruimte onder errorLabel
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		GUI.opstelling442Panel.add(GUI.newEmptyLabel(1), c);
		
		// Als er een opstelling is, selecteer deze dan
		if (GUI.manager.getTeam().getOpstelling() != null) {
			Opstelling opstelling= GUI.manager.getTeam().getOpstelling();
			
			aanvaller1.setSelectedItem(opstelling.getAanvallers().get(0).getNaam());
			aanvaller2.setSelectedItem(opstelling.getAanvallers().get(1).getNaam());
			
			middenvelder1.setSelectedItem(opstelling.getMiddenvelders().get(0).getNaam());
			middenvelder2.setSelectedItem(opstelling.getMiddenvelders().get(1).getNaam());
			middenvelder3.setSelectedItem(opstelling.getMiddenvelders().get(2).getNaam());
			if (opstelling.getMiddenvelders().size() > 3) {
				middenvelder4.setSelectedItem(opstelling.getMiddenvelders().get(3).getNaam());
			}
			
			verdediger1.setSelectedItem(opstelling.getVerdedigers().get(0).getNaam());
			verdediger2.setSelectedItem(opstelling.getVerdedigers().get(1).getNaam());
			verdediger3.setSelectedItem(opstelling.getVerdedigers().get(2).getNaam());
			if (opstelling.getVerdedigers().size() > 3) {
				verdediger4.setSelectedItem(opstelling.getVerdedigers().get(3).getNaam());
			}
			
			doelman1.setSelectedItem(opstelling.getDoelman().get(0).getNaam());			
		}
		
		// Listeners
		selecteerOpstellingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				Opstelling o = new Opstelling();
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller1.getSelectedIndex()));
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller2.getSelectedIndex()));
				
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder1.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder2.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder3.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder4.getSelectedIndex()));
				
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger1.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger2.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger3.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger4.getSelectedIndex()));
				
				for (int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i ++) {	
					if(GUI.getManager().getTeam().getSpeler(i).getNaam().equals(doelman1.getSelectedItem())) {	
							o.addDoelman(GUI.getManager().getTeam().getSpeler(i));
					}
				}
	
				Set<Speler> set = new HashSet<Speler>();
				
				for (int i = 0; i < 4; i++) {
					if (i < 2) {
						set.add(o.getAanvallers().get(i));
					}
					set.add(o.getMiddenvelders().get(i));
					set.add(o.getVerdedigers().get(i));
				}
				
				// Controleer op geblesseerde spelers
				String geblesseerd = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 2) {
						if(o.getAanvallers().get(i).getStatus().equals("blessure-1")) {
							geblesseerd = o.getAanvallers().get(i).getNaam();
							break;
						}
					}
					
					if(o.getMiddenvelders().get(i).getStatus().equals("blessure-1")) {
						geblesseerd = o.getMiddenvelders().get(i).getNaam();
						break;
					}
					
					if(o.getVerdedigers().get(i).getStatus().equals("blessure-1")) {
						geblesseerd = o.getVerdedigers().get(i).getNaam();
						break;
					}
				}
				
				// Controleer op geschorste spelers
				String geschorst = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 2) {
						if(o.getAanvallers().get(i).getStatus().equals("rood-1") || o.getAanvallers().get(i).getStatus().equals("rood-2") || o.getAanvallers().get(i).getStatus().equals("rood-3")) {
							geschorst = o.getAanvallers().get(i).getNaam();
							break;
						}
					}
					
					if(o.getMiddenvelders().get(i).getStatus().equals("rood-1") || o.getMiddenvelders().get(i).getStatus().equals("rood-2") || o.getMiddenvelders().get(i).getStatus().equals("rood-3")) {
						geschorst = o.getMiddenvelders().get(i).getNaam();
						break;
					}
					
					if(o.getVerdedigers().get(i).getStatus().equals("rood-1") || o.getVerdedigers().get(i).getStatus().equals("rood-2") || o.getVerdedigers().get(i).getStatus().equals("rood-3")) {
						geschorst = o.getVerdedigers().get(i).getNaam();
						break;
					}					
				}
				
				if(o.getDoelman().get(0).getStatus().equals("rood-1") || o.getDoelman().get(0).getStatus().equals("rood-2") || o.getDoelman().get(0).getStatus().equals("rood-3")) {
					geschorst = o.getDoelman().get(0).getNaam();
				}
				
				// Check for duplicates
				if (set.size() == 10) {	
					if (geblesseerd.equals("Leeg")) {
						if (geschorst.equals("Leeg")) {
							GUI.getManager().getTeam().setOpstelling(o);
							errorLabel.setText("Deze opstelling is opgeslagen");
							errorLabel.setVisible(true);
							} else {
								errorLabel.setText(geschorst + " is geschorst en kan niet spelen");
								errorLabel.setVisible(true);
							}
					} else {
						errorLabel.setText(geblesseerd + " is geblesseerd en kan niet spelen");
						errorLabel.setVisible(true);
					}
				} else {
					errorLabel.setText("Een speler kan maar 1 keer opgesteld staan");
					errorLabel.setVisible(true);
				}
			}
		});
		
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;		
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 3;
		
		GUI.opstellingPanel.add(GUI.opstelling442Panel, g);
		
		GUI.opstelling442Panel.setVisible(true);
	}
	
	public static void addOpstelling433() {
		if (!firstRun) {
			GUI.opstellingPanel.remove(GUI.opstelling433Panel);
			}
			
		try {
			GUI.opstelling433Panel = new JPanel(new GridBagLayout()){
				private Image img = ImageIO.read(new File(
						"src//Afbeeldingen/veld.png"));
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
				
				
			};
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GridBagConstraints c = new GridBagConstraints();
			
		GUI.opstelling433Panel.setBorder(new LineBorder(Color.BLACK));
			
		// Scheiding tussen combobox opstelling selecteren de opstelling zelf
		c.gridx = 0;
		c.gridy = 0;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
				
		// Aanvallers
		c.gridx = 0;
		c.gridy = 1;
		final JComboBox aanvaller1 = spelerComboBox();
		GUI.opstelling433Panel.add(aanvaller1, c);
		
		c.gridx = 2;
		c.gridy = 1;
		final JComboBox aanvaller2 = spelerComboBox();
		GUI.opstelling433Panel.add(aanvaller2, c);
		
		c.gridx = 4;
		c.gridy = 1;
		final JComboBox aanvaller3 = spelerComboBox();
		GUI.opstelling433Panel.add(aanvaller3, c);
		
		// Scheiding tussen aanvallers en middenvelders
		c.gridx = 0;
		c.gridy = 2;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		// Middenvelders
		c.gridx = 0;
		c.gridy = 3;
		final JComboBox middenvelder1 = spelerComboBox();
		GUI.opstelling433Panel.add(middenvelder1, c);
		
		c.gridx = 2;
		c.gridy = 3;
		final JComboBox middenvelder2 = spelerComboBox();
		GUI.opstelling433Panel.add(middenvelder2, c);
		
		c.gridx = 4;
		c.gridy = 3;
		final JComboBox middenvelder3 = spelerComboBox();
		GUI.opstelling433Panel.add(middenvelder3, c);
		
		// Scheiding tussen middenvelders en verdedigers
		c.gridx = 0;
		c.gridy = 4;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		// Verdedigers
		c.gridx = 0;
		c.gridy = 5;
		final JComboBox verdediger1 = spelerComboBox();
		GUI.opstelling433Panel.add(verdediger1, c);
		
		c.gridx = 1;
		c.gridy = 5;
		final JComboBox verdediger2 = spelerComboBox();
		GUI.opstelling433Panel.add(verdediger2, c);
		
		c.gridx = 3;
		c.gridy = 5;
		final JComboBox verdediger3 = spelerComboBox();
		GUI.opstelling433Panel.add(verdediger3, c);
		
		c.gridx = 4;
		c.gridy = 5;
		final JComboBox verdediger4 = spelerComboBox();
		GUI.opstelling433Panel.add(verdediger4, c);
		
		// Scheiding tussen verdedigers en doelman
		c.gridx = 0;
		c.gridy = 6;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		// Doelman
		c.gridx = 2;
		c.gridy = 7;
		final JComboBox doelman1 = doelmanComboBox();
		GUI.opstelling433Panel.add(doelman1, c);

		// Scheiding tussen doelman en button
		c.gridx = 0;
		c.gridy = 8;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		// Selecteer opstelling button
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		JButton selecteerOpstellingButton = new JButton("Selecteer deze opstelling");
		selecteerOpstellingButton.setFont(new Font("Arial", Font.PLAIN, 30));
		GUI.opstelling433Panel.add(selecteerOpstellingButton, c);
		
		// Scheiding tussen button en errorLabel
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		//  errorLabel
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 3;
		final JLabel errorLabel = new JLabel(" ");
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		errorLabel.setForeground(Color.BLACK);
		errorLabel.setVisible(true);
		GUI.opstelling433Panel.add(errorLabel, c);
		
		// Ruimte onder errorLabel
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		GUI.opstelling433Panel.add(GUI.newEmptyLabel(1), c);
		
		// Geef opstelling weer in de comboboxes
		if (GUI.manager.getTeam().getOpstelling() != null) {
			Opstelling opstelling= GUI.manager.getTeam().getOpstelling();
			
			aanvaller1.setSelectedItem(opstelling.getAanvallers().get(0).getNaam());
			aanvaller2.setSelectedItem(opstelling.getAanvallers().get(1).getNaam());
			if (opstelling.getAanvallers().size() > 2) {
				aanvaller3.setSelectedItem(opstelling.getAanvallers().get(2).getNaam());
			}
			
			middenvelder1.setSelectedItem(opstelling.getMiddenvelders().get(0).getNaam());
			middenvelder2.setSelectedItem(opstelling.getMiddenvelders().get(1).getNaam());
			middenvelder3.setSelectedItem(opstelling.getMiddenvelders().get(2).getNaam());
			
			verdediger1.setSelectedItem(opstelling.getVerdedigers().get(0).getNaam());
			verdediger2.setSelectedItem(opstelling.getVerdedigers().get(1).getNaam());
			verdediger3.setSelectedItem(opstelling.getVerdedigers().get(2).getNaam());
			if (opstelling.getVerdedigers().size() > 3) {
				verdediger4.setSelectedItem(opstelling.getVerdedigers().get(3).getNaam());
			}
			
			doelman1.setSelectedItem(opstelling.getDoelman().get(0).getNaam());			
		}
		
		selecteerOpstellingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Opstelling o = new Opstelling();
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller1.getSelectedIndex()));
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller2.getSelectedIndex()));
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller3.getSelectedIndex()));
				
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder1.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder2.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder3.getSelectedIndex()));
				
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger1.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger2.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger3.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger4.getSelectedIndex()));
				
				for (int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i ++) {	
					if(GUI.getManager().getTeam().getSpeler(i).getNaam().equals(doelman1.getSelectedItem())) {	
							o.addDoelman(GUI.getManager().getTeam().getSpeler(i));
					}
				}
	
				Set<Speler> set = new HashSet<Speler>();
				
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						set.add(o.getAanvallers().get(i));
						set.add(o.getMiddenvelders().get(i));
					}
					set.add(o.getVerdedigers().get(i));
				}
				
				// Controleer op geblesseerde spelers
				String geblesseerd = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						if(o.getAanvallers().get(i).getStatus().equals("blessure-1")) {
							geblesseerd = o.getAanvallers().get(i).getNaam();
							break;
						}
						if(o.getMiddenvelders().get(i).getStatus().equals("blessure-1")) {
							geblesseerd = o.getMiddenvelders().get(i).getNaam();
							break;
						}
					}
					
					if(o.getVerdedigers().get(i).getStatus().equals("blessure-1")) {
						geblesseerd = o.getVerdedigers().get(i).getNaam();
						break;
					}
				}
				
				// Controleer op geschorste spelers
				String geschorst = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						if(o.getAanvallers().get(i).getStatus().equals("rood-1") || o.getAanvallers().get(i).getStatus().equals("rood-2") || o.getAanvallers().get(i).getStatus().equals("rood-3")) {
							geschorst = o.getAanvallers().get(i).getNaam();
							break;
						}
						if(o.getMiddenvelders().get(i).getStatus().equals("rood-1") || o.getMiddenvelders().get(i).getStatus().equals("rood-2") || o.getMiddenvelders().get(i).getStatus().equals("rood-3")) {
							geschorst = o.getMiddenvelders().get(i).getNaam();
							break;
						}
					}
										
					if(o.getVerdedigers().get(i).getStatus().equals("rood-1") || o.getVerdedigers().get(i).getStatus().equals("rood-2") || o.getVerdedigers().get(i).getStatus().equals("rood-3")) {
						geschorst = o.getVerdedigers().get(i).getNaam();
						break;
					}					
				}
				
				if(o.getDoelman().get(0).getStatus().equals("rood-1") || o.getDoelman().get(0).getStatus().equals("rood-2") || o.getDoelman().get(0).getStatus().equals("rood-3")) {
					geschorst = o.getDoelman().get(0).getNaam();
				}
				
				// Check for duplicates
				if (set.size() == 10) {	
					if (geblesseerd.equals("Leeg")) {
						if (geschorst.equals("Leeg")) {
							GUI.getManager().getTeam().setOpstelling(o);
							errorLabel.setText("Deze opstelling is opgeslagen");
							errorLabel.setVisible(true);
							} else {
								errorLabel.setText(geschorst + " is geschorst en kan niet spelen");
								errorLabel.setVisible(true);
							}
					} else {
						errorLabel.setText(geblesseerd + " is geblesseerd en kan niet spelen");
						errorLabel.setVisible(true);
					}
				} else {
					errorLabel.setText("Een speler kan maar 1 keer opgesteld staan");
					errorLabel.setVisible(true);
				}
			}
		});
		
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;		
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 3;
		
		GUI.opstellingPanel.add(GUI.opstelling433Panel, g);
		
		GUI.opstelling433Panel.setVisible(false);
		
	}
	
	public static void addOpstelling343() {
		if (!firstRun) {
			GUI.opstellingPanel.remove(GUI.opstelling343Panel);
			}
		
		try {
			GUI.opstelling343Panel = new JPanel(new GridBagLayout()){
				private Image img = ImageIO.read(new File(
						"src//Afbeeldingen/veld.png"));
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
				
				
			};
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		GridBagConstraints c = new GridBagConstraints();		
		GUI.opstelling343Panel.setBorder(new LineBorder(Color.BLACK));
		
		// Scheiding tussen combobox opstelling selecteren de opstelling zelf
		c.gridx = 0;
		c.gridy = 0;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
						
		// Aanvallers
		c.gridx = 0;
		c.gridy = 1;
		final JComboBox aanvaller1 = spelerComboBox();
		GUI.opstelling343Panel.add(aanvaller1, c);
		
		c.gridx = 2;
		c.gridy = 1;
		final JComboBox aanvaller2 = spelerComboBox();
		GUI.opstelling343Panel.add(aanvaller2, c);
		
		c.gridx = 4;
		c.gridy = 1;
		final JComboBox aanvaller3 = spelerComboBox();
		GUI.opstelling343Panel.add(aanvaller3, c);
		
		// Scheiding tussen aanvallers en middenvelders
		c.gridx = 0;
		c.gridy = 2;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);	
		
		// Middenvelders
		c.gridx = 0;
		c.gridy = 3;
		final JComboBox middenvelder1 = spelerComboBox();
		GUI.opstelling343Panel.add(middenvelder1, c);
		
		c.gridx = 1;
		c.gridy = 3;
		final JComboBox middenvelder2 = spelerComboBox();
		GUI.opstelling343Panel.add(middenvelder2, c);
		
		c.gridx = 3;
		c.gridy = 3;
		final JComboBox middenvelder3 = spelerComboBox();
		GUI.opstelling343Panel.add(middenvelder3, c);
		
		c.gridx = 4;
		c.gridy = 3;
		final JComboBox middenvelder4 = spelerComboBox();
		GUI.opstelling343Panel.add(middenvelder4, c);
		
		// Scheiding tussen middenvelders en verdedigers
		c.gridx = 0;
		c.gridy = 4;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
		
		// Verdedigers
		c.gridx = 0;
		c.gridy = 5;
		final JComboBox verdediger1 = spelerComboBox();
		GUI.opstelling343Panel.add(verdediger1, c);
		
		c.gridx = 2;
		c.gridy = 5;
		final JComboBox verdediger2 = spelerComboBox();
		GUI.opstelling343Panel.add(verdediger2, c);
		
		c.gridx = 4;
		c.gridy = 5;
		final JComboBox verdediger3 = spelerComboBox();
		GUI.opstelling343Panel.add(verdediger3, c);
		
		// Scheiding tussen verdedigers en doelman
		c.gridx = 0;
		c.gridy = 6;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
		
		// Doelman
		c.gridx = 2;
		c.gridy = 7;
		final JComboBox doelman1 = doelmanComboBox();
		GUI.opstelling343Panel.add(doelman1, c);
		
		// Scheiding tussen doelman en 'selecteer deze opstelling' button
		c.gridx = 0;
		c.gridy = 8;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
		
		// Selecteer opstelling button
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		JButton selecteerOpstellingButton = new JButton("Selecteer deze opstelling");
		selecteerOpstellingButton.setFont(new Font("Arial", Font.PLAIN, 30));
		GUI.opstelling343Panel.add(selecteerOpstellingButton, c);
		
		// Scheiding tussen button en errorLabel
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
		
		//  errorLabel
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 3;
		final JLabel errorLabel = new JLabel(" ");
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		errorLabel.setForeground(Color.BLACK);
		errorLabel.setVisible(true);
		GUI.opstelling343Panel.add(errorLabel, c);
		
		// Ruimte onder errorLabel
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		GUI.opstelling343Panel.add(GUI.newEmptyLabel(1), c);
		
		// Geef opstelling weer in de comboboxes
		if (GUI.manager.getTeam().getOpstelling() != null) {
			Opstelling opstelling= GUI.manager.getTeam().getOpstelling();
			
			aanvaller1.setSelectedItem(opstelling.getAanvallers().get(0).getNaam());
			aanvaller2.setSelectedItem(opstelling.getAanvallers().get(1).getNaam());
			if (opstelling.getAanvallers().size() > 2) {
				aanvaller3.setSelectedItem(opstelling.getAanvallers().get(2).getNaam());
			}
			
			middenvelder1.setSelectedItem(opstelling.getMiddenvelders().get(0).getNaam());
			middenvelder2.setSelectedItem(opstelling.getMiddenvelders().get(1).getNaam());
			middenvelder3.setSelectedItem(opstelling.getMiddenvelders().get(2).getNaam());
			if (opstelling.getMiddenvelders().size() > 3) {
				middenvelder4.setSelectedItem(opstelling.getMiddenvelders().get(3).getNaam());
			}
			
			verdediger1.setSelectedItem(opstelling.getVerdedigers().get(0).getNaam());
			verdediger2.setSelectedItem(opstelling.getVerdedigers().get(1).getNaam());
			verdediger3.setSelectedItem(opstelling.getVerdedigers().get(2).getNaam());
			
			doelman1.setSelectedItem(opstelling.getDoelman().get(0).getNaam());			
		}
		
		selecteerOpstellingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Opstelling o = new Opstelling();
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller1.getSelectedIndex()));
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller2.getSelectedIndex()));
				o.addAanvaller(GUI.getManager().getTeam().getSpeler(aanvaller3.getSelectedIndex()));
				
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder1.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder2.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder3.getSelectedIndex()));
				o.addMiddenvelder(GUI.getManager().getTeam().getSpeler(middenvelder4.getSelectedIndex()));
				
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger1.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger2.getSelectedIndex()));
				o.addVerdediger(GUI.getManager().getTeam().getSpeler(verdediger3.getSelectedIndex()));
				
				for (int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i ++) {	
					if(GUI.getManager().getTeam().getSpeler(i).getNaam().equals(doelman1.getSelectedItem())) {	
							o.addDoelman(GUI.getManager().getTeam().getSpeler(i));
					}
				}
	
				Set<Speler> set = new HashSet<Speler>();
				
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						set.add(o.getAanvallers().get(i));
						set.add(o.getVerdedigers().get(i));						
					}
					set.add(o.getMiddenvelders().get(i));
				}
				
				// Controleer op geblesseerde spelers
				String geblesseerd = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						if(o.getAanvallers().get(i).getStatus().equals("blessure-1")) {
							geblesseerd = o.getAanvallers().get(i).getNaam();
							break;
						}
						if(o.getVerdedigers().get(i).getStatus().equals("blessure-1")) {
							geblesseerd = o.getVerdedigers().get(i).getNaam();
							break;
						}
					}
					
					if(o.getMiddenvelders().get(i).getStatus().equals("blessure-1")) {
						geblesseerd = o.getMiddenvelders().get(i).getNaam();
						break;
					}									
				}
				
				// Controleer op geschorste spelers
				String geschorst = "Leeg";
				for (int i = 0; i < 4; i++) {
					if (i < 3) {
						if(o.getAanvallers().get(i).getStatus().equals("rood-1") || o.getAanvallers().get(i).getStatus().equals("rood-2") || o.getAanvallers().get(i).getStatus().equals("rood-3")) {
							geschorst = o.getAanvallers().get(i).getNaam();
							break;
						}
						if(o.getVerdedigers().get(i).getStatus().equals("rood-1") || o.getVerdedigers().get(i).getStatus().equals("rood-2") || o.getVerdedigers().get(i).getStatus().equals("rood-3")) {
							geschorst = o.getVerdedigers().get(i).getNaam();
							break;
						}							
					}
					
					if(o.getMiddenvelders().get(i).getStatus().equals("rood-1") || o.getMiddenvelders().get(i).getStatus().equals("rood-2") || o.getMiddenvelders().get(i).getStatus().equals("rood-3")) {
						geschorst = o.getMiddenvelders().get(i).getNaam();
						break;
					}				
				}
				
				if(o.getDoelman().get(0).getStatus().equals("rood-1") || o.getDoelman().get(0).getStatus().equals("rood-2") || o.getDoelman().get(0).getStatus().equals("rood-3")) {
					geschorst = o.getDoelman().get(0).getNaam();
				}
				
				// Check for duplicates
				if (set.size() == 10) {	
					if (geblesseerd.equals("Leeg")) {
						if (geschorst.equals("Leeg")) {
							GUI.getManager().getTeam().setOpstelling(o);
							errorLabel.setText("Deze opstelling is opgeslagen");
							errorLabel.setVisible(true);
							} else {
								errorLabel.setText(geschorst + " is geschorst en kan niet spelen");
								errorLabel.setVisible(true);
							}
					} else {
						errorLabel.setText(geblesseerd + " is geblesseerd en kan niet spelen");
						errorLabel.setVisible(true);
					}
				} else {
					errorLabel.setText("Een speler kan maar 1 keer opgesteld staan");
					errorLabel.setVisible(true);
				}
			}
		});		
		
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;		
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 3;
		
		GUI.opstellingPanel.add(GUI.opstelling343Panel, g);
		
		GUI.opstelling343Panel.setVisible(false);
	}
	
	public static JComboBox spelerComboBox() {		
		int doelmanCount = 0;
		for(int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i++) {
			if(GUI.getManager().getTeam().getSpeler(i) instanceof Doelman) {
				doelmanCount++;
			}
		}
		
		
		
		String playerList[] = new String[GUI.getManager().getTeam().getAantalSpelers() - doelmanCount];
		
		int playerCounter = 0;
		for(int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i++) {
			if(!(GUI.getManager().getTeam().getSpeler(i) instanceof Doelman)) {
			playerList[playerCounter] = GUI.getManager().getTeam().getSpeler(i).getNaam();	
			playerCounter++;
			}
		}			
		
		JComboBox comboBox = new JComboBox(playerList);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		return comboBox;
	}
	
	public static JComboBox doelmanComboBox() {
		int doelmanCount = 0;
		for(int i = 0; i < GUI.getManager().getTeam().getAantalSpelers(); i++) {
			if(GUI.getManager().getTeam().getSpeler(i) instanceof Doelman) {
				doelmanCount++;
			}
		}
		String doelmanList[] = new String[doelmanCount];
		
		int x = 0;
		for(int j = 0; j < GUI.getManager().getTeam().getAantalSpelers(); j++) {
			if(GUI.getManager().getTeam().getSpeler(j) instanceof Doelman) {	
			doelmanList[x] = GUI.getManager().getTeam().getSpeler(j).getNaam();	
			x++;
			}
		}			
		
		JComboBox comboBox = new JComboBox(doelmanList);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		return comboBox;
	}
	
	public static void setFirstRun(boolean b) {
		firstRun = b;
	}
}
