package com.PhotoFilters.Scaling;

import java.awt.*;

//https://github.com/harrisoncattell/Nearest-Neighbour-Image-Interpolation/blob/master/nn_int.m
public class NearestNeighbor implements Scaler {
    @Override
    public Color[][] scale(Color[][] PixelArray, float factor) {
        int width = PixelArray.length;
        int height = PixelArray[0].length;

        int newWidth = (int)(width * factor);
        int newHeight = (int)(height * factor);
        //new BIGGER or SMALLER pic
        Color[][] pa = new Color[newWidth][newHeight];

        for(int x = 0; x < newWidth; x++){
            for(int y = 0; y < newHeight; y++){
                int a = (int) Math.ceil(x/factor);
                int b = (int) Math.ceil(y/factor);
                //overflow handle
                if(a == newWidth / factor)  a = a - 1;
                if(b == newHeight / factor) b = b - 1;

                pa[x][y] = PixelArray[a][b];
            }
        }
        return pa;
    }

}
