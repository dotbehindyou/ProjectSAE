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

public class Enemy extends Actor {
    private SpriteBatch spriteBatch;
    private Sprite sprite;
    private Texture texture;

    private int spriteIndex;

    private Vector2 position = new Vector2(0, 0);
    private Vector2 size = new Vector2(80, 80);
    private float rotate = 90f;

    private Body body;

    public Enemy(){
        this.spriteIndex = 0;

        this.texture = new Texture(Gdx.files.internal("core/assets/enemy.png"));

        this.sprite = new Sprite(texture, 0,0,this.texture.getWidth(),this.texture.getHeight());
        this.sprite.setRotation(this.rotate);


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

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void act(float deltaTime){
        body.setLinearVelocity(10f, 10);

        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }
}
