import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Сохранить невозможно! Нет свободного места.");
        }
        else if (getIndex(r.getUuid()) != -1){
            System.out.println("Резюме с uuid " + r.getUuid() + " уже есть в базе! Сохранить невозможно.");
        }
        else {
            storage[size] = r;
            size++;
            System.out.println(r.getUuid() + " добавлено в базу.");
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме в базе отсутствует.");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
        } else {
            storage[i] = storage[size-1];
            storage[size-1] = null;
            size--;
        }
    }

    public void update(Resume r) {
        int i = getIndex(r.getUuid());
        if (i == -1) {
            System.out.println("!!!Ошибка обновления, в базе отсутствует!!!");
        } else {
            storage[i] = r;
            System.out.println(r.getUuid() + " Обновлено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
