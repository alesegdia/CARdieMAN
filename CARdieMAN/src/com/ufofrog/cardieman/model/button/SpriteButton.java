package com.ufofrog.cardieman.model.button;
/**
 * @author Alejandro Seguí Díaz
 */

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class SpriteButton extends Button {

	private Sprite sprite;

	public SpriteButton(AtlasRegion reg, float x, float y, Touchable touchable) {
		super(x, y, touchable);
		this.sprite = new Sprite( reg );
		this.sprite.setPosition(x, y);
		SetRect( this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight() );
	}
	
	public void Render( SpriteBatch batch )
	{
		sprite.draw( batch );
	}


}
