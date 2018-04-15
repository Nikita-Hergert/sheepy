package org.sheepy.managers;

import org.sheepy.Environment.Environment;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Managed all the scene of the game which can occur.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public class SceneManager {
    //private static final String graphicsStartSheep = "Graphics/Sheeps/start.png";
    private static final String graphicsloseSheep = "Graphics/Sheeps/lose.png";
    private static final String graphicsEndSheep = "Graphics/Sheeps/end.png";
    private static final String graphicsGrass = "Graphics/grass.png";

    private static final float screenMiddle = 2;
    private static final float spritePosition = 0;

    private static final int textureX = 0;
    private static final int textureY = 0;

    private static Scene splashScene;
    private static Scene gameScene;
    private static AllScenes currentScene;

    private BitmapTextureAtlas splashStartTA;
    private BitmapTextureAtlas splashLosTA;
    private BitmapTextureAtlas splashEndTA;
    private BitmapTextureAtlas gameTA;

    private Sprite grass;
    private Sprite startSheep;
    private Sprite losSheep;
    private Sprite endSheep;

    /**
     * The method is static so that you do not have to create the class to get the scene but be careful because if you have not create the class the scene will be null.
     *
     * @return Scene: Return the current splashScene.
     */
    public static Scene getSplashScene() {
        return SceneManager.splashScene;
    }

    /**
     * The method is static so that you do not have to create the class to get the scene but be careful because if you have not create the class the scene will be null.
     *
     * @return Scene: Return the current gameScene.
     */
    public static Scene getGameScene() {
        return SceneManager.gameScene;
    }

    /**
     * If the scene is changed in the engine, the current scene can be seen here.
     *
     * @return AllScenes: Is an enumeration value for the current scene.
     */
    public static AllScenes getCurrentScene() {
        return SceneManager.currentScene;
    }

    /**
     * The constructor creates two scene once which are being used in the game all the time.
     * Moreover it loads up the textures of all background screens from the game once and creates immediately the sprites.
     * The last step is to register the scene to make them touchable and updatable.
     */
    public SceneManager() {
        //Create the two scenes which are switching during the game.
        SceneManager.splashScene = new Scene();
        SceneManager.gameScene = new Scene();

        //Creates the texture area of the background. They have to be generated three times even the passed parameter are the same. it just happen that the passed parameter are the same.
        this.gameTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), (int) Environment.getCamera().getWidth(), (int) Environment.getCamera().getHeight());
        this.splashStartTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), (int) Environment.getCamera().getWidth(), (int) Environment.getCamera().getHeight());
        this.splashLosTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), (int) Environment.getCamera().getWidth(), (int) Environment.getCamera().getHeight());
        this.splashEndTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), (int) Environment.getCamera().getWidth(), (int) Environment.getCamera().getHeight());

        //Create the texture of the background image and specify where to put the image. In my case it is the left hand top corner of the screen.
        ITextureRegion gameTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.gameTA, Environment.getActivity(), graphicsGrass, textureX, textureY);
        //	ITextureRegion splashStartTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.splashStartTA, Environment.getActivity(), graphicsStartSheep, textureX, textureY);
        ITextureRegion splashLosTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.splashLosTA, Environment.getActivity(), graphicsloseSheep, textureX, textureY);
        ITextureRegion splashEndTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.splashEndTA, Environment.getActivity(), graphicsEndSheep, textureX, textureY);

        //Creates the 4 main background sprites.
        this.grass = new Sprite(spritePosition, spritePosition, gameTR, Environment.getEngine().getVertexBufferObjectManager());
        //	this.startSheep = new Sprite(spritePosition, spritePosition, splashStartTR, Environment.getEngine().getVertexBufferObjectManager());
        this.losSheep = new Sprite(spritePosition, spritePosition, splashLosTR, Environment.getEngine().getVertexBufferObjectManager());
        this.endSheep = new Sprite(spritePosition, spritePosition, splashEndTR, Environment.getEngine().getVertexBufferObjectManager());

        //Attach the two scenes to make them touchable and the game scene has also a method which needs to be updarted all the time during the game.
        SceneManager.splashScene.setOnSceneTouchListener((IOnSceneTouchListener) Environment.getActivity());
        SceneManager.gameScene.setOnSceneTouchListener((IOnSceneTouchListener) Environment.getActivity());
        SceneManager.gameScene.registerUpdateHandler((IUpdateHandler) Environment.getActivity());
    }

    /**
     * In this method it is possible to switch between two different scenes the game scene and the splash scene. Within the splash scene you can also distinguish between win or lose.
     *
     * @param toCreateScene: Is an enumeration for changing to game or splash scene.
     */
    public void createScene(AllScenes toCreateScene) {

        if (AllScenes.GAME == toCreateScene) {
            this.grass.setPosition((Environment.getCamera().getWidth() - grass.getWidth()) / screenMiddle, (Environment.getCamera().getHeight() - this.grass.getHeight()) / screenMiddle);
            SceneManager.gameScene.attachChild(grass);
            this.gameTA.load();
            setCurrentScene(AllScenes.GAME);
        }
    }

    /**
     * Unregister the current splash or game scene. Therefore the detachChildren method is called. This method detach everything what was once attached even the onTouchRegisters.
     *
     * @param toBeUnregistered: Enumeration which scene should be detached, the game scene or splash scene.
     */
    public void unregister(AllScenes toBeUnregistered) {
        if (AllScenes.GAME == toBeUnregistered) {
            this.gameTA.unload();
            SceneManager.gameScene.detachChildren();
            SceneManager.gameScene.reset();
        }
    }

    /**
     * Specify the next scene which will become the current scene.
     *
     * @param nextScene: Which is the next scene.
     */
    public static void setCurrentScene(AllScenes nextScene) {
        SceneManager.currentScene = nextScene;
        switch (currentScene) {
            case GAME:
                Environment.getEngine().setScene(SceneManager.gameScene);
                break;
            default:
                break;
        }
    }

    /**
     * Enumeration of all scenes in the game.
     *
     * return enum: Both scenes which can occur.
     */
    public enum AllScenes {
        GAME
    }
}
