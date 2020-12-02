package pl.oldcotage.simplegame.service;

import java.util.LinkedList;

import pl.oldcotage.simplegame.objects.template.Ship;

public class ShipService {

    private static Ship player;
    private static LinkedList<Ship> enemiesShips = new LinkedList();

    public Ship getPlayer() {
        return player;
    }

    public void setPlayer(Ship player) {
        this.player = player;
    }

    public LinkedList<Ship> getEnemiesShips() {
        return enemiesShips;
    }

    public void addEnemyShip(Ship enemyShip){
        enemiesShips.add(enemyShip);
    }

    public void deleteEnemyShip(Ship enemyShip){
        enemiesShips.remove(enemyShip);
    }
}
