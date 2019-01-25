package de.sae.flyby;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
//import de.sae.flyby.stage.GameStage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.sae.flyby.stage.GameStage;
import de.sae.flyby.stage.MenuStage;

public class FlyBy extends ApplicationAdapter {
	public static BitmapFont getFont(int size){
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

		return generator.generateFont(parameter);
	}

	private MenuStage menuStage;
	private GameStage gameStage;

	@Override
	public void create () {
		menuStage = new MenuStage(true);

		Gdx.input.setInputProcessor(menuStage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		menuStage.act(Gdx.graphics.getDeltaTime());
		menuStage.draw();
	}

	@Override
	public void resize(int width, int height){

	}

	@Override
	public void pause(){

	}

	@Override
	public void resume(){

	}
	
	@Override
	public void dispose () {
	}
}
