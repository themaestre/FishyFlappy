package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.erdarkniel.fishyflappy.FishyFlappy;
import com.erdarkniel.fishyflappy.sprites.Animation;

public class GameOver extends State{
    private final int MENUXLEFT=115,MENUXRIGHT=959,MENUYUP=1342,MENUYDOWN=1555;
    private Texture bg,gotomenubtn,game_over, newbest;
    BitmapFont puntuacion;
    BitmapFont hs;
    private int highScore=0;
    Animation nbanim;
    Preferences prefs = Gdx.app.getPreferences("game preferences");
    int score;
    private boolean show = false;
    //private BitmapFont puntuacion;
    //private PlayState playState;
    public GameOver(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        score = PlayState.getTotalScore();
        puntuacion = new BitmapFont();
        puntuacion.setColor(Color.WHITE);
        puntuacion.getData().setScale(2);
        hs = new BitmapFont();
        hs.setColor(Color.WHITE);
        hs.getData().setScale(2);



        if (PlayState.totalscore > prefs.getInteger("highscore")) {
            show = true;
            prefs.putInteger("highscore", PlayState.totalscore);
            prefs.flush();
            highScore = PlayState.totalscore;

        }

        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("go-to-menu-btn.png"));
        Pixmap pixmapPT = new Pixmap(300, 440, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );/*
        Pixmap pixmapGR = new Pixmap(Gdx.files.internal("reload_button.png"));
        Pixmap pixmapPR = new Pixmap(60, 60, pixmapGR.getFormat());
        pixmapPT.drawPixmap(pixmapGR,
                0, 0, pixmapGR.getWidth(), pixmapGR.getHeight(),
                0, 0, pixmapPR.getWidth(), pixmapPR.getHeight()
        );*/
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("game over.png"));
        Pixmap pixmapP = new Pixmap(270, 450, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        bg = new Texture("bg.png");
        gotomenubtn = new Texture(pixmapPT);
        game_over = new Texture(pixmapP);

        Pixmap pixmapGNB = new Pixmap(Gdx.files.internal("new best.png"));
        Pixmap pixmapPNB = new Pixmap(540, 450, pixmapGNB.getFormat());
        pixmapPNB.drawPixmap(pixmapGNB,
                0, 0, pixmapGNB.getWidth(), pixmapGNB.getHeight(),
                0, 0, pixmapPNB.getWidth(), pixmapPNB.getHeight()
        );
        newbest = new Texture(pixmapPNB);
        nbanim = new Animation(new TextureRegion(newbest),2,0.5f);

        //resume = new Texture(pixmapPR);
        /*puntuacion = new BitmapFont();
        puntuacion.setColor(Color.BLACK);
        puntuacion.getData().setScale(2);*/
        //playState = new PlayState(gameStateManager);
        //System.out.println(playState.arrayScore.get(playState.arrayScore.size()-1));
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.getX()>=MENUXLEFT&&Gdx.input.getX()<=MENUXRIGHT&&
                Gdx.input.getY()>=MENUYUP&&Gdx.input.getY()<=MENUYDOWN){
            if (Gdx.input.justTouched()){
                PlayState.totalscore=0;
                show = false;
                gsm.set(new MenuState(gsm));//Devuelve al jugador al MenuState
            }
        }
        //System.out.println(Gdx.input.getY()+""+camera.position.x);
       //System.out.println("Posicion y "+Gdx.input.getY());


       // System.out.println(prefs.getInteger("highScore"));

    }

    @Override
    public void update(float dt) {
        handleInput();
        camera.update();


    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        //playState.puntuacion.draw(spriteBatch, String.valueOf(playState.totalscore),camera.position.x-(camera.viewportWidth/bg.getWidth()), camera.position.y+(camera.viewportHeight/2));
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),camera.position.y-(camera.viewportHeight/2));
        spriteBatch.draw(gotomenubtn,camera.position.x - gotomenubtn.getWidth()/2,camera.position.y - gotomenubtn.getHeight()+120);
        spriteBatch.draw(game_over,-16,-50);
        if(show)
        spriteBatch.draw(getNewBest(), -16,-50);
        puntuacion.draw(spriteBatch, String.valueOf(score),camera.position.x-(camera.viewportWidth/bg.getWidth()+5), camera.position.y+(camera.viewportHeight/2)-140);
        hs.draw(spriteBatch, String.valueOf(prefs.getInteger("highscore")),camera.position.x-(camera.viewportWidth/bg.getWidth()+5), camera.position.y+(camera.viewportHeight/2)-210);


        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        gotomenubtn.dispose();
    }

    public TextureRegion getNewBest() {return nbanim.getFrame();}
}
