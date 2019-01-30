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
import de.sae.flyby.SAEGame;

public class Player extends Actor {
    private Sprite sprite;
    private Texture texture;
    private int spriteIndex;

    private Vector2 position = new Vector2(250, 20);
    private Vector2 size = new Vector2(80, 80);
    private float rotate = 180f;

    private Body body;

    public Player(){
        this.spriteIndex = 0;

        this.texture = new Texture(Gdx.files.internal("core/assets/player.png"));

        this.sprite = new Sprite(texture);
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
        sprite.setPosition(this.position.x, this.position.y);
    }
}
