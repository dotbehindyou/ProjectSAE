package de.sae.flyby.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.sae.flyby.MainGame;
import de.sae.flyby.actor.Textbox;

import java.util.ArrayList;
import java.util.List;

public class StageHUD extends Stage {
    int scorePoints = 0;

    Label score;
    Textbox box ;

    public StageHUD(){
        Label.LabelStyle scoreStyle = new Label.LabelStyle();
        scoreStyle.font = MainGame.getFont(20);
        scoreStyle.fontColor = Color.BLACK;

        score = new Label("Punkte: 0", scoreStyle);

        score.setPosition(Gdx.graphics.getWidth() - 120f, Gdx.graphics.getHeight() - 30f);

        this.addActor(score);

        setTextbox("Willkommen bei FlyBy! Dieses Spiel rasiert deinen Verstand! Ich bin Noll Exception und begleite dich bei deinem Abenteuer.", "talker");
        setTextbox("Hoehr nicht auf diesen Idioten! Ich bin Lord Chungus. Und Befehle dir den Boss zu besiegen.", "lord");
    }

    public int getScorePoints()
    {
        return this.scorePoints;
    }

    public void addScore(int addPoints){
        scorePoints += addPoints;

        score.setText("Punkte: " + scorePoints);
    }

    @Override
    public void act(float deltaTime){
        nextTextbox();

        score.act(deltaTime);
        box.act(deltaTime);
    }

    TextureRegion talker = new TextureRegion(MainGame.getTexture(), 128, 0, 64, 64);
    TextureRegion big = new TextureRegion(MainGame.getTexture(), 0, 0, 128, 128);
    TextureRegion boss = new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64);

    List<Textbox> msgs = new ArrayList<Textbox>();
    public void setTextbox(String msg, String icon){
        TextureRegion tal;
        if(icon.toLowerCase() == "lord"){
            tal = big;
        }
        else if(icon.toLowerCase() == "boss"){
            tal = boss;
        }
        else {
            tal = talker;
        }
        if(box == null || box.isRemoved()){
            box = new Textbox(msg, tal);
            this.addActor(box);
            msgs.remove(msg);
        }
        else{
            msgs.add(new Textbox(msg, tal));
        }
    }

    private void nextTextbox(){
        if(box == null || box.isRemoved() && msgs.size() > 0){
            box = msgs.get(0);
            this.addActor(box);
            msgs.remove(box);
        }
    }
}
