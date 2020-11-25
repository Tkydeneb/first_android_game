package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import java.util.LinkedList;
import java.util.ListIterator;

import pl.oldcotage.simplegame.objects.enemies.EnemyShip;
import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.weapons.Laser;
import pl.oldcotage.simplegame.objects.PlayerShip;
import pl.oldcotage.simplegame.objects.Ship;
import pl.oldcotage.simplegame.screens.textures.Background;

public class GameScreen implements Screen {
    private final GameRunner game;
    public SpriteBatch batch;

    //timing


    //graphics
    Background background;
    private TextureRegion playerShipTextureRegion, enemyShipTextureRegion, playerLaserTextureRegion, enemyLaserTextureRegion;
    private TextureAtlas textureAtlas;


    //game objects
    private Ship playerShip;
    private Ship enemyShip;
    private LinkedList<Laser> playerLaserList;
    private LinkedList<Laser> enemyLaserList;

    public GameScreen(GameRunner game) {
        batch = new SpriteBatch();
        this.game = game;


        textureAtlas = new TextureAtlas("images.atlas");
        //initialize textures
        background = new Background();
        playerShipTextureRegion = textureAtlas.findRegion("player_ship");
        enemyShipTextureRegion = textureAtlas.findRegion("enemy_ship");
        enemyShipTextureRegion.flip(false, true);

        playerLaserTextureRegion = textureAtlas.findRegion("blue_bullet");
        enemyLaserTextureRegion = textureAtlas.findRegion("pink_bullet");

        //setUp game objects
        playerShip = new PlayerShip(GameRunner.WIDTH / 2, GameRunner.HEIGHT / 4,
                50, 50,
                2, 4,
                0.4f, 4, 45, 0.5f,
                playerShipTextureRegion, playerLaserTextureRegion);

        enemyShip = new EnemyShip(GameRunner.WIDTH / 2, GameRunner.HEIGHT * 3 / 4,
                50, 50,
                2, 1, 0.3f, 5,
                50, 0.8f,
                enemyShipTextureRegion, enemyLaserTextureRegion);


        playerLaserList = new LinkedList<>();
        enemyLaserList = new LinkedList<>();
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.begin();

        playerShip.update(delta);
        enemyShip.update(delta);
        // scrolling background
        background.renderSingleBackground(batch);

        playerShip.draw(batch);
        enemyShip.draw(batch);
        //weapons

        //create new lasers
        //player lasers
        if (playerShip.canFireLaser()) {
            Laser[] lasers = playerShip.fireLasers();
            for (Laser laser : lasers) {
                playerLaserList.add(laser);
            }
        }
        //enemy lasers
        if (enemyShip.canFireLaser()) {
            Laser[] lasers = enemyShip.fireLasers();
            for (Laser laser : lasers) {
                enemyLaserList.add(laser);
            }
        }
        //draw lasers
        //remove lasers
        ListIterator<Laser> iterator = playerLaserList.listIterator();
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            laser.draw(batch);
            laser.yPosition += laser.movementSpeed * delta;
            if (laser.yPosition > GameRunner.HEIGHT) {
                iterator.remove();
            }
        }

        iterator = enemyLaserList.listIterator();
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            laser.draw(batch);
            laser.yPosition -= laser.movementSpeed * delta;
            if (laser.yPosition > GameRunner.HEIGHT) {
                iterator.remove();
            }
        }

        // move ship
        movingShip();

        batch.end();
    }

    private void movingShip() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float x = playerShip.getPosition();
            x--;
            playerShip.setPosition(x);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float x = playerShip.getPosition();
            x++;
            playerShip.setPosition(x);
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
