/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;


/**
 *
 * @author koonfa
 */
public class Player extends Entity{
    private BufferedImage[][] animations;
    private int direction =0;
    private int timeattackstart =0;
    private int timeattackstop =0;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false,Dead=false;
    private boolean left,right,roll;
      private int HealthbarWidth = 385;
        private int HealthbarHeight = 17;
        private int HealthbarXStart = 92;
        private int HealthbarYStart = 32;
        private int maxHealth = 1000;
	private int currentHealth = maxHealth;
        private int healthWidth = HealthbarWidth;
        private BufferedImage statusBarImg;
        private int statusBarWidth = (int) (512 );
	private int statusBarHeight = (int) (80 );
	private int statusBarX = (int) (10 );
	private int statusBarY = (int) (20 );
        public Font font;

    public Player(int positionX, int positionY, int width,int height) {
        super(positionX, positionY, width, height);
        setTimeattackstop(30);
        try {
            InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res\\OptimusPrinceps.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadAnimations();
    }

 
    
    public void update(){
                updateHealthBar();
                if (currentHealth <= 0) {
			playerAction = DEATH;
                        updateAnimationTick();
                        Dead = true;
                        aniIndex = 9;
                        return;
                        
		}
                
                updatePos();
                updateAnimationTick();
		setAnimation();
                
    }
    public void render(Graphics g2){
                
        drawUI(g2);
//g2.setColor(Color.red);
//        g2.drawRect(positionX+70,positionY+80,scaleX+70,scaleY);
        g2.drawImage(animations[playerAction][aniIndex], (int) positionX, (int)positionY, 240, 160, null);
    }
    public void renderStart(Graphics g2){
        g2.drawImage(animations[0][aniIndex], (int) -200, (int)100, 960, 640, null);
        g2.setFont(font.deriveFont(Font.BOLD,60f));
        g2.setColor(new Color(155, 14, 14));
	g2.drawString("Jorney Of Knight", 800, 150);
        g2.setFont(font.deriveFont(Font.BOLD,40f));
        g2.setColor(Color.white);
	g2.drawString("Enter to play", 900, 320);
        g2.drawString("Escape to quit", 900, 420);
        
    }
    @Override
    public void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
//                                attackChecked = false;
			}

		}

	}
    @Override
    	public void setAnimation() {
		int startAni = playerAction;

		if (moving){
                    attacking = false;
                }
                else{
			playerAction = IDLE;
                }

		if (attacking) {
                    moving = false;
			playerAction= ATTACK;
			if (startAni != ATTACK) {
				aniIndex = 1;
				aniTick = 0;
				return;
			}
		}


		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}
    @Override
       public void loadAnimations() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

			animations = new BufferedImage[9][12];
			for (int j = 0; j < animations.length; j++)
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 120, j * 80, 120, 80);
                        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
		
		
	}
    private void updatePos() {
		moving = false;
		if (left && !right) {
			positionX -= speed;
                        playerAction = RUNNINGLEFT;
			moving = true;
		} else if (right && !left) {
                    playerAction = RUNNINGRIGHT;
			positionX += speed;
			moving = true;
		}else if(roll){
                    playerAction = RollLeft;
                    positionX -= speed+1;
                    moving = true;
                }
                if(positionX <= -80 ){
                positionX =-80;
            }else if( positionX>=1300){
                positionX =1300;
            }
                
	}
    public Rectangle getPlayerHitBlock(){
        return new Rectangle(positionX+80,positionY+80,scaleX-30,scaleY);
    }
    public Rectangle getPlayerHitBlockWhenAttack(){
        return new Rectangle(positionX+70,positionY+80,scaleX+70,scaleY);
    }
        public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    
    public int getTimeattackstart() {
        return timeattackstart;
    }

    public void setTimeattackstart(int timeattackstart) {
        this.timeattackstart = timeattackstart;
    }

    public int getTimeattackstop() {
        return timeattackstop;
    }

    public void setTimeattackstop(int timeattackstop) {
        this.timeattackstop = timeattackstop;
    }
    
    public void resetDirBooleans() {
		left = false;
		right = false;
                roll = false;
	}

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isRoll() {
        return roll;
    }

    public void setRoll(boolean roll) {
        this.roll = roll;
    }
    
    private void updateHealthBar() {
        healthWidth =(int)((currentHealth / (float)maxHealth) * HealthbarWidth);
    }

    private void drawUI(Graphics g) {
        g.setFont(font.deriveFont(Font.BOLD,24f));
        g.setColor(Color.WHITE);
        g.drawString("Knight", statusBarX+110, statusBarY+20);
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(new Color(163, 56, 56));
	g.fillRect(HealthbarXStart + statusBarX, HealthbarYStart + statusBarY, healthWidth, HealthbarHeight);
    }
            public void changeHealth(int value) {
            currentHealth += value;

            if (currentHealth <= 0){
		currentHealth = 0;
                
            }
            else if (currentHealth >= maxHealth){
                currentHealth = maxHealth;
            }
	}
            
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isDead() {
        return Dead;
    }

    public void setDead(boolean Dead) {
        this.Dead = Dead;
    }
    
}
