package com.ufofrog.cardieman.model.displayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
/**
 * @author Alejandro Seguí Díaz
 */

public class NumberDisplay {

	private Sprite[] sprites = new Sprite[10];
	private float spacing;

	public NumberDisplay( AtlasRegion region, float spacing ) {
		this.spacing = spacing;
		
		for( int i = 0; i < 10; i++ )
		{
			sprites[i] = new Sprite( region, i*3, 0, 3, 5 );
		}
		
	}
	
	public void RenderNumber( SpriteBatch batch, int number, float xoffset, float yoffset )
	{
		if( number == 0 )
		{
			sprites[0].setPosition(xoffset, yoffset);
			sprites[0].draw( batch );
		}
		else
		{
			int digito = number;
			int resto = number;
			int i = 0;
	
			while( resto > 0 )
			{
				digito = resto % 10;
				resto = resto / 10;
	
				sprites[digito].setPosition(xoffset - i * spacing, yoffset );
				sprites[digito].draw( batch );
				i++;
				//System.out.print(digito + "-");
			}
		}
	}
	
}
