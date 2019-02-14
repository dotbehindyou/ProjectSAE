package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.sae.flyby.MainGame;

import java.util.ArrayList;
import java.util.List;

public class Textbox extends Actor {
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sound/text.mp3"));
    private Label text;
    private TextureRegion icon;
    private TextureRegion[] bg = new TextureRegion[3];

    private List<String> mainText;
    private int index;
    private String currentMainText;
    private String currentText = "";

    public Textbox(String messsage, TextureRegion icon){
        bg[0] = new TextureRegion(MainGame.getTexture(), 128, 64, 64, 64);
        bg[1] = new TextureRegion(MainGame.getTexture(), 128, 128, 64, 64);
        bg[2] = new TextureRegion(MainGame.getTexture(), 128, 192, 64, 64);

        this.icon = icon;

        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = MainGame.getFont(20);
        textStyle.fontColor = Color.WHITE;

        mainText = new ArrayList<String>();
        for(String item : messsage.split("!.?")){
            mainText.add(item);
        }
        currentMainText = mainText.get(0);

        text = new Label("...", textStyle);
        text.setPosition(74, 10);
        text.setSize(Gdx.graphics.getWidth(), 50);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(bg[0], 0, 0, 64, 64);
        batch.draw(bg[1], 64, 0, Gdx.graphics.getWidth() - 128, 64);
        batch.draw(bg[2], Gdx.graphics.getWidth() - 64, 0, 64, 64);
        batch.draw(icon, 10, 10, 128, 128);
        text.draw(batch, alpha);
    }

    public Boolean isRemoved(){
        return isFin;
    }

    long lastTime = System.currentTimeMillis();
    long elapsedTime = 0L;
    boolean isFin = false;
    long soundId = -1;
    @Override
    public void act(float deltaTime){
        if(soundId == -1){
            soundId = sound.loop();
        }
        if(currentText.length() < currentMainText.length()) {
            if (lastTime + (50) < elapsedTime) {
                lastTime = elapsedTime;
                currentText = currentMainText.substring(0, currentText.length() + 1);
                text.setText(currentText + "...");

            }
        }
        else if((index + 1) == mainText.size()){
            if (lastTime + (2000) < elapsedTime) {
                lastTime = elapsedTime;
                this.remove();
                this.isFin = true;
            }
            sound.stop(soundId);
        }
        else{
            ++index;
            currentMainText = mainText.get(index);
            currentText = "";
        }
        elapsedTime = System.currentTimeMillis();
    }
}
