package fr.uge.andrawid.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Coordinates {

    private final float x;
    private final float y;

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private float getDistanceX(float x) {
        return Math.abs(this.x - x);
    }

    private float getDistanceY(float y) {
        return Math.abs(this.y - y);
    }

    public Coordinates getDistance(Coordinates coordinates) {
        return new Coordinates(getDistanceX(Objects.requireNonNull(coordinates).x), getDistanceY(coordinates.y));
    }

    public boolean isSmallerThan(Coordinates c) {
        if (c == null)
            return true;

        if (x <= c.x && y <= c.y)
            return true;

        if (c.x <= x && c.y <= y)
            return false;

        if (x <= c.x || y <= c.y)
            return true;

        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public Coordinates sum(Coordinates coordinates) {
        return new Coordinates(x + coordinates.x, y + coordinates.y);
    }

    public JSONObject toJSON() {
        return new JSONObject() {{
            try {
                put("x", x);
                put("y", y);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }};
    }
}
