package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends State{
    public SplashScreen(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void handleInput() {

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
        System.out.println("Menu state disposed - Memoria limpia");
    }
}
