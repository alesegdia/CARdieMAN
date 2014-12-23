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
 * @author Alejandro Segu� D�az
 */

public class MenuScreen extends GameScreen {
	
	Sprite splashSprite;

	public MenuScreen(final GdxGame game, float viewportWidth, float viewportHeight) {
		super(game, viewportWidth, viewportHeight);
		
		splashSprite = new Sprite( Gfx.splash );
		splashSprite.setPosition(-25, -13);

		AddButton(
			new TextButton("play", game.textdisplay, -10, -22,
					new Touchable() {
						public void OnTouch( )
						{
							game.setScreen(game.gameplayScreen);
						}
					}));

		String[] strs = { "debug off", "debug on" };
		AddButton(
				new ToggleTextButton(strs, game.minitextdisplay, -14, 20,
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
