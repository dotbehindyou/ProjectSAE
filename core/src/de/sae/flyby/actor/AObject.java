package de.sae.flyby.actor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import sun.java2d.Disposer;

/*
    Diese Klasse ist die Hauptklasse aller Objekte für das Spiel
 */
public abstract class AObject{
    //Wird jeden Frame ausgeführt
    public abstract void render();

    public abstract void initHitbox(World world);

    public abstract Body getBody();

    //Wird ausgeführt wenn das Objekt nichtmehr verwendet wird.
    public abstract void dispose();
}