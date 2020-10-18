package com.PhotoFilters.Scaling;

import java.awt.*;

public interface Scaler {
    Color[][] scale(Color[][] PixelArray, float factor);
}
