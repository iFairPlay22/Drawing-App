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

        for (DrawableShape shape : this.shapeContainer.keySet()) {
            shape.drawShape(this.shapeContainer.get(shape), canvas);
        }
    }

    public boolean add(DrawableShape shape, ShapeProperties properties) {

        final boolean res = !this.shapeContainer.containsKey(shape);

        this.shapeContainer.put(Objects.requireNonNull(shape), Objects.requireNonNull(properties));
        fireListeners();

        return res;
    }

    public void addChangeListener(ShapeContainerChangeListener listener) {

        changeListeners.add(Objects.requireNonNull(listener));
        fireListeners();

    }

    public void removeChangeListener(ShapeContainerChangeListener listener) {

        changeListeners.remove(Objects.requireNonNull(listener));
        fireListeners();

    }

    public void fireListeners() {
        for (ShapeContainerChangeListener listener: changeListeners)
            listener.onShapeContainerChange();
    }
}
