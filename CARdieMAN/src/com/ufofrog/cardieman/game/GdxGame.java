package com.ufofrog.cardieman.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.asset.Loader;
import com.ufofrog.cardieman.asset.Sfx;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
import com.ufofrog.cardieman.screen.DeathScreen;
import com.ufofrog.cardieman.screen.GameplayScreen;
import com.ufofrog.cardieman.screen.MenuScreen;
import com.ufofrog.cardieman.screen.SubmitScreen;
/**
 * @author Alejandro Segu� D�az
 */

public class GdxGame extends Game {

	public GameplayScreen gameplayScreen;
	public MenuScreen splashScreen;
	public DeathScreen deathScreen;
	public SubmitScreen submitScreen;
	public Sprite esquelaSprite;

	public int score;
	public char[] playerName = { 'a', 'a', 'a' };
	public boolean debugrender = false;
	public HighscoreManager highscoremanager;

	public TextDisplay textdisplay;
	public TextDisplay minitextdisplay;
	public TextDisplay midtextdisplay;
	public TextDisplay namedisplay;


	@Override
	public void create() {

		Loader.Init();

		//splashScreen = new GameScreen( this );
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		textdisplay = new TextDisplay( Gfx.letras, 1f, 6 );
		minitextdisplay = new TextDisplay( Gfx.letras, 0.4f, 3f );
		midtextdisplay = new TextDisplay( Gfx.letras, 0.75f, 5f );
		namedisplay = new TextDisplay( Gfx.letras, 2.f, 12.f );

		int scale = 60;
		gameplayScreen = new GameplayScreen( this, (w/h) * scale, scale );
		splashScreen = new MenuScreen( this, (w/h) * scale, scale );
		deathScreen = new DeathScreen( this, (w/h) * scale, scale );
		submitScreen = new SubmitScreen( this, (w/h) * scale, scale );
		
		setScreen( splashScreen );
		highscoremanager = new HighscoreManager();
		
		esquelaSprite = new Sprite( Gfx.esquela );
		esquelaSprite.setPosition(-39, -26);
		

	}
	
	public void setScreen( GameScreen gs )
	{
		setScreen( ((Screen)gs) );
		Input.SetCamera( gs.GetCam() );
		gs.Reset();
	}
	

}
