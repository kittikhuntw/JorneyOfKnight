/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import utilz.LoadSave;

/**
 *
 * @author koonfa
 */
public class Potion extends Entity{
    private BufferedImage[][] animations;
    private int counter =0;
    private int i=1;
    private Random random;
    private int numberrandom;
    private boolean delay =true;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int potionAction = 0;

    public Potion(int positionX, int positionY, int width,int height) {
     super(positionX, positionY, width, height);
     loadAnimations();
    }
    public void update(){
        if(delay == true){
            Delay();
        }
        else if(delay == false){
            updateAnimationTick();
//		setAnimation();   
        }
                
    }
    public void render(Graphics g2){
        if(delay == false){
//        g2.drawRect(positionX,positionY,scaleX,scaleY);
        g2.drawImage(animations[0][aniIndex], (int) positionX, (int)positionY, scaleX, scaleY, null);
        }
    }
    @Override
        public void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 18) {
				aniIndex = 0;
			}

		}

	}
    @Override
              public void loadAnimations() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.potion);

			animations = new BufferedImage[1][18];
			for (int j = 0; j < animations.length; j++)
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 512, j * 512, 512, 512);

		
		
	}
              public void Delay(){
                counter += 1;
                //1sec
                if(counter == 280*15){
                   delay = false;
//                    System.out.println("30sec");
                   counter = 0;
                   random = new Random();
                   positionX =  random.nextInt(10) * 100;
                           }
              }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
   
    public Rectangle getpotionHitblock(){
        return new Rectangle(positionX,positionY,scaleX,scaleY);
    }

    @Override
    public void setAnimation() {
       
    }
    
    
}
