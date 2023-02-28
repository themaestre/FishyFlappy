package com.erdarkniel.fishyflappy.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.erdarkniel.fishyflappy.FishyFlappy;

public class SplashScreen extends State{
    private float lifeTime;
    private Long delay = 2L;
    private Texture title,fish, bg;
    public SplashScreen(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        Pixmap pixmapGT = new Pixmap(Gdx.files.internal("main_logo.png"));
        Pixmap pixmapPT = new Pixmap(340, 150, pixmapGT.getFormat());
        pixmapPT.drawPixmap(pixmapGT,
                0, 0, pixmapGT.getWidth(), pixmapGT.getHeight(),
                0, 0, pixmapPT.getWidth(), pixmapPT.getHeight()
        );
        title = new Texture(pixmapPT);
        Pixmap pixmapGF = new Pixmap(Gdx.files.internal("flappy.png"));
        Pixmap pixmapPF = new Pixmap(100, 100, pixmapGF.getFormat());
        pixmapPF.drawPixmap(pixmapGF,
                0, 0, pixmapGF.getWidth(), pixmapGF.getHeight(),
                0, 0, pixmapPF.getWidth(), pixmapPF.getHeight()
        );
        fish = new Texture(pixmapPF);
        bg = new Texture(Gdx.files.internal("bg.png"));
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
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),camera.position.y-(camera.viewportHeight/2));
        spriteBatch.draw(title,camera.position.x - title.getWidth()/2,camera.position.y - title.getHeight());
        spriteBatch.draw(fish,camera.position.x - fish.getWidth()/2,camera.position.y);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        title.dispose();
        fish.dispose();
    }
}
