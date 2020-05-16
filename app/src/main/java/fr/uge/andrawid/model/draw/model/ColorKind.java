package fr.uge.andrawid.model.draw.model;

import android.graphics.Color;

public enum ColorKind {
    BLACK(Color.rgb(0, 0, 0)),
    RED(Color.rgb(255, 0, 0)),
    GREEN(Color.rgb(0, 255, 0)),
    BLUE(Color.rgb(0, 0, 255)),
    YELLOW(Color.rgb(255, 255, 0)),
    PURPLE(Color.rgb(255, 0, 255));

    private final int color;

    ColorKind(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
