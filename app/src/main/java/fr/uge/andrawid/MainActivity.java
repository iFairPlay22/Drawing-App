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

        DrawingView dv = findViewById(R.id.drawingView);
        final ShapeContainer sc = new ShapeContainer();
        final LineShape ls = new LineShape(new float[]{0.0f, 0.0f, 50.0f, 15.0f});
        sc.add(ls, new ShapeProperties(10.0f, 30.0f));
        dv.setModel(sc);
        // listener to move the line
        dv.setOnClickListener( v -> {
            sc.add(ls, new ShapeProperties(20.0f, 40.0f));
        });
    }
}
