package de.sae.flyby.listener;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.sae.flyby.actor.Enemy;
import de.sae.flyby.actor.Grade;
import de.sae.flyby.actor.Player;
import de.sae.flyby.screen.GameScreen;

/**
 * Kontaktabfrage Klasse
 */
public class HitListener implements ContactListener {
    /**
     * Diese Methode wird von der Engine ausgeführt wenn eine Kollision beginnt
     * @param contact Hier sind alle wichtigen Informationen gespeichert nach einer Kollision
     */
    @Override
    public void beginContact(Contact contact) {
        Body fixA = contact.getFixtureA().getBody();    //Spiel Objekt 1
        Body fixB = contact.getFixtureB().getBody();    //Spiel Objekt 2

        //Sind die alle Objekte gefüllt?
        if (fixA != null && fixB != null &&
                fixA.getUserData() != null && fixB.getUserData() != null)
        {
            if (fixA.getUserData().equals("right") || fixB.getUserData().equals("right"))
            {//Wurde die Wand rechts berührt
                this.borderContact("right", fixA.getUserData().equals("right") ? fixB : fixA); //Bearbeite diesen Kontakt in der Methode
            }
            else if(fixA.getUserData().equals("left") || fixB.getUserData().equals("left"))
            {//Wurde die Wand links berührt
                this.borderContact("left", fixA.getUserData().equals("left") ? fixB : fixA); //Bearbeite diesen Kontakt in der Methode
            }
            else if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player)
            { //Hat / Wurde der Spieler berührt
                Boolean isFixAPlayer = fixA.getUserData() instanceof Player; //Ist FixA der Spieler? Wird für "inline If" verwendet
                this.playerContact(isFixAPlayer ? fixA : fixB, isFixAPlayer ? fixB : fixA);  //Bearbeite diesen Kontakt in der Methode
            }
            else if(fixA.getUserData() instanceof Grade || fixB.getUserData() instanceof Grade)
            { //Hat / Wurde die Note (schuss) berührt
                Boolean isFixAGrade = fixA.getUserData() instanceof Grade; //Ist FixA die Note? Wird für "inline If" verwendet
                this.gradeContact(isFixAGrade ? fixA : fixB, isFixAGrade ? fixB : fixA); //Bearbeite diesen Kontakt in der Methode
            }
        }
    }

    /**
     * Hier werden alle Kolisionen der Noten bearbeitet
     * @param grade Objekt Note
     * @param obj Das Objekt wo berührt wurde oder hat
     */
    public void gradeContact(Body grade, Body obj){
        if(obj.getUserData() instanceof Enemy) { //Ist obj ein Genger?
            GameScreen.getCurrentGameScreen.getHUD().addScore( //Füge die zurückgegebene Zahl als Punktzahl hinzu
                    ((Enemy) obj.getUserData()).getHitFromGrade( //Gebe den Notenwert mit
                            ((Grade) grade.getUserData()).getValue() //Aktueller Wert der Note
                    )
            );
            ((Actor) grade.getUserData()).remove(); //Lösche "grade"
            grade.setUserData("isDead");    //Setze "grade"s Hitbos als "tot"
        }
    }

    /**
     * Hier werden alle Kolisionen vom Spiler bearbeitet
     * @param player Spieler
     * @param obj Das Objekt was mit dem Spieler eine Kolision hatte
     */
    public void playerContact(Body player, Body obj){
        if (obj.getUserData() instanceof Enemy) { //Ist die Kolision mit einem Gegner entstanden?
            ((Player) player.getUserData()).remove(); //Dann lösche den Spieler
            player.setUserData("isDead"); //Setze die Hitbox vom Spieler als "tot"
            GameScreen.getCurrentGameScreen.gameOver(); //Führe die Methode GameOver aus
        }
    }

    /**
     * @param side Welche Wand
     * @param obj Welches Objekt hatte eine Kolision mit der Wand
     */
    public void borderContact(String side, Body obj){
        if ((side == "right" && obj.getUserData() instanceof Enemy) || //Ist "obj" ein Gegner und hat die rechte Wand berührt
                side == "left" && obj.getUserData() instanceof Grade) { //Ist "obj" eine Note und hat die linke Wand berührt
            ((Actor) obj.getUserData()).remove(); //Dan lösche "obj"
            obj.setUserData("isDead"); //Und setze die Hitbox als "tot"
        }
    }

    /**
     * Wird aufgerufen nach einer Kolision
     * @param contact Colisions Objekt
     */
    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
