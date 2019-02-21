package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import de.sae.flyby.MainGame;

public class Lifebar extends Actor {
    private int maxLife, currentLife;
    private Image lifebarImg, lowlifeImg;

    public Lifebar(float x, float y, float w, float h, int lifepoints){
        this.maxLife = lifepoints;
        this.setPosition(x, y);
        this.setSize(w, h);
        lifebarImg = new Image(new TextureRegion(MainGame.getTexture(), 64, 256, 64,64));
        lowlifeImg = new Image(new TextureRegion(MainGame.getTexture(), 128, 256, 64,64));

        lifebarImg.setSize(w, h);
        lowlifeImg.setSize(0, h);
    }

    public void addLife(int life){
        currentLife += life;
    }

    public void setLife(int life){
        currentLife = life;
    }

    @Override
    public void draw(Batch batch, float alpha){
        lifebarImg.draw(batch, alpha);
        lowlifeImg.draw(batch, alpha);
    }

    @Override
    public void act(float delta){
        lifebarImg.setWidth(this.getWidth() * ((float)currentLife / (float)maxLife));
        lowlifeImg.setX(lifebarImg.getWidth() - this.getWidth());
        lowlifeImg.setWidth(lifebarImg.getWidth() - this.getWidth());
    }
}