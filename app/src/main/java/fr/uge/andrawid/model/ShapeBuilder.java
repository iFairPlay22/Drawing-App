package fr.uge.andrawid.model;

import java.util.ArrayList;

import fr.uge.andrawid.model.draw.CursiveShape;
import fr.uge.andrawid.model.draw.DrawableShape;
import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.draw.RectangleShape;
import fr.uge.andrawid.model.draw.ShapeKind;

public class ShapeBuilder {
    private ShapeKind selectedShapeKind;

    private float initialX;
    private float initialY;
    private ArrayList<Float> cursiveCoords;

    /** to set the current kind of shape to create */
    public void setShapeKind(ShapeKind shapeKind) {
        selectedShapeKind = shapeKind;
    }

    /** to build a shape
     * @param coords coordinates of the shape:
     * coords[0] and coords[1] are the x and y of the start of the shape
     * coords[coords.length-2] and coords[coords.length-1] are the x and y of the end of the shape
     * some shape kinds (like CURSIVE) may admit intermediate points in the array
     */

    public void actionDown(float x, float y) {
        initialX = x;
        initialY = y;

        cursiveCoords = new ArrayList<>();
    }

    public void actionMove(float x, float y) {
        if (selectedShapeKind == ShapeKind.CURSIVE) {
            cursiveCoords.add(x - initialX);
            cursiveCoords.add(y - initialY);
        }
    }

    public DrawableShape actionUp(float x, float y) {
        return build(buildCoordinates(x, y));
    }

    public ShapeProperties getShapeProperties() {
        return new ShapeProperties(initialX, initialY);
    }

    private DrawableShape build(float[] coords) {
        switch (selectedShapeKind) {
            case SEGMENT:
                return new LineShape(coords);

            case RECTANGLE:
                return new RectangleShape(coords);

            case CURSIVE:
                return new CursiveShape(coords);

            default:
                return null;
        }
    }

    private float[] buildCoordinates(float x, float y) {
        int size = cursiveCoords.size() + 4;
        float[] coordinates = new float[size];

        coordinates[0] = 0.0f;
        coordinates[1] = 0.0f;

        int i = 2;
        for (Float coords : cursiveCoords) {
            coordinates[i] = coords;
            i++;
        }

        coordinates[size - 2] = x - initialX;
        coordinates[size - 1] = y - initialY;

        return coordinates;
    }
}
