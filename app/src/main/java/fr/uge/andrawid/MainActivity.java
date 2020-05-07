package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;

import fr.uge.andrawid.model.draw.CursiveShape;
import fr.uge.andrawid.model.draw.DrawableShape;
import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeProperties;
import fr.uge.andrawid.model.draw.RectangleShape;
import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;



public class MainActivity extends AppCompatActivity {
    private ShapeKind selectedShapeKind = ShapeKind.CURSIVE;
    private DrawingView drawingView;
    private ShapeContainer shapeContainer;

    private float initialX;
    private float initialY;
    private ArrayList<Float> cursiveCoords;

    private float[] getCoordinates(float x, float y) {
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

    private DrawableShape createForm(float[] coordinates) {
        switch (selectedShapeKind) {
            case SEGMENT:
                return new LineShape(coordinates);

            case RECTANGLE:
                return new RectangleShape(coordinates);

            case CURSIVE:
                return new CursiveShape(coordinates);

            default:
                return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeContainer = new ShapeContainer();

        drawingView = findViewById(R.id.drawingView);
        drawingView.setModel(shapeContainer);


        drawingView.setOnTouchListener(

                (v, event) -> {
                    switch (event.getActionMasked()) {

                        case MotionEvent.ACTION_DOWN:

                            initialX = event.getX();
                            initialY = event.getY();

                            cursiveCoords = new ArrayList<>();

                            return true;

                        case MotionEvent.ACTION_MOVE:

                            if (selectedShapeKind == ShapeKind.CURSIVE) {
                                cursiveCoords.add(event.getX() - initialX);
                                cursiveCoords.add(event.getY() - initialY);
                            }

                            return true;

                        case MotionEvent.ACTION_UP:

                            shapeContainer.add(createForm(getCoordinates(event.getX(), event.getY())), new ShapeProperties(initialX, initialY));

                            return true;

                        default:

                            return true;
                    }
                }
        );

    }
}