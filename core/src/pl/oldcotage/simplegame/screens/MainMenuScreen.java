package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import pl.oldcotage.simplegame.GameRunner;

public class MainMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 40;
    private static final int NEW_GAME_BUTTON_Y = 520;
    private static final int SETTINGS_BUTTON_Y = 460;
    private static final int EXIT_BUTTON_Y = 400;


    private float[] backgroundOffsets = {0, 0, 0, 0};
    private float backgroundMaxScrollingSpeed;

    private Texture[] backgrounds;
    private Texture newGameBtnActive;
    private Texture newGameBtnInactive;
    private Texture settingsBtnActive;
    private Texture settingsBtnInactive;
    private Texture exitBtnActive;
    private Texture exitBtnInactive;

    GameRunner game;


    public MainMenuScreen(GameRunner game) {
        this.game = game;
        newGameBtnActive = new Texture("new_game_active_btn.png");
        newGameBtnInactive = new Texture("new_game_inactive_btn.png");
        settingsBtnActive = new Texture("settings_active_button.png");
        settingsBtnInactive = new Texture("settings_inactive_btn.png");
        exitBtnActive = new Texture("exit_active_button.png");
        exitBtnInactive = new Texture("exit_inactive_btn.png");


        //Texture Array for parallax background scrolling in four layers.
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("space_background.gif");
        backgrounds[1] = new Texture("space_background.gif");
        backgrounds[2] = new Texture("space_background.gif");
        backgrounds[3] = new Texture("space_background.gif");

        //Set scrolling speed for all four background layers.
        backgroundMaxScrollingSpeed = (float) GameRunner.HEIGHT / 4;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        renderBackground(delta);
        renderButtons();

        game.batch.end();
    }


    private void renderButtons(){
        int x = GameRunner.WIDTH / 2 - BUTTON_WIDTH / 2;
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        createButton(x, NEW_GAME_BUTTON_Y, mouseX, mouseY, newGameBtnActive,newGameBtnInactive);
        createButton(x, SETTINGS_BUTTON_Y, mouseX, mouseY, settingsBtnActive,settingsBtnInactive);
        createButton(x, EXIT_BUTTON_Y, mouseX, mouseY,exitBtnActive,exitBtnInactive);
    }

    private void createButton(int xPosition, int yPosition, int mouseX, int mouseY, Texture active, Texture inactive){
        if (mouseX < xPosition + BUTTON_WIDTH && mouseX > xPosition && GameRunner.HEIGHT - mouseY < yPosition + BUTTON_HEIGHT && GameRunner.HEIGHT - mouseY > yPosition) {
            game.batch.draw(active, xPosition, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                startAction(yPosition);
            }
        } else {
            game.batch.draw(inactive, xPosition, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    private void startAction(int buttonYPosition) {



        if (buttonYPosition == NEW_GAME_BUTTON_Y){
            this.dispose();
            //TODO change to game screen
            game.setScreen(new MainMenuScreen(game));
        }
        if (buttonYPosition == SETTINGS_BUTTON_Y){
            //TODO change to settings screen
            game.setScreen(new MainMenuScreen(game));
        }
        if (buttonYPosition == EXIT_BUTTON_Y){
            Gdx.app.exit();
        }
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
