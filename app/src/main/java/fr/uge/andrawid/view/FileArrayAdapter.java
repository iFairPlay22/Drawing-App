package fr.uge.andrawid.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.Objects;

public class FileArrayAdapter extends ArrayAdapter<File> {
    private final Context context;

    public FileArrayAdapter(@NonNull Context context, int resource, @NonNull File[] objects) {
        super(context, resource, objects);

        this.context = Objects.requireNonNull(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView = new TextView(context);
        textView.setText(getItem(position).getName());

        return textView;
    }
}
