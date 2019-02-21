package de.sae.flyby.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.sae.flyby.MainGame;
import de.sae.flyby.SAEGame;
import de.sae.flyby.actor.Background;
import de.sae.flyby.objects.Options;

//TODO: Menu -> background music
public class MenuScreen implements Screen {
    private Stage background = new Stage(), mainStage = new Stage(), optionsStage = new Stage();
    private Boolean isMainStage = true;
    private Table startTable = new Table(), optionsTable = new Table();

    public MenuScreen(){
        this.loading();
        Gdx.input.setInputProcessor(mainStage);
    }

    private void loading(){
        de.sae.flyby.objects.Sound.playSound("menu");
        startTable.setFillParent(true);
        optionsTable.setDebug(true);

        Background bg = new Background();
        background.addActor(bg);

        mainStage.addActor(startTable);

        this.createTitle("SAE Project", startTable);

        this.createButton("Start", startTable, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                de.sae.flyby.objects.Sound.stopSound("menu");
                SAEGame.currentGame.setScreen(new GameScreen());
            }
        });/*
        this.createButton("Options", startTable, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(optionsStage);
                isMainStage = false;
            }
        });*/
        this.createButton("Schliessen", startTable, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        initOptions();
    }

    private void initOptions(){
        optionsTable.setFillParent(true);
        optionsStage.addActor(optionsTable);

        this.createTitle("SAE Project", optionsTable);

        this.createCheckbox("Sound", true, optionsTable, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Options.isSoundOn = ((CheckBox)actor).isChecked();
            }
        });
        this.createButton("Back", optionsTable, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(mainStage);
                isMainStage = true;
            }
        });
    }

    @Override
    public void show(){
    }

    private void createTitle(String text, Table table){
        TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle();
        titleStyle.font = SAEGame.getFont(45);
        titleStyle.fontColor = Color.OLIVE;

        TextField titleGame = new TextField(text, titleStyle);
        titleGame.setDisabled(true);

        table.add(titleGame).size(400, 200);
        table.row().pad(10, 0, 10, 0);
    }

    private void createButton(String text, Table table, ChangeListener listener){
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

    private void createCheckbox(String text, Boolean value, Table table, ChangeListener listener){
        CheckBox.CheckBoxStyle checkboxStyle = new CheckBox.CheckBoxStyle();
        checkboxStyle.font = MainGame.getFont(20);
        checkboxStyle.fontColor = Color.BLACK;

        CheckBox checkBox = new CheckBox(text, checkboxStyle);
        checkBox.setChecked(value);

        table.add(checkBox).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
    }

    private void createScrollpane(String text, Table table, ChangeListener listener){
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();

        ScrollPane pane = new ScrollPane(table, paneStyle);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        background.act(delta);
        background.draw();

        if (isMainStage) {
            mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            mainStage.draw();
        } else {
            optionsStage.act(delta);
            optionsStage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
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
        mainStage.dispose();
    }
}
