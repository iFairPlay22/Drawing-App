package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;
import android.graphics.Path;

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

        Path path = new Path();

        path.moveTo(properties.getOriginX() + newCoordinates[0], properties.getOriginY() + newCoordinates[1]);

        for (int i = 2; i < coordinates.length; i+=2) {

            path.lineTo(properties.getOriginX() + coordinates[i], properties.getOriginY() + coordinates[i + 1]);

        }

        canvas.drawPath(path, properties.getPaint());
    }
}
