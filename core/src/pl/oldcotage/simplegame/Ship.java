package pl.oldcotage.simplegame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship {
    //shp characteristics
    float movementSpeed;
    int hitPoints;

    //position & dimension
    float xPosition, yPosition;
    float width, height;

    //graphics
    TextureRegion shipTexture;

    public Ship(float movementSpeed, int hitPoints,
                float width, float height,
                float xCentre, float yCentre,
                TextureRegion shipTexture) {
        this.movementSpeed = movementSpeed;
        this.hitPoints = hitPoints;
        this.xPosition = xCentre - width/2;
        this.yPosition = yCentre - height/2;
        this.width = width;
        this.height = height;
        this.shipTexture = shipTexture;
    }

    public void draw(Batch batch){
        batch.draw(shipTexture, xPosition,yPosition,width,height);
    }
}
