package com.PhotoFilters.Filters;

import java.awt.*;

public class Negative implements Filter {
    @Override
    public void apply(Color[][] pixelArray) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = pixelArray[x][y];
                int r = 255 - c.getRed();
                int g = 255 - c.getGreen();
                int b = 255 - c.getBlue();

                pixelArray[x][y] = new Color(r,g,b);
            }
        }
    }
}
