package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.screen.GameScreen;

public class AActor extends Actor {
    protected TextureRegion texture;
    protected float rotation;

    private Body body;

    public AActor(float x, float y, float w, float h) {
        this.texture = new TextureRegion();

        this.setBounds(x, y, w, h);
    }

    public void setTexture(Texture texture){
        this.texture = new TextureRegion( texture);
    }

    public void setTexture(TextureRegion texture){
        this.texture = texture;
    }

    public void setBody(Body body){
        body.setUserData(texture);
        this.body = body;
    }

    public void move(float x, float y){
        this.body.setLinearVelocity(x * 10f , y * 10f);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.body.getPosition().x, this.body.getPosition().y, 0, 0, getWidth(), getHeight(), 1, 1, getRotation());
    }

    public void update(float deltaTime){
    }

    @Override
    public void act(float deltaTime){
        this.update(deltaTime);
        this.setRotation(rotation);
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
        //System.out.println(this.body.getPosition().y);
    }
}
