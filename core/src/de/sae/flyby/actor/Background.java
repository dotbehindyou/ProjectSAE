package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private Texture background1, background2;

    float xMax, xCoordBg1, xCoordBg2;
    private static int BACKGROUND_MOVE_SPEED = 100;

    public Background(){
        this.background1 = new Texture(Gdx.files.internal("core/assets/background.jpg"));
        this.background2 = new Texture(this.background1.getTextureData());

        this.xMax = 1280;
        this.xCoordBg1 = xMax*(-1);
        this.xCoordBg2 = 0;
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(this.background1, this.xCoordBg1, 0);
        batch.draw(this.background2, this.xCoordBg2, 0);
    }

    @Override
    public void act(float deltaTime){
        this.xCoordBg1 += Background.BACKGROUND_MOVE_SPEED * deltaTime;
        this.xCoordBg2 = this.xCoordBg1 + this.xMax;
        if(xCoordBg1 >= 0) {
            this.xCoordBg1 = this.xMax * (-1);
            this.xCoordBg2 = 0;
        }
    }
}
