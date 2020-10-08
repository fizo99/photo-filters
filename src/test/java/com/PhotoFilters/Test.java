package com.PhotoFilters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Test {
    @org.junit.Test
    public void testJpg() throws IOException {
        String path = Paths.get("").toAbsolutePath().toString();
        PhotoFilters.main(new String[]{path + "\\assets\\kitten.png"});
    }
//    @org.junit.Test
//    public void testGif(){
//        String path = Paths.get("").toAbsolutePath().toString();
//        PhotoFilters.main(new String[]{path + "\\assets\\mona.jpg"});
//    }
}
