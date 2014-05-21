package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufofrog.cardieman.game.GameScreen;
import com.ufofrog.cardieman.game.GdxGame;
/**
 * @author Alejandro Seguí Díaz
 */

public class DummyScreen extends GameScreen {

	public DummyScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );

		// añadir botones
		// cargar cosas

	}
	
	public void Reset( int puntos )
	{
		// se llama con setScreen
	}
	
	@Override
	public void Update( float delta )
	{
		
	}
	
	@Override
	public void Render( SpriteBatch batch ) {
		
	}

}
