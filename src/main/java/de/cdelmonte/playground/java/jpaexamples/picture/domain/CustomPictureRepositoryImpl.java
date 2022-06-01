package de.cdelmonte.playground.java.jpaexamples.picture.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.StreamUtils;

public class CustomPictureRepositoryImpl implements CustomPictureRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public ByteArrayOutputStream getImageOfPicture(Long id) throws Exception {
        Picture picture = em.find(Picture.class, id);
        InputStream imageDataStream = picture.getImageBlob().getBinaryStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        StreamUtils.copy(imageDataStream, outStream);

        // byte[] imageBytes = outStream.toByteArray();

        return outStream;
    }

    @Override
    public Picture addPictureWithImage(ByteArrayInputStream imageInputStream) {
        Picture picture = new Picture();
        Session session = em.unwrap(Session.class);
        Blob blob = session.getLobHelper()
                .createBlob(imageInputStream, 1024);
        picture.setImageBlob(blob);
        em.persist(picture);

        return picture;
    }
}
