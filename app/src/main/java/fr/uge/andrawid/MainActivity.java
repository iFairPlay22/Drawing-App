package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.uge.andrawid.controller.Controller;
import fr.uge.andrawid.model.draw.ShapeKind;
import fr.uge.andrawid.view.DrawingView;



public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawingView drawingView = findViewById(R.id.drawingView);
        Controller controller = new Controller(drawingView);

        drawingView.setOnTouchListener(

                (v, event) -> {
                    switch (event.getActionMasked()) {

                        case MotionEvent.ACTION_DOWN:
                            controller.onDown(event.getX(), event.getY());
                            break;

                        case MotionEvent.ACTION_MOVE:
                            controller.onMove(event.getX(), event.getY());
                            break;

                        case MotionEvent.ACTION_UP:
                            controller.onUp(event.getX(), event.getY());
                            break;

                        default:
                            break;
                    }

                    return true;
                }
        );

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<ShapeKind>(this, android.R.layout.simple_list_item_1, ShapeKind.values()));

        lv.setOnItemClickListener( (adapterView, view, i, l) -> {
            controller.onShapeSelection((ShapeKind) adapterView.getItemAtPosition(i));
        });

    }
}