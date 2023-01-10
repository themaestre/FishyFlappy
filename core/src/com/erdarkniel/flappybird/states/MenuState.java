package com.erdarkniel.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.flappybird.FlappyBird;
public class MenuState extends State{
    private Texture bg;
    private Texture playBtn;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
        bg = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }
    @Override
    public void handleInput() {
        //Toque de entrada
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }
    @Override
    public void update(float dt) {
        handleInput();
    }
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        //Dibujando el fondo y el play
        spriteBatch.draw(bg,0,0);
        spriteBatch.draw(playBtn,camera.position.x - playBtn.getWidth()/2,camera.position.y);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        bg.dispose();
        playBtn.dispose();
        System.out.println("Menu state disposed - Memoria limpia");
    }
}
