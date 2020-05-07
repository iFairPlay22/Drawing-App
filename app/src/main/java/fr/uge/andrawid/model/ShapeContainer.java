package fr.uge.andrawid.model;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

import fr.uge.andrawid.model.draw.DrawableShape;

public class ShapeContainer {
    private final Map<DrawableShape, ShapeProperties> shapeContainer = new HashMap<>();

    public void draw(Canvas canvas) {
        for (DrawableShape shape : shapeContainer.keySet()) {
            shape.drawShape(shapeContainer.get(shape), canvas);
        }
    }

    public boolean add(DrawableShape shape, ShapeProperties properties) {
        if (shapeContainer.containsKey(shape)) {
            shapeContainer.put(shape, properties);
            return false;
        }
        shapeContainer.put(shape, properties);
        return true;
    }
}
