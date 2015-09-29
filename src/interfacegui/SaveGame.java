package interfacegui;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mechanicsspel.*;


public class SaveGame {
	private static JFrame mainFrame;
	private static JButton save1, save2, save3, save4;

	protected static void createGUI() throws Exception {
		mainFrame = new JFrame("Sla het spel op");
		mainFrame.setSize(1600,200);
		addComponents();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}	

	protected static void addComponents() throws Exception {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		mainPanel.add(leftPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		mainPanel.add(rightPanel);
			
		save1 = new JButton(ReaderWriter.createNaamSlot("src//LoadGame1.xml",GUI.competitie));
		save1.setFont(new Font("Arial", Font.PLAIN, 36));
		leftPanel.add(save1);
		
		save2 = new JButton(ReaderWriter.createNaamSlot("src//LoadGame2.xml",GUI.competitie));
		save2.setFont(new Font("Arial", Font.PLAIN, 36));
		leftPanel.add(save2);
		
		save3 = new JButton(ReaderWriter.createNaamSlot("src//LoadGame3.xml",GUI.competitie));
		save3.setFont(new Font("Arial", Font.PLAIN, 36));
		rightPanel.add(save3);
		
		save4 = new JButton(ReaderWriter.createNaamSlot("src//LoadGame4.xml",GUI.competitie));
		save4.setFont(new Font("Arial", Font.PLAIN, 36));
		rightPanel.add(save4);
		
		save1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReaderWriter.Writer("src//LoadGame1.xml",GUI.competitie,GUI.speelschema,GUI.manager, Mechanics.getSpeelRonde());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				mainFrame.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(false);
			}
		});
		
		save2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					ReaderWriter.Writer("src//LoadGame2.xml", GUI.competitie, GUI.speelschema,GUI.manager, Mechanics.getSpeelRonde());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				mainFrame.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(false);
			}
		});
		
		save3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReaderWriter.Writer("src//LoadGame3.xml", GUI.competitie, GUI.speelschema,GUI.manager, Mechanics.getSpeelRonde());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				mainFrame.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(false);
			}
		});
		
		save4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReaderWriter.Writer("src//LoadGame4.xml", GUI.competitie, GUI.speelschema,GUI.manager, Mechanics.getSpeelRonde());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				mainFrame.dispose();
				GUI.splashPanel.setVisible(false);
				GUI.addComponents(false);
			}
		});		
		
		mainFrame.add(mainPanel);
	}
}