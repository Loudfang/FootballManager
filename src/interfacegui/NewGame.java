package interfacegui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import mechanicsspel.*;

public class NewGame {

	private static JFrame mainFrame;
	private static String teamSelected;

	protected static void createGUI() {
		mainFrame = new JFrame("Nieuw spel");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setUndecorated(true);

		try {
			addComponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
	}

	private static void addComponents() throws IOException {
		JPanel mainPanel = new JPanel(){
			private Image img = ImageIO.read(new File("src//Afbeeldingen/wallpaper.jpg"));
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);							
				g.drawImage(img, 0, 0, null);
			}
		};
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	
		JLabel selectTeam = new JLabel("Kies een team");
        selectTeam.setFont(new Font("Arial", Font.BOLD, 70));
        selectTeam.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectTeam.setBorder(new EmptyBorder(25,0,0,0));
		mainPanel.add(selectTeam);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setOpaque(false);		
		teamPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(40,40,40,40);
		
		// eerste lijn van teams		
		ImageButton team1 = new ImageButton("src//Afbeeldingen/feyenoord_logo.png");
		c.gridx = 0;
		c.gridy = 1;
		teamPanel.add(team1,c);
		
		ImageButton team2 = new ImageButton("src//Afbeeldingen/ajax_logo.png");
		c.gridx = 1;
		c.gridy = 1;
		teamPanel.add(team2,c);
		
		ImageButton team3 = new ImageButton("src//Afbeeldingen/psv_logo.png");
		c.gridx = 2;
		c.gridy = 1;
		teamPanel.add(team3,c);
		
		ImageButton team4 = new ImageButton("src//Afbeeldingen/pec_logo.png");
		c.gridx = 3;
		c.gridy = 1;
		teamPanel.add(team4,c);
		
		ImageButton team5 = new ImageButton("src//Afbeeldingen/az_logo.png");
		c.gridx = 4;
		c.gridy = 1;
		teamPanel.add(team5,c);
		
		ImageButton team6 = new ImageButton("src//Afbeeldingen/twente_logo.png");
		c.gridx = 5;
		c.gridy = 1;
		teamPanel.add(team6,c);
		
		// tweede lijn van teams	
		ImageButton team7 = new ImageButton("src//Afbeeldingen/cambuur_logo.png");
		c.gridx = 0;
		c.gridy = 2;
		teamPanel.add(team7,c);
		
		ImageButton team8 = new ImageButton("src//Afbeeldingen/heerenveen_logo.png");
		c.gridx = 1;
		c.gridy = 2;
		teamPanel.add(team8,c);
		
		ImageButton team9 = new ImageButton("src//Afbeeldingen/groningen_logo.png");
		c.gridx = 2;
		c.gridy = 2;
		teamPanel.add(team9,c);
		
		ImageButton team10 = new ImageButton("src//Afbeeldingen/willem_logo.png");
		c.gridx = 3;
		c.gridy = 2;
		teamPanel.add(team10,c);
		
		ImageButton team11 = new ImageButton("src//Afbeeldingen/utrecht_logo.png");
		c.gridx = 4;
		c.gridy = 2;
		teamPanel.add(team11,c);
		
		ImageButton team12 = new ImageButton("src//Afbeeldingen/vitesse_logo.png");
		c.gridx = 5;
		c.gridy = 2;
		teamPanel.add(team12,c);
		
		// derde lijn van teams	
		ImageButton team13 = new ImageButton("src//Afbeeldingen/excelsior_logo.png");
		c.gridx = 0;
		c.gridy = 3;
		teamPanel.add(team13,c);

		ImageButton team14 = new ImageButton("src//Afbeeldingen/ado_logo.png");
		c.gridx = 1;
		c.gridy = 3;
		teamPanel.add(team14,c);
		
		ImageButton team15 = new ImageButton("src//Afbeeldingen/heracles_logo.png");
		c.gridx = 2;
		c.gridy = 3;
		teamPanel.add(team15,c);
		
		ImageButton team16 = new ImageButton("src//Afbeeldingen/eagles_logo.png");
		c.gridx = 3;
		c.gridy = 3;
		teamPanel.add(team16,c);
		
