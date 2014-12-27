package com.ufofrog.cardieman.screen;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.asset.Sfx;
import com.ufofrog.cardieman.game.GameDefaults;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.TextButton;
import com.ufofrog.cardieman.model.displayers.NumberDisplay;
import com.ufofrog.cardieman.model.enemy.Car;
import com.ufofrog.cardieman.model.enemy.Enemy;
import com.ufofrog.cardieman.model.enemy.Ovni;
import com.ufofrog.cardieman.model.enemy.Skate;
import com.ufofrog.cardieman.model.player.Player;
import com.ufofrog.core.GameScreen;
/**
 * @author Alejandro Segu� D�az
 */

public class GameplayScreen extends GameScreen {


	// MODEL
	private Player player;
	private List<Enemy> activeEnemies = new LinkedList<Enemy>();
	private Queue<Enemy> carPool = new ArrayDeque<Enemy>();
	private Queue<Enemy> skatePool = new ArrayDeque<Enemy>();
	private Queue<Enemy> ovniPool = new ArrayDeque<Enemy>();

	// GUI
	private NumberDisplay numdisplay;
	private Sprite billboardSprite;
	
	// LOGIC
	float timeSinceLastLaunch;
	float[] probs = new float[4];
	private float timeSinceLastSpawn;
	private float timeSinceLastSpawnCheck;
	private boolean playerIsDead;
	private Enemy enemyRef;

	// RENDER
	private boolean debugMsgs = false;

	// private float vehicleSpeed;

	public GameplayScreen( GdxGame game, float viewportWidth, float viewportHeight ) {

		super( game, viewportWidth, viewportHeight );

		for( int i = 0; i < 5; i++ )
		{
			carPool.add( new Car() );
			skatePool.add( new Skate() );
			ovniPool.add( new Ovni() );
		}

		player = new Player( GameDefaults.PLAYER_X_START, GameDefaults.FLOOR_POSITION );
		billboardSprite = new Sprite( Gfx.billboard );
		billboardSprite.setPosition(-13, 4);
		numdisplay = new NumberDisplay( Gfx.numbers, 4f );

	}
	
	@Override
	public void Reset()
	{
		game.score = 0;
		playerIsDead = false;
		for( Enemy e : activeEnemies )
		{
			StoreAtProperPool( e );
		}
		activeEnemies.clear();
		player.Reset();
		Sfx.music.play();
		Sfx.music.setLooping(true);
	}
	
	public void DebugLists()
	{

		System.out.println("=======================");
		System.out.println("CAR POOL: " + carPool.size());
		System.out.println("SKATE POOL: " + skatePool.size());
		System.out.println("OVNI POOL: " + ovniPool.size());
		System.out.println("ACTIVE: " + activeEnemies.size());
		System.out.println("=======================");
		
	}
	
	private void StoreAtProperPool(Enemy e) {

		if( e instanceof Car )
		{
			carPool.add(e);
		}
		else if( e instanceof Skate )
		{
			skatePool.add(e);
		}
		else if( e instanceof Ovni )
		{
			ovniPool.add(e);				
		}
		
	}

	@Override
	public void Update( float delta )
	{
		/** UPDATE / CHECKOUT ENEMIES **/
		player.Update();
		Iterator<Enemy> it = activeEnemies.iterator();
		while( it.hasNext() )
		{
			Enemy e = it.next();
			e.Update( Gdx.graphics.getDeltaTime() );
			playerIsDead = playerIsDead || e.CollideWith(player);
			if( e.DoScore(player) )
			{
				Sfx.clin.play();
				game.score++;
			}
			if( playerIsDead || e.GetX() < GameDefaults.ENEMY_X_LIMIT )
			{
				StoreAtProperPool(e);
				it.remove();
				if( debugMsgs )	System.out.println("\n[ENEMIGO ELIMINADO]");
			}
		}

		if( debugMsgs ) DebugLists();

		/** PLAYER LIFE LOGIC **/
		if( playerIsDead )
		{
			// poner el sonido en la pantalla de haber perdido
			//Sfx.death.play();
			Sfx.music.stop();
			Sfx.death.play();
			game.setScreen( game.deathScreen );
		}
		
		/** SPAWNER LOGIC **/
		timeSinceLastSpawnCheck += Gdx.graphics.getDeltaTime();
		timeSinceLastSpawn += Gdx.graphics.getDeltaTime();
		
		if( timeSinceLastSpawn > GameDefaults.TIME_BETWEEN_SPAWNS &&
				timeSinceLastSpawnCheck >= GameDefaults.TIME_BETWEEN_CHECKS )
		{
			//System.out.println("CHECK!");
			timeSinceLastSpawnCheck = 0;
			if( activeEnemies.size() < GameDefaults.ACTIVE_ENEMIES_AMOUNT &&
					Math.random() < GameDefaults.SPAWN_PROB )
			{
				timeSinceLastSpawn = 0;
				//System.out.println("SPAWNIN!");

				probs[0] = (float) (Math.random() * GameDefaults.CAR_PROB_FACTOR);
				probs[2] = (float) (Math.random() * GameDefaults.SKATE_PROB_FACTOR);
				probs[1] = (float) (Math.random() * GameDefaults.BIKE_PROB_FACTOR);
				probs[3] = (float) (Math.random() * GameDefaults.OVNI_PROB_FACTOR);
				
				int mejor_index = 0;
				float mejor = probs[0];
				for( int i = 1; i < probs.length; i++ )
				{
					if( probs[i] > mejor )
					{
						mejor = probs[i];
						mejor_index = i;
					}
				}
				
				switch( mejor_index )
				{
				case 0: enemyRef = carPool.poll();		break;
				case 2: enemyRef = skatePool.poll();	break;
				case 3: enemyRef = ovniPool.poll();		break;
				default: System.out.println( "INDICE NO RECONOCIDO! " + mejor_index );
				}
				
				enemyRef.Reset(
					GameDefaults.ENEMY_X_START,
					GameDefaults.FLOOR_POSITION,
					(float) (GameDefaults.ENEMY_INITIAL_SPEED + (
							Math.min(Math.floor(game.score/GameDefaults.UP_LEVEL), GameDefaults.MAX_LEVEL)
							* GameDefaults.SPEED_LEVEL_MULTIPLIER )));
				activeEnemies.add( enemyRef );
				
				if( debugMsgs )
				{
					System.out.println("\n[ENEMIGO AÑADIDO]");
					DebugLists();
				}
			}
			
		}

	}
	
	@Override
	public void Render( SpriteBatch batch )
	{
		
		player.Render( batch );
		for( Enemy e : activeEnemies )
		{
			e.Render( batch );
		}
		
		numdisplay.RenderNumber( batch, game.score, -25, 14 );
		// char[] array = { 'a', 'b', 'c' };
		// namedisplay.RenderCharArray(batch, array, -25, 14);
	}
	
	@Override
	public void DebugRender( ShapeRenderer shaperenderer )
	{
		player.DebugRender(shaperenderer);
		for( Enemy e : activeEnemies )
		{
			e.DebugRender( shaperenderer );
		}
	}

	float sometime = 0;

	@Override
	public void dispose() {
		
		super.dispose();
		
	}
	
}
