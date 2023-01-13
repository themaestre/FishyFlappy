package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.erdarkniel.fishyflappy.FishyFlappy;
public class MenuState extends State{
    private Texture bg,playBtn,title,fish,rate;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        bg = new Texture("bg.png");

        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("main_logo.png"));
        Pixmap pixmapPT = new Pixmap(336, 150, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );
        title = new Texture(pixmapPT);
        pixmapGT.dispose();
        pixmapPT.dispose();

        Pixmap pixmapG = new Pixmap(Gdx.files.internal("button_play.png"));
        Pixmap pixmapP = new Pixmap(110, 100, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        playBtn = new Texture(pixmapP);
        pixmapG.dispose();
        pixmapP.dispose();


        Pixmap pixmapGF = new Pixmap(Gdx.files.internal("flappy.png"));
        Pixmap pixmapPF = new Pixmap(100, 100, pixmapGF.getFormat());
        pixmapPF.drawPixmap(pixmapGF,
                0, 0, pixmapGF.getWidth(), pixmapGF.getHeight(),
                0, 0, pixmapPF.getWidth(), pixmapPF.getHeight()
        );
        fish = new Texture(pixmapPF);
        pixmapGF.dispose();
        pixmapPF.dispose();

        Pixmap pixmapGR = new Pixmap(Gdx.files.internal("rate us.png"));
        Pixmap pixmapPR = new Pixmap(200, 60, pixmapGR.getFormat());
        pixmapPR.drawPixmap(pixmapGR,
                0, 0, pixmapGR.getWidth(), pixmapGR.getHeight(),
                0, 0, pixmapPR.getWidth(), pixmapPR.getHeight()
        );
        rate = new Texture(pixmapPR);
        pixmapGR.dispose();
        pixmapPR.dispose();
    }

    //ImageButton button = new ImageButton(playBtn);

    @Override
    public void handleInput() {
        //Toque de entrada

        if (Gdx.input.justTouched()){
            /*
            button.addListener( new ClickListener(){
                public void clicked(InputEvent event, float x, float y){
                    gsm.set(new PlayState(gsm));
                }
            });*/
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
        spriteBatch.draw(title,camera.position.x - title.getWidth()/2,camera.position.y + title.getHeight()/3);
        spriteBatch.draw(playBtn,camera.position.x - playBtn.getWidth()/2,camera.position.y - playBtn.getHeight());
        spriteBatch.draw(fish, (float) (camera.position.x - fish.getWidth()/1.75),camera.position.y - fish.getHeight()/10);
        spriteBatch.draw(rate,camera.position.x - rate.getWidth()/2, (float) (camera.position.y - rate.getHeight()/0.35));
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        bg.dispose();
        title.dispose();
        playBtn.dispose();
        fish.dispose();
        rate.dispose();
        System.out.println("Menu state disposed - Memoria limpia");
    }
}
