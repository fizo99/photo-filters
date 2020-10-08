package com.PhotoFilters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage myPicture = null;
    private Color[][] PixelArray = null;

    Image(String path) throws IOException {
        File file = new File(path);
        myPicture = ImageIO.read(file);
    }

    public int getHeight() {
        return myPicture.getHeight();
    }

    public int getWidth() {
        return myPicture.getWidth();
    }

    public void loadPixels() {
        if (PixelArray != null) return;
        else {
            int height = myPicture.getHeight();
            int width = myPicture.getWidth();
            PixelArray = new Color[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    PixelArray[i][j] = new Color(myPicture.getRGB(i, j));
                }
            }
        }
    }

    public void applyFilter(Applicable filter) throws IOException {
        filter.apply(PixelArray);
    }

    public void save() throws IOException {
        BufferedImage output = new BufferedImage(PixelArray.length, PixelArray[0].length,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < PixelArray.length; x++) {
            for (int y = 0; y < PixelArray[x].length; y++) {
                output.setRGB(x, y, PixelArray[x][y].getRGB());
            }
        }

        File outputfile = new File("output.jpg");
        ImageIO.write(output, "jpg", outputfile);
    }
}
