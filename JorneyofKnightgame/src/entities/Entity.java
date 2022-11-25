/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


/**
 *
 * @author koonfa
 */
public abstract class Entity {

	    protected int positionX = 0;
            protected int positionY = 0;
            protected int scaleX =0;
            protected int scaleY =0;
            protected float speed = 1;
            public abstract void updateAnimationTick();
            public abstract void setAnimation();
            public abstract void loadAnimations();

	public Entity(int positionX, int positionY, int width, int height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.scaleX = width;
		this.scaleY = height;
	
        }
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getScaleX() {
        return scaleX;
    }

    public void setScaleX(int scaleX) {
        this.scaleX = scaleX;
    }

    public int getScaleY() {
        return scaleY;
    }

    public void setScaleY(int scaleY) {
        this.scaleY = scaleY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
        
}
