package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.SAEGame;

public class AActor extends Actor {
    private SpriteBatch spriteBatch;
    private Sprite sprite;
    protected Texture texture;

    private Vector2 position;
    private Vector2 size;
    private float rotate;

    private Body body;

    public AActor(Vector2 position, Vector2 size){
        this.position = position;
        this.size = size;

        //Physik
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        this.body = SAEGame.currentGame.getWorld().createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(this.size.x, this.size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);

        box.dispose();
    }

    public void setTexture(Texture texture){
        this.texture = texture;

        this.sprite = new Sprite(texture, 0,0,this.texture.getWidth(),this.texture.getHeight());
        this.sprite.setRotation(this.rotate);
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void act(float deltaTime){
        body.setLinearVelocity(10f * deltaTime, 0);

        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }
}
