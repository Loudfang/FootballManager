package interfacegui;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import mechanicsspel.*;


public class LoadGame {

	private static JButton load1, load2, load3, load4;
	private static Boolean noGames = false;
	
	private static boolean setButtonVisible(JButton temp){
		boolean res = false;
		if(!temp.getText().equals("Leeg"))
			res = true;
		return res;
	}

	protected static void createGUI() throws Exception {
		
		final JDialog laadscherm = new JDialog();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		JLabel noGamesFound = new JLabel("Er zijn geen opgeslagen spellen gevonden");
		noGamesFound.setFont(new Font("Arial", Font.PLAIN, 36));	
		noGamesFound.setVisible(false);
		mainPanel.add(noGamesFound);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		mainPanel.add(leftPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		mainPanel.add(rightPanel);
			
		load1 = new JButton(new AbstractAction(ReaderWriter.createNaamSlot("src//LoadGame1.xml",GUI.competitie)){
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
					GUI.competitie = ReaderWriter.createCompetitie(ReaderWriter.read("src//LoadGame1.xml"));
					GUI.speelschema = ReaderWriter.createSpeelschema(ReaderWriter.read("src//LoadGame1.xml"),GUI.competitie);
					GUI.manager = ReaderWriter.createManager(ReaderWriter.read("src//LoadGame1.xml"), GUI.competitie);
					int r = ReaderWriter.readSpeelronde(ReaderWriter.read("src//LoadGame1.xml"));
					Mechanics.setSpeelRonde(r);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				laadscherm.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(true);
				GUI.hidePanels();
				GUI.opstellingPanel.setVisible(true);
				GUI.generatePlayerList();
				OpstellingGUI.addOpstelling442();
			}
		});
		load1.setFont(new Font("Arial", Font.PLAIN, 36));		
		load1.setVisible(true);		
		leftPanel.add(load1);
		
		load2 = new JButton(new AbstractAction(ReaderWriter.createNaamSlot("src//LoadGame2.xml",GUI.competitie)){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GUI.competitie = ReaderWriter.createCompetitie(ReaderWriter.read("src//LoadGame2.xml"));
					GUI.speelschema = ReaderWriter.createSpeelschema(ReaderWriter.read("src//LoadGame2.xml"),GUI.competitie);
					GUI.manager = ReaderWriter.createManager(ReaderWriter.read("src//LoadGame2.xml"), GUI.competitie);
					int r = ReaderWriter.readSpeelronde(ReaderWriter.read("src//LoadGame2.xml"));
					Mechanics.setSpeelRonde(r);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				laadscherm.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(true);
				GUI.hidePanels();
				GUI.opstellingPanel.setVisible(true);
				GUI.generatePlayerList();
				OpstellingGUI.addOpstelling442();
			}
		});
		load2.setFont(new Font("Arial", Font.PLAIN, 36));
		load2.setVisible(true);
		leftPanel.add(load2);
		
		load3 = new JButton(new AbstractAction(ReaderWriter.createNaamSlot("src//LoadGame3.xml",GUI.competitie)){
			public void actionPerformed(ActionEvent e) {
				try {
					GUI.competitie = ReaderWriter.createCompetitie(ReaderWriter.read("src//LoadGame3.xml"));
					GUI.speelschema = ReaderWriter.createSpeelschema(ReaderWriter.read("src//LoadGame3.xml"),GUI.competitie);
					GUI.manager = ReaderWriter.createManager(ReaderWriter.read("src//LoadGame3.xml"), GUI.competitie);
					int r = ReaderWriter.readSpeelronde(ReaderWriter.read("src//LoadGame3.xml"));
					Mechanics.setSpeelRonde(r);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				laadscherm.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(true);
				GUI.hidePanels();
				GUI.opstellingPanel.setVisible(true);
				GUI.generatePlayerList();
				OpstellingGUI.addOpstelling442();
			}
		});
		load3.setFont(new Font("Arial", Font.PLAIN, 36));
		load3.setVisible(true);
		rightPanel.add(load3);
		
		load4 = new JButton(new AbstractAction(ReaderWriter.createNaamSlot("src//LoadGame4.xml",GUI.competitie)){
			public void actionPerformed(ActionEvent e) {
				try {
					GUI.competitie = ReaderWriter.createCompetitie(ReaderWriter.read("src//LoadGame4.xml"));
					GUI.speelschema = ReaderWriter.createSpeelschema(ReaderWriter.read("src//LoadGame4.xml"),GUI.competitie);
					GUI.manager = ReaderWriter.createManager(ReaderWriter.read("src//LoadGame4.xml"), GUI.competitie);
					int r = ReaderWriter.readSpeelronde(ReaderWriter.read("src//LoadGame4.xml"));
					Mechanics.setSpeelRonde(r);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				laadscherm.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(true);
				GUI.hidePanels();
				GUI.opstellingPanel.setVisible(true);
				GUI.generatePlayerList();
				OpstellingGUI.addOpstelling442();
			}
		});
		load4.setFont(new Font("Arial", Font.PLAIN, 36));
		load4.setVisible(true);
		rightPanel.add(load4);
		
		
		if (load1.getText().equals("Leeg") && load2.getText().equals("Leeg") && load3.getText().equals("Leeg") && load4.getText().equals("Leeg")) {
			noGamesFound.setVisible(true);
			noGames = true;
		}
		
		load1.setVisible(setButtonVisible(load1));
		load2.setVisible(setButtonVisible(load2));
		load3.setVisible(setButtonVisible(load3));
		load4.setVisible(setButtonVisible(load4));
		
		laadscherm.add(mainPanel);
		laadscherm.setModal(true);	
		
		if (noGames) {
			laadscherm.setSize(800, 100);
		} else {
			laadscherm.setSize(1600,200);
		}		
		laadscherm.setLocationRelativeTo(null);
		laadscherm.setVisible(true);
	}	
}
