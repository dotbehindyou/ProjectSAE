package de.sae.flyby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.sae.flyby.screen.GameScreens;

public class MainGame extends Game {
    public static BitmapFont getFont(int size){
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        return generator.generateFont(parameter);
    }

    public static MainGame currentGame;

    private ScreenManager manager;

    @Override
    public void create(){
        manager = new ScreenManager();

        manager.initialize(this);
        manager.showScreen(GameScreens.MAIN_MENU);

        currentGame = this;
    }
}
