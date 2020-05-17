package fr.uge.andrawid.model.save;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import java.io.InputStream;
import java.io.OutputStream;

import fr.uge.andrawid.model.draw.container.ShapeContainer;
import fr.uge.andrawid.view.DrawingView;

public class DrawingBitmapExporter implements DrawingIO {

    private final Bitmap.CompressFormat format;
    private final DrawingView drawingView;

    public DrawingBitmapExporter(Bitmap.CompressFormat format, DrawingView drawingView) {
        this.format = format;
        this.drawingView = drawingView;
    }

    @Override
    public void save(ShapeContainer container, OutputStream output) {
        Bitmap bitmap = Bitmap.createBitmap(drawingView.getWidth(), drawingView.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE); // draw white background on the canvas
        container.draw(canvas);

        bitmap.compress(format, 100, output);
    }

    @Override
    public ShapeContainer load(InputStream input) {
        throw new IllegalStateException();
    }

}
