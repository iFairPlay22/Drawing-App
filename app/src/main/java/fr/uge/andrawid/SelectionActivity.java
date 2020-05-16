package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.util.Objects;

import fr.uge.andrawid.model.save.FileManager;
import fr.uge.andrawid.view.FileArrayAdapter;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        EditText drawingNameEditText = (EditText) findViewById(R.id.drawingNameEditText);
        Button drawingNameButton = (Button) findViewById(R.id.drawingNameButton);
        drawingNameButton.setOnClickListener((e) -> {
            changeActivity(drawingNameEditText.getEditableText().toString());
        });


        ListView drawingNameListView = (ListView) findViewById(R.id.drawingNameListView);
        drawingNameListView.setAdapter(new FileArrayAdapter(this, android.R.layout.simple_list_item_1, FileManager.getInstance().listFiles()));
        drawingNameListView.setOnItemClickListener( (adapterView, view, i, l) -> {
            changeActivity(((File) adapterView.getItemAtPosition(i)).getName());
        });

    }

    private void changeActivity(String drawingName) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("drawingName", Objects.requireNonNull(drawingName));
        setResult(RESULT_OK, intent);
        finish();
    }
}
