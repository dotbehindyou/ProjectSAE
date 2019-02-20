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
import de.sae.flyby.listener.HitListener;
import de.sae.flyby.objects.Sound;
import de.sae.flyby.stage.StageGameover;
import de.sae.flyby.stage.StageHUD;

import java.util.Random;

/**
 * Hauptspiel Screen
 */
public class GameScreen implements Screen {
    public static GameScreen getCurrentGameScreen; //Aktueller GameScreen

    private StageHUD hud;               //HUD (Punkte, Textbox, etc)
    private Stage ingame;               //Ingame (Spieler, Hintergrund, Enemys, Noten, etc)
    private StageGameover gameover;     //GameOver (Neustart, Beenden, etc)
    private int nextBossSpawn = 100;    //Ab welcher Punktzahl kommt der Gegner ?
    private long currentTimeMillis;     //Aktuelle Zeit in Millisekunden
    private long spawnTimer = 0L;       //Wann wurde zuletzt ein Enemy gespawnt
    private int spawnTicks = 500;       //Nach wieviel Millisekunden soll der nächste Gegner spawnen
    private boolean isBossLife;         //Ist der Endboss am leben
    private Boss currentBoss;           //Aktueller Endboss

    public World world;     //Hitbox Welt

    /**
     * @return HUD vom GameScreen zurück geben
     */
    public StageHUD getHUD(){
        return hud;
    }

    public GameScreen(){
        ingame = new Stage();       //Ingame wird initialisiert
        hud = new StageHUD(false);       //HUD wird initialisiert

        getCurrentGameScreen = this;    //Aktueller Spiel Screen wird gesetzt

        loading();
    }

    //Spiel im GameOver Modus setzen
    public void gameOver(){;
    //Textboxen hinzufügen
        hud.setTextbox("Leider bist du TOT und hast alle deine Punkte verloren :(", "talker");
        hud.setTextbox("Du IDIOT! Dafuer lass ich dich toeten! Ach warte, du bist ja schon tot :D", "lord");

        //"gameover" Stage deklarieren
        gameover = new StageGameover();
    }

    /**
     * Hier wird alles geladen
     */
    private void loading(){
        initWorldHitbox(); //Hitbox initialisieren

        Sound.resetSounds(); //Alle Sounds beenden
        Sound.playSound("ingame"); //Hintergrundmusik für das Spiel abspielen

        this.generateBorder(); //Weltgrenzen setzen

        ingame.addActor(new Background()); //Hintergrund initialisieren und hinzufügen
        this.addActor(new Player());       //Spieler initialisieren und hinzufügen

        currentTimeMillis = System.currentTimeMillis(); //Aktuelle Zeit setzen
    }

    /**
     * Hier wird alles für die Hitbox und Collisionabfragen initialisiert und gesetzt
     */
    private void initWorldHitbox(){
        Box2D.init();   //Box2D Engine initialisieren

        this.world = new World(new Vector2(98f, 0), true); //Welt parameter setzten (Gravitation, etc)

        //Collisionsabfrage hinzufügen
        this.world.setContactListener(new HitListener());
    }

    /**
     * Mit dieser Methode bekommt die Welt ihre "Spielgrenzen"
     */
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

    /**
     * Hier mit werden neue Actoren hinzugefüt und dem Actor wird eine neue Hitbox zugewiesen
     * @param actor Welcher Actor soll in die Welt gestzt werden
     */
    public void addActor(AActor actor){
        //AUFPASSEN BEI DER HITBOX!
        //ACTOR UND HITBOX SIND ZWEI UNTERSCHIEDLICHE OBJEKTE UND HABEN NUR EINEN THEORETISCHEN ZUSAMMENHANG
        //SIE WERDEN NUR IN DER KLASSE "AACTOR" ZUSAMMEN BEARBEITET!

//BEGIN HITBOX_INITALISATION
        BodyDef bodyDef = new BodyDef();        //Hitbox definitionen
        bodyDef.type = BodyDef.BodyType.DynamicBody;    //Actor ist dynamisch (Beweglich) in der Welt (sonst Bewegt sich der Actor nicht!)
        bodyDef.position.set(new Vector2(actor.getX(), actor.getY())); //Hitbox wird beim Spieler gesetzt

        Body body = world.createBody(bodyDef); //Hitbox Welt neue Hitbox initialisieren

        CircleShape hitbox = new CircleShape(); //Hitbox Form (Kreis)
        hitbox.setPosition(new Vector2(actor.getWidth() / 2, actor.getHeight() / 2)); //Hitbox relativ zum Body setzen
        hitbox.setRadius(actor.getWidth() / 2.5f); //Radius des Kreises setzen

        FixtureDef fixtureDef = new FixtureDef(); //FixtureDef deklarieren und initalisieren
        fixtureDef.shape = hitbox; //Form übergeben
        fixtureDef.density = 1f;

        body.createFixture(hitbox, 0.0f); //Body Fixture initialisieren

        body.setUserData(actor); //Actor als UserData - wird bei der Kolisionsabfrage "HitListener" gebraucht
        actor.setBody(body); //AActor klasse Hitbox überliefern

        hitbox.dispose(); //Nichtmehr verwendete Form "löschen" (Beinträchtigt die Hitbox nicht!)
//END HITBOX_INITALISATION

        ingame.addActor(actor); //Actor wird dem Spiel hinzugefügt
    }

