package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		// CREATING THE WINDOW
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows user to close window when clicking ("x") button
		window.setResizable(false); // Not allowing resizing of the window
		window.setTitle("2DWorld");

		// ADD GAMEPANEL TO WINDOW
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack(); // Causes this window to be sized to fit the preferred size and layouts of its
						// subcomponents (=GamePanel)

		window.setLocationRelativeTo(null); // Window will be displayed at the center of the screen
		window.setVisible(true); // To be able to see the window

		gamePanel.setupGame(); // calling object method
		gamePanel.startGamethread(); // calling the method
	}

}
