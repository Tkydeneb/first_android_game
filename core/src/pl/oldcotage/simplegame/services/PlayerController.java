package pl.oldcotage.simplegame.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.PlayerShip;
import pl.oldcotage.simplegame.objects.Ship;

public class PlayerController {

    public void detectInput(float deltaTime, Ship ship){

        boolean isLeftLimit, isRightLimit;

        isLeftLimit = ship.xPosition > 0;
        isRightLimit = ship.xPosition + ship.width < GameRunner.WIDTH;

        //keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && isRightLimit){
            ship.xPosition += ship.movementSpeed*deltaTime;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && isLeftLimit){
            ship.xPosition -= ship.movementSpeed*deltaTime;
        }

        //touch input (mouse input)

    }
}
