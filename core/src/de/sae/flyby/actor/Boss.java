package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import de.sae.flyby.MainGame;

//TODO Heathbar, throw enemys
public class Boss extends Enemy {
    public Boss(){
        super(-64, Gdx.graphics.getWidth() / 2,256,256);

        this.maxLifePoints = 250;
        this.lifePoints = this.maxLifePoints;

        this.setTexture(new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64));
    }

    public boolean getIsAlive(){
        return this.lifePoints > 0;
    }

    @Override
    public void update(float deltaTime){
        float height = (float)Math.sin(deltaTime)*10;
        this.move(0, height);
    }
}
