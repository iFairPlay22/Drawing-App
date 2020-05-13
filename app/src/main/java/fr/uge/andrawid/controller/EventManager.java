package fr.uge.andrawid.controller;

import java.util.Objects;

import fr.uge.andrawid.model.draw.model.ShapeKind;
import fr.uge.andrawid.view.DrawingView;

public class EventManager {

    private static final int TESTING_THIRD_CLICK_TIME = 250;
    private static final int TESTING_SELECTION_TIME = 2000;
    private static final int SELECTION_MAX_MOVES = 10;

    private boolean isFormSelected = false;
    private int moves = 0;
    private int down = 0;
    private long thirdClickTime = 0;
    private long selectionTime = 0;

    private final Controller controller;

    public EventManager(DrawingView drawingView) {
        controller = new Controller(Objects.requireNonNull(drawingView));
    }

    public void onDown(float x, float y) {

        if (testingThirdClick()) {

            // On teste le triple clic
            if (down == 0)
                thirdClickTime = System.currentTimeMillis();

            down++;

            if (tripleCick()) {
                controller.onShapeDelete();
                thirdClickTime = 0;
                down = 0;
            }
        }

        if (testingSelection()) {

            // On teste la selection

            if (moves == 0) {
                selectionTime = System.currentTimeMillis();
                isFormSelected = false;
            }
        }

        controller.onDown(x, y);
    }

    public void onMove(float x, float y) {

        if (testingSelection()) {

            // On teste la sélection

            moves++;
            controller.onMove(x, y);

        } else if (isSelected()) {
            if (!isFormSelected) {
                controller.onShapeSelection();
                isFormSelected = true;
            }
            controller.onShapeMovement(x, y);
        } else {
            controller.onMove(x, y);
        }
    }

    public void onUp(float x, float y) {

        if (!isSelected() && !testingThirdClick() && !tripleCick())
            controller.onUp(x, y);

        if (!testingSelection())
            moves = 0;

        if (!testingThirdClick())
            down = 0;

    }

    public void onShapeItemSelection(ShapeKind shapeKind) {
        controller.onShapeItemSelection(shapeKind);
    }

    private boolean testingSelection() {
        if (moves == 0)
                return true;

        long actualTime = System.currentTimeMillis() - selectionTime;
        return 0 <= actualTime && actualTime < TESTING_SELECTION_TIME;
    }

    private boolean isSelected() {
        return moves < SELECTION_MAX_MOVES;
    }

    private boolean testingThirdClick() {
        if (down == 0)
            return true;

        long actualTime = System.currentTimeMillis() - thirdClickTime;
        return 0 <= actualTime && actualTime < TESTING_THIRD_CLICK_TIME;
    }

    private boolean tripleCick() {
        return 2 <= down;
    }
}
