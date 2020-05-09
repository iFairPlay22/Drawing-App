package fr.uge.andrawid.model;

import android.graphics.Color;
import android.graphics.Paint;

public class ShapeProperties {

    private final float originX;
    private final float originY;

    private int color = Color.BLACK;
    private float strokeWidth = 10;
    // color
    // lineWidth

    public ShapeProperties(float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public Coordinates getCoords(Coordinates coordinates) {
        return new Coordinates(originX, originY).sum(coordinates);
    }

}
