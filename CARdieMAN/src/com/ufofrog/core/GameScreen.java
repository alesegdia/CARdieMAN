package com.ufofrog.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.cardieman.model.button.Button;
import com.ufofrog.cardieman.model.button.ButtonManager;
import com.ufofrog.core.Input;
/**
 * @author Alejandro Segu� D�az
 */

// generics para acceder a las demás screens
// en nuestra clase game propia desde las heredadas
public abstract class GameScreen implements Screen {
	
	final protected GdxGame game;
	
	private OrthographicCamera _camera;
	private SpriteBatch _batch;
	private ShapeRenderer _shaperenderer;
	private ButtonManager _buttonmanager;


	public GameScreen(GdxGame game, float viewportWidth, float viewportHeight) {

		this.game = game;
		this._shaperenderer = new ShapeRenderer();

		this._camera = new OrthographicCamera(viewportWidth, viewportHeight);
		this._batch = new SpriteBatch();
		this._buttonmanager = new ButtonManager();
		
		// set camera en Input en vez de pasar la camara en el update
		
	}
	
	// codigo al reiniciar un screen
	public void Reset()
	{
		
	}
	
	protected void AddButton( Button txtbt )
	{
		this._buttonmanager.AddButton(txtbt);
	}
	
	@Override
	public void render( float delta )
	{
		Input.Update( );
		_buttonmanager.Update();
		
		Update( delta );
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		_batch.setProjectionMatrix(_camera.combined);
		_batch.begin();
		Render( _batch );
		_buttonmanager.Render( _batch );
		_batch.end();
		
		if( game.debugrender )
		{
			_shaperenderer.setProjectionMatrix(_camera.combined);
			_shaperenderer.begin(ShapeType.Line);
			DebugRender( _shaperenderer );
			_buttonmanager.DebugRender( _shaperenderer );
			_shaperenderer.end();
		}
	}

	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}

	public void DebugRender(ShapeRenderer shaperenderer) {
		// TODO Auto-generated method stub
		
	}

	public void Render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
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
