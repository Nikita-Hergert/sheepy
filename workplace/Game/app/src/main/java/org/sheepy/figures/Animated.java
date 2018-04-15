package org.sheepy.figures;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * This class is an abstract call to pass the methods from the IFigure interface as well as to avoid to create an instance of this class.
 * Moreover it has a method which is static so that recourses of an animated sprite can be loaded before the actually animated sprite will be created.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public abstract class Animated extends AnimatedSprite implements IFigure {
    /**
     * Global parameter how the animations should behave.
     */
    private static final boolean ANIMATION_LOOP = false;
    private static final int SHEEP_RUNNING_SPEED = 100;
    private static final int EXPLOSION_SEQUENCE = 65;

    /**
     * Calls the upper class of the animated sprite heredity to pass the needed parameter and the AndEngine take care of all the rest.
     *
     * @param pX:                         Current x position of the sprite.
     * @param pY:                         current y position of the sprite.
     * @param pTiledTextureRegion:        All information about the texture are stored in this object, like size and position.
     * @param pVertexBufferObjectManager: Buffer of the vertex manager.
     */
    public Animated(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }

    /**
     * Creates an specific animated figure which can be specified via the AllFigure enumeration.
     * Moreover the method is static so that it can be called without creating the Animated object which is not possible because the class is abstract.
     * This is needed because the resources of a animation sprite has to be uploaded at first before the actual animation sprite can be created.
     *
     * @param xPos:   Current x position of the sprite.
     * @param yPos:   current y position of the sprite.
     * @param figure: Current figure to create.
     * @return The created animation sprite reference.
     */
    public static AnimatedSprite createFigure(float xPos, float yPos, AllFigures figure) {
        AnimatedSprite createdFigure;

        switch (figure) {
            case BOMB:
                Bomb.loadResources();
                createdFigure = new Bomb(xPos, yPos);
                createdFigure.animate(EXPLOSION_SEQUENCE, ANIMATION_LOOP);
                break;
            case SHEEP:
                Sheep.loadResources();
                createdFigure = new Sheep(xPos, yPos);
                createdFigure.animate(SHEEP_RUNNING_SPEED);
                break;
            default:
                createdFigure = null;
                break;
        }
        return createdFigure;
    }

    /* (non-Javadoc)
     * @see htc.figures.IFigure#unloadFigure()
     */
    @Override
    public abstract void unloadFigure();


    /* (non-Javadoc)
     * @see htc.figures.IFigure#getPosX()
     */
    @Override
    public abstract float getPosX();

    /* (non-Javadoc)
     * @see htc.figures.IFigure#getPosY()
     */
    @Override
    public abstract float getPosY();
}
