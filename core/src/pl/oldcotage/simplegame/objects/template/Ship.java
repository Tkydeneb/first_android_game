package pl.oldcotage.simplegame.objects.template;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import pl.oldcotage.simplegame.objects.weapons.Laser;

abstract public class Ship {
    //shp characteristics
    public float movementSpeed;
    public int hitPoints;

    //position & dimension
    public float xPosition, yPosition;
    public float width, height;

    //laser information
    public float laserWidth, laserHeight;
    public float laserMovementSpeed;
    public float timeBetweenShoots;
    public float timeSinceLastShot = 0;

    //graphics
    public TextureRegion shipTextureRegion, laserTextureRegion;

    public Ship(float xCentre, float yCentre,
                float width, float height,
                float movementSpeed, int hitPoints,
                float laserWidth, float laserHeight, float laserMovementSpeed, float timeBetweenShoots,
                TextureRegion shipTextureRegion,
                TextureRegion laserTextureRegion) {
        this.movementSpeed = movementSpeed;
        this.hitPoints = hitPoints;
        this.xPosition = xCentre - width / 2;
        this.yPosition = yCentre - height / 2;
        this.width = width;
        this.height = height;
        this.shipTextureRegion = shipTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
        this.timeBetweenShoots = timeBetweenShoots;
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
    }

    public void setShipTextureRegion(TextureRegion shipTextureRegion) {
        this.shipTextureRegion = shipTextureRegion;
    }

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser() {
        return (timeSinceLastShot - timeBetweenShoots >=0);
    }

    public abstract Laser[] fireLasers();

    public abstract void movingShip(TextureAtlas textureAtlas);

    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
    }
    //TODO detect collision with other rectangles public boolean
    public Rectangle getRectangle(){
        return new Rectangle(xPosition, yPosition, width, height);
    }

    public float getPosition() {
        return xPosition;
    }

    public void setPosition(float x) {
        xPosition = x;
    }
}
