package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import fr.uge.andrawid.model.draw.DrawableShape;
import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeProperties;
import fr.uge.andrawid.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private ShapeContainer shapeContainer;

    private float initialX;
    private float initialY;

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

                            return true;

                        case MotionEvent.ACTION_MOVE:

                            return true;

                        case MotionEvent.ACTION_UP:

                            shapeContainer.add(new LineShape(new float[]{0.0f, 0.0f, event.getX() - initialX, event.getY() - initialY}), new ShapeProperties(initialX, initialY));

                            return true;

                        default:

                            return true;
                    }
                }
        );

    }
}