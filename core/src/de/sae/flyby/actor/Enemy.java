package de.sae.flyby.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.sae.flyby.MainGame;

import java.util.Random;

public class Enemy extends AActor {
    public int lifePoints;

    public TextureRegion[] textureRegions = new TextureRegion[5];

    public Enemy(float x, float y){
        super(x, y, 64, 64);
        //TODO Random Enemy auswählen (Bsp.: Paul = 50%, Jann = 30%, Sebbi = 20%, Chris = 5%)
        //TODO Leben (Bsp.: Paul = 10hp, Jann = 20hp, Sebbi = 35hp, Chris 50hp;

        textureRegions[0] = new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64);
        textureRegions[1] = new TextureRegion(MainGame.getTexture(), 256, 0, 64, 64);
        textureRegions[2] = new TextureRegion(MainGame.getTexture(), 320, 0, 64, 64);
        textureRegions[3] = new TextureRegion(MainGame.getTexture(), 192, 0, 64, 64);
        textureRegions[4] = new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64);

        int chance = new Random().nextInt(101);
        int texture = 0;

        System.out.println((chance / 100f));

        if(chance > 70){ //Paul
            texture = 3;
            lifePoints = 10;
        } else if(chance > 60){ //Jann
            texture = 0;
            lifePoints = 20;
        } else if(chance > 50){ //Sebbi
            texture = 1;
            lifePoints = 35;
        } else if(chance > 40){ //Chris
            texture = 2;
            lifePoints = 50;
        }  else if(chance > 25){ //Endboss
            texture = 4;
            lifePoints = 100;
        }

        this.setTexture(textureRegions[texture]);
    }

    @Override
    public void update(float deltaTime){

    }

    public void getHitFromHit(int grade){
        lifePoints -= grade;
        if(lifePoints < 1){
            this.remove();
            this.body.setUserData("isDead");
        }
    }
}
