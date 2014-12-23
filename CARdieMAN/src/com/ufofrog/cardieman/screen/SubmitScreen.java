package com.ufofrog.cardieman.screen;

import java.io.Reader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameScreen;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.game.HighscoreManager;
import com.ufofrog.cardieman.model.button.SpriteButton;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.button.Touchable;
import com.ufofrog.cardieman.model.displayers.NumberDisplay;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
/**
 * @author Alejandro Segu� D�az
 */

public class SubmitScreen extends GameScreen {

	char[] name = { 'a', 'a', 'a' };
	private float xoffset = 15;
	private float yoffset = 12;
	private NumberDisplay numdisplay;
	private Sprite killmeSprite;

	public SubmitScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );
		
		killmeSprite = new Sprite( Gfx.billboard );
		killmeSprite.setPosition(0, 0);

		numdisplay = new NumberDisplay( Gfx.numbers, 4f);

		// SUBIR!
		AddButton(
				new SpriteButton( Gfx.flechaup, -15f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[0]++;
								if( name[0] > 'z' ) name[0] = 'a';
							}
						}));

		AddButton(
				new SpriteButton( Gfx.flechaup, -3f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[1]++;
								if( name[1] > 'z' ) name[1] = 'a';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechaup, 9f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[2]++;
								if( name[2] > 'z' ) name[2] = 'a';
							}
						}));

		
		// BAJAR!
		AddButton(
				new SpriteButton( Gfx.flechadown, -15f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[0]--;
								if( name[0] < 'a' ) name[0] = 'z';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechadown, -3f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[1]--;
								if( name[1] < 'a' ) name[1] = 'z';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechadown, 9f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[2]--;
								if( name[2] < 'a' ) name[2] = 'z';
							}
						}));
		
		AddButton(
				new TextButton( "submit", game.midtextdisplay, 0.5f,-20f,
						new Touchable() {
							public void OnTouch( )
							{
								HighscoreManager hm = game.highscoremanager;
								hm.Register(new String(name), game.score);
								System.out.println(hm.toString());
								game.setScreen(game.splashScreen);
							}
						}));

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
		
	}

	@Override
	public void Render( SpriteBatch batch ) {
		game.esquelaSprite.draw(batch);
		game.namedisplay.RenderCharArray(batch, name, -14 + xoffset, -10 + yoffset);
		game.midtextdisplay.RenderString(batch, "score", -33, 10);
		numdisplay.RenderNumber(batch, game.score, -15, 0);
	}

}
