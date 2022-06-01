package de.cdelmonte.playground.java.jpaexamples.picture.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.cdelmonte.playground.java.jpaexamples.configuration.SpringDataConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringDataConfiguration.class })
public class PictureRepositoryTest {

    @Autowired
    PictureRepository pictureRepository;

    @Test
    @Transactional
    void testGetPicture() throws Exception {

        ByteArrayInputStream imageInputStream = new ByteArrayInputStream("abc".getBytes());
        Picture fromDB = pictureRepository.addPictureWithImage(imageInputStream);

        assertEquals("abc",
                pictureRepository.getImageOfPicture(fromDB.getId()).toString());
    }
}
