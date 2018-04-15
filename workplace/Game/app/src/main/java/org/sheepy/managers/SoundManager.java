package org.sheepy.managers;

import org.sheepy.Environment.Environment;

import java.io.IOException;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;

/**
 * The SoundManger load and managed the the sound effect of the game. Basically it is a container with all the sounds which can occur in the game. 
 * 
 * @author Vitali Dettling on 30.01.2016.
 *
 */
public class SoundManager 
{
	private static final String backGroundMusikPath = "Sounds/greenGarden.ogg";
	private static final String explotionSoundPath = "Sounds/explosion.ogg";
	private static final String foodStepsSoundPath = "Sounds/foodSteps.ogg";

	private static final float backGroundVolumen = 0.3f;
	private static final float walkingGrassVolumen = 0.7f;
	private static final float bombExplotionVolumen = 0.9f;

	private Music musicBackGround;
	private Sound soundBomb;
	private Sound soundWalkingGrass;

	/**
	 * Loads the resources of the music and sound from the asset folder. 
	 * 
	 * @throws IOException: If any exception occur they will be passed to the StartGame class and this again pass it to the AndEngine.
	 */
	public SoundManager() throws IOException
	{
		musicBackGround = MusicFactory.createMusicFromAsset(Environment.getEngine().getMusicManager(), Environment.getActivity(), backGroundMusikPath);
		musicBackGround.setVolume(backGroundVolumen);
		musicBackGround.setLooping(true);

		soundBomb = SoundFactory.createSoundFromAsset(Environment.getEngine().getSoundManager(), Environment.getActivity(), explotionSoundPath);
		soundBomb.setVolume(walkingGrassVolumen);

		soundWalkingGrass = SoundFactory.createSoundFromAsset(Environment.getEngine().getSoundManager(), Environment.getActivity(), foodStepsSoundPath);
		soundWalkingGrass.setVolume(bombExplotionVolumen);
	}

	/**
	 * The music object from the asset folder.
	 * 
	 * @return Music: Service to start, stop or pause the current music.
	 */
	public Music getMusicBackGround() 
	{
		return musicBackGround;
	}

	/**
	 * The sound object from the asset folder.
	 * 
	 * @return Sound: Service to start, stop or pause the current music. 
	 */
	public Sound getSoundBomb() 
	{
		return soundBomb;
	}

	/**
	 * The sound object from the asset folder.
	 * 
	 * @return Sound: Service to start, stop or pause the current music. 
	 */
	public Sound getSoundWalkingGrass() 
	{
		return soundWalkingGrass;
	}
}
