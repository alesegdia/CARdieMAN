package com.ufofrog.cardieman.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
/**
 * @author Alejandro Segu� D�az
 */

public class Sfx {
	
	public static Sound clin;
	public static Sound jump;
	
	public static Sound death;
	public static Music music;
	

	public static void Init() {
		
		clin = Gdx.audio.newSound(Gdx.files.internal(
				"data/sfx/Pickup_Coin5.wav"));
		
		death = Gdx.audio.newSound(Gdx.files.internal(
				"data/sfx/Death.wav"));
		
		music = Gdx.audio.newMusic(Gdx.files.internal(
				"data/audio/cardiemantheme.mp3"));
		
		jump = Gdx.audio.newSound(Gdx.files.internal(
				"data/sfx/Jump4.wav"));
				
	}

}
