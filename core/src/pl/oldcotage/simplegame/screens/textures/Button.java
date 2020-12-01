package pl.oldcotage.simplegame.screens.textures;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.screens.GameScreen;
import pl.oldcotage.simplegame.screens.MainMenuScreen;

/**
 * Class for setting buttons textures and rendering
 */
public class Button {

    //Buttons sizes end vertical positions
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 40;
    private static final int NEW_GAME_BUTTON_Y = 520;
    private static final int SETTINGS_BUTTON_Y = 460;
    private static final int EXIT_BUTTON_Y = 400;

    private final int X = GameRunner.WIDTH / 2 - BUTTON_WIDTH / 2;

    private TextureAtlas textureAtlas;
    private TextureRegion newGameBtnActive;
    private TextureRegion newGameBtnInactive;
    private TextureRegion settingsBtnActive;
    private TextureRegion settingsBtnInactive;
    private TextureRegion exitBtnActive;
    private TextureRegion exitBtnInactive;

    private Screen screen;
    private GameRunner gameRunner;
    private Game game;
    private int mouseX;
    private int mouseY;


    public Button(Screen screen, Game game,GameRunner gameRunner) {
        this.screen = screen;
        this.game = game;
        this.gameRunner = gameRunner;
    }

    //initialize texture atlas
    private void setTextureAtlas() {
        textureAtlas = new TextureAtlas("utility/buttons/buttons.atlas");
    }

    private void setButtonsTexture(){
        setTextureAtlas();

        newGameBtnActive = textureAtlas.findRegion("new_game_white");
        newGameBtnInactive = textureAtlas.findRegion("new_game_red");
        settingsBtnActive = textureAtlas.findRegion("options_white");
        settingsBtnInactive = textureAtlas.findRegion("options_red");
        exitBtnActive = textureAtlas.findRegion("exit_white");
        exitBtnInactive = textureAtlas.findRegion("exit_red");
    }

    public void renderButtons() {
        setButtonsTexture();

        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();

        createButton(NEW_GAME_BUTTON_Y, newGameBtnActive, newGameBtnInactive);
        createButton(SETTINGS_BUTTON_Y, settingsBtnActive, settingsBtnInactive);
        createButton(EXIT_BUTTON_Y, exitBtnActive, exitBtnInactive);
    }

    private void createButton(int yPosition, TextureRegion active, TextureRegion inactive) {
        if (mouseX < X + BUTTON_WIDTH && mouseX > X && GameRunner.HEIGHT - mouseY < yPosition + BUTTON_HEIGHT && GameRunner.HEIGHT - mouseY > yPosition) {
            gameRunner.batch.draw(active, X, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                startAction(yPosition);
            }
        } else {
            gameRunner.batch.draw(inactive, X, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }
    private void startAction(int buttonYPosition) {
        if (buttonYPosition == NEW_GAME_BUTTON_Y) {
            screen.dispose();
            game.setScreen(new GameScreen(gameRunner));
        }
        if (buttonYPosition == SETTINGS_BUTTON_Y) {
            //TODO change to settings screen
            game.setScreen(new MainMenuScreen(gameRunner));
        }
        if (buttonYPosition == EXIT_BUTTON_Y) {
            Gdx.app.exit();
        }
    }
}
