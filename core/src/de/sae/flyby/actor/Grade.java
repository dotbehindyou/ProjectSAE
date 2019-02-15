package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.sae.flyby.MainGame;

import java.util.Random;

/**
 * Diese Klasse sind die Objekte mit dem der Spieler schießt und zwar mit Noten
 */
public class Grade extends AActor {
    //Die Noten sind Texturen und werden hier als statich final Objekte gespeichert.
    final static TextureRegion[] grades = new TextureRegion[]{
            new TextureRegion(MainGame.getTexture(), 0, 128, 32, 32),
            new TextureRegion(MainGame.getTexture(), 32, 128, 32, 32),
            new TextureRegion(MainGame.getTexture(), 64, 128, 32, 32),
            new TextureRegion(MainGame.getTexture(), 0, 160, 32, 32),
            new TextureRegion(MainGame.getTexture(), 32, 160, 32, 32),
            new TextureRegion(MainGame.getTexture(), 64, 160, 32, 32)
    };

    //Aktuelle Wert der Note
    private int currentGrade;

    /**
     * @return Aktueller Noten wert
     */
    public int getValue(){
        return currentGrade;
    }

    /**
     * @param x X Position der Note
     * @param y Y Position der Note
     * @param w Breite der Note
     * @param h Höhe der Note
     */
    public Grade(float x, float y, float w, float h){
        super(x, y, w, h);  //Hauptklasse initialisieren

        currentGrade = new Random().nextInt(6) + 1; //Random Zahlen von 1 bis 6

        //Texture initialisieren
        this.setTexture(grades[currentGrade - 1]); //Zahlenwert minus setzen damit der Index stimmt
    }

    /**
     * Wir von der Basisklasse getriggert (Siehe AActor -> act(...) Methode)
     * @param deltaTime Verbrauchte Zeit beim letzten Rendern (Nicht die ausführung)
     */
    @Override
    public void update(float deltaTime)
    {
        //Note bewegen
        this.move(-20f, 0);
    }
}
