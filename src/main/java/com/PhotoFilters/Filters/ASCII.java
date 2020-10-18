package com.PhotoFilters.Filters;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//https://marmelab.com/blog/2018/02/20/convert-image-to-ascii-art-masterpiece.html
//TODO: scaling to make it look good
public class ASCII implements Filter {
    private final String grayRamp = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~i!lI;:,\"^`'. ";
    @Override
    public void apply(Color[][] pixelArray) {
        GreyScale greyScale = new GreyScale();
        greyScale.apply(pixelArray);
        //increase  contrast
        Contrast contrast = new Contrast();
        contrast.apply(pixelArray);

        StringBuilder s = new StringBuilder();
        s.append("<center><pre style=\"font-family: Courier, \"Lucida Console\", monospace;\">");

        int width = pixelArray.length;
        int height = pixelArray[0].length;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Color c = pixelArray[x][y];
                int gs = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
                //int index = x + height*y;
                char nextChars = getCharacterForGrayScale(gs);
                //end of line
                if (x == width - 1) s.append("<br>");
                s.append(nextChars);
            }
        }
        s.append("</pre>");
        File output = new File("output.html");
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
            writer.write(s.toString());
            writer.flush();
            writer.close();
            System.out.println("File saved as output.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private char getCharacterForGrayScale(double grayScale){
        int length = grayRamp.length();
        int id = (int) Math.ceil(( (length-1) * grayScale ) / 255);
        return grayRamp.charAt(id);
    }
}
