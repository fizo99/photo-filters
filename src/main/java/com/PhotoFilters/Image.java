package com.PhotoFilters;

import com.PhotoFilters.Filters.Filter;
import com.PhotoFilters.Scaling.Scaler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage myPicture = null;
    private Color[][] PixelArray = null;
    private String format = null;

    Image(String path) throws IOException {
        File file = new File(path);
        format = readFileFormat(file.getName());
        myPicture = ImageIO.read(file);
        PixelArray = loadPixels(myPicture);
    }

    public Color[][] loadPixels(BufferedImage im) {
        int height = im.getHeight();
        int width = im.getWidth();
        Color[][] pxArr = new Color[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pxArr[x][y] = new Color(im.getRGB(x, y));
            }
        }
        return pxArr;
    }
    public Color[][] getPixels() {
        return this.PixelArray;
    }
    public void setPixels(Color[][] pxArr) {
        this.PixelArray = pxArr;
    }

    public void filter(Filter filter) throws IOException {
        filter.apply(this.PixelArray);
    }
    public void scale(Scaler scaler, float factor){
        setPixels(scaler.scale(this.PixelArray,factor));
    }

    public void save(String name, Color[][] pxArr) {
        int width = pxArr.length;
        int height = pxArr[0].length;

        BufferedImage output = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                output.setRGB(x, y, pxArr[x][y].getRGB());
            }
        }
//
        try {
            File outputFile = new File(name+format);
            boolean test = ImageIO.write(output, format.substring(1), outputFile);
            if(test == true) System.out.println("Image saved as: " + name + format);
            else System.out.println("Error occured while saving file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readFileFormat(String fileName){
        StringBuilder s = new StringBuilder();
        boolean formatBegin = false;
        for(char c: fileName.toCharArray()){
            if(c == '.') formatBegin = true;
            if(formatBegin == false) continue;
            s.append(c);
        }
        return s.toString();
    }
}
