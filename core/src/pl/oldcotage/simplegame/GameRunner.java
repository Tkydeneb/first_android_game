package pl.oldcotage.simplegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.oldcotage.simplegame.screens.MainMenuScreen;

public class GameRunner extends Game {

	// set game window size
	public static final int WIDTH = 360;
	public static final int HEIGHT = 640;


	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
}
