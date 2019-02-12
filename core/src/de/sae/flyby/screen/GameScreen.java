package de.sae.flyby.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.sae.flyby.actor.AActor;
import de.sae.flyby.actor.Background;
import de.sae.flyby.actor.Enemy;
import de.sae.flyby.actor.Player;

import java.util.ArrayList;
import java.util.List;

//TODO: Pause menu, background music
public class GameScreen implements Screen {
    private Stage ingame;

    public World world;
    List<Body> bodys = new ArrayList<Body>();

    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    Camera cam;

    public GameScreen(){
        ingame = new Stage();
        Box2D.init();

        cam = ingame.getCamera();

        this.world = new World(new Vector2(98f, 0), true);

        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                System.out.println("TEST");
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        this.debugMatrix = new Matrix4(cam.combined);
        this.debugMatrix.scale(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1f);


        this.debugRenderer = new Box2DDebugRenderer();
    }

    private void addBody(AActor actor){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(actor.getX(), actor.getY()));

        Body body = world.createBody(bodyDef);
        
        PolygonShape hitbox = new PolygonShape();
        hitbox.setAsBox(actor.getWidth(), actor.getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitbox;
        fixtureDef.density = 1f;

        body.createFixture(hitbox, 0.0f);

        body.setUserData(actor);
        actor.setBody(body);

        hitbox.dispose();
        ingame.addActor(actor);
    }

    @Override
    public void show(){
        ingame.addActor(new Background());
        this.addBody(new Player());
        this.addBody(new Enemy());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, debugMatrix);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        ingame.act(Gdx.graphics.getDeltaTime());
        ingame.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        ingame.dispose();
        //menu.dispose();
    }
}
