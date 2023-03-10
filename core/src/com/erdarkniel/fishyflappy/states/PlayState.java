package com.erdarkniel.fishyflappy.states;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.erdarkniel.fishyflappy.FishyFlappy;
import com.erdarkniel.fishyflappy.sprites.Bottle;
import com.erdarkniel.fishyflappy.sprites.Cap;
import com.erdarkniel.fishyflappy.sprites.Fish;

public class PlayState extends State{
    private static final int bottle_SPACING = 175;
    private static final int CAP_SPACING = 130;
    private static final int bottle_COUNT = 4;
    private static final int caps_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;
    private Fish fish;
    float timeState=0f;
    private Texture bg,ground, pauseBtn,pauseScreen,resumeBtn, restart;
    private Vector2 groundPos1,groundPos2;
    private Array<Bottle> bottles;
    private Array<Cap> caps;
    private boolean pause = false;
    private final int RESTARTXLEFT=(int)(Gdx.graphics.getWidth()*0.113);
    private final int RESTARTXRIGHT=(int)(Gdx.graphics.getWidth()*0.893);
    private final int RESTARTYUP=(int)(Gdx.graphics.getHeight()*0.280);
    private final int RESTARTYDOWN=(int)(Gdx.graphics.getHeight()*0.460);

    private final int RESUMEXLEFT=(int)(Gdx.graphics.getWidth()*0.369);
    private final int RESUMEXRIGHT=(int)(Gdx.graphics.getWidth()*0.641);
    private final int RESUMEYUP=(int)(Gdx.graphics.getHeight()*0.5078);
    private final int RESUMEYDOWN=(int)(Gdx.graphics.getHeight()*0.637);

    private final int PAUSEXLEFT=Gdx.graphics.getWidth()/12;//40
    private final int PAUSEXRIGHT=Gdx.graphics.getWidth()/16*3;//90
    private final int PAUSEYUP=Gdx.graphics.getHeight()/72;//10
    private final int PAUSEYDOWN=Gdx.graphics.getHeight()/12;//60
    public boolean isPause() {
        return pause;
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    BitmapFont puntuacion;
    static int totalscore=0;
    private Music music;
    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        fish = new Fish(50,320);
        camera.setToOrtho(false, FishyFlappy.WIDTH/2, FishyFlappy.HEIGHT/2);
        bg = new Texture("bg.png");
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("pauseScreen.png"));
        Pixmap pixmapP = new Pixmap(270, 450, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        Pixmap pixmapGR = new Pixmap(Gdx.files.internal("resume.png"));
        Pixmap pixmapPR = new Pixmap(250, 440, pixmapGR.getFormat());
        pixmapPR.drawPixmap(pixmapGR,
                0, 0, pixmapGR.getWidth(), pixmapGR.getHeight(),
                0, 0, pixmapPR.getWidth(), pixmapPR.getHeight()
        );
        Pixmap pixmapGB = new Pixmap(Gdx.files.internal("button_pause.png"));
        Pixmap pixmapPB = new Pixmap(25, 25, pixmapGB.getFormat());
        pixmapPB.drawPixmap(pixmapGB,
                0, 0, pixmapGB.getWidth(), pixmapGB.getHeight(),
                0, 0, pixmapPB.getWidth(), pixmapPB.getHeight()
        );
        Pixmap pixmapGRes = new Pixmap(Gdx.files.internal("reload_button.png"));
        Pixmap pixmapPRes = new Pixmap(75, 75, pixmapGRes.getFormat());
        pixmapPRes.drawPixmap(pixmapGRes,
                0, 0, pixmapGRes.getWidth(), pixmapGRes.getHeight(),
                0, 0, pixmapPRes.getWidth(), pixmapPRes.getHeight()
        );
        restart = new Texture(pixmapPRes);
        pauseBtn = new Texture(pixmapPB);
        pauseScreen = new Texture(pixmapP);
        resumeBtn = new Texture(pixmapPR);
        puntuacion = new BitmapFont();
        puntuacion.setColor(Color.BLACK);
        puntuacion.getData().setScale(2);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);
        bottles = new Array<Bottle>();
        caps = new Array<Cap>();
        setUpMusic();
        for (int i = 1; i <= bottle_COUNT; i++) {
            bottles.add(new Bottle(i * (bottle_SPACING + Bottle.BOTTLE_WIDTH)));
        }

