package pl.oldcotage.simplegame.objects.weapons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Laser {

    //position and dimensions
    private float xPosition, yPosition;
    private float width, height;

    private boolean enemy;

    //graphics
    private TextureRegion textureRegion;

    //laser physical characteristics
    private float movementSpeed;

    public Laser(float xPosition, float yPosition, float width, float height, TextureRegion textureRegion, float movementSpeed, boolean isEnemy) {
        this.xPosition = xPosition;
        this.xPosition = yPosition;
        this.width = width;
        this.height = height;
        this.textureRegion = textureRegion;
        this.movementSpeed = movementSpeed;
        this.enemy = isEnemy;
    }

    public void draw(Batch batch){
        batch.draw(textureRegion,xPosition-width/2,yPosition,width,height);
    }
     public Rectangle getRectangle(){
        return new Rectangle(xPosition, yPosition, width, height);
     }


    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
