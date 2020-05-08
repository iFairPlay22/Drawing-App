package fr.uge.andrawid.model.draw;

import java.util.Objects;

import fr.uge.andrawid.R;

public enum ShapeKind {
    SEGMENT(R.drawable.line),
    RECTANGLE(R.drawable.square),
    CURSIVE(R.drawable.cursive);

    private final int path;

    ShapeKind(int path) {
        this.path = path;
    }

    public int getPath() {
        return path;
    }
}
