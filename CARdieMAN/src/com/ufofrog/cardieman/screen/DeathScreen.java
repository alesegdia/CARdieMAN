package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameScreen;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.game.Input;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.button.Touchable;
import com.ufofrog.cardieman.model.displayers.NumberDisplay;
import com.ufofrog.cardieman.model.displayers.TextDisplay;

/**
 * @author Alejandro Seguí Díaz
 */


public class DeathScreen extends GameScreen {

	private Sprite losersprite;
	private TextDisplay namedisplay;
	private int puntos;
	private float passedSinceDead;
	private TextDisplay textdisplay;
	private NumberDisplay numdisplay;


	public DeathScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );
		
		numdisplay = new NumberDisplay( Gfx.numbers, 4f);
		losersprite = new Sprite( Gfx.lose );
		losersprite.setPosition(-7, -10);
		textdisplay = new TextDisplay( Gfx.letras, 1f, 5 );

		AddButton(
				new TextButton("play", textdisplay, 4f, -20,
						new Touchable() {
							public void OnTouch( )
							{
								game.setScreen(game.gameplayScreen);
							}
						}));
		
		AddButton(
				new TextButton("submit", textdisplay, -38f, -20,
						new Touchable() {
							public void OnTouch( )
							{
								game.setScreen(game.submitScreen);
							}
						}));

	}
	
	public void Reset( int puntos )
	{
		passedSinceDead = 0;
	}
	
	@Override
	public void Update( float delta )
	{
		
	}
	
	@Override
	public void Render( SpriteBatch batch ) {

		losersprite.draw(batch);
		passedSinceDead += Gdx.graphics.getDeltaTime();
		textdisplay.RenderString(batch, "score", -36, 10);
		numdisplay.RenderNumber(batch, game.score, -15, 0);
		
	}

}
