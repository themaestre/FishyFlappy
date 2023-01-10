package com.erdarkniel.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.erdarkniel.flappybird.states.GameStateManager;
import com.erdarkniel.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
	//TAMALLO PANTALLA
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	//Titulo oficial aplicacion
	public static final String TITLE = "Fishy Flappy";
	//Administrador de estado del juego
	private GameStateManager gsm;
	//Lote de sprites
	private SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		ScreenUtils.clear(0, 0, 1, 1);
		gsm.push(new MenuState(gsm));
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 1, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
