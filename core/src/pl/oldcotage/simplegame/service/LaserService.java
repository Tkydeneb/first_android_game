package pl.oldcotage.simplegame.service;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.template.Ship;
import pl.oldcotage.simplegame.objects.weapons.Laser;

public class LaserService {

    private LinkedList<Laser> laserList = new LinkedList<>();
    private LinkedList<Laser> enemyLaserList = new LinkedList<>();

    public void  initLaserFire(Ship ship, SpriteBatch batch, float delta){
        //player lasers
        if (ship.canFireLaser()) {
            Laser[] lasers = ship.fireLasers();
            laserList.addAll(Arrays.asList(lasers));
        }

        move(batch, delta);
    }

    private void move(SpriteBatch batch, float delta){
        ListIterator<Laser> iterator = laserList.listIterator();
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            laser.draw(batch);
            if(laser.isEnemy()){
                laser.setyPosition(laser.getyPosition() - laser.getMovementSpeed() * delta);
            }else {
                laser.setyPosition(laser.getyPosition() + laser.getMovementSpeed() * delta);
            }
            if (laser.getyPosition() > GameRunner.HEIGHT) {
                iterator.remove();
            }
        }
    }
}
