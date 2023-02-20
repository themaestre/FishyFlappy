package com.erdarkniel.fishyflappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Fish {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -20;
    private Vector3 position;
    private Vector3 velocity;
    private Texture fish;
    private Rectangle bounds;
    private Animation fishAnimation;
    private Sound flap;
    public Fish(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        Pixmap pixmapG = new Pixmap(Gdx.files.internal("fishanimation.png"));
        Pixmap pixmapP = new Pixmap(100, 24, pixmapG.getFormat());//No modificar width sin modificar puntuacion
        pixmapP.drawPixmap(pixmapG,
                0, 0, pixmapG.getWidth(), pixmapG.getHeight(),
                0, 0, pixmapP.getWidth(), pixmapP.getHeight()
        );
        fish = new Texture(pixmapP);
        fishAnimation = new Animation(new TextureRegion(fish),3,0.5f);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        bounds = new Rectangle(x,y,fish.getWidth()/3,fish.getHeight());
    }
    public void update(float dt){
        fishAnimation.update(dt);
        if (position.y > 0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if (position.y < 0){
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }
    public Rectangle getBounds(){return bounds;}
    public Vector3 getPosition() {return position;}
    public TextureRegion getFish() {return fishAnimation.getFrame();}
    public void jump(){
        velocity.y = 250;
        flap.play(0.4f);
    }
    public void dispose(){
        fish.dispose();
        flap.dispose();
    }
}
