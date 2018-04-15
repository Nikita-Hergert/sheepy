package org.sheepy.figures;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.sheepy.Environment.Environment;

import java.util.Random;

/**
 * Sheep inherit the Animated class which inherit the AnimatedSprite and implements the IFigure class.
 * Hence it has to implement certain methods which were specified in the IFigure interface.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public class Sheep extends Animated {
    private static final String graphicsSheepSequence = "Graphics/Sheeps/sheep_sequence.png";

    private static final int wholeImageSequenceWidth = 192;
    private static final int imageSequenceHeight = 64;
    private static final int imageSequenceRows = 3;
    private static final int imageSequenceColumns = 1;

    protected static BitmapTextureAtlas sheepTA;
    protected static ITiledTextureRegion sheepTR;

    private float xPos;
    private float yPos;

    private int speed;

    /**
     * Calls the upper class of the heredity and pass the needed parameter to create the sprite.
     * The texture has to be generated in advanced because the sprite class needs it.
     *
     * @param pX: Current x position of the sheep
     * @param pY: Current y position of the sheep
     */
    public Sheep(float pX, float pY) {
        super(pX, pY, sheepTR, Environment.getEngine().getVertexBufferObjectManager());

        speed = new Random().nextInt(15) + 5;

        this.xPos = pX;
        this.yPos = pY;
    }

    /**
     * Loads all the resources and images up which are needed for the sprite itself.
     * That's why the method is static, so that it can be called without creating the sprite object before.
     */
    protected static void loadResources() {
        sheepTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), wholeImageSequenceWidth, imageSequenceHeight);
        sheepTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(sheepTA, Environment.getActivity(), graphicsSheepSequence, textureX, textureY, imageSequenceRows, imageSequenceColumns);
        sheepTA.load();
    }

    /**
     * This method is being called all the time automatically from the system.
     * Set the sheep to the next position of the screen.
     * The speed of the sheep is randomly generated once when the sprite object is created.
     *
     * @param pSecondsElapsed: Is not specially used in this method.
     */
    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.yPos -= speed;
        this.setPosition(this.xPos, this.yPos);
    }

    /* (non-Javadoc)
     * @see htc.figures.Animated#getPosX()
     */
    public float getPosX() {
        return this.xPos;
    }

    /* (non-Javadoc)
     * @see htc.figures.Animated#getPosY()
     */
    public float getPosY() {
        return this.yPos;
    }

    /* (non-Javadoc)
     * @see htc.figures.Animated#unloadFigure()
     */
    public void unloadFigure() {
        if (!this.isDisposed()) {
            this.detachSelf();
            this.dispose();
        }
        Sheep.sheepTA.unload();
    }
}
