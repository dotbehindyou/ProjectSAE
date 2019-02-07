package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.SAEGame;

public class Player extends AActor {
    public float speed = 10f;

    public Player(){
        super((Gdx.graphics.getWidth() - 100), (Gdx.graphics.getHeight() / 2) - (50 / 2), 50, 50);

        this.rotation = 90f;
        this.setTexture(new Texture(Gdx.files.internal("core/assets/player.png")));
    }

    @Override
    public void update(float deltaTime){
        float x = 0, y = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            y = 1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            y = -1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            x = -1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            x = 1f * speed;
        }
        this.move(x, y);
    }
}
