package com.ufofrog.cardieman.model.button;
/**
 * @author Alejandro Segu� D�az
 */

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ufofrog.core.GameScreen;
import com.ufofrog.core.Input;

public class ButtonManager {

	private List<Button> buttons = new ArrayList<Button>();
	
	public ButtonManager()
	{
		
	}
	
	public void AddButton( Button bt )
	{
		buttons.add(bt);
	}
	
	public void Update()
	{
		for( Button bt : buttons )
		{
			if( Input.HasJustTouchedRect( bt.GetRect() ) )
			{
				bt.OnTouch( );
			}
		}
	}
	
	public void Render( SpriteBatch batch )
	{
		for( Button bt : buttons )
		{
			bt.Render(batch);
		}
	}
	
	public void DebugRender( ShapeRenderer srend )
	{
		for( Button bt : buttons )
		{
			bt.DebugRender(srend);
		}
	}
	
	

}
