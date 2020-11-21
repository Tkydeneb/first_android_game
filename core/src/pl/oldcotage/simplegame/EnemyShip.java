package pl.oldcotage.simplegame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyShip extends Ship{
    public EnemyShip(float xCentre, float yCentre,
                      float width, float height,
                      float movementSpeed, int hitPoints,
                      float laserWidth, float laserHeight,
                      float laserMovementSpeed, float timeBetweenShoots,
                      TextureRegion shipTextureRegion,
                      TextureRegion laserTextureRegion) {

        super(xCentre, yCentre,
                width, height,
                movementSpeed, hitPoints,
                laserWidth, laserHeight,
                laserMovementSpeed, timeBetweenShoots,
                shipTextureRegion,
                laserTextureRegion);
    }


    @Override
    public Laser[] fireLasers() {
        Laser[] laser = new Laser[2];
        laser[0] = new Laser(xPosition+width*0.5f,yPosition+height*0.45f,laserWidth,laserHeight,laserTextureRegion,laserMovementSpeed);

        timeSinceLastShot = 0;

        return laser;
    }
}
