package fr.uge.andrawid.controller;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;

import fr.uge.andrawid.R;
import fr.uge.andrawid.model.draw.model.ShapeBuilder;
import fr.uge.andrawid.model.draw.container.ShapeContainer;
import fr.uge.andrawid.model.draw.model.ShapeProperties;
import fr.uge.andrawid.model.draw.model.ShapeKind;
import fr.uge.andrawid.model.save.FileManager;
import fr.uge.andrawid.view.DrawingView;

public class Controller {

    private ShapeContainer shapeContainer;
    private ShapeBuilder shapeBuilder;
    private DrawingView drawingView;
    private String drawingName;

    public Controller(DrawingView drawingView) {

        // TODO ?
        this.drawingView = drawingView;
        this.shapeContainer = new ShapeContainer();
        this.drawingView.setModel(this.shapeContainer);
        // TODO ?

        this.shapeBuilder = new ShapeBuilder();
        this.shapeBuilder.setShapeKind(ShapeKind.CURSIVE);
        FileManager.getInstance().init();
    }

    private float initialX;
    private float initialY;
    private ArrayList<Float> moveCoords;

    public void onRefresh(String drawingName) {
        FileManager fileManager = FileManager.getInstance();
        this.shapeContainer = fileManager.exists(Objects.requireNonNull(drawingName)) ? fileManager.load(drawingName) : fileManager.save(shapeContainer, drawingName);
        this.drawingView.setModel(this.shapeContainer);
        this.drawingName = drawingName;
    }

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
        shapeContainer.moveSelectedShape(initialX, initialY);
    }

    public void onShapeMovement(float x, float y) {
        shapeContainer.moveSelectedShape(x, y);
    }

    public void onShapeDelete() {
        shapeContainer.selectNearestShape(initialX, initialY);
        shapeContainer.deleteSelectedShape();
    }

    public void onSave() {
        if (drawingName == null)
            return ;

        FileManager.getInstance().save(shapeContainer, drawingName);
    }
}
