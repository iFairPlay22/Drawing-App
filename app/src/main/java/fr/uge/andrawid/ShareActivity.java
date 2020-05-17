package fr.uge.andrawid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import fr.uge.andrawid.model.save.DrawingUploadingAsyncTask;

public class ShareActivity extends AppCompatActivity {

    private final String SITE_URL = "https://codfish.pythonanywhere.com/sendPicture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Uri uri = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);

        ImageView iv = (ImageView) findViewById(R.id.imagePreview);
        iv.setImageURI(uri);

        Button button = (Button) findViewById(R.id.validationButton);
        button.setOnClickListener((v) -> {

            EditText comment = (EditText) findViewById(R.id.commentEditText);

            DrawingUploadingAsyncTask drawingUploadingAsyncTask = new DrawingUploadingAsyncTask();
            drawingUploadingAsyncTask.execute(this.SITE_URL, uri, comment.getText().toString(), this.getContentResolver(), this);

        });
    }
}
