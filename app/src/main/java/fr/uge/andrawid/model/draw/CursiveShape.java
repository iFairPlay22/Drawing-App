package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import java.util.Objects;

import fr.uge.andrawid.model.ShapeProperties;

public class CursiveShape extends AbstractDrawableShape {

    public CursiveShape(float[] coordinates) {
        super(coordinates);
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        Objects.requireNonNull(properties);
        Objects.requireNonNull(canvas);

        float[] newCoordinates = new float[coordinates.length];

        for (int i = 0; i < coordinates.length; i++) {

            if (i % 2 == 0) {

                newCoordinates[i] = properties.getOriginX() + coordinates[i];

            } else {

                newCoordinates[i] = properties.getOriginY() + coordinates[i];
            }

        }

        canvas.drawLines(newCoordinates, properties.getPaint());
    }
}
