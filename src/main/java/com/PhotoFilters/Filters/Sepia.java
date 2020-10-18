package com.PhotoFilters.Filters;

import java.awt.*;

//https://www.geeksforgeeks.org/image-procesing-java-set-6-colored-image-sepia-image-conversion/
public class Sepia implements Filter {
    @Override
    public void apply(Color[][] pixelArray) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = pixelArray[x][y];
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();

                double newRed = 0.393*r + 0.769*g + 0.189*b;
                double newGreen = 0.349*r + 0.686*g + 0.168*b;
                double newBlue = 0.272*r + 0.534*g + 0.131*b;

                if(newRed > 255)   newRed = 255;
                if(newGreen > 255) newGreen = 255;
                if(newBlue > 255)  newBlue = 255;
                pixelArray[x][y] = new Color((int)newRed,(int)newGreen,(int)newBlue);
            }
        }
    }
}
