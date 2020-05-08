package com.example.tp1.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import fr.uge.andrawid.model.draw.ShapeKind;

public class ShapeArrayAdapter extends ArrayAdapter<ShapeKind> {

    private final Context context;

    public ShapeArrayAdapter(@NonNull Context context, int resource, @NonNull ShapeKind[] objects) {
        super(context, resource, objects);

        this.context = Objects.requireNonNull(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView = new TextView(context);
        textView.setText("" + getItem(position));

        return textView;
    }
}
