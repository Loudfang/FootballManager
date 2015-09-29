package interfacegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import mechanicsspel.*;

public class GUI extends JFrame {

	private static JFrame mainFrame;
	private static JPanel komendewedstrijdenPanel, panel,
			gespeeldCompetitiePanel, transferPanel, speelPanel, subMenuPanel,
			standPanel, playerListPanel, optionsPanel;
	private static JTable uitslagenTable, standTable, komendewedstrijdenTable;
	private static JLabel tegenstanderLabel, picEigen, picTegen;
	protected static JComboBox opstellingenList, transferList;
	protected static JPanel splashPanel, opstellingPanel, opstelling442Panel,
			opstelling433Panel, opstelling343Panel, addTransferM,
			transferpanel, eindPanel;
	protected static Competitie competitie;
	protected static Manager manager;
	protected static Speelschema speelschema;
	protected static JLabel eindLabel;
	private static DefaultTableModel KWlistTableModel, standlistTableModel;

	public static void main(String args[]) {
		createGUI();
	}

	public static Manager getManager() {
		return manager;
	}

	public static void setManager(Manager manager) {
		GUI.manager = manager;
	}

	public static Competitie getCompetitie() {
		return competitie;
	}

	public static Speelschema getSpeelschema() {
		return speelschema;
	}

	public static void setSpeelschema(Speelschema speelschema) {
		GUI.speelschema = speelschema;
	}

