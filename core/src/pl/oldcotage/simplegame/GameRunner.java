package pl.oldcotage.simplegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.oldcotage.simplegame.screens.MainMenuScreen;

public class GameRunner extends Game {
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
