package de.sae.flyby.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.sae.flyby.SAEGame;
import de.sae.flyby.actor.Background;

//TODO: Menu -> background music
public class MenuScreen implements Screen {
    private Stage main;
    //private Stage options;
    //private Stage credits;

    private Table table;

    public MenuScreen(){
        main = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(main);
        table = new Table();
    }

    @Override
    public void show(){
        table.setFillParent(true);
        table.setDebug(false);

        Background bg = new Background();
        main.addActor(bg);

        main.addActor(table);

        this.createTitle("SAE Project");

        this.createButton("Start",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SAEGame.currentGame.setScreen(new GameScreen());
            }
        });
        //TODO: Option Stage
        /*this.createButton("Options",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });*/
        this.createButton("Schliessen",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    private void createTitle(String text){
        TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle();
        titleStyle.font = SAEGame.getFont(45);
        titleStyle.fontColor = Color.OLIVE;

        TextField titleGame = new TextField(text, titleStyle);
        titleGame.setDisabled(true);

        table.add(titleGame).size(400, 200);
        table.row().pad(10, 0, 10, 0);
    }

    private void createButton(String text, ChangeListener listener){
        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();

        BitmapFont font = SAEGame.getFont(25);
        btnStyle.font = font;

        btnStyle.downFontColor = Color.RED;
        btnStyle.overFontColor = Color.GRAY;
        btnStyle.fontColor = Color.BLACK;

        TextButton btn = new TextButton(text, btnStyle);

        btn.addListener(listener);

        table.add(btn).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        main.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        main.draw();
    }

    @Override
    public void resize(int width, int height) {
        main.getViewport().update(width, height, true);
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
        main.dispose();
    }
}
