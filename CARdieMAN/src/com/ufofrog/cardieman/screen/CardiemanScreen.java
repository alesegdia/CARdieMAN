package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.Button;
import com.ufofrog.cardieman.model.button.ButtonManager;
import com.ufofrog.core.GameScreen;

public abstract class CardiemanScreen extends GameScreen<GdxGame> {

	private ButtonManager _buttonmanager;

	
	public CardiemanScreen(GdxGame game, float viewportWidth,
			float viewportHeight) {
		super(game, viewportWidth, viewportHeight);
		this._buttonmanager = new ButtonManager();

	}
	
	protected void AddButton( Button txtbt )
	{
		this._buttonmanager.AddButton(txtbt);
	}
	
	@Override
	public void Update( float delta )
	{
		_buttonmanager.Update();
	}
	
	@Override
	public void Render(SpriteBatch batch)
	{
		_buttonmanager.Render( batch );
	}
	
	@Override
	public void DebugRender( ShapeRenderer shaperenderer )
	{
		_buttonmanager.DebugRender( shaperenderer );
	}

}
