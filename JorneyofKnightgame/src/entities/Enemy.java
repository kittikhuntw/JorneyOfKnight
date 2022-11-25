/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author koonfa
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import static utilz.Constants.EnemyConstants.*;
import utilz.LoadSave;
public class Enemy extends Entity{
    private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 30;
	private int BossAction = Idle;
	private boolean moving = false, attacking = false,alive = true;
	private boolean left,right;
        private int timemove =0;
        private Random random;
        private int numberrandom;
        private final int deafultspawn=1400;
       private int maxHealth = 200;
       private int currentHealth = maxHealth;
       private boolean attackChecked;
       private int count=0;
    public Enemy(int positionX, int positionY, int width,int height) {
        super(positionX, positionY, width, height);
        loadAnimations();
    }
    public void update() {
                if(alive == true){
		updatePos();
		updateAnimationTick();
		setAnimation();
                }
	}

	public void render(Graphics g2) {
            if(alive == true){
		g2.drawImage(animations[BossAction][aniIndex], (int) positionX, (int)positionY, scaleX, scaleY, null);
//                g2.setColor(Color.red);
//                g2.drawRect(positionX+50,positionY+50,scaleX-230,scaleY-50);
            }
            g2.setColor(Color.WHITE);
            g2.drawString("Score : " + count, 1300, 50);

	}
        
    @Override
	public void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(Boss,BossAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

    @Override
	public void setAnimation() {
		int startAni = BossAction;

		if (moving){attacking = false;}
		else
			BossAction = Idle;

		if (attacking) {
                        moving = false;
			BossAction= ATTACK;
                        if(aniIndex == 0){
                            attackChecked = false;
                        }
			if (aniIndex == 6 && !attackChecked) {
                            attackChecked = true;	
			}
                        if(aniIndex <6){
                            attackChecked = false;
                        }
                        
		}

		if (startAni != BossAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {

		moving = false;
            timemove++;
            if(timemove >=1 && timemove<=1){
                random = new Random();
                numberrandom = random.nextInt(10);
                if(numberrandom >=1 && numberrandom <= 2){
                    right = true;
                }
                else if(numberrandom >=3 && numberrandom <= 10){
                    left= true;
                }
            }

            if(timemove >=250 && timemove<=250){
                right = false;
                left = false;
//                attacking = false;
            }
             if(timemove >=500 && timemove<=500){
                timemove =0;
             }
        
		if (left && !right) {
			positionX -= speed;
                        BossAction = Running;
			moving = true;
		} else if (right && !left) {
                    BossAction = Idle;
			positionX += speed;
			moving = true;
		}
                 if(positionX <= 0 ){
                positionX =0;
            }else if( positionX>=1400){
                positionX =1400;
            }
             
	}

    @Override
	public void loadAnimations() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Boss_sprites);

			animations = new BufferedImage[4][11];
			for (int j = 0; j < animations.length; j++)
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 366, j * 189, 351, 180);

		
		
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
	}
    public Rectangle getEnemyHitBlock(){
        return new Rectangle(positionX+175,positionY+50,scaleX-270,scaleY-50);
    }
    public Rectangle getEnemyHitBlockWhenAttack(){
        return new Rectangle(positionX+50,positionY+50,scaleX-230,scaleY-50);
    }
 

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isAttackChecked() {
        return attackChecked;
    }

    public void setAttackChecked(boolean attackChecked) {
        this.attackChecked = attackChecked;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
                public void changeHealth(int value) {
            currentHealth += value;

            if (currentHealth <= 0){
		currentHealth = 0;
                alive = false;
                 
                resetEnemie();
                count++;
             
             
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
        public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void resetEnemie(){
                positionX = deafultspawn;
                currentHealth = 100;
                alive = true;
    }

    public int getTimemove() {
        return timemove;
    }

    public void setTimemove(int timemove) {
        this.timemove = timemove;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }
    
}
