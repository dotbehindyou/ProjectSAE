package de.sae.flyby.hitbox;

import com.badlogic.gdx.physics.box2d.*;

public class AHitbox implements ContactListener {
    //Todo: meteorite, alien shoots, etc
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        bodyA.applyForceToCenter(20, 20, true);

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
