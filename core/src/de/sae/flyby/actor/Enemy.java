package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.MassData;
import de.sae.flyby.MainGame;
import de.sae.flyby.stage.StageHUD;

import java.util.Random;

/**
 * Diese Klasse wird für die Gegner im Spiel verwendet.
 */
public class Enemy extends AActor {

    //Hier sind alle TextureRegions, für die Gegner gespeichert (auser vom Boss)
    //Damit nicht bei jedem Spawn die TextureRegion generiert wird, ist der Array "final".
    final static TextureRegion[] textureRegions = new TextureRegion[]{
            new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64),   //Jann
            new TextureRegion(MainGame.getTexture(), 256, 0, 64, 64),   //Sebi
            new TextureRegion(MainGame.getTexture(), 320, 0, 64, 64),   //Chris
            new TextureRegion(MainGame.getTexture(), 192, 128, 64, 64), //Paul
        };

    //Hier sind alle TextureRegions, für die Gegner die wenig Leben haben (auser Boss!)
    //*Siehe oben zweiter Kommentar von "textureRegions"
    final static TextureRegion[] lowLifeTexture = new TextureRegion[]{
        new TextureRegion(MainGame.getTexture(), 192, 64, 64, 64),      //Jann
        new TextureRegion(MainGame.getTexture(), 256, 64, 64, 64),      //Sebi
        new TextureRegion(MainGame.getTexture(), 320, 64, 64, 64),      //Chris
        new TextureRegion(MainGame.getTexture(), 192, 192, 64, 64),     //Paul
    };

    public int lifePoints;              //Lebenspunkte
    public int maxLifePoints;           //Maximale Lebenspunkte. Wird nie verändert nach Initialisierung!
    public int textureIndex;            //Texture Index für "textureRegions" und "lowLifeTexture"

    /**
     * @param x X Position des Gegners
     * @param y Y Position des Gegners
     */
    public Enemy(float x, float y){
        super(x, y, 64, 64);    //Basisklasse wird initialisiert

        int chance = new Random().nextInt(101);

        if(chance > 70){            //Paul
            textureIndex = 3;   //TextureIndex wird gesetzt
            lifePoints = 10;    //Lebenspunkte des Gegners
        } else if(chance > 55){     //Jann
            textureIndex = 0;
            lifePoints = 20;
        } else if(chance > 30){     //Sebbi
            textureIndex = 1;
            lifePoints = 35;
        } else{                     //Chris
            textureIndex = 2;
            lifePoints = 50;
        }
        this.maxLifePoints = lifePoints;    //Maximale Lebenspunkte wird gesetzt

        this.setTexture(textureRegions[textureIndex]); //Texture wird initialisiert
    }

    public Enemy(float x, float y, float w, float h){
        super(x, y, w, h);
    }

    /**
     * @param grade Die Größer der Note (1 bis 6)
     * @return Wenn der Gegner stirbt, wird das Maximale Leben als Punkte zurück gegeben
     */
    public int getHitFromGrade(int grade){
        lifePoints -= grade; //Lebenspunkte abziehen
        if(maxLifePoints / 2 > lifePoints){ //Wenn Gegner unter der Hälfte seines Maximal Lebens ist, lowLifeTexture als Texture setzen
            this.setTexture(lowLifeTexture[textureIndex]);
        }
        if(lifePoints < 1){ //Wenn Gegner keine Lebenspunkte hat
            this.remove(); //Als Actor löschen
            this.body.setUserData("isDead"); //Hitbox als "tot" setzen
            return maxLifePoints; //Maximales Leben als Punkte zurück geben
        }
        return 0; //Wenn er nocht Lebt, keine Punkte zurückgeben
    }
}
