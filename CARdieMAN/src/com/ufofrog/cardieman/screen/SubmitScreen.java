package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameScreen;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.SpriteButton;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.button.Touchable;
import com.ufofrog.cardieman.model.displayers.NumberDisplay;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
/**
 * @author Alejandro Seguí Díaz
 */

public class SubmitScreen extends GameScreen {

	char[] name = { 'a', 'a', 'a' };
	TextDisplay namedisplay;
	private float xoffset = 10;
	private float yoffset = 10;
	TextDisplay textdisplay;
	private NumberDisplay numdisplay;

	public SubmitScreen(final GdxGame game, float viewportWidth, float viewportHeight) {

		super( game, viewportWidth, viewportHeight );

		namedisplay = new TextDisplay( Gfx.letras, 2.f, 10.f );
		numdisplay = new NumberDisplay( Gfx.numbers, 4f);
		textdisplay = new TextDisplay( Gfx.letras, 1.f, 5.f );

		// SUBIR!
		AddButton(
				new SpriteButton( Gfx.flechaup, -11.5f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[0]++;
								if( name[0] > 'z' ) name[0] = 'a';
							}
						}));

		AddButton(
				new SpriteButton( Gfx.flechaup, -1.5f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[1]++;
								if( name[1] > 'z' ) name[1] = 'a';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechaup, 8.5f + xoffset, yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[2]++;
								if( name[2] > 'z' ) name[2] = 'a';
							}
						}));

		
		// BAJAR!
		AddButton(
				new SpriteButton( Gfx.flechadown, -11.5f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[0]--;
								if( name[0] < 'a' ) name[0] = 'z';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechadown, -1.5f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[1]--;
								if( name[1] < 'a' ) name[1] = 'z';
							}
						}));
		AddButton(
				new SpriteButton( Gfx.flechadown, 8.5f + xoffset, -21 + yoffset,
						new Touchable() {
							public void OnTouch( )
							{
								name[2]--;
								if( name[2] < 'a' ) name[2] = 'z';
							}
						}));
		
		AddButton(
				new TextButton( "submit", textdisplay, -3f,-20f,
						new Touchable() {
							public void OnTouch( )
							{
								game.setScreen(game.splashScreen);
							}
						}));

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
		namedisplay.RenderCharArray(batch, name, -10 + xoffset, -10 + yoffset);
		textdisplay.RenderString(batch, "score", -36, 10);
		numdisplay.RenderNumber(batch, game.score, -15, 0);

	}

}
