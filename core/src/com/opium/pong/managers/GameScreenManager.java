package com.opium.pong.managers;

import com.badlogic.gdx.Application;
import com.opium.pong.MyGdxGame;
import com.opium.pong.screens.AbstractScreen;
import com.opium.pong.screens.GameScreen;

import java.util.HashMap;

/**
 *  Created by opium on 27.01.18.
 */

public class GameScreenManager {

    public final MyGdxGame app;
    private HashMap<STATE, AbstractScreen> gameScreens;
    public enum STATE {
        MAIN_MAIN,
        PLAY,
        SETTINGS
    }

    public GameScreenManager(final MyGdxGame app){
        this.app = app;

        initGameScreens();
        setScreen(STATE.PLAY);
    }

    private void initGameScreens(){
        this.gameScreens = new HashMap<STATE, AbstractScreen>();
        this.gameScreens.put(STATE.PLAY, new GameScreen(app));
    }

    public void setScreen(STATE nextScreen) {
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose(){
        for(AbstractScreen screen : gameScreens.values()){
            if(screen != null){
                screen.dispose();
            }
        }
    }
}
