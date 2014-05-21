package com.ufofrog.cardieman.model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.asset.SpritesheetAnimation;
import com.ufofrog.cardieman.game.GameDefaults;
import com.ufofrog.cardieman.model.player.Player;
/**
 * @author Alejandro Seguí Díaz
 */

public abstract class Enemy {

	protected Sprite sprite;
	private float speed;
	protected SpritesheetAnimation sheetAnim = null;

	protected Rectangle[] localBoxes;
	private Rectangle[] collisionBoxes;
	
	boolean lastPlayerLeft;
	private float animTimer = 0;
	protected float yAxis = 0;
	
	public Enemy( )
	{
		sprite = new Sprite();

	}
	
	public void Reset( float x, float y, float speed )
	{
		yAxis = 0;
		lastPlayerLeft = false;
		Place( x, y );
		this.speed = speed;
	}
	
	
	public void Update( double delta )
	{
		Place( ((float)(sprite.getX() - delta * speed)), yAxis + GameDefaults.FLOOR_POSITION );
		if( sheetAnim != null )
		{
			animTimer += Gdx.graphics.getDeltaTime();
			sprite.setRegion( sheetAnim.GetKeyFrame( animTimer  ) );
		}
	}
	
	public boolean DoScore( Player player )
	{
		boolean ret, current;
		current = sprite.getX() < player.GetRect().x - sprite.getWidth();
		ret = !lastPlayerLeft && current;
		lastPlayerLeft = current;
		return ret;

	}
	
	
	protected void SetColBoxes()
	{

		collisionBoxes = new Rectangle[localBoxes.length];
		for( int i = 0; i < localBoxes.length; i++ )
		{
			collisionBoxes[i] = new Rectangle( localBoxes[i] );
		}
		
	}

	public boolean CollideWith( Player player )
	{
		for( int i = 0; i < collisionBoxes.length; i++ )
		{
			if( collisionBoxes[i].overlaps( player.GetRect() ))
			{
				return true;
			}
		}
		return false;
	}
	
	public void Place( float x, float y )
	{
		sprite.setPosition( x, y );
		for( int i = 0; i < collisionBoxes.length; i++ )
		{
			collisionBoxes[i].setPosition(
					sprite.getX() + localBoxes[i].x,
					sprite.getY() + localBoxes[i].y);
		}
	}

	public void Move( float dx, float dy )
	{
		Place( sprite.getX() + dx,  sprite.getY() + dy );
	}

	public void Render(SpriteBatch batch)
	{
		sprite.draw( batch );
	}

	public float GetX()
	{
		return sprite.getX();
	}

	public void DebugRender( ShapeRenderer shaperenderer )
	{
		shaperenderer.setColor( 0, 1, 0, 1 );
		for( Rectangle r : collisionBoxes )
		{
			shaperenderer.box( r.x, r.y, 0, r.width, r.height, 0 );
		}
	}
	
	protected void SetSprite( TextureRegion reg )
	{
		sprite.setTexture( reg.getTexture() );
		sprite.setRegion( reg );
		sprite.setSize( reg.getRegionWidth(), reg.getRegionHeight() );
	}
	
	protected void SetAnimation( SpritesheetAnimation anim )
	{
		sheetAnim = anim;
		sheetAnim.ConfigSprite( sprite );
		//sprite = sheetAnim.NewSprite();
	}
	
	protected void DefaultBoxConfig()
	{
		localBoxes = new Rectangle[1];
		localBoxes[0] = new Rectangle( 0, 0, sprite.getWidth(), sprite.getHeight() );
		SetColBoxes();
		Reset( 0, 0, 0 );

	}
	
	protected void MultipleBoxConfig()
	{
		SetColBoxes();
		Reset( 0, 0, 0 );
	}
	
	protected void SetNumBoxes( int boxes )
	{
		localBoxes = new Rectangle[boxes];
	}
	
	protected void SetBox( int index, Rectangle rect )
	{
		localBoxes[index] = rect;
	}

}
