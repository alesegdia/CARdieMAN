package com.ufofrog.cardieman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.game.Input;
import com.ufofrog.cardieman.model.button.Button;
import com.ufofrog.cardieman.model.button.ButtonManager;
import com.ufofrog.core.GameScreen;

public abstract class CardiemanScreen extends GameScreen<GdxGame> {

	private ButtonManager _buttonmanager;

	private OrthographicCamera _camera;
	private SpriteBatch _batch;
	private ShapeRenderer _shaperenderer;
	private boolean _debugrender = false;

	
	public CardiemanScreen(GdxGame game, float viewportWidth,
			float viewportHeight) {
		super(game);
		this._shaperenderer = new ShapeRenderer();

		this._camera = new OrthographicCamera(viewportWidth, viewportHeight);
		this._batch = new SpriteBatch();

		this._buttonmanager = new ButtonManager();

	}
	
	protected void AddButton( Button txtbt )
	{
		this._buttonmanager.AddButton(txtbt);
	}
	
	@Override
	public void render( float delta )
	{
		Input.Update( );
		
		Update( delta );
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		_batch.setProjectionMatrix(_camera.combined);
		_batch.begin();
		Render( _batch );
		_batch.end();
		
		if( _debugrender )
		{
			_shaperenderer.setProjectionMatrix(_camera.combined);
			_shaperenderer.begin(ShapeType.Line);
			DebugRender( _shaperenderer );
			_shaperenderer.end();
		}
	}


	
	public void Update( float delta )
	{
		_buttonmanager.Update();
	}
	
	public void Render(SpriteBatch batch)
	{
		_buttonmanager.Render( batch );
	}
	
	public void DebugRender( ShapeRenderer shaperenderer )
	{
		_buttonmanager.DebugRender( shaperenderer );
	}

	@Override
	public void dispose() {
		_batch.dispose();
		_shaperenderer.dispose();
	}

	public Camera GetCam() {
		return _camera;
	}

	
}
