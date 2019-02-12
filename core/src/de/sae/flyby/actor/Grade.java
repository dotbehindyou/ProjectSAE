package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import de.sae.flyby.SAEGame;

public class Grade extends AActor {


    public Grade(float x, float y){
        super(x, y, 10, 10);

        String gradeChar = "1";

        Label.LabelStyle gradeStyle = new Label.LabelStyle();
        gradeStyle.font = SAEGame.getFont(15);
        gradeStyle.fontColor = Color.BLACK;

        Label gradeText = new Label(gradeChar, gradeStyle);
    }

    @Override
    public void update(float deltaTime)
    {

    }
}
