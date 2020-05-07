package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.uge.andrawid.model.draw.LineShape;
import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeProperties;
import fr.uge.andrawid.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LineShape lineShape = new LineShape(new float[]{0.0f, 0.0f, 50.0f, 15.0f});

        final ShapeContainer shapeContainer = new ShapeContainer();

        shapeContainer.add(lineShape, new ShapeProperties(10.0f, 30.0f));



        final DrawingView drawingView = findViewById(R.id.drawingView);

        drawingView.setModel(shapeContainer);
        // listener to move the line
        drawingView.setOnClickListener( v -> {
            shapeContainer.add(lineShape, new ShapeProperties(20.0f, 40.0f));
        });
    }
}
