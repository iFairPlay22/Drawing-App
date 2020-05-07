package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import java.util.Objects;

import fr.uge.andrawid.model.ShapeProperties;

public class AbstractDrawableShape implements DrawableShape {

    protected final float[] coordinates;

    public AbstractDrawableShape(float[] coordinates) {

        this.coordinates = Objects.requireNonNull(coordinates);
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {}

}
