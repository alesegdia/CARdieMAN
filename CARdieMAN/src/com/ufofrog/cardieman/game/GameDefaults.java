package com.ufofrog.cardieman.game;
/**
 * @author Alejandro Seguí Díaz
 */

public class GameDefaults {
	
		// coordenada X inicial del jugador
	public static final float PLAYER_X_START = -30.f;

		// altura del suelo
	public static final float FLOOR_POSITION = -15.f;

		// fuerza del salto del jugador
	public static final float JUMP_FORCE = 100f;

		// fuerza de gravedad
	public static final float JUMP_RESTITUTION = 400f;

		// velocidad inicial de los enemigos
	public static final float ENEMY_INITIAL_SPEED = 70f;
	
		// coordenada X inicial de los enemigos
	public static final float ENEMY_X_START = 70.f;
	
		// coordenada X a partir de la cual se eliminan los enemigos de la escena
	public static final float ENEMY_X_LIMIT = -50.f;



	/** SPAWNING LOGIC **/

		// numero de enemigos a la vez en escena
	public static final int ACTIVE_ENEMIES_AMOUNT = 3;
	
		// tiempo entre intentos de spawneo
	public static final float TIME_BETWEEN_CHECKS = 0.3f;

		// tiempo mÃ­nimo que ha de pasar para permitirse una solicitud de spawneo
	public static final float TIME_BETWEEN_SPAWNS = 0.8f;
	
		// probabilidad de que se spawnee
	public static final float SPAWN_PROB = 0.3f;
	
		// probabilidad asociada a cada tipo de enemigo
	public static final float CAR_PROB_FACTOR = 0.7f;
	public static final float BIKE_PROB_FACTOR = 0;
	public static final float SKATE_PROB_FACTOR = 0.6f;
	public static final float OVNI_PROB_FACTOR = 0.5f;

	public static final float UP_LEVEL = 10;

	public static final double SPEED_LEVEL_MULTIPLIER = 10;

}
