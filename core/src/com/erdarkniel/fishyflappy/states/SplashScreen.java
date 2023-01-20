package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends State{
    private float lifeTime;
    private Long delay = 2L; //1000 milliseconds per second, so 2 seconds.
    public SplashScreen(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    @Override
    public void handleInput() {
    }
    public void create() {
        lifeTime = System.currentTimeMillis();
    }
    @Override
    public void update(float dt) {
        handleInput();
        lifeTime += dt;
        if (lifeTime > delay) {
            gsm.set(new MenuState(gsm));
        }
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
