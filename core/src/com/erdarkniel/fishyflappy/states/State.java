package com.erdarkniel.fishyflappy.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    //Clase de la camara
    protected OrthographicCamera camera;
    //Clase de raton
    protected Vector3 mouse;
    //Clase estado del juego
    protected GameStateManager gsm;
    //Constructor de la Clase estado del juego
    protected State(GameStateManager gameStateManager){
        this.gsm = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }
    //Metodo deteccion de entradas
    protected abstract void handleInput();
    //Metodo que determina los estados: ejecucion ,carga y actualizarlos
    public abstract void update(float dt);
    //Metodo que carga todos lo elementos
    public abstract void render(SpriteBatch spriteBatch);
    //Metodo que libera los elementos
    public abstract void dispose();
}
