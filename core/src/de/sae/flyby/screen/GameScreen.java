package de.sae.flyby.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import de.sae.flyby.SAEGame;
import de.sae.flyby.actor.*;

import java.util.ArrayList;
import java.util.Iterator;

//TODO: Pause menu, background music
public class GameScreen implements Screen {
    public static GameScreen getCurrentGameScreen;

    private Stage ingame;
    private Stage gameover;
    public World world;

    public GameScreen(){
        ingame = new Stage();
        Box2D.init();

        this.world = new World(new Vector2(98f, 0), true);

        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body fixA = contact.getFixtureA().getBody();
                Body fixB = contact.getFixtureB().getBody();

                if (fixA != null && fixB != null &&
                        fixA.getUserData() != null && fixB.getUserData() != null)
                {
                    if (fixA.getUserData().equals("right") ||
                            fixB.getUserData().equals("right")) {
                        if (fixA.getUserData() instanceof Enemy) {
                            ((Enemy) fixA.getUserData()).remove();
                            fixA.setUserData("isDead");
                        } else if (fixB.getUserData() instanceof Enemy) {
                            ((Enemy) fixB.getUserData()).remove();
                            fixB.setUserData("isDead");
                        }
                    } else if (fixA.getUserData() instanceof Player ||
                            fixB.getUserData() instanceof Player) {
                        if (fixA.getUserData() instanceof Enemy) {
                            ((Player) fixB.getUserData()).remove();
                            getCurrentGameScreen.gameOver();

                        } else if (fixB.getUserData() instanceof Enemy) {
                            ((Player) fixA.getUserData()).remove();
                            getCurrentGameScreen.gameOver();
                        }
                    } else if(fixA.getUserData() instanceof Grade  ||
                                fixB.getUserData() instanceof Grade)
                    {
                        if (fixA.getUserData().equals("left")){
                            ((Grade)fixB.getUserData()).remove();
                            fixB.setUserData("isDead");
                        } else if(fixB.getUserData().equals("left")){
                            ((Grade)fixA.getUserData()).remove();
                            fixA.setUserData("isDead");
                        }else if(fixA.getUserData() instanceof Enemy){
                            ((Enemy)fixA.getUserData()).getHitFromHit(((Grade)fixB.getUserData()).getValue());
                            ((Grade)fixB.getUserData()).remove();
                            fixB.setUserData("isDead");
                        }else if(fixB.getUserData() instanceof Enemy){
                            ((Grade)fixA.getUserData()).remove();
                            fixA.setUserData("isDead");
                            ((Enemy)fixB.getUserData()).getHitFromHit(((Grade)fixA.getUserData()).getValue());
                        }
                    }
                }
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

        getCurrentGameScreen = this;
    }


    public void clearDeadBodys() {
        Array<Body> iter = new Array<Body>();
        getCurrentGameScreen.world.getBodies(iter);
        for (Body body : iter ) {
            if (body != null && body.getUserData() != null && body.getUserData().equals("isDead")) {
                GameScreen.getCurrentGameScreen.world.destroyBody(body);
                body.setUserData(null);
            }
        }
    }

    private void generateBorder(){
        BodyDef groundBodyDef = new BodyDef();

        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(new Vector2(0, 0));
        Body top = world.createBody(groundBodyDef);
        groundBodyDef = new BodyDef();

        groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(new Vector2(0, ingame.getCamera().viewportHeight));
        Body bottom = world.createBody(groundBodyDef);

        groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(new Vector2(0, 0));
        Body left = world.createBody(groundBodyDef);
        left.setUserData("left");

        groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(new Vector2(ingame.getCamera().viewportWidth, 0));
        Body right = world.createBody(groundBodyDef);
        right.setUserData("right");

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(ingame.getCamera().viewportWidth, 1f);
        top.createFixture(groundBox, 0.0f);

        groundBox = new PolygonShape();
        groundBox.setAsBox(ingame.getCamera().viewportWidth, 1f);
        bottom.createFixture(groundBox, 0.0f);

        groundBox = new PolygonShape();
        groundBox.setAsBox(1f, ingame.getCamera().viewportHeight);
        left.createFixture(groundBox, 0.0f);

        groundBox = new PolygonShape();
        groundBox.setAsBox(1f, ingame.getCamera().viewportHeight);
        right.createFixture(groundBox, 0.0f);

        groundBox.dispose();
    }

    public void addActor(AActor actor){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(actor.getX(), actor.getY()));

        Body body = world.createBody(bodyDef);
        
        PolygonShape hitbox = new PolygonShape();
        hitbox.setAsBox(actor.getWidth() / 2, actor.getHeight() / 2, new Vector2(actor.getWidth() / 2, actor.getHeight() / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitbox;
        fixtureDef.density = 1f;

        body.createFixture(hitbox, 0.0f);

        body.setUserData((AActor)actor);
        actor.setBody(body);

        hitbox.dispose();
        ingame.addActor(actor);
    }

    Table table;
    public void gameOver(){
        gameover = new Stage();

        table = new Table();

        table.setFillParent(true);
        table.setDebug(false);

        gameover.addActor(table);

        this.createTitle("Game Over");

        this.createButton("Neustart",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SAEGame.currentGame.setScreen(new GameScreen());
            }
        });
        //TODO: Option Stage
        /*this.createButton("Options",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });*/
        this.createButton("Schliessen",new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    private void createTitle(String text){
        TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle();
        titleStyle.font = SAEGame.getFont(45);
        titleStyle.fontColor = Color.OLIVE;

        TextField titleGame = new TextField(text, titleStyle);
        titleGame.setDisabled(true);

        table.add(titleGame).size(400, 200);
        table.row().pad(10, 0, 10, 0);
    }

    private void createButton(String text, ChangeListener listener){
        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();

        BitmapFont font = SAEGame.getFont(25);
        btnStyle.font = font;

        btnStyle.downFontColor = Color.RED;
        btnStyle.overFontColor = Color.GRAY;
        btnStyle.fontColor = Color.BLACK;

        TextButton btn = new TextButton(text, btnStyle);

        btn.addListener(listener);

        table.add(btn).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
    }

    @Override
    public void show(){
        this.generateBorder();

        ingame.addActor(new Background());
        this.addActor(new Player());
        this.addActor(new Enemy());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        ingame.act(Gdx.graphics.getDeltaTime());
        ingame.draw();

        if(gameover != null){
            gameover.act(Gdx.graphics.getDeltaTime());
            gameover.draw();
        }
        clearDeadBodys();
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
