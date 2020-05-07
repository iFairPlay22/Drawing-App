package fr.uge.andrawid.model;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import fr.uge.andrawid.model.draw.DrawableShape;

public class ShapeContainer {
    private final Map<DrawableShape, ShapeProperties> shapeContainer = new HashMap<>();
    private HashSet<ShapeContainerChangeListener> changeListeners = new HashSet<>();

    public void draw(Canvas canvas) {

        Objects.requireNonNull(canvas);

        for (DrawableShape shape : shapeContainer.keySet()) {
            shape.drawShape(shapeContainer.get(shape), canvas);
        }
    }

    public boolean add(DrawableShape shape, ShapeProperties properties) {

        Objects.requireNonNull(shape);
        Objects.requireNonNull(properties);

        if (shapeContainer.containsKey(shape)) {
            shapeContainer.put(shape, properties);
            fireListeners();
            return false;
        }
        shapeContainer.put(shape, properties);
        fireListeners();
        return true;
    }

    public void addChangeListener(ShapeContainerChangeListener listener) {
        changeListeners.add(listener);
    }

    public void removeChangeListener(ShapeContainerChangeListener listener) {
        changeListeners.remove(listener);
    }

    public void fireListeners() {
        for (ShapeContainerChangeListener listener: changeListeners)
            listener.onShapeContainerChange();
    }
}
