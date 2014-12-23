package com.ufofrog.cardieman.model.button;

import com.ufofrog.cardieman.model.displayers.TextDisplay;
/**
 * @author Alejandro Segu� D�az
 */

public class ToggleTextButton extends TextButton {

	int currentTextIndex;
	private String[] texts;
	
	public ToggleTextButton(String[] texts, TextDisplay display, float x, float y,
			Touchable touchable) {
		super(texts[0], display, x, y, touchable);
		this.texts = texts;
		currentTextIndex = 0;
	}
	
	@Override
	public void OnTouch( )
	{
		currentTextIndex = (currentTextIndex + 1) % texts.length;
		this.text = texts[currentTextIndex];
		SetRectWidth();
		touchable.OnTouch( );
	}


	
	
}
