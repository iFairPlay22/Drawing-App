package fr.uge.andrawid.model;

import fr.uge.andrawid.model.draw.CursiveShape;
import fr.uge.andrawid.model.draw.DrawableShape;
import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.draw.RectangleShape;
import fr.uge.andrawid.model.draw.ShapeKind;

public class ShapeBuilder {
    private ShapeKind selectedShapeKind;

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
    public DrawableShape build(float[] coords) {
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
}
