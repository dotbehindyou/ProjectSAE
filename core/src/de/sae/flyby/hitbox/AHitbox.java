package de.sae.flyby.hitbox;

import com.badlogic.gdx.physics.box2d.*;
import de.sae.flyby.FlyBy;
import de.sae.flyby.actor.AActor;
import de.sae.flyby.actor.AEnemie;
import de.sae.flyby.actor.AObject;
import de.sae.flyby.actor.APlayer;
import sun.java2d.opengl.WGLSurfaceData;

public class AHitbox implements ContactListener {
    //Todo: meteorite, alien shoots, etc
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        APlayer player;
        AEnemie enemie;

        for (AObject obj: FlyBy.wObjects) {
            if(obj.getBody().equals(bodyA) || obj.getBody().equals(bodyB)){
                if(obj instanceof APlayer){
                    player = (APlayer)obj;
                    player.getHitFromEnemie(-10);
                }else if(obj instanceof AEnemie){
                    enemie = (AEnemie)obj;
                }
            }
        }

        System.out.println("hit");
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
}
