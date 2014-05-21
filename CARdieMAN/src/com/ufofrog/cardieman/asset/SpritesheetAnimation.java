package com.ufofrog.cardieman.asset;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * @author Alejandro Seguí Díaz
 */

public class SpritesheetAnimation {
	
	private Animation anim;
	private int tileWidth;
	private int tileHeight;
	private TextureRegion sheet;
	
	public SpritesheetAnimation( TextureRegion sheet, int xtiles, int ytiles, int numFrames, float duration, int playMode )
	{
		this.sheet = sheet;
		LoadAnimation( xtiles, ytiles, numFrames, duration );
		SetPlayMode(playMode);
	}
	
	public void LoadAnimation( int xtiles, int ytiles, int numFrames, float duration )
	{
		TextureRegion[] frames = new TextureRegion[numFrames];
		
		tileWidth = this.sheet.getRegionWidth() / xtiles;
		tileHeight = this.sheet.getRegionHeight() / ytiles;		
		
		for( int i = 0; i < numFrames; i++ )
		{
			int currentX = (int) ((i % xtiles) * tileWidth);
			int currentY = (int) ((i / xtiles) * tileHeight);
			
			TextureRegion reg = new TextureRegion(
					this.sheet,
					currentX, currentY,
					tileWidth, tileHeight);
			System.out.println("x,y,w,h: " + currentX + "," + currentY + "," + tileWidth + "," + tileHeight );
			frames[i] = reg;
		}
		
		anim = new Animation( duration, frames );
	}

	public Sprite NewSprite() {
		Sprite sprite = new Sprite( sheet );
		sprite.setSize( tileWidth,  tileHeight );
		return sprite;
	}

	public TextureRegion GetKeyFrame(float f) {
		return anim.getKeyFrame( f );
	}
	
	public void SetPlayMode( int playMode )
	{
		anim.setPlayMode( playMode );
	}

	public void ConfigSprite(Sprite sprite)
	{

		sprite.setTexture( sheet.getTexture() );
		sprite.setSize( tileWidth,  tileHeight );

	}


}
