package de.sae.flyby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.sae.flyby.screen.MenuScreen;

public class SAEGame extends Game {
    public static BitmapFont getFont(int size){
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        return generator.generateFont(parameter);
    }
    public static SAEGame currentGame;

    public SAEGame(){
        SAEGame.currentGame = this;
    }

    @Override
    public void create(){
        SAEGame.currentGame = this;

        MenuScreen screen = new MenuScreen();
        this.setScreen(screen);
    }
}
