package pl.oldcotage.simplegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import pl.oldcotage.simplegame.GameRunner;

public class MainMenuScreen implements Screen {

    Texture newGameBtnActive;
    Texture newGameBtnInactive;

    GameRunner game;


    public MainMenuScreen(GameRunner game){
        this.game = game;
        newGameBtnActive = new Texture("new_game_active_btn.png");
//        newGameBtnInactive = new Texture("new_game_inactive_btn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(newGameBtnActive,100,100);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
