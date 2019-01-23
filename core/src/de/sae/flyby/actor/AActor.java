package de.sae.flyby.actor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import de.sae.flyby.FlyBy;

public class AActor extends AObject {
    protected Vector2       size;
    protected Vector2       position;
    protected float         rotation;
    protected float         torque = 0.0f;

    private SpriteBatch   batch;
    private Texture       img;
    private Sprite        sprite;

    private Body          body;


    public AActor(float x, float y, float w, float h){
        this.batch = new SpriteBatch();
        this.img = new Texture("core/assets/player.png");
        this.sprite = new Sprite(this.img);

        this.size = new Vector2(w, h);
        this.position = new Vector2(x, y);

    }

    @Override
    public void initHitbox(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(size.x / 2, size.y / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;

        this.body.createFixture(fixtureDef);
        polygonShape.dispose();
    }

    //Diese Methode wird jeden Frame ausgeführt, kann für Input verwendet werden.
    public void update(){
    }

    protected void lineareVelocity(Vector2 force, float speed){
        this.body.setLinearVelocity(force.x * speed,force.y * speed );
    }

    private void _update(){
        this.update();

        this.position.set(this.body.getPosition());
        this.body.applyTorque(this.torque, true);

        this.sprite.setPosition(this.position.x, this.position.y);
        this.sprite.setSize(this.size.x, this.size.y);
        this.sprite.setRotation(this.rotation);

    }

    @Override
    public Body getBody(){
        return this.body;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize(){
        return size;
    }

    //Hier mit wird der Actor angezeigt, und davor wird die Methode "Update" ausgeführt.
    @Override
    public void render(){
        this._update();

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose(){
        batch.dispose();
        img.dispose();
        FlyBy.wObjects.remove(this);
    }
}
