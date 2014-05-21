package com.ufofrog.cardieman.model.enemy;

import com.ufofrog.cardieman.asset.Gfx;
import com.ufofrog.cardieman.game.GameDefaults;

/**
 * @author Alejandro Seguí Díaz
 */

public class Skate extends Enemy {

	private float altura_salto = 15;
	private float apertura = 0.25f;
	private float puntoSalto;


	public Skate() {
		
		// graficos
		SetAnimation( Gfx.skate_anim );
		
		// fisicas
		DefaultBoxConfig();
		
	}
	
	public void Reset( float x, float y, float speed )
	{
		super.Reset( x, y, speed );
		puntoSalto = (float) (Math.sqrt( altura_salto ) / apertura);
	}

	public void Update( double delta )
	{
		// - (x * alpha) ^ 2 + origen
		if( GetX() < puntoSalto + GameDefaults.PLAYER_X_START &&
				GetX() > -puntoSalto + GameDefaults.PLAYER_X_START )
		{
			float x = apertura * ( GetX() - GameDefaults.PLAYER_X_START );
			yAxis = - (x * x) + altura_salto;
		}
		else
		{
			yAxis = 0;
		}
		super.Update( delta );
	}

}
