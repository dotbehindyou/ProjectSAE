package de.sae.flyby.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import de.sae.flyby.FlyBy;
import de.sae.flyby.actor.Background;

import java.awt.*;

public class MenuStage extends Stage {
    private int yBtnPosition;

    public MenuStage(boolean isDebug){
        Background background = new Background();
        this.addActor(background);

        this.yBtnPosition = Gdx.graphics.getHeight() / 2;
        this.createButton("Starten");
        this.createButton("Options");
        this.createButton("Beenden");
        this.createButton("Credits");
    }

    //private void

    private void createButton(String text){
        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();

        BitmapFont font = FlyBy.getFont(20);
        btnStyle.font = font;

        btnStyle.downFontColor = Color.RED;
        btnStyle.overFontColor = Color.GRAY;
        btnStyle.fontColor = Color.BLACK;

        float btnSize = 15;
        Vector2 btnPos = new Vector2(Gdx.graphics.getWidth() / 2, yBtnPosition);

        yBtnPosition -= btnSize + btnSize;

        TextButton btn = new TextButton(text, btnStyle);
        btn.setPosition(btnPos.x, btnPos.y);

        this.addActor(btn);
    }
}
