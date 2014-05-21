package com.ufofrog.cardieman.model.button;
/**
 * @author Alejandro Seguí Díaz
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	
	private float x;
	private float y;
	private Rectangle rect;
	protected Touchable touchable;

	public Button(float x, float y, Touchable touchable) {

		this.x = x;
		this.y = y;
		this.touchable = touchable;
		
	}
	
	public void DebugRender( ShapeRenderer shaperenderer )
	{
		shaperenderer.box(rect.x, rect.y, 0, rect.width, rect.height, 0);
	}
	
	public void Render( SpriteBatch batch )
	{
		
	}

	public void SetRect( float x, float y, float w, float h )
	{
		this.rect = new Rectangle( x, y, w, h );
	}
	
	public void SetRectWidth(float f) {
		this.rect.width = f;
	}

	
	public Rectangle GetRect()
	{
		return rect;
	}

	public void OnTouch( )
	{
		touchable.OnTouch( );
	}
	
	float GetX()
	{
		return x;
	}
	
	float GetY()
	{
		return y;
	}

}
