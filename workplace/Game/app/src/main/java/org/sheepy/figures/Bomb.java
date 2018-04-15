package org.sheepy.figures;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.sheepy.Environment.Environment;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

/**
 * Bomb inherit the Figure class which inherit the Sprite and implements the IFigure class.
 * Hence it has to implement certain methods which were specified in the IFigure interface.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public class Bomb extends Animated {
    protected static final String graphicsExplosion = "Graphics/explosion/bomb_sequence.png";

    private static final int wholeImageSequenceWidth = 540;
    private static final int imageSequenceHeight = 64;
    private static final int imageSequenceRows = 10;
    private static final int imageSequenceColumns = 1;

    private static BitmapTextureAtlas bombTA;
    private static ITiledTextureRegion bombTR;

    /**
     * Calls the upper class of the heredity and pass the needed parameter to create the sprite.
     * The texture has to be generated in advanced because the sprite class needs it.
     *
     * @param pX: Current x position of the sheep
     * @param pY: Current y position of the sheep
     */
    public Bomb(float pX, float pY) {
        super(pX, pY, bombTR, Environment.getEngine().getVertexBufferObjectManager());
    }

    /**
     * Loads all the resources and images up which are needed for the sprite itself.
     * To generate a explosion sequence the method gets a parameter for the current image of the explosion.
     * Thats why the method is static, so that it can be called without creating the sprite object before.
     */
    protected static void loadResources() {
        bombTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), wholeImageSequenceWidth, imageSequenceHeight);
        bombTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bombTA, Environment.getActivity(), graphicsExplosion, textureX, textureY, imageSequenceRows, imageSequenceColumns);
        bombTA.load();
    }

    /**
     * @param pSecondsElapsed: Is not specially used in this method.
     */
    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.setPosition(this.mX, this.mY);
    }

    /* (non-Javadoc)
     * @see htc.figures.Figure#getPosX()
     */
    public float getPosX() {
        return this.mX;
    }

    /* (non-Javadoc)
     * @see htc.figures.Figure#getPosY()
     */
    public float getPosY() {
        return this.mY;
    }

    /* (non-Javadoc)
     * @see htc.figures.Figure#unloadFigure()
    */
    @Override
    public void unloadFigure() {
        if (!this.isDisposed()) {
            this.detachSelf();
            this.dispose();
        }
        Bomb.bombTA.unload();
    }
}
