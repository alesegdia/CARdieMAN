package com.ufofrog.cardieman.model.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.asset.Sfx;
import com.ufofrog.cardieman.game.GameDefaults;
import com.ufofrog.core.Input;
/**
 * @author Alejandro Segu� D�az
 */

public class Player {
	
	public static final int STATE_STAND = 0;
	public static final int STATE_JUMP = 1;
	
	private int currentState; 
	private Sprite sprite;
	
	private Vector2 position;
	private float jumpForce;
	
	public Player( float x, float y ) 
	{
		position = new Vector2( x, y );
		sprite = new Sprite( Gfx.player_stand );
		//sprite.setScale(2, 2);
		sprite.setPosition( position.x,  position.y );
		currentState = 0;
		SetState( currentState );
	}
	
	public void SetState( int state  )
	{
		currentState = state;
		switch( currentState )
		{
		case STATE_STAND:
			sprite.setRegion( Gfx.player_stand );
			break;
		case STATE_JUMP:
			sprite.setRegion( Gfx.player_jump );
			break;
		}
	}
	
	public void DebugRender( ShapeRenderer shaperenderer )
	{
		shaperenderer.setColor( 0, 1, 0, 1 );
		shaperenderer.box(sprite.getX(), sprite.getY(), 0, sprite.getWidth(), sprite.getHeight(), 0);
	}

	
	public void Update( )
	{
		switch( currentState )
		{
		case STATE_STAND:

			if( Gdx.input.justTouched()/*Input.OneTouch()*/ ){
				SetState( STATE_JUMP );
				StartJump();
			}
			
			break;
		case STATE_JUMP:
			ProcessJump();

			if( position.y < GameDefaults.FLOOR_POSITION )
			{
				SetState( STATE_STAND );
				position.y = GameDefaults.FLOOR_POSITION;
			}
			
			break;
		}
		
		sprite.setPosition(position.x, position.y);
	}
	
	public void Reset()
	{
		SetState(STATE_STAND);
		position.set( GameDefaults.PLAYER_X_START, -15f );
	}
	
	private void ProcessJump()
	{
		jumpForce += -GameDefaults.JUMP_RESTITUTION * Gdx.graphics.getDeltaTime();
		position.y += jumpForce * Gdx.graphics.getDeltaTime();
		//if( jumpForce < 0.1 )
	}

	private void StartJump()
	{
		Sfx.jump.play();
		jumpForce = GameDefaults.JUMP_FORCE;
	}

	public void Render( SpriteBatch spritebatch )
	{
		sprite.draw(spritebatch);
	}

	public Rectangle GetRect() {
		return sprite.getBoundingRectangle();
	}

}
