package de.sae.flyby.actor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import javax.xml.soap.Text;

public class AActor extends AObject{
    protected Vector2       size;
    protected Vector2       position;

    private SpriteBatch   batch;
    private Texture       img;
    private Sprite        sprite;

    private Body          body;

    public AActor(float x, float y, float w, float h){
        this.batch = new SpriteBatch();
        this.img = new Texture("core/assets/badlogic.jpg");
        this.sprite = new Sprite(this.img);

        this.size = new Vector2(w, h);
        this.position = new Vector2(x, y);

    }

    public AActor(Vector2 position, Vector2 size){
        this.size = size;
        this.position = position;
    }

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

        Fixture fixture = this.body.createFixture(fixtureDef);

        polygonShape.dispose();
    }

    //Diese Methode wird jeden Frame ausgeführt, kann für Input verwendet werden.
    public void update(){
    }

    private void _update(){
        this.update();

        position.set(body.getPosition());

        sprite.setPosition(position.x, position.y);
        sprite.setSize(size.x, size.y);
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
    }
}
