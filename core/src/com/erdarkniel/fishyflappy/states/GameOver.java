package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.fishyflappy.FishyFlappy;
public class GameOver extends State{
    private static final int MENUXLEFT=115,MENUXRIGHT=365,MENUYUP=645,MENUYDOWN=705;
    private Texture bg,gotomenubtn,game_over;
    public GameOver(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("go-to-menu-btn.png"));
        Pixmap pixmapPT = new Pixmap(200, 300, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("game_over.png"));
        Pixmap pixmapP = new Pixmap(340, 200, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        bg = new Texture("bg.png");
        gotomenubtn = new Texture(pixmapPT);
        game_over = new Texture(pixmapP);
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.getX()>=MENUXLEFT&&Gdx.input.getX()<=MENUXRIGHT&&
                Gdx.input.getY()>=MENUYUP&&Gdx.input.getY()<=MENUYDOWN){
            if (Gdx.input.justTouched()){
                gsm.set(new MenuState(gsm));//Devuelve al jugador al MenuState
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
        //playState.puntuacion.draw(spriteBatch, String.valueOf(playState.totalscore),camera.position.x-(camera.viewportWidth/bg.getWidth()), camera.position.y+(camera.viewportHeight/2));
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),camera.position.y-(camera.viewportHeight/2));
        spriteBatch.draw(gotomenubtn,camera.position.x - gotomenubtn.getWidth()/2,camera.position.y - gotomenubtn.getHeight());
        spriteBatch.draw(game_over,camera.position.x - game_over.getWidth()/2,camera.position.y + game_over.getHeight()/10);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        bg.dispose();
        gotomenubtn.dispose();
    }
}
