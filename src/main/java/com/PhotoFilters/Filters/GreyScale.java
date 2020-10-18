package com.PhotoFilters.Filters;

import java.awt.*;

public class GreyScale implements Filter {
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

                int avg = (r+b+g)/3;
                pixelArray[x][y] = new Color(avg,avg,avg);
            }
        }
    }
}
