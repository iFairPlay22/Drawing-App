package fr.uge.andrawid.model.draw.model;

import android.graphics.Canvas;

import org.json.JSONObject;

import fr.uge.andrawid.model.Coordinates;

public interface DrawableShape {

    public void drawShape(ShapeProperties properties, Canvas canvas);

    public Coordinates getTopLeftCoords();

    public JSONObject toJSON();
}