		ImageButton team17 = new ImageButton("src//Afbeeldingen/nac_logo.png");
		c.gridx = 4;
		c.gridy = 3;
		teamPanel.add(team17,c);
		
		ImageButton team18 = new ImageButton("src//Afbeeldingen/dordrecht_logo.png");
		c.gridx = 5;
		c.gridy = 3;
		teamPanel.add(team18,c);
			
		// terug knop
		JButton terug = new JButton("Terug");
		c.gridx = 5;
		c.gridy = 4;
		teamPanel.add(terug,c);	
		
		mainPanel.add(teamPanel);
		mainFrame.add(mainPanel);	
		
		
		team1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Feyenoord";
				addGegevens();
			}
		});
		team2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Ajax";
				addGegevens();
			}
		});
		team3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "PSV";
				addGegevens();
			}
		});
		team4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "PEC Zwolle";
				addGegevens();
			}
		});
		team5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "AZ";
				addGegevens();
			}
		});
		team6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "FC Twente";
				addGegevens();
			}
		});
		team7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "SC Cambuur Leeuwarden";
				addGegevens();
			}
		});		
		team8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "SC Heerenveen";
				addGegevens();
			}
		});
		team9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "FC Groningen";
				addGegevens();
			}
		});
		team10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Willem II";
				addGegevens();
			}
		});
		team11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "FC Utrecht";
				addGegevens();
			}
		});
		team12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Vitesse";
				addGegevens();
			}
		});
		team13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Excelsior";
				addGegevens();
			}
		});
		team14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "ADO Den Haag";
				addGegevens();
			}
		});
		team15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Heracles Almelo";
				addGegevens();
			}
		});		
		team16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "Go Ahead Eagles";
				addGegevens();
			}
		});		
		team17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "NAC Breda";
				addGegevens();
			}
		});	
		team18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelected = "FC Dordrecht";
				addGegevens();
			}
		});
		
		terug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});	
	}
	
	private static void addGegevens(){	
		final JDialog focus = new JDialog();
		JPanel gegevens = new JPanel();
		gegevens.setLayout(new BoxLayout(gegevens, BoxLayout.Y_AXIS));
		
		final JLabel naamlabel = new JLabel("Vul uw naam in");
		naamlabel.setFont(new Font("Arial", Font.PLAIN, 30));
		naamlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gegevens.add(naamlabel);
		
		final JTextField nameTextField = new JTextField();
		nameTextField.setHorizontalAlignment(JTextField.CENTER);
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		gegevens.add(nameTextField);
			
		JButton verder = new JButton(new AbstractAction("Ga verder"){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameTextField.getText().equals("")) {
					naamlabel.setForeground(Color.red);
				} else {
					Team managerTeam = GUI.competitie.getTeam(teamSelected);
					GUI.manager = new Manager(managerTeam, 100000, nameTextField.getText());
				
					//Genereer opstelling voor de overige teams
					for(int i = 0; i < GUI.competitie.length(); i++) {
						if(!(GUI.competitie.getTeam(i).equals(GUI.manager.getTeam()))) {
							Opstelling.opstellingNPT(GUI.competitie.getTeam(i));
						}
					}
					
					//Genereer nummers voor de spelers
					for(int i = 0; i < GUI.competitie.length(); i++) {
						for(int j = 0; j < GUI.competitie.getTeam(i).getAantalSpelers(); j++) {
							GUI.competitie.getTeam(i).getSpeler(j).setNummer(j + 1);
						}
					}
					focus.dispose();	
					mainFrame.dispose();
					GUI.splashPanel.setVisible(false);
					GUI.addComponents(false);
					GUI.generatePlayerList();
					OpstellingGUI.addOpstelling442();
					GUI.hidePanels();
					GUI.opstellingPanel.setVisible(true);
				}	
			}		
		});
		verder.setAlignmentX(Component.CENTER_ALIGNMENT);
		gegevens.add(verder);
		
		focus.add(gegevens);
		focus.setModal(true);	
		focus.setSize(400,200);
		focus.setLocationRelativeTo(null);
		focus.setVisible(true);
	}
}
