package de.sae.flyby.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AObject extends Actor {
    private Texture texture;
    private Vector2 position;
    private Vector2 size;

    public AObject(Vector2 position, Vector2 size){
        this.texture = new Texture(Gdx.files.internal("core/assets/player.png"));
        this.position = position;
        this.size = size;

        this.setBounds(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(this.texture, this.position.x, this.position.y, this.size.x, this.size.y);
    }

    @Override
    public void act(float delta){
        this.position.x += 5f;
    }
}
