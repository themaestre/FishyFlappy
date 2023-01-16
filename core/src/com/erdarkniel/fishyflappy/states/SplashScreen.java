package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.erdarkniel.fishyflappy.FishyFlappy;


public class SplashScreen extends State{



    public SplashScreen(GameStateManager gameStateManager) {
        super(gameStateManager);

    }

    @Override
    public void handleInput() {
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               gsm.set(new MenuState(gsm));
                           }
                       }
                , 5, 10, 0);


    }

    @Override
    public void update(float dt) {
        handleInput();


    }
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        //Dibujando los componentes
        //spriteBatch.draw();
        spriteBatch.end();
    }
    @Override
    public void dispose() {
    }
}
