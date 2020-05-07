package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

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

        canvas.drawRect( properties.getOriginX() + coordinates[0], properties.getOriginY() + coordinates[1],properties.getOriginX() + coordinates[2], properties.getOriginY() + coordinates[3], properties.getPaint());
    }
}
