package com.opium.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.opium.pong.MyGdxGame;
import com.opium.pong.managers.GameScreenManager;

/**
 *  Created by opium on 27.01.18.
 */

public abstract class AbstractScreen implements Screen {

    protected final MyGdxGame app;

    Stage stage;

    public AbstractScreen(final MyGdxGame app){
        this.app = app;
        this.stage = new Stage();
    }

    public abstract void update(float delta);

    @Override
    public void render(float delta){
        update(delta);
        Gdx.gl.glClearColor(0f,.0f,.0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose(){
        this.stage.dispose();

    }
}
