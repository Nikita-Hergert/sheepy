package org.sheepy.figures;

import org.sheepy.Environment.Environment;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 * DeadSheep inherit the Figure class which inherit the Sprite and implements the IFigure class.
 * Hence it has to implement certain methods which were specified in the IFigure interface.
 * 
 * @author Vitali Dettling on 30.01.2016.
 *
 */
public class DeadSheep extends Figure
{
	private static final String graphicsDeathSheep = "Graphics/Sheeps/deadSheep.png";

	private static final int dumy = -1;

	private static BitmapTextureAtlas deathSheepTA;
	private static TextureRegion deathSheepTR;

	/**
	 * Calls the upper class of the heredity and pass the needed parameter to create the sprite. 
	 * The texture has to be generated in advanced because the sprite class needs it. 
	 * 
	 * @param pX: Current x position of the sheep
	 * @param pY: Current y position of the sheep
	 */
	public DeadSheep(float pX, float pY)
	{
		super(pX, pY, deathSheepTR, Environment.getEngine().getVertexBufferObjectManager());
	}

	/**
	 * Loads all the resources and images up which are needed for the sprite itself. 
	 * Thats why the method is static, so that it can be called without creating the sprite object before. 
	 */
	protected static void loadResources()
	{
		deathSheepTA = new BitmapTextureAtlas(Environment.getActivity().getTextureManager(), figureSize, figureSize);
		deathSheepTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(deathSheepTA, Environment.getActivity(), graphicsDeathSheep, textureX, textureY);
		deathSheepTA.load();
	}

	/* (non-Javadoc)
	 * @see htc.figures.Figure#unloadFigure()
	 */
	@Override
	public void unloadFigure() 
	{/*Do nothing*/}

	/* (non-Javadoc)
	 * @see htc.figures.Figure#getPosX()
	 */
	@Override
	public float getPosX() 
	{
		/*Do nothing*/
		return dumy;
	}

	/* (non-Javadoc)
	 * @see htc.figures.Figure#getPosY()
	 */
	@Override
	public float getPosY()
	{
		/*Do nothing*/
		return dumy;
	}
}
