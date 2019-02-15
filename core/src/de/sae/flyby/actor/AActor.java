package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AActor extends Actor {
    public final static short CATEGORY_PLAYER = 0x0001;
    public final static short CATEGORY_MONSTER = 0x0002;
    public final static short CATEGORY_SCENERY = 0x0004;

    final float PIXELS_TO_METERS = 100f;

    Matrix4 debugMatrix;

    protected TextureRegion texture;
    protected float rotation;

    protected Body body;

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
        this.body = body;
    }

    public void move(float x, float y){
        this.body.setLinearVelocity(x * 10f , y * 10f);
    }

    public void setMass(float mass){
        MassData enmass = new MassData();

        this.body.setMassData(enmass);
    }

    public void loaded(){

    }

    public void update(float deltaTime){
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.body.getPosition().x, this.body.getPosition().y, 0, 0, getWidth(), getHeight(), 1, 1, getRotation());
    }

    @Override
    public void act(float deltaTime){
        this.update(deltaTime);
        this.setRotation(rotation);
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
    }
}
