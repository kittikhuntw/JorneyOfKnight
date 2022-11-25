package main;

import entities.Enemy;
import entities.Player;
import entities.Potion;
import java.awt.Color;
import java.awt.Graphics;

public class Game {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
        private boolean playing = false;
	private Player player;
        private Potion potion;
        private Enemy enemy;
        private final int Game_Width = 1440;
        private final int Game_Height =800;

	public Game() {
		player = new Player(100,570,80,80);
                enemy  = new Enemy(1400,560,351,189);
                potion = new Potion(100,700,40,40);
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
                player_thread.start();
                Enemy_thread.start();
                Potion_thread.start();
	}
        Thread player_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            player.update();
                            gamePanel.repaint();
                             if(playing == true){
                                checkPlayerHitEnemy();
                                checkPlayerGetPotion();
                             }
                             Thread.sleep(4);
                        } catch (Exception e) {
                        }
                    }
                }
            });    
                Thread Enemy_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                             if(playing == true){
                            enemy.update();
                            HitBlock();
                            checkplayerInrange();
                            gamePanel.repaint();
                            Thread.sleep(4);
                             }
                        } catch (Exception e) {
                        }   
                    }
                }
            });
                Thread Potion_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                             if(playing == true){
                             potion.update();
                            gamePanel.repaint();
                            Thread.sleep(7);
                             }
                        } catch (Exception e) {
                        }   
                    }
                }
            });
	

	public void render(Graphics g) {
            if( playing == false){
                drawStartMenu(g);
            }
            if(playing == true){
		player.render(g);
                enemy.render(g);
                potion.render(g);
                 if(player.isDead() == true){
                    drawGameover(g);
                }
            }
                
	}
        public void drawGameover(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game_Width, Game_Height);

		g.setColor(Color.white);
		g.drawString("Game Over", Game_Width / 3+200, 150);
		g.drawString("Thanks for playing", Game_Width / 2-70, 300);
                g.drawString("Your score is "+enemy.getCount(), Game_Width / 2-60, 350);

	}
        public void drawStartMenu(Graphics g){
                player.renderStart(g);
        }


	public Player getPlayer() {
		return player;
	}
        public void HitBlock(){
            if(player.getPlayerHitBlock().intersects(enemy.getEnemyHitBlock())){
                player.setPositionX(enemy.getPositionX()+40);
            }
        }
        public void checkEnemyHitPlayer(){
            if(player.getPlayerHitBlock().intersects(enemy.getEnemyHitBlockWhenAttack()) &&enemy.isAttackChecked() == true && enemy.getAniIndex() == 6){
                  player.changeHealth(-4*(enemy.getCount()/2+1));
//                  enemy.setAttackChecked(false);
        }
    }
                public void checkplayerInrange(){
                    //-81 - 41
            int distance = player.getPositionX() - enemy.getPositionX();
            if(distance >= -50 && distance <= 41){
                checkEnemyHitPlayer();
                enemy.setAttacking(true);
                enemy.setTimemove(0);
                
            }
        }
        public void checkPlayerHitEnemy(){
            if(player.getPlayerHitBlockWhenAttack().intersects(enemy.getEnemyHitBlock())){
                if(player.isAttacking() == true){
                    enemy.changeHealth(-1);
//                    player.setAttacking(false);
                }
            }
        }
        public void checkPlayerGetPotion(){
            if(player.getPlayerHitBlock().intersects(potion.getpotionHitblock()) && potion.isDelay() == false){
                player.changeHealth(100);
                potion.setDelay(true);
            }
        }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
        
}