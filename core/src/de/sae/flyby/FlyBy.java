package de.sae.flyby;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.sae.flyby.actor.AActor;
import de.sae.flyby.actor.AObject;
import de.sae.flyby.actor.APlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlyBy extends ApplicationAdapter {
	Collection<AObject> wObjects;

	World mWorld;
	
	@Override
	public void create () {
		wObjects = new ArrayList<AObject>();
		wObjects.add(new APlayer(Gdx.graphics.getWidth() / 2 - 20,Gdx.graphics.getWidth() / 2- 20,20,20));

		mWorld = new World(new Vector2(98f, 0), true);

		for (AObject obj: wObjects){
			if(obj instanceof AActor){
				((AActor) obj).initHitbox(mWorld);
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(150, 150, 150, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mWorld.step(Gdx.graphics.getDeltaTime(), 6, 2);

		for (AObject obj: wObjects) {
			obj.render();
		}
	}
	
	@Override
	public void dispose () {
		mWorld.dispose();

		for (AObject obj: wObjects) {
			obj.dispose();
		}
	}
}
