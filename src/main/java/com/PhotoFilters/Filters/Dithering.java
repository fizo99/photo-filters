package com.PhotoFilters.Filters;

import com.PhotoFilters.Applicable;

import java.awt.*;

//08.10 doesn`t work properly
public class Dithering implements Applicable {
    final int factor = 4;

    @Override
    public void apply(Color[][] pixelArray) {
        applyGreyScale(pixelArray);
        int width = pixelArray.length;
        int height = pixelArray[0].length;
        for (int y = 0; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                Color pixel = pixelArray[x][y];
                float r = pixel.getRed();
                float g = pixel.getGreen();
                float b = pixel.getBlue();

                pixelArray[x][y] = findClosestPaletteColor(r, g, b);

                float eR = r - pixelArray[x][y].getRed();
                float eG = g - pixelArray[x][y].getGreen();
                float eB = b - pixelArray[x][y].getBlue();

                applyError(pixelArray, x + 1, y, eR, eG, eB);
                applyError(pixelArray, x - 1, y + 1, eR, eG, eB);
                applyError(pixelArray, x, y + 1, eR, eG, eB);
                applyError(pixelArray, x + 1, y + 1, eR, eG, eB);
            }
        }
    }

    private Color findClosestPaletteColor(float r, float g, float b) {
        float newR = Math.round(factor * r / 255.0f) * (255.0f / factor);
        float newG = Math.round(factor * g / 255.0f) * (255.0f / factor);
        float newB = Math.round(factor * b / 255.0f) * (255.0f / factor);
        return new Color((int) newR, (int) newG, (int) newB);
    }

    private void applyError(Color[][] pixelArray, int x, int y, float errorRed, float errorGreen, float errorBlue) {
        Color color;
        float r, g, b;

        color = pixelArray[x][y];
        r = color.getRed() + errorRed * 7.0f / 16.0f;
        g = color.getGreen() + errorGreen * 7.0f / 16.0f;
        b = color.getBlue() + errorBlue * 7.0f / 16.0f;
        if (r > 255 || g > 255 || b > 255
                || r < 0 || g < 0 || b < 0) pixelArray[x][y] = findClosestPaletteColor(r, g, b);
        else pixelArray[x][y] = new Color((int) r, (int) g, (int) b);

        color = pixelArray[x][y];
        r = color.getRed() + errorRed * 3.0f / 16.0f;
        g = color.getGreen() + errorGreen * 3.0f / 16.0f;
        b = color.getBlue() + errorBlue * 3.0f / 16.0f;
        if (r > 255 || g > 255 || b > 255
                || r < 0 || g < 0 || b < 0) pixelArray[x][y] = findClosestPaletteColor(r, g, b);
        else pixelArray[x][y] = new Color((int) r, (int) g, (int) b);

        color = pixelArray[x][y];
        r = color.getRed() + errorRed * 5.0f / 16.0f;
        g = color.getGreen() + errorGreen * 5.0f / 16.0f;
        b = color.getBlue() + errorBlue * 5.0f / 16.0f;
        if (r > 255 || g > 255 || b > 255
                || r < 0 || g < 0 || b < 0) pixelArray[x][y] = findClosestPaletteColor(r, g, b);
        else pixelArray[x][y] = new Color((int) r, (int) g, (int) b);

        color = pixelArray[x][y];
        r = color.getRed() + errorRed * 1.0f / 16.0f;
        g = color.getGreen() + errorGreen * 1.0f / 16.0f;
        b = color.getBlue() + errorBlue * 1.0f / 16.0f;
        if (r > 255 || g > 255 || b > 255
                || r < 0 || g < 0 || b < 0) pixelArray[x][y] = findClosestPaletteColor(r, g, b);
        else pixelArray[x][y] = new Color((int) r, (int) g, (int) b);
    }

    private void applyGreyScale(Color[][] pixelArray) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int red = pixelArray[i][j].getRed();
                int green = pixelArray[i][j].getGreen();
                int blue = pixelArray[i][j].getBlue();
                int avg = (red + green + blue) / 3;
                pixelArray[i][j] = new Color(avg, avg, avg);
            }
        }
    }
}
