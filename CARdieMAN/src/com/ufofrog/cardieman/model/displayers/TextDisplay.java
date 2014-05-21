package com.ufofrog.cardieman.model.displayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
/**
 * @author Alejandro Seguí Díaz
 */

public class TextDisplay {

	private Texture texture;
	private Sprite[] sprites = new Sprite[26];
	private float spacing;

	public TextDisplay( AtlasRegion region, float scale, float spacing )
	{
		this.texture = region.getTexture();
		this.spacing = spacing;
		
		for( int i = 0; i < 26; i++ )
		{
			sprites[i] = new Sprite( region, i*4, 0, 4, 5 );
			sprites[i].setScale(scale,scale);
		}
		
	}
	
	public float GetSpacing()
	{
		return spacing;
	}
	
	public void RenderCharArray( SpriteBatch batch, char[] cadena, float xoffset, float yoffset )
	{
		for( int i = 0; i < cadena.length; i++ )
		{
			int spriteNum = cadena[i] - 'a';
			sprites[spriteNum].setPosition( xoffset + i * spacing, yoffset );
			sprites[spriteNum].draw( batch );
		}
	}
	
	public void RenderString( SpriteBatch batch, String cadena, float xoffset, float yoffset )
	{
		for( int i = 0; i < cadena.length(); i++ )
		{
			char ch = cadena.charAt(i);
			if( ch == ' ' ) continue;
			int spriteNum = ch - 'a';
			sprites[spriteNum].setPosition( xoffset + i * spacing, yoffset );
			sprites[spriteNum].draw( batch );			
		}
	}

	public float GetHeight() {
		return sprites[0].getHeight();
	}
	
}
