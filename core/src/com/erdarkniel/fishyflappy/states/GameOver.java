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
    private final int PLAYAGAINXLEFT=(int)(Gdx.graphics.getWidth()*0.10648),PLAYAGAINXRIGHT=(int)(Gdx.graphics.getWidth()*0.8879),PLAYAGAINYUP=(int)(Gdx.graphics.getHeight()*0.6322),PLAYAGAINYDOWN=(int)(Gdx.graphics.getHeight()*0.81103);
    private final int MENUXLEFT=(int)(Gdx.graphics.getWidth()*0.10648),MENUXRIGHT=(int)(Gdx.graphics.getWidth()*0.8879),MENUYUP=(int)(Gdx.graphics.getHeight()*0.8355),MENUYDOWN=(int)(Gdx.graphics.getHeight()*0.9487);
    private Texture bg,playagain,game_over,gotomenu, newbest;
    BitmapFont puntuacion;
    BitmapFont hs;
    private int highScore=0;
    Animation nbanim;
    Preferences prefs = Gdx.app.getPreferences("game preferences");
    int score;
    private boolean show = false;
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

        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("playagain.png"));
        Pixmap pixmapPT = new Pixmap(300, 440, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );

        Pixmap pixmapGM = new Pixmap(Gdx.files.internal("go-to-menu-btn.png"));
        Pixmap pixmapPM = new Pixmap(300, 440, pixmapGM.getFormat());
        pixmapPM.drawPixmap(pixmapGM,
                0, 0, pixmapGM.getWidth(), pixmapGM.getHeight(),
                0, 0, pixmapPM.getWidth(), pixmapPM.getHeight()
        );

        Pixmap pixmapG = new Pixmap(Gdx.files.internal("game over.png"));
        Pixmap pixmapP = new Pixmap(270, 450, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        bg = new Texture("bg.png");
        playagain = new Texture(pixmapPT);
        gotomenu = new Texture(pixmapPM);
        game_over = new Texture(pixmapP);

        Pixmap pixmapGNB = new Pixmap(Gdx.files.internal("new best.png"));
        Pixmap pixmapPNB = new Pixmap(540, 450, pixmapGNB.getFormat());
        pixmapPNB.drawPixmap(pixmapGNB,
                0, 0, pixmapGNB.getWidth(), pixmapGNB.getHeight(),
                0, 0, pixmapPNB.getWidth(), pixmapPNB.getHeight()
        );
        newbest = new Texture(pixmapPNB);
        nbanim = new Animation(new TextureRegion(newbest),2,0.5f);

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.getX()>=MENUXLEFT&&Gdx.input.getX()<=MENUXRIGHT&&
                Gdx.input.getY()>=MENUYUP&&Gdx.input.getY()<=MENUYDOWN){
            if (Gdx.input.justTouched()){
                PlayState.totalscore=0;
                show = false;
                gsm.set(new MenuState(gsm));
            }
        }
        if (Gdx.input.getX()>=PLAYAGAINXLEFT&&Gdx.input.getX()<=PLAYAGAINXRIGHT&&
                Gdx.input.getY()>=PLAYAGAINYUP&&Gdx.input.getY()<=PLAYAGAINYDOWN){
            if (Gdx.input.justTouched()){
                PlayState.totalscore=0;
                show = false;
                gsm.set(new PlayState(gsm));
            }
        }
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
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),camera.position.y-(camera.viewportHeight/2));
        spriteBatch.draw(playagain,camera.position.x - playagain.getWidth()/2,camera.position.y - playagain.getHeight()+140);
        spriteBatch.draw(gotomenu,camera.position.x - gotomenu.getWidth()/2,camera.position.y - gotomenu.getHeight()+90);
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
        playagain.dispose();
    }

    public TextureRegion getNewBest() {return nbanim.getFrame();}
}
