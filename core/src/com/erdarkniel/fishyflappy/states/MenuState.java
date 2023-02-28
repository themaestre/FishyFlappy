package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.fishyflappy.FishyFlappy;

public class MenuState extends State{
    private final int PLAYXLEFT=(int)(Gdx.graphics.getWidth()/2.8),PLAYXRIGHT=(int)(Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/2.8),PLAYYUP=(int)(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2),PLAYYDOWN=(int)(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4.407);
    private final int EXITXLEFT=(int)(Gdx.graphics.getWidth()*0.165),EXITXRIGHT=(int)(Gdx.graphics.getWidth()*0.83981),EXITYUP=(int)(Gdx.graphics.getHeight()*0.8522),EXITYDOWN=(int)(Gdx.graphics.getHeight()*0.98606);
    private Texture bg,playBtn,title,fish,exit;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        bg = new Texture("bg.png");

        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("main_logo.png"));
        Pixmap pixmapPT = new Pixmap(340, 150, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );
        title = new Texture(pixmapPT);
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("button_play.png"));
        Pixmap pixmapP = new Pixmap(110, 100, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        playBtn = new Texture(pixmapP);

        Pixmap pixmapGF = new Pixmap(Gdx.files.internal("flappy.png"));
        Pixmap pixmapPF = new Pixmap(100, 100, pixmapGF.getFormat());
        pixmapPF.drawPixmap(pixmapGF,
                0, 0, pixmapGF.getWidth(), pixmapGF.getHeight(),
                0, 0, pixmapPF.getWidth(), pixmapPF.getHeight()
        );
        fish = new Texture(pixmapPF);

        Pixmap pixmapGR = new Pixmap(Gdx.files.internal("exit.png"));
        Pixmap pixmapPR = new Pixmap(200, 60, pixmapGR.getFormat());
        pixmapPR.drawPixmap(pixmapGR,
                0, 0, pixmapGR.getWidth(), pixmapGR.getHeight(),
                0, 0, pixmapPR.getWidth(), pixmapPR.getHeight()
        );
        exit = new Texture(pixmapPR);
    }
    @Override
    public void handleInput() {
        if (Gdx.input.getX()>=PLAYXLEFT&&Gdx.input.getX()<=PLAYXRIGHT&&
        Gdx.input.getY()>=PLAYYUP&&Gdx.input.getY()<=PLAYYDOWN){
            if (Gdx.input.justTouched()){
                gsm.set(new PlayState(gsm));
            }
        }
        if (Gdx.input.getX()>=EXITXLEFT&&Gdx.input.getX()<=EXITXRIGHT&&
                Gdx.input.getY()>=EXITYUP&&Gdx.input.getY()<=EXITYDOWN){
            if (Gdx.input.justTouched()){
                Gdx.app.exit();
            }
        }
        System.out.println(Gdx.graphics.getHeight());
    }

    @Override
    public void update(float dt) {
        handleInput();
    }
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg,0,0);
        spriteBatch.draw(title,camera.position.x - title.getWidth()/2,camera.position.y + title.getHeight()/3);
        spriteBatch.draw(playBtn,camera.position.x - playBtn.getWidth()/2,camera.position.y - playBtn.getHeight());
        spriteBatch.draw(fish,camera.position.x - fish.getWidth()/2,camera.position.y);
        spriteBatch.draw(exit,camera.position.x - exit.getWidth()/2, camera.position.y - exit.getHeight()*3);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        bg.dispose();
        title.dispose();
        playBtn.dispose();
        fish.dispose();
        exit.dispose();
    }
}
