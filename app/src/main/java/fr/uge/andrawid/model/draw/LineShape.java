package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import fr.uge.andrawid.model.ShapeProperties;

public class LineShape extends AbstractDrawableShape {

    public LineShape(float[] coordinates) {
        super(coordinates);
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        canvas.drawLine(properties.getOriginX() + coordinates[0], properties.getOriginY() + coordinates[1],
                properties.getOriginX() + coordinates[2], properties.getOriginY() + coordinates[3], properties.getPaint());
    }
}
