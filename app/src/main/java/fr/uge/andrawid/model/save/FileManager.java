package fr.uge.andrawid.model.save;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

import fr.uge.andrawid.MainActivity;
import fr.uge.andrawid.model.draw.container.ShapeContainer;
import fr.uge.andrawid.view.DrawingView;

import static androidx.core.content.FileProvider.getUriForFile;

public class FileManager {

    private static final FileManager fileManager = new FileManager();

    private File appPath;
    private File temporaryImages;
    private Context context;

    private JsonManager jsonManager;

    private FileManager() {}

    public static FileManager getInstance() {
        return fileManager;
    }

    public void init(Context context) {

        this.context = Objects.requireNonNull(context);
        this.jsonManager = new JsonManager();

        appPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "andrawid");

        if (!appPath.exists()){
            if (!appPath.mkdirs()){
                Log.e("azerty", "failed to create appPath directory");
            }
        }

        temporaryImages = new File(context.getCacheDir(), "temporaryImages");

        if (!temporaryImages.exists()){
            if (!temporaryImages.mkdirs()){
                Log.e("azerty", "failed to create temporaryImages directory");
            }
        }
    }

    public File[] listFiles() {
        File[] files = appPath.listFiles();
        return files == null ? new File[0] : files;
    }

    public boolean exists(String fileName) {
        return new File(appPath, Objects.requireNonNull(fileName)).exists();
    }

    public ShapeContainer load(String fileName) {
        ShapeContainer shapeContainer = null;

        try {

            shapeContainer = jsonManager.load(new FileInputStream(new File(appPath, Objects.requireNonNull(fileName))));

        } catch (FileNotFoundException e) {
            Log.e("azerty", "Error when loading " + fileName + " drawing!");
            e.printStackTrace();
        }

        return shapeContainer;
    }

    public ShapeContainer save(ShapeContainer shapeContainer, String fileName) {

        try {

            File file = new File(appPath, Objects.requireNonNull(fileName));

            if (!file.exists())
                file.createNewFile();

            jsonManager.save(shapeContainer, new FileOutputStream(file));


        } catch (Exception e) {
            Log.i("azerty", "Error when saving " + fileName + " drawing!");
            e.printStackTrace();
        }

        return shapeContainer;
    }

    public Uri createTemporaryImage(String drawingName, ShapeContainer shapeContainer, DrawingView drawingView) {


        File filepath = new File(temporaryImages, Objects.requireNonNull(drawingName) + ".jpg");
        DrawingIO exporter = new DrawingBitmapExporter(Bitmap.CompressFormat.JPEG, drawingView);

        try {

            exporter.save(Objects.requireNonNull(shapeContainer), new FileOutputStream(filepath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return getUriForFile(context, "fr.upem.andrawid.tempProvider", filepath);
    }
}
