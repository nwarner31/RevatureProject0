
import net.revature.nwarner.project0.collections.MyArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyArrayListTest {
    MyArrayList<Integer> mal;

    @BeforeEach
    public void setupTest() {
        mal = new MyArrayList<Integer>();
        mal.addItem(5);
    }

    @AfterEach
    public void teardownTest() {
        mal = null;
    }

    @Test
    public void containsOneAddedInteger() {
        assertEquals(0, mal.findItem(5));
    }

    @Test
    public void doesNotContainInteger() {
        assertEquals(-1, mal.findItem(10));
    }

    @Test
    public void doesFindInteger() {
        assertEquals(5, mal.getItem(0));
    }

    @Test
    public void doesNotFindInvalidIndex() {
        assertEquals(null, mal.getItem(100));
    }

    @Test
    public void doesUpdateValidIndex() {
        mal.updateItem(50, 0);
        assertEquals(50, mal.getItem(0));
    }

    @Test
    public void doesNotUpdateInvalidIndex() {
        mal.updateItem(50, 3);
        assertEquals(null, mal.getItem(3));
    }

    @Test
    public void doesFindTwoIndicesForFindAll() {
        mal.addItem(20);
        mal.addItem(5);
        int[] indices = mal.findAllInstances(5);
        assertEquals(0, indices[0]);
        assertEquals(2, indices[1]);
    }

    @Test
    public void doesNotFindAllForEntryNotInArray() {
        mal.addItem(20);
        mal.addItem(100);
        int[] indices = mal.findAllInstances(1);
        assertEquals(null, indices);
    }

    @Test
    public void insertSuccessful() {
        assertTrue(mal.insertItem(25, 0));
        assertEquals(25, mal.getItem(0));
    }

    @Test
    public void insertFailInvalidIndex() {
        assertFalse(mal.insertItem(10, 8));
    }

    @Test
    public void isSubsetTrue() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(20);
        mal2.addItem(38);
        mal2.addItem(5);
        mal2.addItem(100);
        assertTrue(mal.isSubsetOf(mal2));
    }

    @Test
    public void isSubsetFailArrayLarger() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(5);
        mal2.addItem(5);
        assertFalse(mal2.isSubsetOf(mal));
    }

    @Test
    public void isSubsetFailValues () {
        mal.addItem(25);
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(25);
        mal2.addItem(100);
        mal2.addItem(50);
        assertFalse(mal.isSubsetOf(mal2));
    }

    @Test
    public void isEqualTrue() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(25);
        mal2.addItem(5);
        mal.addItem(25);
        assertTrue(mal.isEqualTo(mal2));
    }

    @Test
    public void isEqualsFalseDifferentNumberValues() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(90);
        mal2.addItem(5);
        mal2.addItem(90);
        mal.addItem(90);
        assertFalse(mal.isEqualTo(mal2));
        assertFalse(mal2.isEqualTo(mal));
    }

    @Test
    public void isEqualsFalseDifferentValues() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(30);
        mal2.addItem(5);
        mal.addItem(50);
        assertFalse(mal.isEqualTo(mal2));
        assertFalse(mal2.isEqualTo(mal));
    }

    @Test
    public void isEqualsFalseDifferentNumberSameValues() {
        MyArrayList<Integer> mal2 = new MyArrayList<>();
        mal2.addItem(50);
        mal2.addItem(50);
        mal2.addItem(5);
        mal.addItem(50);
        mal.addItem(5);
        assertFalse(mal.isEqualTo(mal2));
        assertFalse(mal2.isEqualTo(mal));
    }

    @Test
    public void removeSuccess() {
        mal.addItem(50);
        assertTrue(mal.removeItem(0));
        assertEquals(50, mal.getItem(0));
        assertEquals(1, mal.getSize());
    }

    @Test
    public void removeFailInvalidIndex() {
        mal.addItem(20);
        assertFalse(mal.removeItem(5));
    }
}
