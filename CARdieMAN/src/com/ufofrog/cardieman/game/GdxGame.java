package com.ufofrog.cardieman.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ufofrog.cardieman.asset.Loader;
import com.ufofrog.cardieman.asset.Sfx;
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
	
	public int score;
	public char[] playerName = { 'a', 'a', 'a' };
	public boolean debugrender = false;


	@Override
	public void create() {

		Loader.Init();

		//splashScreen = new GameScreen( this );
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		gameplayScreen = new GameplayScreen( this, 80, (h/w) * 80 );
		splashScreen = new MenuScreen( this, 80, (h/w) * 80 );
		deathScreen = new DeathScreen( this, 80, (h/w) * 80 );
		submitScreen = new SubmitScreen( this, 80, (h/w) * 80 );
		
		setScreen( splashScreen );

	}
	
	public void setScreen( GameScreen gs )
	{
		setScreen( ((Screen)gs) );
		Input.SetCamera( gs.GetCam() );
		gs.Reset();
	}
	

}
