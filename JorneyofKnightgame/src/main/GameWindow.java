package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
        private final int Game_Width = 1440;
        private final int Game_Height =800;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();
                jframe.setTitle("Jorney of Knight");
                jframe.setSize(Game_Width,Game_Height);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.setVisible(true);
                jframe.add(gamePanel);
                jframe.pack();
	}

}
