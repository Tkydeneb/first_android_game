package pl.oldcotage.simplegame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import java.util.LinkedList;
import java.util.ListIterator;

public class GameScreen implements Screen {
    private final GameRunner game;
    public SpriteBatch batch;

    //timing
    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    //graphics
    private TextureRegion playerShipTextureRegion, enemyShipTextureRegion, playerLaserTextureRegion, enemyLaserTextureRegion;
    private TextureAtlas textureAtlas;

    private TextureRegion[] backgrounds;

    //game objects
    private Ship playerShip;
    private Ship enemyShip;
    private LinkedList<Laser> playerLaserList;
    private LinkedList<Laser> enemyLaserList;

    public GameScreen(GameRunner game) {
        batch = new SpriteBatch();
        this.game = game;
        //Set up the texture atlas
        textureAtlas = new TextureAtlas("images.atlas");

        //Texture Array for parallax background scrolling in four layers.
        //TODO change images for parallax effects
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("bg");
        backgrounds[1] = textureAtlas.findRegion("bg");
        backgrounds[2] = textureAtlas.findRegion("bg");
        backgrounds[3] = textureAtlas.findRegion("bg");

        //initialize textures
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

        //Set scrolling speed for all four background layers.
        backgroundMaxScrollingSpeed = (float) GameRunner.HEIGHT / 4;
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
        renderBackground(delta);

        // enemies

        //player
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
        //remove pld lasers
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

        //explosions

        batch.end();
    }

    private void renderBackground(float delta) {

        backgroundOffsets[0] += delta * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += delta * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += delta * backgroundMaxScrollingSpeed / 2;
        backgroundOffsets[3] += delta * backgroundMaxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (backgroundOffsets[layer] > GameRunner.HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0,
                    -backgroundOffsets[layer],
                    GameRunner.WIDTH,
                    GameRunner.HEIGHT);
            batch.draw(backgrounds[layer], 0,
                    -backgroundOffsets[layer] + GameRunner.HEIGHT,
                    GameRunner.WIDTH,
                    GameRunner.HEIGHT);
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
