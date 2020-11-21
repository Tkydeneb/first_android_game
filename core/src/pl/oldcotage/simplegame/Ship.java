package pl.oldcotage.simplegame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

abstract class Ship {
    //shp characteristics
    float movementSpeed;
    int hitPoints;

    //position & dimension
    float xPosition, yPosition;
    float width, height;

    //laser information
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShoots;
    float timeSinceLastShot = 0;

    //graphics
    TextureRegion shipTextureRegion, laserTextureRegion;

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

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser() {
        return (timeSinceLastShot - timeBetweenShoots >=0);
    }

    public abstract Laser[] fireLasers();

    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
    }
}
