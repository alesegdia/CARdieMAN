package com.ufofrog.cardieman.model.button;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ufofrog.cardieman.model.displayers.TextDisplay;
/**
 * @author Alejandro Segu� D�az
 */

public class TextButton extends Button {

	protected String text;
	protected TextDisplay display;
	
	public TextButton( String text, TextDisplay display, float x, float y, Touchable touchable )
	{

		super( x, y, touchable );

		this.display = display;
		this.text = text;
		SetRect( (int)x, (int)y, (int) (text.length() * display.GetSpacing()), ((int)display.GetHeight()) );

	}
	
	@Override
	public void Render( SpriteBatch batch )
	{
		display.RenderString(batch, text, GetX(), GetY());
	}
	
	public void SetRectWidth()
	{
		SetRectWidth( text.length() * display.GetSpacing() );
	}

	

}
