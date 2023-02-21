package com.erdarkniel.fishyflappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Cap {
    public static final int CAP_WIDTH = 50;
    private static final int MOVEMENT = 10;
    private static final int FLUCTUATION = 100;
    private static final int CAP_GAP = 50;
    private static final int LOWEST_OPENING = 200;
    public static float GRAVITY = 0.2f;
    private Vector3 velocity;
    private Texture cap;
    private Vector2 posCapBottle;
    private Random rand;
    private Rectangle boundsCap;

    public Cap(float x){
        velocity = new Vector3(0,0,0);

        Pixmap pixmapG = new Pixmap(Gdx.files.internal("square.png"));
        Pixmap pixmapP = new Pixmap(50, 30, pixmapG.getFormat());
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );

        cap = new Texture(pixmapP);

        //posicion tapon
        rand = new Random();
        posCapBottle = new Vector2(x,rand.nextInt(FLUCTUATION)+CAP_GAP+LOWEST_OPENING);

        //colision
        boundsCap = new Rectangle(boundsCap.x,boundsCap.y,cap.getWidth()-10,cap.getHeight());

    }

    public void reposition(float x){
        posCapBottle.set(x,rand.nextInt(FLUCTUATION)+CAP_GAP+LOWEST_OPENING);
        boundsCap.setPosition(posCapBottle.x,posCapBottle.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsCap);
    }

    public Texture getCap() {
        return cap;
    }

    public void setCap(Texture cap) {
        this.cap = cap;
    }

    public Vector2 getPosCapBottle() {
        return posCapBottle;
    }

    public void setPosCapBottle(Vector2 posCapBottle) {
        this.posCapBottle = posCapBottle;
    }

    public void dispose(){cap.dispose();}

    public void update(float dt){
        if (posCapBottle.y > 0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        posCapBottle.add(MOVEMENT*dt,velocity.y);
        if (posCapBottle.y < 0){
            posCapBottle.y = 0;
            velocity.y = 0;
        }

        velocity.scl(1/dt);
        boundsCap.setPosition(posCapBottle.x,posCapBottle.y);

    }

    public void setGravity(float gravityt){
        GRAVITY = gravityt;
    }

    public float getGRAVITY(){
        return GRAVITY;
    }

}
