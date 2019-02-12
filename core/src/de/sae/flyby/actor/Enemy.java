package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.MainGame;
import de.sae.flyby.SAEGame;

public class Enemy extends AActor {

    public Enemy(){
        super(20, 20, 64, 64);
        //TODO Random Enemy auswählen (Bsp.: Paul = 50%, Jann = 30%, Sebbi = 20%, Chris = 5%)
        //TODO Leben (Bsp.: Paul = 10hp, Jann = 20hp, Sebbi = 35hp, Chris 50hp;
        this.setTexture(new TextureRegion(MainGame.getTexture(), 320, 0, 64, 64));
    }

    @Override
    public void update(float deltaTime){

    }
}
