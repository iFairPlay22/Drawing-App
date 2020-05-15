package fr.uge.andrawid.model.draw.model;

import android.graphics.Color;
import android.graphics.Paint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import fr.uge.andrawid.model.Coordinates;

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

    public ShapeProperties(JSONObject jsonShapeProperties) throws JSONException {
        this((float) Objects.requireNonNull(jsonShapeProperties).get("originX"), (float) jsonShapeProperties.get("originY"));
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("originX", originX);
            jsonObject.put("originY", originY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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

