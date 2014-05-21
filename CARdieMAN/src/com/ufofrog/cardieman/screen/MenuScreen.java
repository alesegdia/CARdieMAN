package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameScreen;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.button.ToggleTextButton;
import com.ufofrog.cardieman.model.button.Touchable;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
/**
 * @author Alejandro Seguí Díaz
 */

public class MenuScreen extends GameScreen {
	
	Sprite splashSprite;
	TextDisplay textdisplay;
	TextDisplay minitextdisplay;

	public MenuScreen(final GdxGame game, float viewportWidth, float viewportHeight) {
		super(game, viewportWidth, viewportHeight);
		
		splashSprite = new Sprite( Gfx.splash );
		splashSprite.setPosition(-25, -13);
		textdisplay = new TextDisplay( Gfx.letras, 1f, 5 );
		minitextdisplay = new TextDisplay( Gfx.letras, 0.5f, 3f );

		AddButton(
			new TextButton("play", textdisplay, -10, -22,
					new Touchable() {
						public void OnTouch( )
						{
							game.setScreen(game.gameplayScreen);
						}
					}));

		String[] strs = { "debug off", "debug on" };
		AddButton(
				new ToggleTextButton(strs, minitextdisplay, -14, 20,
						new Touchable() {
							public void OnTouch( )
							{
								game.debugrender = !game.debugrender;
							}
						}));
		
	}
	
	@Override
	public void Render( SpriteBatch batch )
	{
		splashSprite.draw( batch );
	}
	
	public void dispose()
	{
		super.dispose();
	}

}
