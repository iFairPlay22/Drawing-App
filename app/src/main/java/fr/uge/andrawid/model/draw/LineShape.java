package fr.uge.andrawid.model;

import android.graphics.Canvas;

public class LineShape implements DrawableShape {
    private final float[] coordinates;

    public LineShape(float[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        canvas.drawLine(properties.getOriginX() + coordinates[0], properties.getOriginY() + coordinates[1],
                properties.getOriginX() + coordinates[2], properties.getOriginY() + coordinates[3], properties.getPaint());
    }
}
