package de.sae.flyby.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;

public class Sound {
    private final  static String localPath = "core/assets/sound/";

    private static com.badlogic.gdx.audio.Music gameover = Gdx.audio.newMusic(Gdx.files.internal(localPath + "gameover.mp3"));
    private static com.badlogic.gdx.audio.Music ingame = Gdx.audio.newMusic(Gdx.files.internal(localPath + "ingame.mp3"));
    private static com.badlogic.gdx.audio.Music menu = Gdx.audio.newMusic(Gdx.files.internal(localPath + "menu.mp3"));
    private static com.badlogic.gdx.audio.Music boss = Gdx.audio.newMusic(Gdx.files.internal(localPath + "boss.mp3"));
    private static com.badlogic.gdx.audio.Music text = Gdx.audio.newMusic(Gdx.files.internal(localPath + "text.mp3"));

    public static void playSound(String name){
        name = name.toLowerCase();
        if(name == "gameover"){
            gameover.play();
            gameover.setLooping(true);
        }else if(name == "ingame"){
            ingame.setLooping(true);
            ingame.play();
        }else if(name == "menu"){
            menu.setLooping(true);
            menu.play();
        }else if(name == "boss"){
            boss.setLooping(true);
            boss.play();
        }else if(name == "text"){
            text.setLooping(true);
            text.play();
        }
    }

    public static void resetSounds(){
        boss.stop();
        menu.stop();
        gameover.stop();
        text.stop();
        ingame.stop();
    }

    public static void stopSound(String name){
        name = name.toLowerCase();
        if(name == "gameover"){
            gameover.stop();
        }else if(name == "ingame"){
            ingame.stop();
        }else if(name == "menu"){
            menu.stop();
        }else if(name == "boss"){
            boss.stop();
        }else if(name == "text"){
            text.stop();
        }
    }
}
