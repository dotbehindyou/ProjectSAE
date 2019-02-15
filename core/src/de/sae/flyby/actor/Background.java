package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Klasse für den Spiele Hintergrund
 */
public class Background extends Actor {
    private Texture background1, background2;//Texturen für den Hintergrund

    float xMax, xCoordBg1, xCoordBg2;
    private static int BACKGROUND_MOVE_SPEED = 200; //Geschwindkeit vom Hintergrund

    public Background(){
        //background 1 initialisieren
        this.background1 = new Texture(Gdx.files.internal("core/assets/img/background.jpg"));
        //Background 2 initialisieren und die Texturen von Background 1 nehmen
        this.background2 = new Texture(this.background1.getTextureData());

        this.xMax = 1280; //Maximaler abstand der Hintergründe
        this.xCoordBg1 = xMax*(-1); //X Position vom Hintergrund 1
        this.xCoordBg2 = 0; //X Position vom Hintergrund 2
    }

    /**
     * Hintergrund zeichnen
     * Wird automatisch von der Engine aufgerufen
     * @param batch Sprite Buffer
     * @param alpha Alpha
     */
    @Override
    public void draw(Batch batch, float alpha){
        //ES WIRD ZWEIMAL DIE GLEICHE TEXTURE VERWENDET UM KEINE "LÖCHER" IM HINTERGRUND ZU HABEN
        //  DA SICH DIESE ZWEI HINTERGRÜNDE DIE POSITION TAUSCHEN
        batch.draw(this.background1, this.xCoordBg1, 0, background1.getWidth(), Gdx.graphics.getHeight());
        batch.draw(this.background2, this.xCoordBg2, 0, background2.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Hintergrund berechnen
     * @param deltaTime Rendertime
     */
    @Override
    public void act(float deltaTime){
        this.xCoordBg1 += Background.BACKGROUND_MOVE_SPEED * deltaTime; //Hintergrund 1 Postion X berechnen
        this.xCoordBg2 = this.xCoordBg1 + this.xMax; //Hintergrund 2 Postion X berechnen
        if(xCoordBg1 >= 0)
        { //Wenn Hintergrund 1 durchgelaufen ist
            //Hintergründe tauschen
            this.xCoordBg1 = this.xMax * (-1);
            this.xCoordBg2 = 0;
        }
    }
}
