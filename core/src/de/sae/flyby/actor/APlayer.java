package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class APlayer extends AActor{

    public APlayer(float x, float y, float w, float h){
        super(x, y, w, h);
    }

    private void movePlayer(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.add(-1f, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.add(1f, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.add(0, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.add(0, -1f);
        }
    }

    @Override
    public void update(){
        this.movePlayer();
    }
}
