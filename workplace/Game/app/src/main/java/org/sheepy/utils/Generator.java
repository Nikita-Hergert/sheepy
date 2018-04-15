package org.sheepy.utils;

import org.sheepy.figures.IFigure;

import java.util.Random;

/**
 * Created by BigBrother on 27.02.2016.
 */
public class Generator {

    private static final int cameraWidth = 800;
    private static final int atLeastOneSecond = 1;
    private static final int maxTimeNextSheepSeconds = 4;

    private static Random randomNumber;

    static {
        randomNumber = new Random();
    }

    public static int getRandomNumberBelowScreen() {
        return randomNumber.nextInt(cameraWidth - IFigure.figureSize);
    }

    public static float getTimeCreateNewSheep() {
        return (float) randomNumber.nextInt(maxTimeNextSheepSeconds) + atLeastOneSecond;
    }

}
