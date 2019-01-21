package de.sae.flyby;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import de.sae.flyby.actor.AObject;
import de.sae.flyby.actor.APlayer;
import de.sae.flyby.hitbox.AHitbox;

import java.util.ArrayList;
import java.util.Collection;

public class FlyBy extends ApplicationAdapter {
	Collection<AObject> wObjects;

	World mWorld;

	Camera mCamera;
	
	@Override
	public void create () {
		wObjects = new ArrayList<AObject>();

		wObjects.add(new APlayer(Gdx.graphics.getWidth() / 2 - 20,Gdx.graphics.getWidth() / 2- 20,20,20));

		mWorld = new World(new Vector2(0, 0), true);
		mWorld.setContactListener(new AHitbox());

		mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (AObject obj: wObjects){
			obj.initHitbox(mWorld);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mCamera.update();

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
