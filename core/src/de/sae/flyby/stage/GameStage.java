package de.sae.flyby.stage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.sae.flyby.actor.Background;
import de.sae.flyby.objects.AObject;

public class GameStage extends Stage {

    private boolean visible;
    private AObject player;
    private Background background;

    public GameStage(){
        this.player = new AObject(new Vector2(20, 20), new Vector2(50, 50));
        this.background = new Background();

        this.addActor(this.background);
        this.addActor(this.player);
    }
}
