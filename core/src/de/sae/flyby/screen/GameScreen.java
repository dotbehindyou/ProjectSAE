package de.sae.flyby.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.sae.flyby.actor.Background;
import de.sae.flyby.actor.Enemy;
import de.sae.flyby.actor.Player;

//TODO: Pause menu, background music
public class GameScreen implements Screen {
    private Stage ingame;

    private Player player;

    public GameScreen(){
        ingame = new Stage();
        this.player = new Player();
    }

    @Override
    public void show(){
        ingame.addActor(new Background());
        ingame.addActor(this.player);
        ingame.addActor(new Enemy());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        ingame.act(Gdx.graphics.getDeltaTime());
        ingame.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        ingame.dispose();
        //menu.dispose();
    }
}