	public static void createGUI() {
		mainFrame = new JFrame("Eredivisie Manager");

		try {
			splashScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainFrame.setSize(800, 400);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setUndecorated(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	private static void splashScreen() throws IOException {
		splashPanel = new JPanel() {
			private Image img = ImageIO.read(new File(
					"src//Afbeeldingen/wallpaper.jpg"));

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		};

		splashPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		try {
			BufferedImage logo = ImageIO.read(new File(
					"src//Afbeeldingen/main_logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logo));
			c.gridx = 0;
			c.gridy = 0;
			splashPanel.add(logoLabel, c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.insets = new Insets(50, 100, 0, 100);

		JButton newGame = new JButton("Nieuw Spel");
		newGame.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 1;
		splashPanel.add(newGame, c);

		JButton loadGame = new JButton("Spel Laden");
		loadGame.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 2;
		splashPanel.add(loadGame, c);

		JButton exitGame = new JButton("Afsluiten");
		exitGame.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 3;
		splashPanel.add(exitGame, c);

		mainFrame.add(splashPanel);

		// Listeners
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					competitie = ReaderWriter.createCompetitie(ReaderWriter
							.read("src//FINALXML.xml"));
					speelschema = Speelschema.SpeelschemaGenereer(competitie);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				NewGame.createGUI();
			}
		});
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadGame.createGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	protected static void addComponents(boolean bool) {
		try {
			panel = new JPanel() {
				private Image img = ImageIO.read(new File(
						"src//Afbeeldingen/wallpaper.jpg"));

				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
			};
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		panel.setOpaque(false);

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 50;
		c.weightx = 0.5;

		JButton opstelling = new JButton("Opstelling");
		opstelling.setFont(new Font("Arial", Font.PLAIN, 40));
		opstelling.setToolTipText("Verander hier je opstelling");
		opstelling.setPreferredSize(new Dimension(350, 40));
		c.gridx = 0;
		c.gridy = 0;
		panel.add(opstelling, c);

		JButton transfermarkt = new JButton("Transfermarkt");
		transfermarkt.setFont(new Font("Arial", Font.PLAIN, 40));
		transfermarkt.setPreferredSize(new Dimension(300, 40));
		c.gridx = 1;
		c.gridy = 0;
		panel.add(transfermarkt, c);

		JButton speel = new JButton("Speel");
		speel.setBackground(Color.RED);
		speel.setFont(new Font("Arial", Font.PLAIN, 40));
		speel.setPreferredSize(new Dimension(300, 40));
		c.gridx = 2;
		c.gridy = 0;
		panel.add(speel, c);

		JButton competitie = new JButton("Competitie");
		competitie.setFont(new Font("Arial", Font.PLAIN, 40));
		competitie.setPreferredSize(new Dimension(350, 40));
		c.gridx = 3;
		c.gridy = 0;
		panel.add(competitie, c);

		// Toevoegen van submenu competitie
		subMenuPanel = new JPanel();
		subMenuPanel.add(addSubMenu(), BorderLayout.CENTER);
		c.gridx = 3;
		c.gridy = 1;
		panel.add(subMenuPanel, c);
		subMenuPanel.setOpaque(false);
		subMenuPanel.setVisible(false);

		JButton opties = new JButton("Opties");
		opties.setFont(new Font("Arial", Font.PLAIN, 40));
		opties.setPreferredSize(new Dimension(300, 40));
		c.gridx = 4;
		c.gridy = 0;
		panel.add(opties, c);

		// Toevoegen van opties submenu
		optionsPanel = new JPanel();
		optionsPanel.add(addOptionsMenu(), BorderLayout.CENTER);
		c.gridx = 4;
		c.gridy = 1;
		panel.add(optionsPanel, c);
		optionsPanel.setOpaque(false);
		optionsPanel.setVisible(false);

		// Toevoegen van opstelling tabblad
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.weighty = 1.0;
	//	c.ipady = 200;
		c.ipadx = 200;
		panel.add(addOpstelling(), c);

		// Toevoegen van transfermarkt tabblad
		panel.add(addTransferMarkt(), c);

		// Toevoegen van speel tabblad
		panel.add(addSpeel(), c);
		
		// Toevoegen van eindpanel
		panel.add(addEindPanel(), c);

		// Toevoegen van stand tabblad
		panel.add(competitieStand(bool), c);

		// Toevoegen van komende wedstrijden tabblad
		panel.add(komendeWedstrijden(manager, speelschema, bool), c);

		// Toevoegen van competitie tabblad
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(addGespeeldCompetitie(bool), c);

		// Listeners
		opstelling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePlayerList();
				OpstellingGUI.addOpstelling442();
				hidePanels();
				opstellingPanel.setVisible(true);
			}
		});

		speel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				speelPanel.setVisible(true);
			}
		});

		competitie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subMenuPanel.isVisible()) {
					subMenuPanel.setVisible(false);
				} else {
					subMenuPanel.setVisible(true);
				}
			}
		});

		opties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (optionsPanel.isVisible()) {
					optionsPanel.setVisible(false);
				} else {
					optionsPanel.setVisible(true);
				}
			}
		});

		transfermarkt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePlayerList();
				TransferScreen.addTransferM();
				hidePanels();
				transferpanel.setVisible(true);
			}
		});

		mainFrame.add(panel);
	}

	private static JPanel addOpstelling() {
		opstellingPanel = new JPanel();
		opstellingPanel.setOpaque(false);
		opstellingPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		opstellingPanel.add(newEmptyLabel(8), c);

		JLabel opstellingLabel = new JLabel("Huidige opstelling:");
		opstellingLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 1;
		c.gridy = 0;
		opstellingPanel.add(opstellingLabel, c);

		c.gridx = 2;
		c.gridy = 0;
		opstellingPanel.add(newEmptyLabel(8), c);

		String[] opstellingen = { "4-4-2", "4-3-3", "3-4-3" };

		// Selecteer opstelling hier
		opstellingenList = new JComboBox(opstellingen);
		opstellingenList.setBackground(Color.WHITE);
		opstellingenList.setFont(new Font("Arial", Font.PLAIN, 36));
		c.gridx = 1;
		c.gridy = 1;
		opstellingPanel.add(opstellingenList, c);

		// Voeg opstellingen toe
		OpstellingGUI.addOpstelling442();
		OpstellingGUI.addOpstelling433();
		OpstellingGUI.addOpstelling343();

		OpstellingGUI.setFirstRun(false);

		// Scheiding tussen opstellingen en player list
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 1;
		opstellingPanel.add(newEmptyLabel(1), c);		

		// Playerlist toevoegen
		playerListPanel = new JPanel();
		c.gridx = 6;
		c.gridy = 0;
		opstellingPanel.add(playerListPanel, c);

		// Listener voor JCombobox
		opstellingenList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (opstellingenList.getSelectedIndex()) {
				case 0:
					opstelling433Panel.setVisible(false);
					opstelling343Panel.setVisible(false);

					OpstellingGUI.addOpstelling442();
					opstelling442Panel.setVisible(true);
					break;
				case 1:
					opstelling343Panel.setVisible(false);
					opstelling442Panel.setVisible(false);
					OpstellingGUI.addOpstelling433();
					opstelling433Panel.setVisible(true);
					break;
				case 2:
					opstelling442Panel.setVisible(false);
					opstelling433Panel.setVisible(false);
					OpstellingGUI.addOpstelling343();
					opstelling343Panel.setVisible(true);
					break;
				}
			}
		});

		opstellingPanel.setVisible(false);
		return opstellingPanel;
	}

	private static JPanel addSpeel() {
		speelPanel = new JPanel();
		speelPanel.setOpaque(false);
		speelPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.insets = new Insets(20, 50, 20, 50);

		try {
			BufferedImage imgEigen = ImageIO.read(new File(getLogo(manager
					.getTeam().getTeamname())));
			picEigen = new JLabel(new ImageIcon(imgEigen));
			speelPanel.add(picEigen, c);

			BufferedImage imgTegen = ImageIO.read(new File(getLogo(manager
					.getTegenstander().getTeamname())));
			picTegen = new JLabel(new ImageIcon(imgTegen));
			c.gridx = 3;
			speelPanel.add(picTegen, c);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		tegenstanderLabel = new JLabel("TEGEN", SwingConstants.CENTER);
		tegenstanderLabel.setFont(new Font("Arial", Font.BOLD, 35));
		tegenstanderLabel.setForeground(Color.white);
		c.gridx = 1;
		c.gridwidth = 2;
		speelPanel.add(tegenstanderLabel, c);

		JButton speelButton = new JButton("Speel!");
		speelButton.setFont(new Font("Arial", Font.PLAIN, 25));
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		speelPanel.add(speelButton, c);

		final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		errorLabel.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		errorLabel.setVisible(false);
		speelPanel.add(errorLabel, c);

		// Listener voor speel button
		speelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getTeam().getOpstelling() != null
						|| manager.getNaam().equals("admin")) {

					// Controleer op geblesseerde of geschorste spelers in
					// opstelling
					Opstelling o = manager.getTeam().getOpstelling();
					String geblesseerd = "Leeg";
					for (int i = 0; i < 4; i++) {
						if (i < o.getAanvallers().size()) {
							if (o.getAanvallers().get(i).getStatus()
									.equals("blessure-1")) {
								geblesseerd = o.getAanvallers().get(i)
										.getNaam();
								break;
							}
						}

						if (i < o.getVerdedigers().size()) {
							if (o.getVerdedigers().get(i).getStatus()
									.equals("blessure-1")) {
								geblesseerd = o.getVerdedigers().get(i)
										.getNaam();
								break;
							}
						}

						if (i < o.getMiddenvelders().size()) {
							if (o.getMiddenvelders().get(i).getStatus()
									.equals("blessure-1")) {
								geblesseerd = o.getMiddenvelders().get(i)
										.getNaam();
								break;
							}
						}
					}

					// Controleer op geschorste spelers
					String geschorst = "Leeg";
					for (int i = 0; i < 4; i++) {
						if (i < o.getAanvallers().size()) {
							if (o.getAanvallers().get(i).getStatus()
									.equals("rood-1")
									|| o.getAanvallers().get(i).getStatus()
											.equals("rood-2")
									|| o.getAanvallers().get(i).getStatus()
											.equals("rood-3")) {
								geschorst = o.getAanvallers().get(i).getNaam();
								break;
							}
						}

						if (i < o.getMiddenvelders().size()) {
							if (o.getMiddenvelders().get(i).getStatus()
									.equals("rood-1")
									|| o.getMiddenvelders().get(i).getStatus()
											.equals("rood-2")
									|| o.getMiddenvelders().get(i).getStatus()
											.equals("rood-3")) {
								geschorst = o.getMiddenvelders().get(i)
										.getNaam();
								break;
							}
						}

						if (i < o.getVerdedigers().size()) {
							if (o.getVerdedigers().get(i).getStatus()
									.equals("rood-1")
									|| o.getVerdedigers().get(i).getStatus()
											.equals("rood-2")
									|| o.getVerdedigers().get(i).getStatus()
											.equals("rood-3")) {
								geschorst = o.getVerdedigers().get(i).getNaam();
								break;
							}
						}
					}

					if (o.getDoelman().get(0).getStatus().equals("rood-1")
							|| o.getDoelman().get(0).getStatus()
									.equals("rood-2")
							|| o.getDoelman().get(0).getStatus()
									.equals("rood-3")) {
						geschorst = o.getDoelman().get(0).getNaam();
					}

					if (geblesseerd.equals("Leeg") && geschorst.equals("Leeg")) {

						errorLabel.setVisible(false);
							KWlistTableModel.removeRow(0);

							Mechanics.bepaalUitslagenSpeelronde(speelschema);
							Speelronde tempSpeelronde = speelschema
									.get((Mechanics.getSpeelRonde() - 1));
							for (int i = 0; i < tempSpeelronde.length(); i++) {
								Uitslag tempUitslag = tempSpeelronde
										.getUitslag(i);
								uitslagenTable.setValueAt(tempUitslag
										.getTeamThuis().getTeamname(), i, 0);
								uitslagenTable.setValueAt(tempUitslag
										.getTeamUit().getTeamname(), i, 1);
								uitslagenTable.setValueAt(
										tempUitslag.getUitslag()[0] + " - "
												+ tempUitslag.getUitslag()[1],
										i, 2);
								hidePanels();
								gespeeldCompetitiePanel.setVisible(true);

							}
							Competitie competitie1 = competitie.sorteer();
							for (int i = 0; i < competitie1.length(); i++) {
								standTable.setValueAt(competitie1.getTeam(i)
										.getTeamname(), i + 1, 1);
								standTable.setValueAt(competitie1.getTeam(i)
										.getWinst(), i + 1, 2);
								standTable.setValueAt(competitie1.getTeam(i)
										.getGelijk(), i + 1, 3);
								standTable.setValueAt(competitie1.getTeam(i)
										.getVerlies(), i + 1, 4);
								standTable.setValueAt(competitie1.getTeam(i)
										.getDoelv(), i + 1, 5);
								standTable.setValueAt(competitie1.getTeam(i)
										.getDoelsaldo()
										- competitie1.getTeam(i).getDoelv(),
										i + 1, 6);
								standTable.setValueAt(competitie1.getTeam(i)
										.getDoelsaldo(), i + 1, 7);
								standTable.setValueAt(competitie1.getTeam(i)
										.getPunten(), i + 1, 8);
							}

							if (Mechanics.getSpeelRonde() < speelschema
									.length()) {
								try {
									picTegen.setIcon(new ImageIcon(ImageIO
											.read(new File(getLogo(manager
													.getTegenstander()
													.getTeamname())))));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else {
								picEigen.removeAll();
								picTegen.removeAll();
								// eindplaats in de stand vaststellen
								int plaats = 0;
								for (int i = 0; i < competitie.length(); i++) {
									if (competitie.getTeam(i).equals(manager.getTeam())) {
										plaats = i + 1;
										break;
									}
								}
								
								// Genereer eind tekst
								String text = new String("U bent " + plaats + "e geworden met " + manager.getTeam().getTeamname());
								eindLabel.setText(text);
								
								hidePanels();
								eindPanel.setVisible(true);
							}

						
					} else {
						if (!geblesseerd.equals("Leeg")
								&& !geschorst.equals("Leeg")) {
							errorLabel
									.setText("Er staan geblesseerde en geschorste spelers opgesteld");
						} else if (!geblesseerd.equals("Leeg")) {
							errorLabel
									.setText("Er staan geblesseerde spelers opgesteld");
						} else if (!geschorst.equals("Leeg")) {
							errorLabel
									.setText("Er staan geschorste spelers opgesteld");
						}
						errorLabel.setVisible(true);
					}
				} else {
					errorLabel
							.setText("Selecteer alstublieft eerst een opstelling!");
					errorLabel.setVisible(true);
				}
			}
		});

		speelPanel.setVisible(false);
		return speelPanel;
	}

	private static JPanel addGespeeldCompetitie(boolean bool) {
		gespeeldCompetitiePanel = new JPanel();
		gespeeldCompetitiePanel.setOpaque(false);
		gespeeldCompetitiePanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;

		JLabel gespeeldeWedstrijdenLabel = new JLabel("Gespeelde wedstrijden");
		gespeeldeWedstrijdenLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 0;
		gespeeldCompetitiePanel.add(gespeeldeWedstrijdenLabel, c);

		uitslagenTable = new JTable(9, 3);
		uitslagenTable.setRowHeight(40);
		uitslagenTable.setFont(new Font("Arial", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 1;
		gespeeldCompetitiePanel.add(uitslagenTable, c);

		TableColumnModel columnModel = uitslagenTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(300);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(150);

		gespeeldCompetitiePanel.setVisible(false);
		uitslagenTable.setEnabled(false);
		return gespeeldCompetitiePanel;
	}

	private static JPanel addSubMenu() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JButton standButton = new JButton("Stand");
		standButton.setFont(new Font("Arial", Font.PLAIN, 30));
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		tempPanel.add(standButton, c);

		JButton gespeeldeButton = new JButton("Gespeelde wedstrijden");
		gespeeldeButton.setFont(new Font("Arial", Font.PLAIN, 30));
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		tempPanel.add(gespeeldeButton, c);

		JButton komendeButton = new JButton("Komende wedstrijden");
		komendeButton.setFont(new Font("Arial", Font.PLAIN, 30));
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		tempPanel.add(komendeButton, c);

		// Listeners
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				standPanel.setVisible(true);
			}
		});

		gespeeldeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				gespeeldCompetitiePanel.setVisible(true);

			}
		});

		komendeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanels();
				komendewedstrijdenPanel.setVisible(true);
			}
		});

		tempPanel.setVisible(true);
		tempPanel.setOpaque(false);
		return tempPanel;
	}

	private static JPanel addOptionsMenu() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JButton opslaanButton = new JButton("Opslaan");
		opslaanButton.setFont(new Font("Arial", Font.PLAIN, 30));
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		tempPanel.add(opslaanButton, c);

		JButton menuButton = new JButton("Menu");
		menuButton.setFont(new Font("Arial", Font.PLAIN, 30));
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		tempPanel.add(menuButton, c);

		// Listeners
		opslaanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SaveGame.createGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrame.dispose();
					createGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		tempPanel.setVisible(true);
		tempPanel.setOpaque(false);
		return tempPanel;
	}
	
	private static JPanel addEindPanel() {
		eindPanel = new JPanel();
		eindPanel.setOpaque(false);
		eindPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 50, 20, 50);
		
		JLabel gefeliciteerd = new JLabel("Gefeliciteerd!");
		gefeliciteerd.setFont(new Font("Arial", Font.PLAIN, 50));
		gefeliciteerd.setForeground(Color.white);
		c.gridx = 1;		
		c.gridy = 0;
		eindPanel.add(gefeliciteerd, c);
		
		eindLabel = new JLabel("");
		eindLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		eindLabel.setForeground(Color.white);
		c.gridx = 0;		
		c.gridy = 1;
		c.gridwidth = 3;
		eindPanel.add(eindLabel, c);
		
		JButton backToMenu = new JButton("Terug naar het menu");
		backToMenu.setFont(new Font("Arial", Font.PLAIN, 30));
		c.gridx = 1;		
		c.gridy = 2;
		c.gridwidth = 1;
		eindPanel.add(backToMenu, c);
		
		// Back to menu button listener
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrame.dispose();
					createGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		eindPanel.setVisible(false);
		return eindPanel;
	}

	private static JPanel addTransferMarkt() {

		transferpanel = new JPanel();
		transferpanel.setOpaque(false);
		transferpanel.setLayout(new GridBagLayout());

		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.BOTH;

		t.gridx = 0;
		t.gridy = 0;
		transferpanel.add(newEmptyLabel(8), t);

		JLabel transferLabel = new JLabel("De Transfermarkt");
		transferLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		t.gridx = 1;
		t.gridy = 0;
		transferpanel.add(transferLabel, t);

		TransferScreen.addTransferM();
		TransferScreen.SetCheck(false);

		t.gridx = 5;
		t.gridy = 0;
		t.gridwidth = 1;
		transferpanel.add(newEmptyLabel(2), t);

		transferpanel.setVisible(false);
		return transferpanel;
	}

	private static JPanel competitieStand(boolean bool) {
		standPanel = new JPanel();
		standPanel.setOpaque(false);
		standPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;

		JLabel StandLabel = new JLabel("Stand");
		StandLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 0;
		standPanel.add(StandLabel, c);

		Object[][] rowData = {};
		Object[] columnNames = { "Plek:", "Team:", "Winst", "Gelijk",
				"Verlies", "DV", "DT", "Doelsaldo", "Punten" };

		standlistTableModel = new DefaultTableModel(rowData, columnNames);
		standlistTableModel.addRow(new Object[] { "Plek:", "Team:", "Winst:",
				"Gelijk:", "Verlies", "DV", "DT", "Doelsaldo", "Punten" });
		for (int i = 1; i < competitie.length() + 1; i++) {
			String rowString = competitie.getTeam(i - 1).getTeamname();
			standlistTableModel.addRow(new Object[] { i + ".", rowString, 0, 0,
					0, 0, 0, 0, 0, 0 });
		}

		standTable = new JTable(standlistTableModel);
		standTable.setRowHeight(40);

		TableColumnModel columnModel = standTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(350);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(150);
		columnModel.getColumn(7).setPreferredWidth(150);
		columnModel.getColumn(8).setPreferredWidth(150);

		standTable.setFont(new Font("Arial", Font.PLAIN, 30));
		c.gridx = 0;
		c.gridy = 2;
		standPanel.add(standTable, c);
		standPanel.setVisible(false);
		standTable.setEnabled(false);

		if (bool == true) {

			for (int i = 0; i < competitie.length(); i++) {
				standTable.setValueAt(competitie.getTeam(i).getTeamname(),
						i + 1, 1);
				standTable.setValueAt(competitie.getTeam(i).getWinst(), i + 1,
						2);
				standTable.setValueAt(competitie.getTeam(i).getGelijk(), i + 1,
						3);
				standTable.setValueAt(competitie.getTeam(i).getVerlies(),
						i + 1, 4);
				standTable.setValueAt(competitie.getTeam(i).getDoelv(), i + 1,
						5);
				standTable.setValueAt(competitie.getTeam(i).getDoelsaldo()
						- competitie.getTeam(i).getDoelv(), i + 1, 6);
				standTable.setValueAt(competitie.getTeam(i).getDoelsaldo(),
						i + 1, 7);
				standTable.setValueAt(competitie.getTeam(i).getPunten(), i + 1,
						8);
			}

		}

		return standPanel;
	}

	private static JPanel komendeWedstrijden(Manager manager,
			Speelschema schema, boolean bool) {
		komendewedstrijdenPanel = new JPanel();
		komendewedstrijdenPanel.setOpaque(false);
		komendewedstrijdenPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;

		JLabel komendewedstrijden = new JLabel("Komende Wedstrijden");
		komendewedstrijden.setFont(new Font("Arial", Font.PLAIN, 40));
		c.gridx = 0;
		c.gridy = 0;
		komendewedstrijdenPanel.add(komendewedstrijden, c);

		Object[][] rowData = {};
		Object[] columnNames = { "Wedstrijd:" };
		KWlistTableModel = new DefaultTableModel(rowData, columnNames);
		Speelronde ronde = schema.getKomendeWedstrijden(manager.getTeam());

		for (int i = 0; i < ronde.length(); i++) {
			KWlistTableModel.addRow(new Object[] { ronde.getWedstrijd(i)
					.toString() });
		}

		if (bool == true) {
			for (int i = 0; i < Mechanics.getSpeelRonde(); i++) {
				KWlistTableModel.removeRow(0);
			}
		}

		komendewedstrijdenTable = new JTable(KWlistTableModel);
		komendewedstrijdenTable.setFont(new Font("Arial", Font.PLAIN, 18));

		komendewedstrijdenTable.setRowHeight(22);
		komendewedstrijdenTable.getColumnModel().getColumn(0)
				.setPreferredWidth(600);

		c.gridx = 0;
		c.gridy = 1;
		komendewedstrijdenPanel.add(komendewedstrijdenTable, c);
		komendewedstrijdenPanel.setVisible(false);
		komendewedstrijdenTable.setEnabled(false);
		return komendewedstrijdenPanel;
	}

	/**
	 * Maakt een nieuw leeg label met een bepaalde lengte
	 * 
	 * @param length
	 *            De lengte van het label
	 * @return Het aangemaakt label
	 */
	public static JLabel newEmptyLabel(int length) {
		String text = "";
		for (int i = 0; i < length; i++) {
			text = text + " ";
		}
		JLabel tempLabel = new JLabel(text);
		tempLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		return tempLabel;
	}

	public static void generatePlayerList() {
		opstellingPanel.remove(playerListPanel);
		playerListPanel = new JPanel(new GridLayout(0,4));
		playerListPanel.setOpaque(false);
		
		
		playerListPanel.add(playerListHeadLabel("Naam"));
		playerListPanel.add(playerListHeadLabel("Offensief"));
		playerListPanel.add(playerListHeadLabel("Defensief"));
		playerListPanel.add(playerListHeadLabel("Stamina"));

		for (int i = 0; i < manager.getTeam().getAantalSpelers(); i++) {
			JLabel nameLabel = playerListLabel(manager.getTeam().getSpeler(i)
					.getNaam());
			playerListPanel.add(nameLabel);
			String status = manager.getTeam().getSpeler(i).getStatus();
			switch (status) {
			case "blessure-1":
				nameLabel.setForeground(Color.blue);
				break;
			case "geel":
				nameLabel.setForeground(Color.yellow);
				break;
			case "rood-1":
				nameLabel.setForeground(Color.red);
				break;
			case "rood-2":
				nameLabel.setForeground(Color.red);
				break;
			case "rood-3":
				nameLabel.setForeground(Color.red);
				break;
			}

			playerListPanel.add(playerListLabel(Integer.toString(manager
					.getTeam().getSpeler(i).getOffensief())));
			playerListPanel.add(playerListLabel(Integer.toString(manager
					.getTeam().getSpeler(i).getDefensief())));
			playerListPanel.add(playerListLabel(Integer.toString(manager
					.getTeam().getSpeler(i).getStamina())));
		}

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 6;
		c.gridy = 0;
		c.gridheight = 4;
		opstellingPanel.add(playerListPanel, c);
	}

	private static JLabel playerListLabel(String text) {
		JLabel temp = new JLabel(text);
		temp.setFont(new Font("Arial", Font.PLAIN, 15));
		temp.setForeground(Color.WHITE);

		return temp;
	}

	private static JLabel playerListHeadLabel(String text) {
		JLabel temp = new JLabel(text);
		temp.setFont(new Font("Arial", Font.BOLD, 24));
		temp.setForeground(Color.WHITE);
		return temp;
	}

	/**
	 * Zet de visibility van alle panels op false
	 */
	static void hidePanels() {
		speelPanel.setVisible(false);
		gespeeldCompetitiePanel.setVisible(false);
		opstellingPanel.setVisible(false);
		subMenuPanel.setVisible(false);
		standPanel.setVisible(false);
		komendewedstrijdenPanel.setVisible(false);
		transferpanel.setVisible(false);
		optionsPanel.setVisible(false);
		eindPanel.setVisible(false);
	}

	private static String getLogo(String temp) {
		String s = "";
		switch (temp) {
		case "Ajax":
			s = "src//Afbeeldingen/ajax_logo.png";
			break;
		case "Feyenoord":
			s = "src//Afbeeldingen/feyenoord_logo.png";
			break;
		case "PSV":
			s = "src//Afbeeldingen/psv_logo.png";
			break;
		case "PEC Zwolle":
			s = "src//Afbeeldingen/pec_logo.png";
			break;
		case "AZ":
			s = "src//Afbeeldingen/az_logo.png";
			break;
		case "FC Twente":
			s = "src//Afbeeldingen/twente_logo.png";
			break;
		case "SC Cambuur Leeuwarden":
			s = "src//Afbeeldingen/cambuur_logo.png";
			break;
		case "SC Heerenveen":
			s = "src//Afbeeldingen/heerenveen_logo.png";
			break;
		case "FC Groningen":
			s = "src//Afbeeldingen/groningen_logo.png";
			break;
		case "Willem II":
			s = "src//Afbeeldingen/willem_logo.png";
			break;
		case "FC Utrecht":
			s = "src//Afbeeldingen/utrecht_logo.png";
			break;
		case "Vitesse":
			s = "src//Afbeeldingen/vitesse_logo.png";
			break;
		case "Excelsior":
			s = "src//Afbeeldingen/excelsior_logo.png";
			break;
		case "ADO Den Haag":
			s = "src//Afbeeldingen/ado_logo.png";
			break;
		case "Heracles Almelo":
			s = "src//Afbeeldingen/heracles_logo.png";
			break;
		case "Go Ahead Eagles":
			s = "src//Afbeeldingen/eagles_logo.png";
			break;
		case "NAC Breda":
			s = "src//Afbeeldingen/nac_logo.png";
			break;
		case "FC Dordrecht":
			s = "src//Afbeeldingen/dordrecht_logo.png";
			break;
		}
		return s;
	}

}
