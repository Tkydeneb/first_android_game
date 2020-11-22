package pl.oldcotage.simplegame.objects.weapons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Laser {

    //position and dimensions
    public float xPosition, yPosition;
    public float width, height;

    //graphics
    public TextureRegion textureRegion;

    //laser physical characteristics
    public float movementSpeed;

    public Laser(float xPosition, float yPosition, float width, float height, TextureRegion textureRegion, float movementSpeed) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.textureRegion = textureRegion;
        this.movementSpeed = movementSpeed;
    }

    public  void draw(Batch batch){
        batch.draw(textureRegion,xPosition-width/2,yPosition,width,height);
    }
}
