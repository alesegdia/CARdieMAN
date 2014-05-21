package com.ufofrog.cardieman.model.enemy;

import com.badlogic.gdx.math.Rectangle;
import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameDefaults;
/**
 * @author Alejandro Seguí Díaz
 */


public class Ovni extends Enemy {

	private float puntoSalto;
	private float altura_vuelo = 30;
	private float apertura = 0.15f;
	private float dy = -2f;

	public Ovni()
	{
		// graficos
		SetAnimation( Gfx.ovni_anim );
		
		// fisicas
		SetNumBoxes( 3 );
		SetBox( 0, new Rectangle( 0,  0, 4, 4 ) );
		SetBox( 1, new Rectangle( 4,  0, 5, 6 ) );
		SetBox( 2, new Rectangle( 9, 0, 4, 4 ) );
		
		MultipleBoxConfig();


	}

	public void Reset( float x, float y, float speed )
	{
		super.Reset( x, y, speed );
		puntoSalto = (float) (Math.sqrt( altura_vuelo ) / apertura);
	}

	public void Update( double delta )
	{
		if( GetX() < puntoSalto + GameDefaults.PLAYER_X_START &&
				GetX() > -puntoSalto + GameDefaults.PLAYER_X_START )
		{
			float x = apertura * ( GetX() - GameDefaults.PLAYER_X_START );
			yAxis = (x * x) + dy;
		}
		else
		{
			yAxis = altura_vuelo + dy;
		}

		super.Update(delta);
	}
	

}
