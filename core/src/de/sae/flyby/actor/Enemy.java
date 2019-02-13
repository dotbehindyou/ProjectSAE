package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.MassData;
import de.sae.flyby.MainGame;

import java.util.Random;

public class Enemy extends AActor {
    public int lifePoints;
    public int maxLifePoints;
    public int textureIndex;

    public TextureRegion[] textureRegions = new TextureRegion[5];
    public TextureRegion[] lowLifeTexture = new TextureRegion[5];

    public Enemy(float x, float y){
        super(x, y, 64, 64);
        //TODO Random Enemy auswählen (Bsp.: Paul = 50%, Jann = 30%, Sebbi = 20%, Chris = 5%)
        //TODO Leben (Bsp.: Paul = 10hp, Jann = 20hp, Sebbi = 35hp, Chris 50hp;

        textureRegions[0] = new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64); //Jann
        textureRegions[1] = new TextureRegion(MainGame.getTexture(), 256, 0, 64, 64); // Sebbi
        textureRegions[2] = new TextureRegion(MainGame.getTexture(), 320, 0, 64, 64); //Chris
        textureRegions[3] = new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64); //Paul
        textureRegions[4] = new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64); //Endboss

        lowLifeTexture[0] = new TextureRegion(MainGame.getTexture(), 192, 64, 64, 64); //Jann
        lowLifeTexture[1] = new TextureRegion(MainGame.getTexture(), 256, 64, 64, 64); //Sebi
        lowLifeTexture[2] = new TextureRegion(MainGame.getTexture(), 320, 64, 64, 64); //Chris
        lowLifeTexture[3] = new TextureRegion(MainGame.getTexture(), 192, 64, 64, 64); //Paul
        lowLifeTexture[4] = new TextureRegion(MainGame.getTexture(), 320, 64, 64, 64); //Endboss

        int chance = new Random().nextInt(101);

        if(chance > 70){ //Paul
            mass = chance;
            textureIndex = 3;
            lifePoints = 10;
        } else if(chance > 60){ //Jann
            mass = chance;
            textureIndex = 0;
            lifePoints = 20;
        } else if(chance > 50){ //Sebbi
            mass = chance;
            textureIndex = 1;
            lifePoints = 35;
        } else if(chance > 40){ //Chris
            mass = chance;
            textureIndex = 2;
            lifePoints = 50;
        }
        this.maxLifePoints = lifePoints;

        this.setTexture(textureRegions[textureIndex]);
    }

    float mass;
    @Override
    public void loaded(){
        this.setMass(mass);
    }

    @Override
    public void update(float deltaTime){

    }

    public void getHitFromHit(int grade){
        lifePoints -= grade;
        if(maxLifePoints / 2 > lifePoints){
            this.setTexture(lowLifeTexture[textureIndex]);
        }
        if(lifePoints < 1){
            this.remove();
            this.body.setUserData("isDead");
        }
    }
}
