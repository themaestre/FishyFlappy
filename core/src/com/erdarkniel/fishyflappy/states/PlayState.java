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
import com.erdarkniel.fishyflappy.sprites.Fish;

public class PlayState extends State{
    private static final int bottle_SPACING = 125;
    private static final int bottle_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;
    private Fish fish;
    private Texture bg,ground,pauseBtn,pauseScreen,resumeBtn;
    private Vector2 groundPos1,groundPos2;
    private Array<Bottle> bottles;
    //Puntuacion
    private BitmapFont puntuacion;
    private int score = 0;
    protected static int totalscore=0;
    //Musica del juego
    private Music music;
    //Pausa
    private boolean pause = false;
    private final int RESUMEXLEFT=Gdx.graphics.getWidth()/6;//80
    private final int RESUMEXRIGHT=Gdx.graphics.getWidth()/24*19;//380
    private final int RESUMEYUP=Gdx.graphics.getHeight()/18*7;//280
    private final int RESUMEYDOWN=Gdx.graphics.getHeight()/24*13;//390
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
        Pixmap pixmapPR = new Pixmap(200, 440, pixmapGR.getFormat());
        pixmapPR.drawPixmap(pixmapGR,
                0, 0, pixmapGR.getWidth(), pixmapGR.getHeight(),
                0, 0, pixmapPR.getWidth(), pixmapPR.getHeight()
        );
        Pixmap pixmapGB = new Pixmap(Gdx.files.internal("button_play.png"));
        Pixmap pixmapPB = new Pixmap(25, 25, pixmapGB.getFormat());
        pixmapPB.drawPixmap(pixmapGB,
                0, 0, pixmapGB.getWidth(), pixmapGB.getHeight(),
                0, 0, pixmapPB.getWidth(), pixmapPB.getHeight()
        );
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
        setUpMusic();
        for (int i = 1; i <= bottle_COUNT; i++) {
            bottles.add(new Bottle(i * (bottle_SPACING + Bottle.BOTTLE_WIDTH)));
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
        if(Gdx.input.isKeyPressed(Input.Keys.A)){//Pausar con tecla A
            setPause(true);
            //System.out.println("Pausando el juego");
            if (isPause()==true){
                //System.out.println("El juego esta en pausa");
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.B)){//Reanudae con tecla B
            setPause(false);
        }
        if (isPause()==false){
            //System.out.println("El juego corriendo");
            handleInput();
            updateGround();
            fish.update(dt);
            camera.position.x = fish.getPosition().x + 80;
            for (int i = 0;i<bottles.size;i++){
                Bottle bottle = bottles.get(i);
                if (camera.position.x - (camera.viewportWidth/2) > bottle.getPosTopBottle().x + bottle.getTopBottle().getWidth()){
                    bottle.reposition(bottle.getPosTopBottle().x + ((Bottle.BOTTLE_WIDTH + bottle_SPACING) * bottle_COUNT));
                }
                if (bottle.scoreCollides(fish.getBounds())){
                    score++;
                    //System.out.println(score);
                    if (score%20==0) {
                        //Gdx.app.log("Score", String.valueOf(score/20));
                        score = score/20;
                        totalscore++;
                    }
                }
                if (bottle.collides(fish.getBounds())){
                    gsm.set(new GameOver(gsm));
                    //Gdx.app.log("Total Score", String.valueOf(totalscore));
                }
            }
            //TRABAJAR SOBRE ESTE CON LA BASE DE DATOS
            if (fish.getPosition().y <= ground.getHeight()+GROUND_Y_OFFSET){
                gsm.set(new GameOver(gsm));
                //Gdx.app.log("Total Score", String.valueOf(totalscore));
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
        if (isPause()==true){
            spriteBatch.draw(pauseScreen,camera.position.x - pauseScreen.getWidth()/2,camera.position.y - pauseScreen.getHeight()/2);
            spriteBatch.draw(resumeBtn,camera.position.x - resumeBtn.getWidth()/2,camera.position.y - resumeBtn.getHeight()/2);
            if (Gdx.input.getX()>=RESUMEXLEFT&&Gdx.input.getX()<=RESUMEXRIGHT&&
                    Gdx.input.getY()>=RESUMEYUP&&Gdx.input.getY()<=RESUMEYDOWN){
                if (Gdx.input.justTouched()){
                    setPause(false);
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
        System.out.println("Play state disposed");
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