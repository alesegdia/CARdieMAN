package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.core.GameScreen;
/**
 * @author Alejandro Segu� D�az
 */

public class DummyScreen extends CardiemanScreen {

	public DummyScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );

		// a�adir botones
		// cargar cosas

	}
	
	public void Reset( int puntos )
	{
		// se llama con setScreen
	}
	
	@Override
	public void Update( float delta )
	{
		super.Update(delta);
	}
	
	@Override
	public void Render( SpriteBatch batch ) {
		super.Render(batch);
	}

}
