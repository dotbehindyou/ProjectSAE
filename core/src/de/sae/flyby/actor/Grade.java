package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.sae.flyby.MainGame;

import java.util.Random;

public class Grade extends AActor {
    TextureRegion[] grades = new TextureRegion[6];
    int currentGrade;

    public Grade(float x, float y, float w, float h){
        super(x, y, w, h);

        grades[0] = new TextureRegion(MainGame.getTexture(), 0, 128, 32, 32);
        grades[1] = new TextureRegion(MainGame.getTexture(), 32, 128, 32, 32);
        grades[2] = new TextureRegion(MainGame.getTexture(), 64, 128, 32, 32);
        grades[3] = new TextureRegion(MainGame.getTexture(), 0, 160, 32, 32);
        grades[4] = new TextureRegion(MainGame.getTexture(), 32, 160, 32, 32);
        grades[5] = new TextureRegion(MainGame.getTexture(), 64, 160, 32, 32);

        currentGrade = new Random().nextInt(6);

        this.setTexture(grades[currentGrade]);
    }

    public int getValue(){
        return currentGrade;
    }

    @Override
    public void update(float deltaTime)
    {
        this.move(-20f, 0);
    }
}
