package fr.uge.andrawid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Objects;

import fr.uge.andrawid.model.ShapeContainer;
import fr.uge.andrawid.model.ShapeContainerChangeListener;

public class DrawingView extends View {
    private ShapeContainer model;
    private ShapeContainerChangeListener cl = () -> invalidate();

    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setModel(ShapeContainer shapeContainer){
        if (model != null)
            shapeContainer.removeChangeListener(cl);
        this.model = Objects.requireNonNull(shapeContainer);
        model.addChangeListener(cl);
        this.invalidate();
    }

    public void onDraw(Canvas canvas){
        if(model != null){
            model.draw(canvas);
        }
    }
}