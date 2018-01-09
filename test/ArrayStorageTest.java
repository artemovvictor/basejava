import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest  extends AbstractArrayStorageTest {

    @Test
    public void fillDeletedElement() {
        ((ArrayStorage)storage).fillDeletedElement(0);
        Resume[] all = storage.getAll();
        Assert.assertTrue(all[0] == all[2]);
    }

    @Test
    public void insertElement() {
        Resume r = new Resume("uuid4");
        ((ArrayStorage)storage).insertElement(r, -1);
        ((ArrayStorage) storage).size++;
        Resume[] all = storage.getAll();
        Assert.assertEquals(r, all[3]);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(1, ((ArrayStorage)storage).getIndex("uuid2"));
    }
}