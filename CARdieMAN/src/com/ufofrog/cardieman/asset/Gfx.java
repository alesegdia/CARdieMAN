
package com.ufofrog.cardieman.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.ufofrog.cardieman.game.PackerConfig;
/**
 * @author Alejandro Segu� D�az
 */

public class Gfx {

	// PLAYER
	public static AtlasRegion player_stand;
	public static AtlasRegion player_jump;
	
	// ENEMY
	public static AtlasRegion car1;
	public static AtlasRegion car2;
	public static Animation car_anim;
	
	// DECORATION
	public static AtlasRegion billboard;
	public static AtlasRegion lose;
	public static AtlasRegion splash;
	
	// DISPLAYS
	public static AtlasRegion numbers;
	public static AtlasRegion letras;
	
	public static SpritesheetAnimation skate_anim;
	
	public static TextureAtlas atlas;
	public static AtlasRegion skate;
	public static SpritesheetAnimation ovni_anim;
	private static TextureRegion ovni;
	public static AtlasRegion flechaup;
	public static AtlasRegion flechadown;
	public static AtlasRegion esquela;
	
	
	public static void Init(  )
	{
		
		atlas = new TextureAtlas(
				Gdx.files.internal(
						PackerConfig.DEST_INTERNAL_PATH + "/" +
						PackerConfig.DEST_PACKED +
						".atlas" ));

		player_stand = atlas.findRegion("ken_stay");
		player_jump = atlas.findRegion("ken_jump");
		car1 = atlas.findRegion("car1");
		car2 = atlas.findRegion("car2");
		billboard = atlas.findRegion("billboard");
		lose = atlas.findRegion("lose");
		splash = atlas.findRegion("splash");
		numbers = atlas.findRegion("numbers");
		letras = atlas.findRegion("letras");
		skate = atlas.findRegion("skate_anim");
		ovni = atlas.findRegion("ovni_anim");
		flechaup = atlas.findRegion("flechaup");
		flechadown = atlas.findRegion("flechadown");
		esquela = atlas.findRegion("esquela");
		
		
		skate_anim = new SpritesheetAnimation( Gfx.skate, 2, 1, 2, 0.30f, Animation.LOOP );
		ovni_anim = new SpritesheetAnimation( Gfx.ovni, 2, 1, 2, 0.30f, Animation.LOOP );

		System.out.println("san cargao");

	}
	
	static Animation LoadAnimation( AtlasRegion spritesheet, int xtiles, int ytiles, int numFrames )
	{
		TextureRegion[] frames = new TextureRegion[numFrames];
		int tileW, tileH, currentX, currentY;
		tileW = spritesheet.getRegionWidth() / xtiles;
		tileH = spritesheet.getRegionHeight() / ytiles;
		currentX = currentY = 0;
		
		for( int i = 0; i < numFrames; i++ )
		{
			currentY = (i / xtiles) * tileH;
			currentX = (i % xtiles) * tileW;
			TextureRegion reg = new TextureRegion(
					spritesheet,
					currentX, currentY,
					tileW, tileH);
			System.out.println("x,y,w,h: " + currentX + "," + currentY + "," + tileW + "," + tileH );
			frames[i] = reg;
		}
		
		return new Animation(0.25f, frames);
	}

}
