package com.ufofrog.cardieman.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.asset.Loader;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
import com.ufofrog.cardieman.screen.CardiemanScreen;
import com.ufofrog.cardieman.screen.DeathScreen;
import com.ufofrog.cardieman.screen.GameplayScreen;
import com.ufofrog.cardieman.screen.MenuScreen;
import com.ufofrog.cardieman.screen.SubmitScreen;
import com.ufofrog.core.ActionResolver;
import com.ufofrog.core.GameApp;
import com.ufofrog.core.GameScreen;
/**
 * @author Alejandro Segu� D�az
 */

public class GdxGame extends GameApp  {
	
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

	public GdxGame(ActionResolver actionResolver)
	{
		super(actionResolver);
	}

	@Override
	public void Create() {

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
		//this.actionResolver.showOrLoadInterstital();
		highscoremanager = new HighscoreManager();
		
		esquelaSprite = new Sprite( Gfx.esquela );
		esquelaSprite.setPosition(-39, -26);
	}
	
	@Override
	public void ScreenChange( GameScreen gs )
	{
		CardiemanScreen cdmsc = ((CardiemanScreen)gs);
		Input.SetCamera( cdmsc.GetCam() );
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		 actionResolver.showOrLoadInterstital();
		 return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
