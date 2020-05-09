package fr.uge.andrawid.model.draw;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Objects;

import fr.uge.andrawid.model.Coordinates;
import fr.uge.andrawid.model.ShapeProperties;

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
    public void drawShape(ShapeProperties properties, Canvas canvas) {}

    @Override
    public Coordinates getTopLeftCoords() {
        return topLeftCoords;
    }

    @NonNull
    @Override
    public String toString() {
        return "DrawableShape : " + Arrays.toString(coordinates);
    }
}
