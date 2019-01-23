package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import sun.java2d.pipe.hw.AccelDeviceEventNotifier;

//TODO: Enemies (aliens, meteorites, etc)
public class AEnemie extends AActor {
    private float speed = (float)Math.random() * 10f + 2f;
    public AEnemie(){
        super(0, 0, 0, 0);

        float size = (float)Math.random() * 25f + 10f;
        float startY = (float)Math.random() * (Gdx.graphics.getHeight() - 10f) + 10;

        this.position.y = startY;
        this.size = new Vector2(size, size);
    }

    public AEnemie(float x, float y, float w, float h){
        super(x, y, w, h);

        this.rotation = -90f;
    }

    @Override
    public void update(){
        if(Gdx.graphics.getWidth() >= this.position.x){
            this.lineareVelocity(new Vector2(10f, 0), this.speed);
        }
    }
}
