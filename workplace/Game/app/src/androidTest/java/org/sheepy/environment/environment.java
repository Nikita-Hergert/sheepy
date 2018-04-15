package org.sheepy.environment;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * This class is a shared for all classes in this project. 
 * It is created because it occur that nearly every class needs this objects and they have to be unique.
 * Moreover, it avoids passing the object to all classes
 * 
 * @author Vitali Dettling on 30.01.2016.
 *
 */
public class environment
{
	private static Camera camera;
	private static Engine engine;
	private static BaseGameActivity activity;

	/**
	 * Contains all information about the architecture of the game. 
	 * 
	 * @return Engine: The engine itself.
	 */
	public static Engine getEngine() 
	{
		return environment.engine;
	}

	/**
	 * Set the engine.
	 * 
	 * @param engine: Game engine.
	 */
	public static void setEngine(Engine engine) 
	{
		environment.engine = engine;
	}

	/**
	 * Activity of the game.
	 * 
	 * @return BaseGameActivity: Starting point and manage point of the game.
	 */
	public static BaseGameActivity getActivity() 
	{
		return environment.activity;
	}

	/**
	 * Set the BaseGameActivity.
	 * 
	 * @param activity: Starting point and manage point of the game.
	 */
	public static void setActivity(BaseGameActivity activity) 
	{
		environment.activity = activity;
	}

	/**
	 * The camera of the game.
	 * 
	 * @return Camera: Configuration of the camera are store here.
	 */
	public static Camera getCamera() 
	{
		return environment.camera;
	}

	/**
	 * Set the camera.
	 * 
	 * @param camera: Store the pre-configured camera.
	 */
	public static void setCamera(Camera camera) 
	{
		environment.camera = camera;
	}
}
