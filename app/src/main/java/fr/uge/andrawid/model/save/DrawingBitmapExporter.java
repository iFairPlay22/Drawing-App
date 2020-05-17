package fr.uge.andrawid.model.save;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.InputStream;
import java.io.OutputStream;

import fr.uge.andrawid.model.draw.container.ShapeContainer;

public class DrawingBitmapExporter implements DrawingIO {

    private final Bitmap.CompressFormat format;

    public DrawingBitmapExporter(Bitmap.CompressFormat format) {
        this.format = format;
    }

    @Override
    public void save(ShapeContainer container, OutputStream output) {

        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        container.draw(canvas);

        bitmap.compress(format, 100, output);

    }

    @Override
    public ShapeContainer load(InputStream input) {
        throw new IllegalStateException();
    }
}
