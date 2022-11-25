package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
            if(gamePanel.getGame().isPlaying() == false){
                switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
                            gamePanel.getGame().setPlaying(true);
                            break;
                case KeyEvent.VK_ESCAPE:
                            System.exit(0);
                            break;
            }
            }
            if(gamePanel.getGame().isPlaying() == true){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			break;
                case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setRoll(false);
			break;

		}
            }
	}
	@Override
	public void keyPressed(KeyEvent e) {
               if(gamePanel.getGame().isPlaying() == false){
                switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
                            gamePanel.getGame().setPlaying(true);
                            break;
                case KeyEvent.VK_ESCAPE:
                            System.exit(0);
                            break;
            }
        }
            if(gamePanel.getGame().isPlaying() == true){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			break;
                case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setRoll(true);
			break;
		}
	}
        }
}
