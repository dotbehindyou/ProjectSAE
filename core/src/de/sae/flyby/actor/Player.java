package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.MainGame;
import de.sae.flyby.SAEGame;
import de.sae.flyby.screen.GameScreen;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Spieler Klasse
 */
public class Player extends AActor {
    public float speed = 10f;                   //Wie schnell kann sich der Spieler bewegen
    long lastTime = System.currentTimeMillis(); //Wird für Schuss cooldown verwendet
    long elapsedTime = 0L;                      //...
    Boolean isCooldown = false;                 //...

    public Player(){
        super((Gdx.graphics.getWidth() - 100), (Gdx.graphics.getHeight() / 2) - (50 / 2), 64, 64);

        //Texture initialisieren
        this.setTexture(new TextureRegion(MainGame.getTexture(), 448, 0, 64, 64));
    }

    /**
     * Hier wird der Spieler bewegt und seine Aktionen bearbeitet
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime){
        float x = 0, y = 0; //x und y Wert für die Bewegung
        if(Gdx.input.isKeyPressed(Input.Keys.W)){ //Wenn der Spieler W drückt
            y = 1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){ //Wenn der Spieler S drückt
            y = -1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){ //Wenn der Spieler A drückt
            x = -1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){ //Wenn der Spieler D drückt
            x = 1f * speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){ //Wenn der Spieler Leertaste drückt
            this.shoot(); //Schießen
        }
        this.move(x, y); //Spieler bewegen
    }

    /**
     * Schießen
     */
    public void shoot(){
        if(lastTime + (500) < elapsedTime) { //Sind 500ms nach dem letzen Schuss vergangen?
            lastTime = elapsedTime; //Setze
            isCooldown = false; //Schuss deaktivierung aufheben
        }
        elapsedTime = System.currentTimeMillis();
        if(!isCooldown){ //Kann der Spieler schießen?
            //Schuss Objekt (Note) in der aktuellen Welt hinzufügen
            GameScreen.getCurrentGameScreen.addActor(new Grade(this.getX() - 50f, this.getY() + (this.getHeight() / 2f), 32, 32));
            isCooldown = true; //Schießen deaktivieren
        }
    }
}
