package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Screen;
import pl.oldcotage.simplegame.GameRunner;
import pl.oldcotage.simplegame.screens.textures.Background;
import pl.oldcotage.simplegame.screens.textures.Button;

public class MainMenuScreen implements Screen {

    private Background background;

    private Button buttons;
    public GameRunner game;


    public MainMenuScreen(GameRunner game) {
        this.game = game;
        background = new Background();
        buttons = new Button(this, game,game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();

        background.renderSingleBackground(game.batch);
        buttons.renderButtons();

        game.batch.end();
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
