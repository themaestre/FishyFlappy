package com.erdarkniel.fishyflappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.erdarkniel.fishyflappy.states.GameStateManager;
import com.erdarkniel.fishyflappy.states.MenuState;
import com.erdarkniel.fishyflappy.states.SplashScreen;

public class FishyFlappy extends ApplicationAdapter {
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
		gsm.push(new SplashScreen(gsm));
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
