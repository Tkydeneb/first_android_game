package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.screens.backgrounds.Background;

public class MainMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 40;
    private static final int NEW_GAME_BUTTON_Y = 520;
    private static final int SETTINGS_BUTTON_Y = 460;
    private static final int EXIT_BUTTON_Y = 400;

    private Background background;

    private Texture newGameBtnActive;
    private Texture newGameBtnInactive;
    private Texture settingsBtnActive;
    private Texture settingsBtnInactive;
    private Texture exitBtnActive;
    private Texture exitBtnInactive;



    public GameRunner game;


    public MainMenuScreen(GameRunner game) {
        this.game = game;
        //TODO change to Texture atlas
        background = new Background();

        newGameBtnActive = new Texture("buttons/new_game_active_btn.png");
        newGameBtnInactive = new Texture("buttons/new_game_inactive_btn.png");
        settingsBtnActive = new Texture("buttons/settings_active_button.png");
        settingsBtnInactive = new Texture("buttons/settings_inactive_btn.png");
        exitBtnActive = new Texture("buttons/exit_active_button.png");
        exitBtnInactive = new Texture("buttons/exit_inactive_btn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();

        background.renderSingleBackground(game.batch);
        renderButtons();

        game.batch.end();
    }


    private void renderButtons() {
        int x = GameRunner.WIDTH / 2 - BUTTON_WIDTH / 2;
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        createButton(x, NEW_GAME_BUTTON_Y, mouseX, mouseY, newGameBtnActive, newGameBtnInactive);
        createButton(x, SETTINGS_BUTTON_Y, mouseX, mouseY, settingsBtnActive, settingsBtnInactive);
        createButton(x, EXIT_BUTTON_Y, mouseX, mouseY, exitBtnActive, exitBtnInactive);
    }

    private void createButton(int xPosition, int yPosition, int mouseX, int mouseY, Texture active, Texture inactive) {
        if (mouseX < xPosition + BUTTON_WIDTH && mouseX > xPosition && GameRunner.HEIGHT - mouseY < yPosition + BUTTON_HEIGHT && GameRunner.HEIGHT - mouseY > yPosition) {
            game.batch.draw(active, xPosition, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                startAction(yPosition);
            }
        } else {
            game.batch.draw(inactive, xPosition, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    private void startAction(int buttonYPosition) {


        if (buttonYPosition == NEW_GAME_BUTTON_Y) {
            this.dispose();
            game.setScreen(new GameScreen(game));
        }
        if (buttonYPosition == SETTINGS_BUTTON_Y) {
            //TODO change to settings screen
            game.setScreen(new MainMenuScreen(game));
        }
        if (buttonYPosition == EXIT_BUTTON_Y) {
            Gdx.app.exit();
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
