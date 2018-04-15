package org.sheepy.Environment;

import android.content.res.Resources;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.ui.activity.BaseGameActivity;

import java.util.ResourceBundle;

/**
 * This class is a shared for all classes in this project. 
 * It is created because it occur that nearly every class needs this objects and they have to be unique.
 * Moreover, it avoids passing the object to all classes
 * 
 * @author Vitali Dettling on 30.01.2016.
 *
 */
public class Environment 
{
	private static Camera camera;
	private static Engine engine;
	private static BaseGameActivity activity;
	private static Resources resources;

	/**
	 * Resource from the drawable resource folder.
	 * */
	public static Resources getResources() {
		return resources;
	}

	/**
	 * Resource from the drawable resource folder.
	 *
	 * @param resources the resource to be used.
	 * */
	public static void setResources(Resources resources) {
		Environment.resources = resources;
	}

	/**
	 * Contains all information about the architecture of the game. 
	 * 
	 * @return Engine: The engine itself.
	 */
	public static Engine getEngine() 
	{
		return Environment.engine;
	}

	/**
	 * Set the engine.
	 * 
	 * @param engine: Game engine.
	 */
	public static void setEngine(Engine engine) 
	{
		Environment.engine = engine;
	}

	/**
	 * Activity of the game.
	 * 
	 * @return BaseGameActivity: Starting point and manage point of the game.
	 */
	public static BaseGameActivity getActivity() 
	{
		return Environment.activity;
	}

	/**
	 * Set the BaseGameActivity.
	 * 
	 * @param activity: Starting point and manage point of the game.
	 */
	public static void setActivity(BaseGameActivity activity) 
	{
		Environment.activity = activity;
	}

	/**
	 * The camera of the game.
	 * 
	 * @return Camera: Configuration of the camera are store here.
	 */
	public static Camera getCamera() 
	{
		return Environment.camera;
	}

	/**
	 * Set the camera.
	 * 
	 * @param camera: Store the pre-configured camera.
	 */
	public static void setCamera(Camera camera) 
	{
		Environment.camera = camera;
	}
}
