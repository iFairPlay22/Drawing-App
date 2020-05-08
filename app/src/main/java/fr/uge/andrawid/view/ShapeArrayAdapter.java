package fr.uge.andrawid.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        ShapeKind shapeKind = getItem(position);

        TextView textView = new TextView(context);
        textView.setText(shapeKind.toString());
        textView.setTextSize(10);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(shapeKind.getPath());
        imageView.setPadding(0, 0, 0, 7);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, imageView.getId());


        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.addView(imageView);
        relativeLayout.addView(textView, layoutParams);



        return relativeLayout;
    }
}
