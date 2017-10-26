import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        try {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    r = storage[i];
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(uuid + " в базе отсутствует.");
        }
        return r;
    }

    void delete(String uuid) {
        try {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = null;

                    /* Оптимизация массива сортировкой*/
                    for (int j = i; j < storage.length-1; j++) {
                        if (storage[j] == null && storage[j + 1] != null) {
                            storage[j] = storage[j + 1];
                            storage[j + 1] = null;
                        }
                    }
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNull = new Resume[size()];

        for (int i = 0, j = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                notNull[j] = storage[i];
                j++;
            }
        }
        return notNull;
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            }
        }
        return count;
    }
}
