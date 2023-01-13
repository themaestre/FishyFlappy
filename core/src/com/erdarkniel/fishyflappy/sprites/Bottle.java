package com.erdarkniel.fishyflappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Bottle {
    public static final int BOTTLE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int BOTTLE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topBottle,bottomBottle;
    private Vector2 posTopBottle,posBotBottle;
    private Random rand;
    private Rectangle boundsTop;
    private Rectangle boundsBot;
    public Bottle(float x){
        topBottle = new Texture("topbottle.png");
        bottomBottle = new Texture("bottombottle.png");
        //posicion tuberia
        rand = new Random();
        posTopBottle = new Vector2(x,rand.nextInt(FLUCTUATION)+BOTTLE_GAP+LOWEST_OPENING);
        posBotBottle = new Vector2(x,posTopBottle.y - BOTTLE_GAP - bottomBottle.getHeight());
        //colision
        boundsTop = new Rectangle(posTopBottle.x,posTopBottle.y,topBottle.getWidth()-10,topBottle.getHeight());
        boundsBot = new Rectangle(posBotBottle.x,posBotBottle.y,bottomBottle.getWidth()-10,bottomBottle.getHeight());
    }
    public void reposition(float x){
        posTopBottle.set(x,rand.nextInt(FLUCTUATION)+BOTTLE_GAP+LOWEST_OPENING);
        posBotBottle.set(x,posTopBottle.y - BOTTLE_GAP - bottomBottle.getHeight());

        boundsTop.setPosition(posTopBottle.x,posTopBottle.y);
        boundsBot.setPosition(posBotBottle.x,posBotBottle.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
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
}
