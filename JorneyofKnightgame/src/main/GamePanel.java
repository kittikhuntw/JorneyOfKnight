package main;

import entities.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;
import utilz.LoadSave;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
        private BufferedImage bglocation;
        private final int Game_Width = 1440;
        private final int Game_Height =800;

	public GamePanel(Game game) {
                bglocation = LoadSave.GetSpriteAtlas(LoadSave.Bglocation);
		mouseInputs = new MouseInputs(this);
		this.game = game;
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game_Width,Game_Height);
		setPreferredSize(size);
                this.setFocusable(true);
                setMinimumSize(size);
                setMaximumSize(size);
	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bglocation, 0, 0,Game_Width,Game_Height,null);

		game.render(g);

	}

	public Game getGame() {
		return game;
	}


}