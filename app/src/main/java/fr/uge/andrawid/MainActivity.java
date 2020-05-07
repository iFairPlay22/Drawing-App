package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;

import fr.uge.andrawid.model.ShapeBuilder;
import fr.uge.andrawid.model.draw.CursiveShape;
import fr.uge.andrawid.model.draw.DrawableShape;
import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeProperties;
import fr.uge.andrawid.model.draw.RectangleShape;
import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;



public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private ShapeContainer shapeContainer;
    private ShapeBuilder shapeBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeContainer = new ShapeContainer();
        shapeBuilder = new ShapeBuilder();

        drawingView = findViewById(R.id.drawingView);
        drawingView.setModel(shapeContainer);

        shapeBuilder.setShapeKind(ShapeKind.CURSIVE);

        drawingView.setOnTouchListener(

                (v, event) -> {
                    switch (event.getActionMasked()) {

                        case MotionEvent.ACTION_DOWN:

                            shapeBuilder.actionDown(event.getX(), event.getY());

                            return true;

                        case MotionEvent.ACTION_MOVE:

                            shapeBuilder.actionMove(event.getX(), event.getY());

                            return true;

                        case MotionEvent.ACTION_UP:

                            shapeContainer.add(shapeBuilder.actionUp(event.getX(), event.getY()), shapeBuilder.getShapeProperties());

                            return true;

                        default:

                            return true;
                    }
                }
        );

    }
}