    /**
     * Hiermit werden alle "tot" hitboxen gelöscht
     * Wird bei jedem Frame ausgeführt
     */
    private void clearDeadBodys() {
        Array<Body> iter = new Array<Body>(); //Hilfsvariable
        world.getBodies(iter); //Alle Hitboxen von der aktuellen Welt bekommen
        for (Body body : iter ) { //Foreach (jedes einzelne Objekt bearbeiten)
            if (body != null && body.getUserData() != null && body.getUserData().equals("isDead"))
            { //Ist das Objekt nicht null und UserData, und Body ist "tot"
                GameScreen.getCurrentGameScreen.world.destroyBody(body); //Löschen
                body.setUserData(null); //Userdata null setzen
            }
        }
    }

    /**
     * Diese Methode lässt Gegner spawnen
     */
    private void spawnEnemy(){
        if(nextBossSpawn - hud.getScorePoints() > 1 && !isBossLife)
        { //Sind die Punkte NICHT ausreichend um einen Endboss spawnen zu lassen und ist der Endboss NICHT am leben
            if(spawnTimer + (spawnTicks) < currentTimeMillis)
            { //Sind 500ms vergangen seit dem letzten Spawn
                Sound.stopSound("boss"); //Endbossmusik Stoppen
                Sound.playSound("ingame"); //Normal Musik stoppen

                spawnTimer = currentTimeMillis; //Letzter Spawn Zeit setzen

                final int MARGIN_TOP = 10; //Wie viel Pixel abstand zu oben
                final int MARGIN_BOTTOM = 10; //Wie viel Pixel abstand zu unten

                //Gegner Zufällige Y Position
                int spawnPos = new Random().nextInt((Gdx.graphics.getHeight() - MARGIN_TOP) + 1) + MARGIN_BOTTOM;

                //Gegner deklarieren und hinzufügen
                addActor(new Enemy(0, spawnPos));
            }
        }else{
            if(!isBossLife)
            {//Ist der Endboss NICHT am leben
                currentBoss = new Boss(); //Neuen Boss initialisieren
                addActor(currentBoss); //Boss hinzufügen
                Sound.stopSound("ingame"); //Normal Musik stoppen
                Sound.playSound("boss"); //Boss Musik abspielen lassen
                nextBossSpawn = 500; //Nexte Boss Spawn Punktzahl setzen
                isBossLife = true; //Boss als Leben setzen
                //Textboxen für den Boss setzen
                hud.setTextbox("OH NEIN, der BOSS hat dich bemerkt. Bitte flieh sollange du noch kannst.", "talker");
                hud.setTextbox("NEIN! Wenn du gehst werde ich dich toeten lassen!", "lord");
                hud.setTextbox("HAHAHA! Ihr könnt nicht fliehen!", "boss");
            }
            isBossLife = currentBoss.getIsAlive(); //Abgleichen ob der Endboss noch am Leben ist
        }
    }

    /**
     * Diese Methode wird automatisch von der Engine aufgerufen
     * @param delta Wie viele Millisekunden sind seit dem letzen Rendern / Zeichnen vergangen
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1); //Hintergrund mit Schwarz füllen
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT); //OpenGL COLOR BUFFER setzen

        //Hitbox-Welt berechnen
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        //Ingame berechnen aufrufen
        ingame.act(Gdx.graphics.getDeltaTime());
        //Ingame zeichnen
        ingame.draw();

        //HUD berechnen aufrufen
        hud.act(Gdx.graphics.getDeltaTime());
        //HUD zeichnen
        hud.draw();

        if(gameover != null)
        {//Wenn Gameover NICHT null ist
            //Gameover berechnen
            gameover.act(Gdx.graphics.getDeltaTime());
            //Gameover zeichnen
            gameover.draw();
        }

        //Gegner spawnen lassen
        spawnEnemy();
        //Alle "toten" Hitboxen löschen
        clearDeadBodys();
        //Jetztige Zeit setzen
        currentTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void show(){
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
