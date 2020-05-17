package fr.uge.andrawid.model.save;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import fr.uge.andrawid.ShareActivity;

public class DrawingUploadingAsyncTask extends AsyncTask<Object, Integer, Boolean> {
    private ShareActivity context;

    @Override
    protected Boolean doInBackground(Object... params) {

        try {
            return postPicture((String) params[0], (Uri) params[1], (String) params[2], (ContentResolver) params[3], (ShareActivity) params[4]);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean postPicture(String siteUrl, Uri pictureUri, String comment, ContentResolver contentResolver, ShareActivity context) throws IOException
    {
        this.context = context;

        String url = Objects.requireNonNull(siteUrl);

        if (comment != null)
            url += "?comment=" + Uri.encode(comment);

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        try {

            InputStream is = contentResolver.openInputStream(pictureUri);

            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(is.available());
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "image/jpeg");

            OutputStream os = conn.getOutputStream();

            byte[] buffer = new byte[1014];
            for (int r = is.read(buffer); r != -1; r = is.read(buffer)) {
                os.write(buffer, 0, r);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {
                    break;
                }
            }

            is.close();
            os.flush();
            os.close();

            try {
                conn.getInputStream().close();
            } catch (Exception e) {
                Log.e("azerty", "Failed to close inputStream.");
            }

            return conn.getResponseCode() == 200;

        } finally {
            conn.disconnect();
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {

        Toast t = Toast.makeText(context, result ? "Success" : "Failure", Toast.LENGTH_SHORT);

        if (result)
            context.finish();

        super.onPostExecute(result);
    }
}