package com.ufofrog.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
/**
 * @author Alejandro Segu� D�az
 */

public class Input {
	
	private static Vector3 touchPos3D = new Vector3(0, 0, 0);
	private static Vector2 touchPos2D = new Vector2( 0, 0 );
	private static Camera cam;
	
	public static void SetCamera( Camera pcam )
	{
		cam = pcam;
	}
	
	public static boolean HasJustTouchedRect( Rectangle rect ) {
		
		return rect.contains( touchPos2D ) && Gdx.input.justTouched();
	}

	public static void Update()
	{
		touchPos3D.x = Gdx.input.getX();
		touchPos3D.y = Gdx.input.getY();
		cam.unproject( touchPos3D );
		touchPos2D.set( touchPos3D.x, touchPos3D.y );
	}


}
