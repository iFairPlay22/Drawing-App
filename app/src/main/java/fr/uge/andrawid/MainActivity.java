package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.GridView;

import fr.uge.andrawid.controller.EventManager;
import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;
import fr.uge.andrawid.view.ShapeArrayAdapter;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawingView drawingView = findViewById(R.id.drawingView);
        EventManager eventManager = new EventManager(drawingView);

        drawingView.setOnTouchListener(
                (v, event) -> {
                    switch (event.getActionMasked()) {

                        case MotionEvent.ACTION_DOWN:
                            eventManager.onDown(event.getX(), event.getY());
                            break;

                        case MotionEvent.ACTION_MOVE:
                            eventManager.onMove(event.getX(), event.getY());
                            break;

                        case MotionEvent.ACTION_UP:
                            eventManager.onUp(event.getX(), event.getY());
                            break;

                        default:
                            break;
                    }

                    return true;
                }
        );

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(2);
        gridView.setAdapter(new ShapeArrayAdapter(this, android.R.layout.simple_list_item_1, ShapeKind.values()));

        gridView.setOnItemClickListener( (adapterView, view, i, l) -> {
            eventManager.onShapeItemSelection((ShapeKind) adapterView.getItemAtPosition(i));
        });

    }
}