package main;

import javax.swing.UIManager;

import controller.Controller;
import model.MySQL;
import view.GUI;
import view.SplashScreen;

public class Main {
	private MySQL mySQL;
	private GUI gui;
	private SplashScreen splash;
	
	public Main() {
		mySQL = new MySQL();
		
		splash = new SplashScreen();
		splash.startLoad();
		gui = new GUI();
		gui.setVisible(true);
		
		new Controller(mySQL, gui);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
