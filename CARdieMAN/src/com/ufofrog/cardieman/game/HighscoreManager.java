package com.ufofrog.cardieman.game;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class HighscoreManager {

	class HighscoreEntry {
		public String nick;
		public int score;
	}
	
	public final static String highscorefile = "highscores.dat";
	private String text;
	private HighscoreEntry highscores[] = new HighscoreEntry[3];
	private FileHandle file;
	public HighscoreManager( )
	{
		file = Gdx.files.local("highscore.txt");
		if( !file.exists() )
		{
			file.writeString("qwe 3 asd 2 zxc 1", false);
		}
		this.text = file.readString();
		System.out.println(text);
		Parse(this.text);
	}
	
	private void Parse(String text2) {
		String[] tokens = text2.split(" ");
		for( int i = 0; i < 3; i++ )
		{
			HighscoreEntry hentry = new HighscoreEntry();
			hentry.nick = tokens[i*2];
			hentry.score = Integer.parseInt(tokens[i*2+1]);
			highscores[i] = hentry;
		}
	}

	public void Register(String nick, int score)
	{
		int index = 0;
		while( index < 3 )
		{
			if( score > highscores[index].score )
				{
				break;
				}
			else index++;
		}
		if( index < 3 )
		{
			for( int i = highscores.length-1; i != index; i-- )
			{
				highscores[i] = highscores[i-1];
			}
			HighscoreEntry hentry = new HighscoreEntry();
			hentry.score = score;
			hentry.nick = nick;
			highscores[index] = hentry;
		}
		file.writeString(this.toString(), false);
	}
	
	@Override
	public String toString()
	{
		String str = new String();
		for( int i = 0; i < 3; i++ )
		{
			str += highscores[i].nick + " " + String.valueOf(highscores[i].score) + " ";
		}
		return str;
	}

	public int GetScore(int i) {
		return this.highscores[i].score;
	}
	
	public String GetName(int i) {
		return this.highscores[i].nick;
	}
	
	
}
