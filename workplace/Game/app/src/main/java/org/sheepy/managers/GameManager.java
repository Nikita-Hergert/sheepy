package org.sheepy.managers;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.sheepy.Environment.Environment;
import org.sheepy.figures.Animated;
import org.sheepy.figures.Bomb;
import org.sheepy.figures.Figure;
import org.sheepy.figures.IFigure;
import org.sheepy.figures.Sheep;
import org.sheepy.utils.Generator;

/**
 * Created by Vitali Dettling on 27.02.2016.
 */
public class GameManager {

    private static final int bellowScreemStartPosY = 480;
    private static final int behindTheScene = -64;
    private static final int COUNT_RESET = 0;
    private static final float END_SIMULATION_BOMB = 0.650f;

    private int winCounter = COUNT_RESET;

    private boolean isBombSet = false;

    private Sprite currentBomb;
    private AnimatedSprite currentSheep;
    private AnimatedSprite animatedBomb;

    public Sprite getCurrentBomb() {
        return currentBomb;
    }

    public boolean isBombSet() {
        return this.isBombSet;
    }

    public void setBomb(boolean bomb) {
        this.isBombSet = bomb;
    }

    public AnimatedSprite getCurrentSheep() {
        return currentSheep;
    }

    public boolean sheepBehindUpperScreen(){
        return ((Animated) currentSheep).getPosY() < behindTheScene;
    }

    public String getWinCounter() {
        return Integer.toString(this.winCounter);
    }

    public void incrementWinCounter() {
        this.winCounter++;
    }

    public void createBombFigure(TouchEvent pSceneTouchEvent) {
        currentBomb = Figure.createFigure(pSceneTouchEvent.getX(), pSceneTouchEvent.getY(), IFigure.AllFigures.BOMB);
        SceneManager.getGameScene().attachChild(currentBomb);
    }

    /**
     * Start the simulation of the bomb as well as it start the sound of the explosion.
     * Furthermore it unload all unneeded sprites from the game and creates a corpses of the sheep.
     */
    public void simulateExplosion() {
        this.isBombSet = false;

        ((Sheep) this.currentSheep).unloadFigure();
        SceneManager.getGameScene().detachChild(this.currentSheep);

        animatedBomb = Animated.createFigure(((Bomb) currentBomb).getPosX(), ((Bomb) currentBomb).getPosY(), IFigure.AllFigures.BOMB);
        SceneManager.getGameScene().attachChild(animatedBomb);
        SceneManager.getGameScene().detachChild(currentBomb);

        Sprite deadSheep = Figure.createFigure(((Animated) currentSheep).getPosX(), ((Animated) currentSheep).getPosY(), IFigure.AllFigures.DEADSHEEP);
        SceneManager.getGameScene().attachChild(deadSheep);

        this.currentSheep = null;
        this.currentBomb = null;

        //Removes the exploded bomb from the screen.
        Environment.getEngine().registerUpdateHandler(new TimerHandler(END_SIMULATION_BOMB, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (pTimerHandler.isTimerCallbackTriggered()) {
                    ((Bomb) animatedBomb).unloadFigure();
                    SceneManager.getGameScene().detachChild(animatedBomb);
                }
            }
        }));
    }

    /**
     * Creates a new sheep when the last one hits a bomb. The sheep will appear in the game between 1 and 5 seconds after the last sheep died.
     * For that a onTimeHandler will be created which will be activated after a certain among of time.
     */
    public void createNewSheep() {//Sheep animation.
        Environment.getEngine().registerUpdateHandler(new TimerHandler(Generator.getTimeCreateNewSheep(), new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (pTimerHandler.isTimerCallbackTriggered()) {
                    currentSheep = Animated.createFigure(Generator.getRandomNumberBelowScreen(), bellowScreemStartPosY, IFigure.AllFigures.SHEEP);
                    SceneManager.getGameScene().attachChild(currentSheep);
                }
            }
        }));
    }
}
