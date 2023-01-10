package com.erdarkniel.fishyflappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    //pila de estados
    private Stack<State> states;
    //Metodo que iniciaza la pila de estados
    public GameStateManager(){
        states = new Stack<State>();
    }
    //Metodo que inserta un elemento a la pila
    public void push(State state){
        states.push(state);
    }
    //Metodo que elimina el ultimo estado de la pila
    public void pop(){
        states.pop().dispose();
    }
    //Metodo que ingresa remplazando un estado
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }
    //float dt -> tiempo real de ejecucion del juego
    public void  update(float dt){
        states.peek().update(dt);
    }
    //metodo que renderiza la aplicacion
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
