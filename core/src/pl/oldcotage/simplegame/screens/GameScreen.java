package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import pl.oldcotage.simplegame.objects.ships.EnemyShip;
import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.objects.ships.PlayerShip;
import pl.oldcotage.simplegame.objects.template.Ship;
import pl.oldcotage.simplegame.screens.textures.Background;
import pl.oldcotage.simplegame.service.LaserService;

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

    //game services
    LaserService laserService = new LaserService();



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
        playerShip = new PlayerShip((float) GameRunner.WIDTH / 2, (float)GameRunner.HEIGHT / 4,
                50, 50,
                15, 4,
                0.4f, 4, 45, 0.5f,
                playerShipTextureRegion, playerLaserTextureRegion);

        enemyShip = new EnemyShip((float)GameRunner.WIDTH / 2, (float) GameRunner.HEIGHT * 3 / 4,
                50, 50,
                2, 1, 0.3f, 5,
                50, 0.9f,
                enemyShipTextureRegion, enemyLaserTextureRegion);

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
        laserService.initLaserFire(playerShip, batch, delta);
        laserService.initLaserFire(enemyShip, batch, delta);

        playerShip.movingShip(textureAtlas);

        batch.end();
    }

    //method for moving ship and rendering



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
