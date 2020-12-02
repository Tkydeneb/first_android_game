package pl.oldcotage.simplegame.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.objects.template.Ship;
import pl.oldcotage.simplegame.service.ShipService;

public class PlayerController {

    private ShipService shipService = new ShipService();
    private TextureRegion shipNormalPosition;
    private Animation<TextureAtlas.AtlasRegion> rightAnimation;
    private Animation<TextureAtlas.AtlasRegion> leftAnimation;

    private float elapsedTime;
    private final float frameDuration = 0.2f;


    public PlayerController(Ship ship) {
        shipNormalPosition = ship.getShipTextureRegion();
        leftAnimation = new Animation<>(frameDuration, new TextureAtlas(Gdx.files.internal("ships/playerMoveLeft.atlas")).getRegions());
        rightAnimation = new Animation<>(frameDuration, new TextureAtlas(Gdx.files.internal("ships/playerMoveRight.atlas")).getRegions());
    }

    public void detectInput(float deltaTime) {
        Ship ship = shipService.getPlayer();
        elapsedTime += deltaTime;
        boolean isLeftLimit, isRightLimit;

        isLeftLimit = ship.xPosition <= 0;
        isRightLimit = ship.xPosition + ship.width >= Gdx.graphics.getWidth();

        //keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ship.setShipTextureRegion(leftAnimation.getKeyFrame(elapsedTime));
            ship.xPosition -= ship.movementSpeed * deltaTime;
            if (isLeftLimit) {
                ship.xPosition = 0;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ship.setShipTextureRegion(rightAnimation.getKeyFrame(elapsedTime));
            ship.xPosition += ship.movementSpeed * deltaTime;
            if (isRightLimit) {
                ship.xPosition = Gdx.graphics.getWidth() - ship.width;
            }
        } else {
            elapsedTime = 0;
            ship.setShipTextureRegion(shipNormalPosition);
        }

        //touch input (mouse input)

    }
}
