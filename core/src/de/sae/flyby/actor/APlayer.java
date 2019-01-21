package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class APlayer extends AActor{
    private int lifePoints = 100;

    public APlayer(float x, float y, float w, float h){
        super(x, y, w, h);
    }

    @Override
    public void update(){
        this.movePlayer();
    }

    private void movePlayer(){
        Vector2 force = new Vector2(0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            force.add(-1f ,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            force.add(1f, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            force.add(0, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            force.add(0, -1f);
        }
        this.applyForce(force);
    }
}
