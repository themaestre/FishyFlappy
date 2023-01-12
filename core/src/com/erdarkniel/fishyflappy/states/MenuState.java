package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.fishyflappy.FishyFlappy;
public class MenuState extends State{
    private Texture bg,playBtn,title,fish;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        bg = new Texture("bg.png");
        title = new Texture("logo.png");
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("button_play.png"));
        Pixmap pixmapP = new Pixmap(110, 100, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        playBtn = new Texture(pixmapP);
        pixmapG.dispose();
        pixmapP.dispose();
        fish = new Texture("flappy.png");
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
        //Dibujando los componentes
        spriteBatch.draw(bg,0,0);
        spriteBatch.draw(title,camera.position.x - title.getWidth()/2,camera.position.y + title.getHeight()/2);
        spriteBatch.draw(playBtn,camera.position.x - playBtn.getWidth()/2,camera.position.y - playBtn.getHeight());
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
