package com.opium.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.opium.pong.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = MyGdxGame.APP_TITLE + " v" + MyGdxGame.APP_VERSION;
		config.width = MyGdxGame.APP_DESKTOP_WIDTH;
		config.height = MyGdxGame.APP_DESKTOP_HEIGHT;
		config.backgroundFPS = MyGdxGame.APP_FPS;
		config.foregroundFPS = MyGdxGame.APP_FPS;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
