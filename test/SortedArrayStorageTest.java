import org.junit.Assert;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void fillDeletedElement() {
        Resume r1 = storage.get("uuid2");
        Resume r2 = storage.get("uuid3");
        ((SortedArrayStorage)storage).fillDeletedElement(0);
        Resume[] all = storage.getAll();
        Assert.assertEquals(r1, all[0]);
        Assert.assertEquals(r2, all[1]);
    }

    @Test
    public void insertElement() {
        Resume r = new Resume("uuid0");
        ((SortedArrayStorage)storage).insertElement(r, -1);
        ((SortedArrayStorage) storage).size++;
        Resume[] all = storage.getAll();
        Assert.assertEquals(r, all[0]);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(1, ((SortedArrayStorage)storage).getIndex("uuid2"));
    }
}