        for (int i = 1; i <= caps_COUNT; i++) {
            caps.add(new Cap(i * (CAP_SPACING + Cap.CAP_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            fish.jump();
        }
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.getX()>=PAUSEXLEFT&&Gdx.input.getX()<=PAUSEXRIGHT&&
                Gdx.input.getY()>=PAUSEYUP&&Gdx.input.getY()<=PAUSEYDOWN){
            if (Gdx.input.justTouched()){
                setPause(true);
            }
        }
        if (isPause()==false){
        handleInput();
        updateGround();
        fish.update(dt);
        camera.position.x = fish.getPosition().x + 80;
        for (int i = 0;i<bottles.size;i++){
            Bottle bottle = bottles.get(i);
            if (camera.position.x - (camera.viewportWidth/2) > bottle.getPosTopBottle().x + bottle.getTopBottle().getWidth()){
                bottle.reposition(bottle.getPosTopBottle().x + ((Bottle.BOTTLE_WIDTH + bottle_SPACING) * bottle_COUNT));
            }

            if (bottle.collides(fish.getBounds())){
                Gdx.input.vibrate(300);
                gsm.set(new GameOver(gsm));
            }

            if (bottle.getPosTopBottle().y >=275){
                bottle.setGravityTop(-1f);
            }
            if (bottle.getPosTopBottle().y <=150 ){
                bottle.setGravityTop(1f);
            }

            if (bottle.getposBotBottle().y <=100 ){
                bottle.setGravityBot(1.3f);
            }

            if (bottle.getposBotBottle().y >=200){
                bottle.setGravityBot(-1.3f);
            }
            bottle.update(dt);
        }

            for (int i = 0;i<caps.size;i++){
                Cap cap = caps.get(i);
                if (camera.position.x - (camera.viewportWidth/2) > cap.getPosCapBottle().x + cap.getCap().getWidth()){
                    cap.reposition(cap.getPosCapBottle().x + ((Cap.CAP_WIDTH + bottle_SPACING) * bottle_COUNT));
                }
                if (cap.collides(fish.getBounds())){
                    Gdx.input.vibrate(300);
                    gsm.set(new GameOver(gsm));
                }

                if (cap.getPosCapBottle().y >=300){
                    cap.setGravity(-0.7f);
                }
                if (cap.getPosCapBottle().y <=175 ){
                    cap.setGravity(0.5f);
                }
                cap.update(dt);

            }

        if (fish.getPosition().y <= ground.getHeight()+GROUND_Y_OFFSET){
            gsm.set(new GameOver(gsm));
            totalscore = 0;
        }

        if (fish.getPosition().y >= 375){
            gsm.set(new GameOver(gsm));
            totalscore = 0;
        }
        camera.update();
    }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),camera.position.y-(camera.viewportHeight/2));
        spriteBatch.draw(fish.getFish(), fish.getPosition().x, fish.getPosition().y);
       for (Bottle bottle : bottles){
            spriteBatch.draw(bottle.getTopBottle(),bottle.getPosTopBottle().x,bottle.getPosTopBottle().y);
            spriteBatch.draw(bottle.getBottomBottle(),bottle.getposBotBottle().x,bottle.getposBotBottle().y);
            spriteBatch.draw(bottle.getSquare(),bottle.getPosSquare().x,bottle.getPosSquare().y);
        }

        for (Cap cap : caps){
            spriteBatch.draw(cap.getCap(),cap.getPosCapBottle().x,cap.getPosCapBottle().y);
        }
        if (isPause()==true){
            spriteBatch.draw(pauseScreen,camera.position.x - pauseScreen.getWidth()/2,camera.position.y - pauseScreen.getHeight()/2);
            spriteBatch.draw(resumeBtn,camera.position.x - resumeBtn.getWidth()/2+6,camera.position.y - resumeBtn.getHeight()/2-40);
            spriteBatch.draw(restart,camera.position.x - restart.getWidth()/2,camera.position.y - restart.getHeight()/2+45);
           if (Gdx.input.getX()>=RESUMEXLEFT&&Gdx.input.getX()<=RESUMEXRIGHT&&
                    Gdx.input.getY()>=RESUMEYUP&&Gdx.input.getY()<=RESUMEYDOWN){
                if (Gdx.input.justTouched()){
                   setPause(false);
                }
            }
            if (Gdx.input.getX()>=RESTARTXLEFT&&Gdx.input.getX()<=RESTARTXRIGHT&&
                    Gdx.input.getY()>=RESTARTYUP&&Gdx.input.getY()<=RESTARTYDOWN){
                if (Gdx.input.justTouched()){
                    gsm.set(new GameOver(gsm));
                    gsm.set(new PlayState(gsm));
                    totalscore=0;
                }
            }
        }
        if (isPause()==false){
            spriteBatch.draw(pauseBtn,camera.position.x - pauseBtn.getWidth()*4,camera.position.y + pauseBtn.getHeight()*6);
        }
        puntuacion.draw(spriteBatch, String.valueOf(totalscore),camera.position.x-(camera.viewportWidth/bg.getWidth()), camera.position.y+(camera.viewportHeight/2));
        spriteBatch.draw(ground,groundPos1.x,groundPos1.y);
        spriteBatch.draw(ground,groundPos2.x,groundPos2.y);
        spriteBatch.end();

        if(isPause()==false) {
            timeState+=Gdx.graphics.getDeltaTime();
            if (timeState >= 1.80f && totalscore == 0) {
                timeState = 0f;
                totalscore++;
            }
            if (timeState >= 2.55f) {
                timeState = 0f;
                totalscore++;
            }
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        puntuacion.dispose();
        ground.dispose();
        fish.dispose();
        music.dispose();
        for (Bottle bottle : bottles){
            bottle.dispose();
        }
        for (Cap cap : caps){
            cap.dispose();
        }
    }
    private void updateGround(){
        if (camera.position.x-(camera.viewportWidth/2) > groundPos1.x+ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if (camera.position.x-(camera.viewportWidth/2) > groundPos2.x+ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }
    private void setUpMusic(){
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }
    public static int getTotalScore(){

        return totalscore;
    }


}
