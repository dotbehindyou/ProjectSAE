package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import de.sae.flyby.MainGame;

public class Boss extends AActor {
    private int lifePoints = 320;
    private boolean isAlive = true;

    public Boss(){
        super(-64, Gdx.graphics.getWidth() / 2,256,256);

        this.setTexture(new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64));
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    @Override
    public void update(float deltaTime){
        float height = MathUtils.sin(deltaTime);
        this.move(this.getX(), height);
    }

    public void getHitFromGrade(int value){
        lifePoints -= value;
        if(lifePoints < 1){
            //TODO Explosion
            this.remove();
            this.isAlive = false;
            this.body.setUserData("isDead");
        }
    }
}
