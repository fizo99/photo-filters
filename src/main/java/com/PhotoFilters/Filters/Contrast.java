package com.PhotoFilters.Filters;

import java.awt.*;

//https://www.dfstudios.co.uk/articles/programming/image-programming-algorithms/image-processing-algorithms-part-5-contrast-adjustment/
public class Contrast implements Filter{
    // intensity of contrast
    private final int factor = 128;
    @Override
    public void apply(Color[][] pixelArray) {
        float F = (259 * (factor + 255))/(255*(259-factor));

        int width = pixelArray.length;
        int height = pixelArray[0].length;

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = pixelArray[x][y];
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();

                int newRed = truncate(F * (r - 128) + 128);
                int newGreen = truncate(F * (g - 128) + 128);
                int newBlue = truncate(F * (b - 128) + 128);

                pixelArray[x][y] = new Color(newRed,newGreen,newBlue);
            }
        }
    }
    private int truncate(double val){
        if(val > 255) return 255;
        else if (val < 0) return 0;
        else return (int)val;
    }
}
