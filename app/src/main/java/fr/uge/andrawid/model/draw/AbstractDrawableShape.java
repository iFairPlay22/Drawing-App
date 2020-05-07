package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import fr.uge.andrawid.model.ShapeProperties;

public class AbstractDrawableShape implements DrawableShape {

    protected final float[] coordinates;

    public AbstractDrawableShape(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public void drawShape(ShapeProperties properties, Canvas canvas) {

    }

}
