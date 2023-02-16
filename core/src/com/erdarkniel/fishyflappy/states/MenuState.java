package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.fishyflappy.FishyFlappy;

import jdk.internal.org.jline.utils.Display;

public class MenuState extends State{
    private final int PLAYXLEFT=Gdx.graphics.getWidth()/48*13;//formula getX * x=480
    private final int PLAYXRIGHT=Gdx.graphics.getWidth()/48*35;
    private final int PLAYYUP=Gdx.graphics.getHeight()/2;
    private final int PLAYYDOWN=Gdx.graphics.getHeight()/9*7;
    private final int EXITXLEFT=Gdx.graphics.getWidth()/6;
    private final int EXITXRIGHT=Gdx.graphics.getWidth()/6*5;
    private final int EXITYUP=Gdx.graphics.getHeight()/6*5;
    private final int EXITYDOWN=Gdx.graphics.getHeight()-20;
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
        //Toque de entrada
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
        //System.out.println(camera.position.x - playBtn.getWidth()/2);//65
        //System.out.println(camera.position.y - playBtn.getHeight());//80
        //System.out.println(Gdx.graphics.getWidth());
        //System.out.println(Gdx.graphics.getHeight());
        //System.out.println(Gdx.input.getX());
        //System.out.println(Gdx.input.getY());
        //System.out.println(camera.position.x);//120
        //System.out.println(camera.position.y);//180
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
        System.out.println("Menu state disposed - Memoria limpia");
    }
}
