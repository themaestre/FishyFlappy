package com.erdarkniel.fishyflappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.erdarkniel.fishyflappy.states.PlayState;

import java.util.Random;

public class Bottle {
    public static final int BOTTLE_WIDTH = 52;
    private static final int MOVEMENT = 10;
    private static final int FLUCTUATION = 130;
    private static final int BOTTLE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static float GRAVITY = -1.3f;
    private Vector3 velocity;
    private Texture topBottle,bottomBottle,square;
    private Vector2 posTopBottle,posBotBottle,posSquare;
    private Random rand;
    private Rectangle boundsTop,boundsBot,boundsSquare;
    public Bottle(float x){
        velocity = new Vector3(0,0,0);

        Pixmap pixmapG = new Pixmap(Gdx.files.internal("topbottle.png"));
        Pixmap pixmapP = new Pixmap(20, 57, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );

        topBottle = new Texture(pixmapP);
        bottomBottle = new Texture("bottombottle.png");
        square = new Texture("square.png");
        //posicion boyella y puntuacion
        rand = new Random();
        posTopBottle = new Vector2(x,rand.nextInt(FLUCTUATION)+BOTTLE_GAP+LOWEST_OPENING);
        posBotBottle = new Vector2(x,posTopBottle.y - BOTTLE_GAP - bottomBottle.getHeight());
        posSquare = new Vector2(x+(topBottle.getWidth()/2),posTopBottle.y-square.getHeight());
        //colision
        boundsTop = new Rectangle(posTopBottle.x,posTopBottle.y,topBottle.getWidth()-10,topBottle.getHeight());
        boundsBot = new Rectangle(posBotBottle.x,posBotBottle.y,bottomBottle.getWidth()-10,bottomBottle.getHeight());
        boundsSquare = new Rectangle(posSquare.x,posSquare.y,square.getWidth(),square.getHeight());
    }
    public void reposition(float x){
        posTopBottle.set(x,rand.nextInt(FLUCTUATION)+BOTTLE_GAP+LOWEST_OPENING);
        posBotBottle.set(x,posTopBottle.y - BOTTLE_GAP - bottomBottle.getHeight());

        boundsTop.setPosition(posTopBottle.x,posTopBottle.y);
        boundsBot.setPosition(posBotBottle.x,posBotBottle.y);
        //Puntuacion
        posSquare.set(x+(topBottle.getWidth()/2),posTopBottle.y - square.getHeight());
        boundsSquare.setPosition(posSquare.x,posSquare.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    public boolean scoreCollides(Rectangle player){
        return player.overlaps(boundsSquare);
    }

    public Texture getSquare() {
        return square;
    }

    public Vector2 getPosSquare() {
        return posSquare;
    }

    public Texture getTopBottle() {
        return topBottle;
    }

    public void setTopBottle(Texture topBottle) {
        this.topBottle = topBottle;
    }

    public Texture getBottomBottle() {
        return bottomBottle;
    }

    public void setBottomBottle(Texture bottomBottle) {
        this.bottomBottle = bottomBottle;
    }

    public Vector2 getPosTopBottle() {
        return posTopBottle;
    }

    public void setPosTopBottle(Vector2 posTopBottle) {
        this.posTopBottle = posTopBottle;
    }

    public Vector2 getposBotBottle() {
        return posBotBottle;
    }

    public void setPosBotBottle(Vector2 posBotBottle) {
        this.posBotBottle = posBotBottle;
    }
    public void dispose(){topBottle.dispose();bottomBottle.dispose();}

    public void update(float dt){
        if (posTopBottle.y > 0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        posTopBottle.add(MOVEMENT*dt,velocity.y);
        if (posTopBottle.y < 0){
            posTopBottle.y = 0;
        }
        velocity.scl(1/dt);
        boundsTop.setPosition(posTopBottle.x,posTopBottle.y);
    }

    public void setGravityT(float gravityt){
        GRAVITY = gravityt;
    }

    public float getGRAVITY(){
        return GRAVITY;
    }
}
