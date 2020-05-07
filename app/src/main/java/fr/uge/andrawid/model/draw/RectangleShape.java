package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import java.util.Objects;

import fr.uge.andrawid.model.ShapeProperties;

public class RectangleShape extends AbstractDrawableShape {

    public RectangleShape(float[] coordinates) {
        super(coordinates);
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        Objects.requireNonNull(properties);
        Objects.requireNonNull(canvas);


    }
}
