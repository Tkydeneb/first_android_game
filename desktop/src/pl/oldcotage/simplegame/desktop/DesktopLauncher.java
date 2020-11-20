package pl.oldcotage.simplegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.oldcotage.simplegame.GameRunner;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameRunner.HEIGHT;
		config.width = GameRunner.WIDTH;
		new LwjglApplication(new GameRunner(), config);
	}
}
