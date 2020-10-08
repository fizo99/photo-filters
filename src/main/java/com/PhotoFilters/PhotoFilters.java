package com.PhotoFilters;

import com.PhotoFilters.Filters.Dithering;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PhotoFilters {
    public static void main(String[] args) throws IOException {
        Image im = new Image(args[0]);
        im.loadPixels();
        im.applyFilter(new Dithering());
        im.save();
    }

}
