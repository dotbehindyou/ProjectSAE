package de.sae.flyby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import de.sae.flyby.screen.GameScreens;

public class ScreenManager {
    private static ScreenManager instance;

    private Game game;

    public ScreenManager(){
    }

    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game){
        this.game = game;
    }

    public void showScreen(GameScreens screen, Object... params){
        Screen currentScreen = game.getScreen();

        Screen newScreen = screen.getScreen(params);

        game.setScreen(newScreen);

        if(currentScreen != null)
            currentScreen.dispose();
    }
}
