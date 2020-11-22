package pl.oldcotage.simplegame.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.objects.weapons.Laser;

public class PlayerShip extends Ship {


    public PlayerShip(float xCentre, float yCentre,
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
        laser[0] = new Laser(xPosition + width * 0.35f, yPosition + height * 0.6f, laserWidth, laserHeight, laserTextureRegion, laserMovementSpeed);
        laser[1] = new Laser(xPosition + width * 0.65f, yPosition + height * 0.6f, laserWidth, laserHeight, laserTextureRegion, laserMovementSpeed);

        timeSinceLastShot = 0;

        return laser;
    }
}
