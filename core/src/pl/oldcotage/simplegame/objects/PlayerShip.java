package pl.oldcotage.simplegame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.weapons.Laser;

public class PlayerShip extends Ship {

    // starting ship statistics
    private final static float WIDTH_SHIP = 50;
    private final static float HEIGHT_SHIP = 50;
    private final static float INITIAL_SPEED = 90;
    private final static int INITIAL_HIT_POINTS = 3;
    private final static float INITIAL_BULLET_SPEED = 45;
    private final static float INITIAL_ATTACK_SPEED = 0.5f;

    public PlayerShip(
                      float laserWidth, float laserHeight,
                      TextureRegion shipTextureRegion,
                      TextureRegion laserTextureRegion) {

        super((float) GameRunner.WIDTH / 2, (float)GameRunner.HEIGHT / 4,
                WIDTH_SHIP, HEIGHT_SHIP,
                INITIAL_SPEED, INITIAL_HIT_POINTS,
                laserWidth, laserHeight,
                INITIAL_BULLET_SPEED, INITIAL_ATTACK_SPEED,
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
    @Override
    public void movingShip(TextureAtlas textureAtlas) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPosition--;
            setPosition(xPosition);
            setShipTextureRegion(textureAtlas.findRegion("player_ship_left_2"));
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPosition++;
            setPosition(xPosition);
            setShipTextureRegion(textureAtlas.findRegion("player_ship_right_2"));
        }else{
            setShipTextureRegion(textureAtlas.findRegion("player_ship"));
        }
    }
}
