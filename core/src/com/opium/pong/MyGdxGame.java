package com.opium.pong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.opium.pong.managers.GameScreenManager;

public class MyGdxGame extends Game {

	public static String APP_TITLE = "Ping Ping Pong";
	public static double APP_VERSION = 0.1;
    public static int APP_DESKTOP_WIDTH = 720;
    public static int APP_DESKTOP_HEIGHT = 420;
    public static int APP_FPS = 60;

    //Game Vars
    public static int V_WIDTH = 720;
    public static int V_HEIGHT = 420;

    // Mangers
    public AssetManager assets;
    public GameScreenManager gsm;

    public SpriteBatch batch;
	public ShapeRenderer shapeBatch;
	
	@Override
	public void create () {
	    shapeBatch = new ShapeRenderer();
        batch = new SpriteBatch();

        assets = new AssetManager();
        gsm = new GameScreenManager(this);
    }

	@Override
	public void render () {
        super.render();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
	}
	
	@Override
	public void dispose () {
	    super.dispose();
		batch.dispose();
		shapeBatch.dispose();
		assets.dispose();
	}
}
