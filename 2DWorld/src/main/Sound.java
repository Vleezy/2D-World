package main;

import java.net.URL;

import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30]; // URL stores file path for sound files
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[2] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[3] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[4] = getClass().getResource("/sound/BlueBoyAdventure.wav");
	}
}
