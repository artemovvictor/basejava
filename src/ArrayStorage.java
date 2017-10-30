import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int resCount = 0;

    void clear() {
        for (int i = 0; i < resCount; i++) {
            storage[i] = null;
        }
        resCount = 0;
    }

    void save(Resume r) {
        storage[resCount] = r;
        resCount++;
    }

    Resume get(String uuid) {
        Resume r = null;
            for (int i = 0; i < resCount; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    r = storage[i];
                    break;
                }
                else if (i >= resCount-1) {
                    System.out.println("Резюме в базе отсутствует.");
                }
            }
        return r;
    }

    void delete(String uuid) {
            for (int i = 0; i < resCount; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = null;

                    /* Оптимизация массива сортировкой*/
                    for (int j = i; j < resCount-1; j++) {
                        if (storage[j] == null && storage[j + 1] != null) {
                            storage[j] = storage[j + 1];
                            storage[j + 1] = null;
                        }
                    }
                    resCount--;
                    break;
                }
                else if (i >= resCount-1) {
                    System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
                }
            }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNull = new Resume[resCount];

        for (int i = 0, j = 0; i < resCount; i++) {
            if (storage[i] != null) {
                notNull[j] = storage[i];
                j++;
            }
        }
        return notNull;
    }

    int size() {
        return resCount;
    }
}
