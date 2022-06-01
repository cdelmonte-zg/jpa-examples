package de.cdelmonte.playground.java.jpaexamples.picture.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public interface CustomPictureRepository {
    public Picture addPictureWithImage(ByteArrayInputStream imageInputStream);

    public ByteArrayOutputStream getImageOfPicture(Long id) throws Exception;
}
