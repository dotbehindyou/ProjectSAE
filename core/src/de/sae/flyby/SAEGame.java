package de.sae.flyby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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

    private World world;

    public SAEGame(){
        this.world = new World(new Vector2(4f, 0), false);
        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                System.out.println("TEST");
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public World getWorld(){
        return this.world;
    }

    @Override
    public void create(){
        SAEGame.currentGame = this;

        MenuScreen screen = new MenuScreen();
        this.setScreen(screen);
    }
}
