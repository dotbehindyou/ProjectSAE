package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.MassData;
import de.sae.flyby.MainGame;
import de.sae.flyby.stage.StageHUD;

import java.util.Random;

public class Enemy extends AActor {
    public int lifePoints;
    public int maxLifePoints;
    public int textureIndex;

    final static TextureRegion[] textureRegions = new TextureRegion[]{
            new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64),//Jann
            new TextureRegion(MainGame.getTexture(), 256, 0, 64, 64),//Sebi
            new TextureRegion(MainGame.getTexture(), 320, 0, 64, 64),//Chris
            new TextureRegion(MainGame.getTexture(), 192, 128, 64, 64),//Paul
            new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64)//Endboss
        };
    final static TextureRegion[] lowLifeTexture = new TextureRegion[]{
        new TextureRegion(MainGame.getTexture(), 192, 64, 64, 64), //Jann
        new TextureRegion(MainGame.getTexture(), 256, 64, 64, 64), //Sebi
        new TextureRegion(MainGame.getTexture(), 320, 64, 64, 64), //Chris
        new TextureRegion(MainGame.getTexture(), 192, 192, 64, 64), //Paul
        new TextureRegion(MainGame.getTexture(), 320, 64, 64, 64), //Endboss
    };

    public Enemy(float x, float y){
        super(x, y, 64, 64);
        //TODO Random Enemy auswählen (Bsp.: Paul = 50%, Jann = 30%, Sebbi = 20%, Chris = 5%)
        //TODO Leben (Bsp.: Paul = 10hp, Jann = 20hp, Sebbi = 35hp, Chris 50hp;

        int chance = new Random().nextInt(101);

        if(chance > 70){        //Paul
            mass = chance;
            textureIndex = 3;
            lifePoints = 10;
        } else if(chance > 55){ //Jann
            mass = chance;
            textureIndex = 0;
            lifePoints = 20;
        } else if(chance > 30){ //Sebbi
            mass = chance;
            textureIndex = 1;
            lifePoints = 35;
        } else{                 //Chris
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

    public int getHitFromHit(int grade){
        lifePoints -= grade;
        if(maxLifePoints / 2 > lifePoints){
            this.setTexture(lowLifeTexture[textureIndex]);
        }
        if(lifePoints < 1){
            this.remove();
            this.body.setUserData("isDead");
            return maxLifePoints;
        }
        return 0;
    }
}
