package fr.uge.andrawid.model.draw.container;

import android.graphics.Canvas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.uge.andrawid.model.Coordinates;
import fr.uge.andrawid.model.draw.model.ShapeProperties;
import fr.uge.andrawid.model.draw.model.DrawableShape;

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
    }

    public void moveSelectedShape(float x, float y) {

        if (selectedShape == null)
            return ;

        shapeContainer.put(selectedShape, new ShapeProperties(x, y));
        fireListeners();
    }

    public void deleteSelectedShape() {

        if (selectedShape == null)
            return ;

        shapeContainer.remove(selectedShape);
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

    private void fireListeners() {
        for (ShapeContainerChangeListener listener: changeListeners)
            listener.onShapeContainerChange();
    }

    public JSONObject toJSON() {
        return new JSONObject() {{
            try {
                put("type", "drawing");
                put("modificationDate", System.currentTimeMillis());
                put("content", new JSONArray() {{
                    shapeContainer.forEach(((drawableShape, shapeProperties) -> {
                        put(new JSONObject() {{
                            try {
                                put("drawableShape", drawableShape.toJSON());
                                put("shapeProperties", shapeProperties.toJSON());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }});
                    }));
                }});
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }};
    }
}
