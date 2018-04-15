package org.sheepy;

import android.content.Intent;
import android.os.Looper;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;
import org.sheepy.Environment.Environment;
import org.sheepy.managers.GameManager;
import org.sheepy.managers.SceneManager;
import org.sheepy.managers.SceneManager.AllScenes;
import org.sheepy.managers.SoundManager;
import org.sheepy.utils.AppHandling;
import org.sheepy.utils.Scope;

import java.io.IOException;

/**
 * This is the entry point of the project. All resources of the game will be loaded here and executed in the right order.
 * The first four methods are call one after another from the AndEngine to initialise the game. After that they will not be called again.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public class Game extends BaseGameActivity implements IUpdateHandler, IOnSceneTouchListener {

    private SceneManager sceneManager;
    private SoundManager soundManager;
    private GameManager gameManager;

    public Game() {
    }

    /**
     * Set up all needed information for the engine to use it during the project.
     *
     * @return EngineOptions: The engine object to AndEngine to handle it intern.
     */
    @Override
    public EngineOptions onCreateEngineOptions() {
        Environment.setCamera(new Camera(Scope.centerX, Scope.centerY, Scope.cameraWidth, Scope.cameraHigh));
        EngineOptions option = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(Scope.cameraWidth, Scope.cameraHigh), Environment.getCamera());

        option.getTouchOptions().setNeedsMultiTouch(true);
        option.getAudioOptions().setNeedsMusic(true);
        option.getAudioOptions().setNeedsSound(true);

        return option;
    }

    /**
     * That is the method where you should upload your resources for the game, such as the textures which are needed during the game.
     * In this case it initialise some parameter which are needed within the game.
     *
     * @param pOnCreateResourcesCallback: Callback a method of AndEngine that says only that the method is done.
     * @throws IOException: Thought an exception if a error occur the exception will be handled by the AndEngine
     *                      which shows you the exception on the logCat of the mobile application development environment.
     */
    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        Environment.setActivity(this);
        Environment.setEngine(this.mEngine);

        //Looper.prepare must be called once so that the program knows that a thread will be created soon.
        Looper.prepare();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    /**
     * That is the method where you should upload your resources for the game, such as the textures which are needed during the game.
     * In this case the resources are loaded in the constructor of the SceneManger class.
     * The background music will be played after passing the method.
     *
     * @param pOnCreateSceneCallback: Callback a method of AndEngine that says only that the method is done.
     * @throws IOException: Thought an exception if a error occur the exception will be handled by the AndEngine
     *                      which shows you the exception on the logCat of the mobile application development environment.
     */
    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        this.sceneManager = new SceneManager();
        this.soundManager = new SoundManager();
        this.gameManager = new GameManager();
        this.soundManager.getMusicBackGround().play();
        pOnCreateSceneCallback.onCreateSceneFinished(SceneManager.getSplashScene());
    }

    /**
     * Begins with the game.
     *
     * @param pScene:                   Is not used in the method.
     * @param pOnPopulateSceneCallback: Callback a method of AndEngine that says only that the method is done.
     * @throws IOException: Thought an exception if a error occur the exception will be handled by the AndEngine
     *                      which shows you the exception on the logCat of the mobile application development environment.
     */
    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        this.sceneManager.createScene(AllScenes.GAME);
        this.gameManager.createNewSheep();
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    /**
     * Handles the touches on the screen and set up the next steps what should be done.
     * Example: Start of the game with the game scene and the first sheep running.
     *
     * @param pScene:           Is not used in the method.
     * @param pSceneTouchEvent: Are all information stored where the touch actually occur
     *                          and the whether the touch is with the finger down on the screen or
     *                          whether the finger was remover (up) from the screen.
     * @return boolean: True if a touch n the screen occurred otherwise false.
     */
    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        if (!this.gameManager.isBombSet() && pSceneTouchEvent.isActionUp() && SceneManager.getCurrentScene() == AllScenes.GAME) {
            this.gameManager.setBomb(true);
            this.gameManager.createBombFigure(pSceneTouchEvent);
            return true;
        }
        return false;
    }

    /**
     * This method frequently called during the game. This method should be sources out, when the game becomes more and more complex.
     * Right know it just check whether the sheep reach the top of the screen, whether the sheep hits a bomb and whether enough sheep are killed to win the game.
     *
     * @param pSecondsElapsed: Is not used in the method.
     */
    @Override
    public void onUpdate(float pSecondsElapsed) {
        if (this.gameManager.getCurrentSheep() != null) {
            if (this.gameManager.getCurrentSheep().collidesWith(this.gameManager.getCurrentBomb())) {
                this.soundManager.getSoundBomb().play();
                this.gameManager.incrementWinCounter();
                this.gameManager.simulateExplosion();
                this.gameManager.createNewSheep();
            } else if (this.gameManager.sheepBehindUpperScreen()) {
                Intent intent = new Intent();
                intent.putExtra(Scope.KEY_STRING_SCORE, this.gameManager.getWinCounter());
                setResult(RESULT_OK, intent);
                finish();//Return to the first activity.
            }
        }
    }

    /**
     * This method comes with the onUdated method but in my case it is doing nothing.
     */
    @Override
    public void reset() {/*Do nothing*/}

    /**
     * This method is called two times, once even before the game was instantiate and the second time when the user press the home bottom.
     * This is important because otherwise the game would run in the back of the mobile device without the knowledge of the user.
     * It basically just terminate the program with the code 0;
     */
    protected void onPause() {
        super.onPause();
        if (this.soundManager != null) {
            this.finish();
            AppHandling.destroy();
        }
    }
}