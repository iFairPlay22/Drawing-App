package fr.uge.andrawid.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import fr.uge.andrawid.model.Coordinates;
import fr.uge.andrawid.model.ShapeBuilder;
import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeProperties;
import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;

public class Controller {

    private ShapeContainer shapeContainer;
    private ShapeBuilder shapeBuilder;


    public Controller(DrawingView drawingView) {

        this.shapeContainer = new ShapeContainer();
        Objects.requireNonNull(drawingView).setModel(shapeContainer);

        this.shapeBuilder = new ShapeBuilder();
        this.shapeBuilder.setShapeKind(ShapeKind.CURSIVE);
    }

    private float initialX;
    private float initialY;
    private ArrayList<Float> moveCoords;

    public void onDown(float x, float y) {
        initialX = x;
        initialY = y;

        moveCoords = new ArrayList<>();
    }

    public void onMove(float x, float y) {
        if (!shapeBuilder.requireMoveAction()) {
            return;
        }

        moveCoords.add(x - initialX);
        moveCoords.add(y - initialY);
    }

    public void onUp(float x, float y) {
        shapeContainer.add(shapeBuilder.build(mergeCoords(x, y)), new ShapeProperties(initialX, initialY));
    }

    private float[] mergeCoords(float x, float y) {
        int size = moveCoords.size() + 4;
        float[] coordinates = new float[size];

        coordinates[0] = 0.0f;
        coordinates[1] = 0.0f;

        int i = 2;
        for (Float coords : moveCoords) {
            coordinates[i] = coords;
            i++;
        }

        coordinates[size - 2] = x - initialX;
        coordinates[size - 1] = y - initialY;

        return coordinates;
    }

    public void onShapeItemSelection(ShapeKind shapeKind) {
        this.shapeBuilder.setShapeKind(Objects.requireNonNull(shapeKind));
    }

    public void onShapeSelection() {
        shapeContainer.selectNearestShape(initialX, initialY);
    }

    public void onShapeMovement(float x, float y) {
        shapeContainer.moveSelectedShape(x, y);
    }

    public void onShapeDelete() {
        shapeContainer.selectNearestShape(initialX, initialY);
        shapeContainer.deleteSelectedShape();
    }
}
