package de.sae.flyby.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.sae.flyby.MainGame;
import de.sae.flyby.SAEGame;
import de.sae.flyby.actor.AActor;
import de.sae.flyby.objects.Sound;
import de.sae.flyby.screen.GameScreen;

public class StageGameover extends Stage {

    private Table table;

    public StageGameover(){
        table = new Table();

        table.setFillParent(true);
        table.setDebug(false);

        this.addActor(table);

        this.createTitle();

        this.createButton("Neustart",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound.stopSound("gameover");
                SAEGame.currentGame.setScreen(new GameScreen());
            }
        });
        this.createButton("Schliessen",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound.stopSound("gameover");
                Gdx.app.exit();
            }
        });
    }

    private void createTitle(){
        Image actor = new Image(new TextureRegion(MainGame.getTexture(), 0, 192, 128, 64));

        table.add(actor).size(400, 200);
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
}
