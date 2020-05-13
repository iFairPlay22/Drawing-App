package fr.uge.andrawid.model.save;

import java.io.InputStream;
import java.io.OutputStream;

import fr.uge.andrawid.model.draw.container.ShapeContainer;

public interface DrawingIO
{

    public void save(ShapeContainer container, OutputStream output);

    public ShapeContainer load(InputStream input);

}
