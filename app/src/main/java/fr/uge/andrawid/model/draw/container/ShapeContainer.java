package fr.uge.andrawid.model.draw.container;

import android.graphics.Canvas;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.uge.andrawid.model.Coordinates;
import fr.uge.andrawid.model.draw.model.ShapeBuilder;
import fr.uge.andrawid.model.draw.model.ShapeKind;
import fr.uge.andrawid.model.draw.model.ShapeProperties;
import fr.uge.andrawid.model.draw.model.DrawableShape;

public class ShapeContainer {

    private final Map<DrawableShape, ShapeProperties> shapeContainer = new HashMap<>();
    private final Set<ShapeContainerChangeListener> changeListeners = new HashSet<>();
    private DrawableShape selectedShape;

    public ShapeContainer() {

    }

    public ShapeContainer(JSONObject jsonObject) throws JSONException {

        JSONArray jsonArray = Objects.requireNonNull(jsonObject).getJSONArray("content");
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        int size = jsonArray.length();

        for (int i = 0; i < size; i++) {
            JSONObject jsonForm = jsonArray.getJSONObject(i);
            JSONObject jsonDrawableShape = jsonForm.getJSONObject("drawableShape");
            JSONObject jsonShapeProperties = jsonForm.getJSONObject("shapeProperties");

            shapeBuilder.setShapeKind(ShapeKind.valueOf((String) jsonDrawableShape.get("shapekind")));
            add(shapeBuilder.build(fillData(jsonDrawableShape.getJSONArray("coordinates"))), new ShapeProperties(jsonShapeProperties));
        }

        fireListeners();

    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        try {
            JSONArray jsonContent = new JSONArray();

            shapeContainer.forEach(((drawableShape, shapeProperties) -> {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("drawableShape", drawableShape.toJSON());

                    Log.i("azerty", "drawableShape : " + drawableShape.toString());

                    jsonObject.put("shapeProperties", shapeProperties.toJSON());

                    Log.i("azerty", "shapeProperties : " + shapeProperties.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonContent.put(jsonObject);

            }));

            json.put("content", jsonContent);
            json.put("type", "drawing");
            json.put("modificationDate", System.currentTimeMillis());

            Log.i("azerty", "ShapeContainer : " + json.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json;
    }

    private float[] fillData(JSONArray jsonArray){

        int size = jsonArray.length();

        float[] fData = new float[size];

        for (int i = 0; i < size; i++) {
            try {
                fData[i] = Float.parseFloat(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return fData;
    }

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
}
