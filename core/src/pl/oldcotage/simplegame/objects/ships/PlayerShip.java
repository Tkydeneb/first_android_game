package pl.oldcotage.simplegame.objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.objects.template.Ship;
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
        laser[0] = new Laser(xPosition + width * 0.35f, yPosition + height * 0.6f, laserWidth, laserHeight, laserTextureRegion, laserMovementSpeed,false);
        laser[1] = new Laser(xPosition + width * 0.65f, yPosition + height * 0.6f, laserWidth, laserHeight, laserTextureRegion, laserMovementSpeed,false);

        timeSinceLastShot = 0;

        return laser;
    }
    @Override
    public void movingShip(TextureAtlas textureAtlas) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPosition -= movementSpeed;
            setPosition(xPosition);
            setShipTextureRegion(textureAtlas.findRegion("player_ship_left_2"));
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPosition += movementSpeed;
            setPosition(xPosition);
            setShipTextureRegion(textureAtlas.findRegion("player_ship_right_2"));
        }else{
            setShipTextureRegion(textureAtlas.findRegion("player_ship"));
        }
    }
}
