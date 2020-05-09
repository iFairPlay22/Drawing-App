package fr.uge.andrawid.controller;

import android.os.Handler;

import java.util.Objects;

import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;

public class EventManager {

    public static final int SELECTION_TIME = 2000;
    private static final int SELECTION_MAX_MOVES = 10;

    private int moves;
    private long time;

    private final Controller controller;
    private final Handler selectionHandler;
    private final Runnable onSelection;


    public EventManager(DrawingView drawingView) {

        controller = new Controller(Objects.requireNonNull(drawingView));
        selectionHandler = new Handler();
        onSelection = () -> controller.onShapeSelection();
        moves = 0;
        time = System.currentTimeMillis();
    }

    public void onDown(float x, float y) {
        moves = 0;
        time = System.currentTimeMillis();

        controller.onDown(x, y);
        selectionHandler.postDelayed(onSelection, SELECTION_TIME);
    }

    public void onMove(float x, float y) {
        if (testingSelection()) {

            // On teste la sélection

            controller.onMove(x, y);
            moves++;
        } else if (!isSelectionTimeOverAndSelected()) {

            // On ne teste plus la sélection
            // Il n'y a pas de sélection

            selectionHandler.removeCallbacks(onSelection);
            controller.onMove(x, y);
        } else {

            // On ne teste plus la sélection
            // Il a une sélection

        }
    }

    public void onUp(float x, float y) {

        if (testingSelection() || !isSelectionTimeOverAndSelected()) {

            // Il n'a pas de sélection

            controller.onUp(x, y);
        } else {

            // Il y a une sélection

        }
    }

    public void onShapeItemSelection(ShapeKind shapeKind) {
        controller.onShapeItemSelection(shapeKind);
    }

    private boolean testingSelection() {
        long actualTime = System.currentTimeMillis() - time;
        return 0 <= actualTime && actualTime < SELECTION_TIME;
    }

    private boolean isSelectionTimeOverAndSelected() {
        return !testingSelection() && moves < SELECTION_MAX_MOVES;
    }
}
