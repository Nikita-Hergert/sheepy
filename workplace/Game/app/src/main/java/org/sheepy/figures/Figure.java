package org.sheepy.figures;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * This class is an abstract call to pass the methods from the IFigure interface as well as to avoid to create an instance of this class.
 * Moreover it has a method which is static so that recourses of an sprite can be loaded before the actually sprite will be created.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public abstract class Figure extends Sprite implements IFigure {
    /**
     * Calls the upper class of the sprite heredity to pass the needed parameter and the AndEngine take care of all the rest.
     *
     * @param pX:                        Current x position of the sprite.
     * @param pY:                        current y position of the sprite.
     * @param pTextureRegion:            All information about the texture are stored in this object, like size and position.
     * @param vertexBufferObjectManager: Buffer of the vertex manager.
     */
    public Figure(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, vertexBufferObjectManager);
    }

    /**
     * Creates an specific figure which can be specified via the AllFigure enumeration.
     * Moreover the method is static so that it can be called without creating the Figure object which is not possible because the class is abstract.
     * This is needed because the resources of a sprite has to be uploaded at first before the actual sprite can be created.
     *
     * @param xPos:   Current x position of the sprite.
     * @param yPos:   current y position of the sprite.
     * @param figure: Current figure to create.
     * @return The created sprite reference.
     */
    public static Sprite createFigure(float xPos, float yPos, AllFigures figure) {
        Sprite createdFigure;
        switch (figure) {
            case DEADSHEEP:
                DeadSheep.loadResources();
                createdFigure = new DeadSheep(xPos, yPos);
                break;
            case BOMB:
                Bomb.loadResources();
                createdFigure = new Bomb(xPos, yPos);
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
