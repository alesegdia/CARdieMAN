package com.ufofrog.cardieman;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.core.PackerConfig;


public class DesktopLauncher {
	public static void main(String[] args) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CARdieMAN";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		
		// PROCESS TEXTURE ATLAS
		Settings settings = new Settings();
		settings.maxWidth = 256;
		settings.maxHeight = 128;
		settings.filterMin = Texture.TextureFilter.Nearest;
		settings.filterMag = Texture.TextureFilter.Nearest;
		settings.stripWhitespaceX = false;
		settings.stripWhitespaceY = false;
		
		TexturePacker2.process( settings,

				"../" + PackerConfig.SRC_CROPPED,
					// directorio donde tenemos las imagenes sueltas
				
				"../CARdieMAN-android/assets/" +	
					// directorio base de los assets en el proyecto android
			
				PackerConfig.DEST_INTERNAL_PATH,
					// directorio interno en el que se encuentra el atlas
				
				PackerConfig.DEST_PACKED );
					// nombre del atlas

		
		new LwjglApplication(new GdxGame( new ActionResolverDesktop()), cfg);
	}
}
