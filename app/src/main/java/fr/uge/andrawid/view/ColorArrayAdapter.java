package fr.uge.andrawid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.Objects;

import fr.uge.andrawid.MainActivity;
import fr.uge.andrawid.model.draw.model.ColorKind;

public class ColorArrayAdapter extends ArrayAdapter<ColorKind> {
    private final Context context;

    public ColorArrayAdapter(@NonNull Context context, int resource, ColorKind[] strings) {
        super(context, resource, strings);

        this.context = Objects.requireNonNull(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ColorKind colorKind = (ColorKind) getItem(position);

        TextView textView = new TextView(context);
        textView.setText(colorKind.toString());
        textView.setBackgroundColor(colorKind.getColor());
        textView.setTextColor(Color.rgb(255, 255, 255));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(5, 5, 5, 5);

        return textView;
    }
}
