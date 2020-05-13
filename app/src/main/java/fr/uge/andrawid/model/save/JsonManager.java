package fr.uge.andrawid.model.save;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import fr.uge.andrawid.model.draw.container.ShapeContainer;

public class JsonManager implements DrawingIO {

    @Override
    public void save(ShapeContainer container, OutputStream output) {

        Log.i("azerty", container.toJSON().toString());

        try {
            Objects.requireNonNull(output).write(Objects.requireNonNull(container).toJSON().toString().getBytes());
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShapeContainer load(InputStream input) {

        try (
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String inputStr;

            while ((inputStr = streamReader.readLine()) != null)
                stringBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            Log.i("azerty", jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
