package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class APlayer extends AActor{
    private int lifePoints = 100;

    private boolean isAlive = true;

    public APlayer(float x, float y, float w, float h){
        super(x, y, w, h);
    }

    @Override
    public void update(){
        this.movePlayer();

        //Todo: Wenn Player kein Leben mehr hat, wird "Game Over" angezeigt.

        if(this.lifePoints <= 0){
            this.isAlive = false;
        }
    }

    public void getHitFromEnemie(float life){
        this.lifePoints -= life;

        if(this.lifePoints <= 0){
            this.isAlive = false;
        }
    }

    private void movePlayer(){
        Vector2 force = new Vector2(0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            force.add(-1f ,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            force.add(1f, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            force.add(0, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            force.add(0, -1f);
        }
        this.lineareVelocity(force, 20f);
    }
}
