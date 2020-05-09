package fr.uge.andrawid.model;

import android.graphics.Canvas;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.uge.andrawid.model.draw.DrawableShape;

public class ShapeContainer {

    private final Map<DrawableShape, ShapeProperties> shapeContainer = new HashMap<>();
    private final Set<ShapeContainerChangeListener> changeListeners = new HashSet<>();
    private DrawableShape selectedShape;

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

    public void selectNearestShape(float x, float y) {

        Coordinates eventCoords = new Coordinates(x, y);
        Coordinates nearestCoords = null;
        DrawableShape nearestShape = null;

        for (DrawableShape drawableShape : shapeContainer.keySet()) {

            ShapeProperties shapeProperties = shapeContainer.get(drawableShape);

            Coordinates actualCoords = shapeProperties.getCoords(drawableShape.getTopLeftCoords()).getDistance(eventCoords);

            if (actualCoords.isSmallerThan(nearestCoords)) {
                nearestShape = drawableShape;
                nearestCoords = actualCoords;
            }
        }

        this.selectedShape = nearestShape;
        moveSelectedShape(x, y);
    }

    public void moveSelectedShape(float x, float y) {
        // ShapeProperties shapeProperties = shapeContainer.get(selectedShape);

        shapeContainer.put(Objects.requireNonNull(selectedShape), new ShapeProperties(x, y));
        fireListeners();
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

    public void deleteSelectedShape() {
        shapeContainer.remove(selectedShape);
    }
}
