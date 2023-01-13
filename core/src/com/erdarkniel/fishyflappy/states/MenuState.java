package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.erdarkniel.fishyflappy.FishyFlappy;
public class MenuState extends State{
    private Texture bg,playBtn,title,fish;
    Button star;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        bg = new Texture("bg.png");
        title = new Texture("logo.png");
        playBtn = new Texture("playbtn.png");
        fish = new Texture("flappy.png");
        star = new Button();
    }
    @Override
    public void handleInput() {
        //Toque de entrada
        /*if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }*/
        /*if (Gdx.input.isButtonPressed(star)){
            gsm.set(new PlayState(gsm));
        }*/
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
        spriteBatch.draw(bg,0,0);
        spriteBatch.draw(title,camera.position.x - title.getWidth()/2,camera.position.y + title.getHeight()/2);
        spriteBatch.draw(playBtn,camera.position.x - playBtn.getWidth()/2,camera.position.y);
        spriteBatch.draw(fish,camera.position.x - fish.getWidth()/2,camera.position.y - title.getHeight()*2);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        bg.dispose();
        title.dispose();
        playBtn.dispose();
        fish.dispose();
        System.out.println("Menu state disposed - Memoria limpia");
    }
}
