package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *  Hauptklasse für Player, Enemy und Boss
 */
public class AActor extends Actor {
    protected TextureRegion texture;    //Aktuelle Textureregion
    protected float rotation;           //Aktuelle Rotation

    protected Body body;                //Aktuelle Hitbox

    /**
     * @param x Position X
     * @param y Position Y
     * @param w Breite vom Objekt
     * @param h Höhe vom Objekt
     */
    public AActor(float x, float y, float w, float h) {
        this.texture = new TextureRegion(); //Initialisiere einen leeren Textureregion (Die "echte" Texture wird im Lauf initialisiert.)

        this.setBounds(x, y, w, h);
    }

    /**
     * Hier mit wird die Texture gesetzt
     * @param texture neue TextureRegion
     */
    public void setTexture(TextureRegion texture){
        this.texture = texture;
    }

    /**
     * Hier wird die Hitbox von dem Objekt World gesetzt
     * @param body aktuelles Body / Hitbox
     */
    public void setBody(Body body){
        this.body = body;
    }

    /**
     * Den Actor in Bewegung setzen
     * @param x X Einwirkung
     * @param y Y Einwirkung
     */
    public void move(float x, float y){
        this.body.setLinearVelocity(x * 10f , y * 10f);
    }

    public void update(float deltaTime){
    }

    /**
     * Diese Mehtode wird jedes mal von der Engine getriggert beim Rendern.
     * Hier wird alles was das Objekt braucht gezeichnet.
     * @param batch Batch Klasse (Sprite Buffer)
     * @param alpha Alpha Value vom Actor
     */
    @Override
    public void draw(Batch batch, float alpha){
        //Texture zeichnen mit Position und Größe
        batch.draw(texture, this.body.getPosition().x, this.body.getPosition().y, 0, 0, getWidth(), getHeight(), 1, 1, getRotation());
    }

    /**
     * Diese Methode wird jedes mal von der Engine getriggert vor dem Rendern
     * Hier werden alle Aktionen des Objektes bearbeitet
     * @param deltaTime Wie viel Zeit ist beim letzten Rendern vergangen (Zum ausgleichen)
     */
    @Override
    public void act(float deltaTime){
        //Methode Update triggern (Wird nur für Unterklassen verwendet)
        this.update(deltaTime);
        //Die aktuelle Rotation des Objektes setzten
        this.setRotation(rotation);
        //Position des Objekt mit der Hitbox gleichsetzen
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
    }
}
