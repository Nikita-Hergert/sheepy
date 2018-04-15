package org.sheepy.figures;

/**
 * Interface to specify which method are must be used in each sprite.
 *
 * @author Vitali Dettling on 30.01.2016.
 */
public interface IFigure {
    //This defines are being used of all sprites.
    int figureSize = 64;
    int textureX = 0;
    int textureY = 0;

    /**
     * Specify how the current sprite should be unloaded.
     */
    void unloadFigure();

    /**
     * The x position of a sprite.
     *
     * @return float: Current x position of a sprite.
     */
    float getPosX();

    /**
     * The y position of a sprite.
     *
     * @return float: Current y position of a sprite.
     */
    float getPosY();

    /**
     * Enumeration of all figures which can occur in the game.
     *
     * @return enum: All figures of the game.
     */
    enum AllFigures {
        SHEEP,
        BOMB,
        DEADSHEEP

    }
}
