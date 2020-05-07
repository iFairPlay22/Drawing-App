package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import fr.uge.andrawid.model.ShapeProperties;

public interface DrawableShape {

    public void drawShape(ShapeProperties properties, Canvas canvas);

}
