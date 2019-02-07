package de.sae.flyby.screen;

import com.badlogic.gdx.Screen;

public enum GameScreens {
    MAIN_MENU{
        public Screen getScreen(Object... params){
            return new MenuScreen();
        }
    },
    GAME{
        public Screen getScreen(Object... params){
            return new GameScreen();
        }
    };
    public abstract Screen getScreen(Object... params);
}
