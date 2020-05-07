package fr.uge.andrawid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import fr.uge.andrawid.model.ShapeContainer;

public class DrawingView extends View {
    private ShapeContainer model;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public DrawingView(Context context) {
        super(context);
    }

    public void setModel(ShapeContainer shapeContainer){
        this.model = shapeContainer;
        this.invalidate();
    }

    public void onDraw(Canvas canvas){
        if(model != null){
            model.draw(canvas);
        }
    }
}