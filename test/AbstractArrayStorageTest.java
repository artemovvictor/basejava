import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AbstractArrayStorageTest {
    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest() {
        storage = new ArrayStorage();
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @After
    public void freeRes() {
        storage = null;
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r1 = storage.get(UUID_2);
        storage.update(new Resume(UUID_2));
        Assert.assertTrue(r1 != storage.get(UUID_2));
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        Assert.assertTrue(all[1] == storage.get(UUID_2));
    }

    @Test
    public void save() {
        Resume r1 = new Resume("uuid4");
        storage.save(r1);
        Assert.assertTrue(r1 == storage.get("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.delete(UUID_2);
    }

    @Test
    public void get() {
        Resume r2 = storage.get(UUID_2);
        Assert.assertTrue(r2 == storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void getStorageOverflow() {
        for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_2));
    }
}