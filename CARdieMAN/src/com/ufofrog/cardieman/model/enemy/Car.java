package com.ufofrog.cardieman.model.enemy;

import com.badlogic.gdx.math.Rectangle;
import com.ufofrog.cardieman.asset.Gfx;

/**
 * @author Alejandro Seguí Díaz
 */

public class Car extends Enemy {

	public Car ()
	{
		
		// graficos
		SetSprite( Gfx.car1 );
		
		// fisicas
		SetNumBoxes( 3 );
		SetBox( 0, new Rectangle( 0,  0, 5, 4 ) );
		SetBox( 1, new Rectangle( 5,  0, 5, 7 ) );
		SetBox( 2, new Rectangle( 10, 0, 2, 5 ) );
		
		MultipleBoxConfig();
		
	}
	
}
