package pl.oldcotage.simplegame.service;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.template.Ship;
import pl.oldcotage.simplegame.objects.weapons.Laser;

public class LaserService {

    private LinkedList<Laser> laserList = new LinkedList<>();
    private LinkedList<Laser> enemyLaserList = new LinkedList<>();

    private ShipService shipService;

    public LaserService(ShipService shipService){
        this.shipService = shipService;
    }

    public void  initLaserFire(SpriteBatch batch, float delta){
        //player lasers
        if (shipService.getPlayer().canFireLaser()) {
            Laser[] lasers = shipService.getPlayer().fireLasers();
            laserList.addAll(Arrays.asList(lasers));
            System.out.println("--->" + lasers.length);
        }

        if (shipService.getEnemiesShips().getFirst().canFireLaser()) {
            Laser[] lasers = shipService.getEnemiesShips().getFirst().fireLasers();
            laserList.addAll(Arrays.asList(lasers));
            System.out.println("--->" + lasers.length);
        }
        move(batch, delta);
    }

    private void move(SpriteBatch batch, float delta){
        ListIterator<Laser> iterator = laserList.listIterator();
        System.out.println(laserList.size() +  " | " + delta);
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            if(laser.isEnemy()){
                laser.setyPosition(laser.getyPosition() - laser.getMovementSpeed() * delta);
                if(hadCollision(shipService.getPlayer().getRectangle(), laser.getRectangle())){
                    iterator.remove();
                    System.out.println("bum");
                }
            }else {
                laser.setyPosition(laser.getyPosition() + laser.getMovementSpeed() * delta);
//                shipService.getEnemiesShips().forEach();
            }
            laser.draw(batch);
            if ((laser.getyPosition() > GameRunner.HEIGHT) || (laser.getyPosition() <= 0)) {
                iterator.remove();
            }
        }
    }

    private boolean hadCollision(Rectangle shipRectangle, Rectangle weaponRectangle){
        return shipRectangle.contains(weaponRectangle);
    }
}
