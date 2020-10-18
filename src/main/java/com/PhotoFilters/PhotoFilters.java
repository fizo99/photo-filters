package com.PhotoFilters;

import com.PhotoFilters.Filters.*;
//import com.PhotoFilters.Scaling.Bicubic;
import com.PhotoFilters.Scaling.Bicubic;
import com.PhotoFilters.Scaling.Bilinear;
import com.PhotoFilters.Scaling.NearestNeighbor;

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

             if ("dithering".equals(args[1])) im.filter(new Dithering());
        else if ("greyscale".equals(args[1])) im.filter(new GreyScale());
        else if ("sepia".equals(args[1]    )) im.filter(new Sepia());
        else if ("negative".equals(args[1] )) im.filter(new Negative());
        else if ("contrast".equals(args[1] )) im.filter(new Contrast());
        else if ("scale".equals(args[1]    ))
        {
            if("nn".equals(args[2])      ) im.scale(new NearestNeighbor(),Float.parseFloat(args[3]));
            if("bicubic".equals(args[2]) ) im.scale(new Bicubic(),Float.parseFloat(args[3]));
            if("bilinear".equals(args[2])) im.scale(new Bilinear(),Float.parseFloat(args[3]));
        }
        else if ("ascii".equals(args[1]))
        {
            im.filter(new ASCII());
            return;
        }
        else
        {
            System.out.println("Wrong option");
            return;
        }
        im.save(args[1], im.getPixels());
    }

}
