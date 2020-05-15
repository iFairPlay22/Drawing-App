package fr.uge.andrawid.model.draw.model;

import android.graphics.Canvas;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

import fr.uge.andrawid.model.Coordinates;

public class AbstractDrawableShape implements DrawableShape {

    protected final float[] coordinates;
    private final Coordinates topLeftCoords;

    public AbstractDrawableShape(float[] coordinates) {
        if (Objects.requireNonNull(coordinates).length < 2 && coordinates.length % 2 == 1){
            throw new IllegalArgumentException("Coordinates length must be pair!");
        }
        this.coordinates = coordinates;

        Coordinates topLeft = new Coordinates(this.coordinates[0], this.coordinates[1]);

        for (int i = 2; i < this.coordinates.length; i += 2) {
            Coordinates actualDistance = new Coordinates(this.coordinates[i], this.coordinates[i + 1]);

            if (actualDistance.isSmallerThan(topLeft)) {
                topLeft = actualDistance;
            }
        }

        this.topLeftCoords = topLeft;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject() {{
            try {
                put("coordinates", new JSONArray(coordinates));
                put("shapekind", getShapeKind());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }};
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {}

    @Override
    public Coordinates getTopLeftCoords() {
        return topLeftCoords;
    }


    protected ShapeKind getShapeKind() {
        return null;
    }
}
