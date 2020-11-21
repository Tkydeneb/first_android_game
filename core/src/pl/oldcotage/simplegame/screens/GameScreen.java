package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.Ship;

public class GameScreen implements Screen {
    private final GameRunner game;
    //timing
    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    //graphics
    private  TextureRegion playerShipTextureRegion,enemyShipTextureRegion, playerLaserTextureRegion, getEnemyLaserTextureRegion;
    private TextureAtlas textureAtlas;

    private TextureRegion[] backgrounds;
    //game objects
    private Ship playerShip;
    private Ship enemyShip;


    public GameScreen(GameRunner game) {
        this.game = game;
        //Set up the texture atlas
        textureAtlas = new TextureAtlas("images.atlas");

        //Texture Array for parallax background scrolling in four layers.
        //TODO change images for paralac effects
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("bg");
        backgrounds[1] = textureAtlas.findRegion("bg");
        backgrounds[2] = textureAtlas.findRegion("bg");
        backgrounds[3] = textureAtlas.findRegion("bg");

        //initialize textures
        playerShipTextureRegion = textureAtlas.findRegion("player_ship");
        enemyShipTextureRegion = textureAtlas.findRegion("enemy_ship");
        enemyShipTextureRegion.flip(false,true);

        playerLaserTextureRegion = textureAtlas.findRegion("blue_bullet");
        getEnemyLaserTextureRegion = textureAtlas.findRegion("pink_bullet");

        //setUp game objects
        playerShip = new Ship(2,3,50,50,
                GameRunner.WIDTH/2, GameRunner.HEIGHT/4,
                playerShipTextureRegion);

        enemyShip = new Ship(2,3,50,50,
                GameRunner.WIDTH/2, GameRunner.HEIGHT*3/4,
                enemyShipTextureRegion);

        //Set scrolling speed for all four background layers.
        backgroundMaxScrollingSpeed = (float) GameRunner.HEIGHT / 4;
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        // scrolling background
        renderBackground(delta);

        // enemies

        //player
        playerShip.draw(game.batch);
        enemyShip.draw(game.batch);
        //weapons

        //explosions

        game.batch.end();
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
            game.batch.draw(backgrounds[layer], 0,
                    -backgroundOffsets[layer],
                    GameRunner.WIDTH,
                    GameRunner.HEIGHT);
            game.batch.draw(backgrounds[layer], 0,
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
