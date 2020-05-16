package fr.uge.andrawid.model.draw.shapes;

import android.graphics.Canvas;

import java.util.Objects;

import fr.uge.andrawid.model.draw.model.AbstractDrawableShape;
import fr.uge.andrawid.model.draw.model.ShapeKind;
import fr.uge.andrawid.model.draw.model.ShapeProperties;

public class CircleShape extends AbstractDrawableShape {

    public CircleShape(float[] coordinates) {
        super(coordinates);
        if (Objects.requireNonNull(coordinates).length != 4)
            throw new IllegalArgumentException("Coordinates length of a circle must be 4!");
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {

        Objects.requireNonNull(properties);
        Objects.requireNonNull(canvas);

        canvas.drawCircle(properties.getOriginX() + coordinates[0], properties.getOriginY() + coordinates[1], Math.abs(coordinates[2] - coordinates[0]), properties.getPaint());
    }

    @Override
    protected ShapeKind getShapeKind() {
        return ShapeKind.CIRCLE;
    }
}
