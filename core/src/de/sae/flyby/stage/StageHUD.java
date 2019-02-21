package de.sae.flyby.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.sae.flyby.MainGame;
import de.sae.flyby.actor.Lifebar;
import de.sae.flyby.actor.Textbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Oberflächen Klasse für Punkte, Textboxen, etc
 */
public class StageHUD extends Stage {
    //Texturen für die Sprecher
    private final static TextureRegion talker = new TextureRegion(MainGame.getTexture(), 128, 0, 64, 64);
    private final static TextureRegion big = new TextureRegion(MainGame.getTexture(), 0, 0, 128, 128);
    private final static TextureRegion boss = new TextureRegion(MainGame.getTexture(), 320, 128, 64, 64);

    private int scorePoints = 0; //Aktuelle Punktzahl
    private Label score;         //Label für die Punktzahl, was gezeichnet wird
    private Textbox box ;        //Textbox
    private List<Textbox> msgs = new ArrayList<Textbox>(); //Aktuell nachzuzeichende Textboxen
    private Lifebar bossLifebar;

    /**
     * @return Aktuelle Punktzahl
     */
    public int getScorePoints()
    {
        return this.scorePoints;
    }

    public StageHUD(Boolean isRestart){
        Label.LabelStyle scoreStyle = new Label.LabelStyle(); //Label aussehen
        scoreStyle.font = MainGame.getFont(20); //Font setzen
        scoreStyle.fontColor = Color.BLACK; //Text color

        score = new Label("Punkte: 0", scoreStyle); //"score" initialisieren

        //Position des Scores setzen
        score.setPosition(Gdx.graphics.getWidth() - 120f, Gdx.graphics.getHeight() - 30f);

        this.addActor(score); //Score als Actor hinzufügen

        if(!isRestart)
        {//Wurde das Spiel NICHT Neugestartet
            //Willkommens Nachrichten
            setTextbox("Willkommen bei FlyBy! Dieses Spiel rasiert deinen Verstand! Ich bin Noll Exception und begleite dich bei deinem Abenteuer.", "talker");
            setTextbox("Hoehr nicht auf diesen Idioten! Ich bin Lord Chungus. Und Befehle dir den Boss zu besiegen.", "lord");
        }
    }

    /**
     * Hiermit werden Punkte hinzugefügt für den aktuellen Punktestand
     * @param addPoints Punkte die dazu kommen
     */
    public void addScore(int addPoints){
        scorePoints += addPoints;

        score.setText("Punkte: " + scorePoints);
    }

    /**
     * @param msg Die Nachricht was geschrieben werden soll
     * @param icon Welcher Sprecher soll angezeigt werden (talker, boss, lord)
     */
    public void setTextbox(String msg, String icon){
        TextureRegion tal; //Hilfvariable für den Sprecher
        if(icon.toLowerCase() == "lord")
        { //BigChungus
            tal = big;
        }
        else if(icon.toLowerCase() == "boss")
        {//Endboss
            tal = boss;
        }
        else { //Default ist der Sprecher
            tal = talker;
        }
        if(box == null || box.isRemoved())
        {//Ist box null oder "gelöscht"
            box = new Textbox(msg, tal); //neue Textbox initialisieren
            this.addActor(box); //Als Actor zur aktuellen Stage hinzufügen
        }
        else{ //Wenn eine Nachricht gerade abgespielt wird soll diese Nachricht in der Warteschleife hinzugefügt werden
            msgs.add(new Textbox(msg, tal)); //In die "Warte"-Liste hinzufügen
        }
    }

    public void initBossLifebar(int maxLife){
        bossLifebar = new Lifebar(64f, 64f, Gdx.graphics.getWidth() - 64f, 64, maxLife);
        this.addActor(bossLifebar);
    }

    public void setBossLifebar(int life){
        if(bossLifebar != null)
            bossLifebar.setLife(life);
    }

    public void removeLifebar(){
        if(bossLifebar != null)
            bossLifebar.remove();
    }

    /**
     * Hier mit werden nächste Textboxen abgespielt
     */
    private void nextTextbox(){
        if(box == null || box.isRemoved() && msgs.size() > 0)
        {//Kann eine neue Textbox abgespielt werden
            box = msgs.get(0); //erste Textbox in der Liste holen
            this.addActor(box); //Als Actor hinzufügen
            msgs.remove(box); //In der Warteschleife löschen
        }
    }

    /**
     * Wird pro Frame aufgerufen
     * @param deltaTime Rendertime
     */
    @Override
    public void act(float deltaTime){
        nextTextbox(); //nächste Textbox abspielen, wenn möglich

        score.act(deltaTime); //Score berechnen
        box.act(deltaTime); //Textbox berechnen

        if(bossLifebar != null)
        {
            bossLifebar.act(deltaTime);
        }
    }
}
