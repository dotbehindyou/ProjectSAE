package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import de.sae.flyby.MainGame;
import de.sae.flyby.screen.GameScreen;

//TODO Heathbar, throw enemys
public class Boss extends Enemy {
    public float sinTime;

    private long currentTimeMillis;     //Aktuelle Zeit in Millisekunden
    private long spawnTimer = 0L;       //Wann wurde zuletzt ein Enemy gespawnt
    private int spawnTicks = 500;       //Nach wieviel Millisekunden soll der nächste Gegner spawnen

    final static float startX = 32, startY = 256;

    public Boss(){
        super(startX, startY,256,256);

        this.maxLifePoints = 250;
        this.lifePoints = this.maxLifePoints;

        this.setTexture(new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64));
    }

    public boolean getIsAlive(){
        return this.lifePoints > 0;
    }

    @Override
    public void update(float deltaTime){
        currentTimeMillis = System.currentTimeMillis();

        if(spawnTimer + spawnTicks < currentTimeMillis){
            spawnTimer = currentTimeMillis;

            GameScreen.getCurrentGameScreen.addActor(new Enemy(this.getX() + 256, this.getY() + 128));
        }

        this.move(0, (GameScreen.getCurrentGameScreen.getPlayer().getY() - this.getY() - 128) * 0.10f);
    }
}
