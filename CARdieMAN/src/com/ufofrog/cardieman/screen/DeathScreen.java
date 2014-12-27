package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.button.Touchable;
import com.ufofrog.cardieman.model.displayers.NumberDisplay;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
import com.ufofrog.core.GameScreen;

/**
 * @author Alejandro Segu� D�az
 */


public class DeathScreen extends GameScreen {

	private Sprite losersprite;
	private TextDisplay namedisplay;
	private int puntos;
	private float passedSinceDead;
	private NumberDisplay numdisplay;


	public DeathScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );
		
		numdisplay = new NumberDisplay( Gfx.numbers, 4f);
		//losersprite = new Sprite( Gfx.lose );
		//losersprite.setPosition(-7, -10);
		
		AddButton(
				new TextButton("again", game.minitextdisplay, -30f, -22f,
						new Touchable() {
							public void OnTouch( )
							{
								game.setScreen(game.gameplayScreen);
							}
						}));
		
		AddButton(
				new TextButton("save score", game.minitextdisplay, 0f, -22f,
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

		game.esquelaSprite.draw(batch);

		//losersprite.draw(batch);
		passedSinceDead += Gdx.graphics.getDeltaTime();
		
		if( (TimeUtils.millis() / 500) % 2 == 0 )
		{
			game.midtextdisplay.RenderString(batch, "your score", -35, -12);
			numdisplay.RenderNumber(batch, game.score, 31, -12);
		}
		int xoff = -28;
		int yoff = 17;
		
		for( int i = 0; i < 3; i++ )
		{
			String name = game.highscoremanager.GetName(i);
			int score = game.highscoremanager.GetScore(i);

			game.textdisplay.RenderString(batch, name, xoff + 0, yoff - (i * 10));
			numdisplay.RenderNumber(batch, score, xoff + 53, yoff - (i * 10));
		}

		
	}

}
