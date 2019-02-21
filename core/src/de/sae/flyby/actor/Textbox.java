package de.sae.flyby.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.sae.flyby.MainGame;
import de.sae.flyby.objects.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Textboxen für die Nachrichten
 */
public class Textbox extends Actor {
    private final static TextureRegion[] bg = new TextureRegion[]{  //Hintergründe für die Sprechbox
        new TextureRegion(MainGame.getTexture(), 128, 64, 64, 64),
        new TextureRegion(MainGame.getTexture(), 128, 128, 64, 64),
        new TextureRegion(MainGame.getTexture(), 128, 192, 64, 64)
    };

    private Label text;                                 //Rendertext
    private TextureRegion icon;                         //Sprecher(icon)


    private List<String> mainText;  //Die aktuelle Nachricht unterteilt
    private int index;              //Aktuelle Position
    private String currentMainText; //aktuelle Nachricht
    private String currentText = "";//aktueller angezeigter Text
    private long lastTime = System.currentTimeMillis();
    private long elapsedTime = 0L;
    private boolean isFin = false;
    private long soundId = -1;

    /**
     * @return Ist die Textbox gelöscht / fertig
     */
    public Boolean isRemoved(){
        return isFin;
    }

    /**
     * @param messsage Nachricht
     * @param icon Sprecher-Icon
     */
    public Textbox(String messsage, TextureRegion icon){
        this.icon = icon; //Sprecher-Icon setzen

        Label.LabelStyle textStyle = new Label.LabelStyle(); //Rendertext style
        textStyle.font = MainGame.getFont(20); //Font
        textStyle.fontColor = Color.WHITE; //Textcolor

        mainText = new ArrayList<String>(); //Maintext Liste initialisieren
        for(String item : messsage.split("!.?")){ //Nachricht splitten
            mainText.add(item); //einzelne Nachricht hinzufügen
        }
        currentMainText = mainText.get(0); //Erster Text als aktueller Text setzen

        text = new Label("...", textStyle); //Rendertext initialisieren
        text.setPosition(110, 10); //Positionieren
        text.setSize(Gdx.graphics.getWidth(), 50); //Größe setzen
    }

    /**
     * Wird automatisch von der Engine aufgerufen
     * @param batch Sprite Buffer
     * @param alpha Alpha
     */
    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(bg[0], 0, 0, 64, 64); //Hintergrund rendern
        batch.draw(bg[1], 64, 0, Gdx.graphics.getWidth() - 128, 64);//Hintergrund rendern
        batch.draw(bg[2], Gdx.graphics.getWidth() - 64, 0, 64, 64);//Hintergrund rendern
        batch.draw(icon, 10, 10, 128, 128);//Hintergrund rendern
        text.draw(batch, alpha);
    }

    @Override
    public void act(float deltaTime){
        Sound.playSound("text"); //Spiele Text Sound ab.
        if(currentText.length() < currentMainText.length())
        {//Ist der NICHT fertig?
            if (lastTime + (50) < elapsedTime)
            { //Ist die Zeit von 50 Millisekunden vergangen?
                lastTime = elapsedTime;
                currentText = currentMainText.substring(0, currentText.length() + 1);
                //Füge dem angezeigten Text ein Buchstabe vom Haupttext hinzu
                text.setText(currentText + "..."); //Aktualisiere das Objekt auf der Oberfläche

            }
        }
        else if((index + 1) == mainText.size())
        { //Sind alle Sätze durchgelaufen?
            if (lastTime + (2000) < elapsedTime)
            {//Warte 2 Sekunden
                lastTime = elapsedTime;
                this.remove(); //Entferne das Objekt auf der Oberfläche
                this.isFin = true; //Setzte das Objekt als "fertig"
            }
            Sound.stopSound("text"); //Stoppe den Textsound
        }
        else { //Wenn der Satz zu ende ist
            if (lastTime + (1000) < elapsedTime)
            { //Warte 1 Sekunde
                ++index; //Setzte den Counter hoch
                currentMainText = mainText.get(index); //Nehme den nächsten Satz
                currentText = ""; //Leere den aktuellen Text;
            }
            Sound.stopSound("text");
            //Stoppe den Sound
        }
        elapsedTime = System.currentTimeMillis();
    }
}
