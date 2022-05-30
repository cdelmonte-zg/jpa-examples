package de.cdelmonte.playground.java.jpaexamples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GenericTests {

    @Test
    public final void givenUsingTheJava9_whenUnmodifiableListIsCreated_thenNotModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final List<String> unmodifiableList1 = List.of(list.toArray(new String[] {}));
        final List<String> unmodifiableList2 = Collections.unmodifiableList(list);
        list.add("something");

        assertEquals(4, list.size());
        assertEquals(3, unmodifiableList1.size());

        assertThrows(
                UnsupportedOperationException.class,
                () -> unmodifiableList1.add("four"));

        assertThrows(
                UnsupportedOperationException.class,
                () -> unmodifiableList2.add("four"));

        list.set(1, "none");
        assertEquals("two", unmodifiableList1.get(1));
        assertEquals("none", unmodifiableList2.get(1));

    }
}
