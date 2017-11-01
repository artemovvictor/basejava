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
        if (resCount == 0) {
            storage[resCount] = r;
            resCount++;
            System.out.println(r.uuid + " добавлено в базу.");
        }
        else if (resCount == storage.length) {
            System.out.println("Отсутствует место в базе. Сохранение невозможно.");
        }
        else {
            for (int i = 0; i < resCount; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    System.out.println("Резюме " + r.uuid + " уже имеется в базе.");
                    break;
                } else {
                    storage[resCount] = r;
                    resCount++;
                    System.out.println(r.uuid + " добавлено в базу.");
                    break;
                }
            }
        }

    }

    Resume get(String uuid) {
            for (int i = 0; i < resCount; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        System.out.println("Резюме в базе отсутствует.");
        return null;
    }

    void delete(String uuid) {
            for (int i = 0; i < resCount; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = storage[resCount-1];
                    storage[resCount-1] = null;
                    resCount--;
                    break;
                }
                else if (i == resCount-1) {
                    System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
                }
            }
    }

    void update(Resume r) {
        for (int i = 0; i < resCount; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                System.out.println(r.uuid + " Обновлено.");
                break;
            }
            else if (i == resCount-1) {
                System.out.println(r.uuid + " обновить невозможно.\nПопытка добавления в базу...");
                save(r);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNull = new Resume[resCount];

        for (int i = 0; i < resCount; i++) {
            notNull[i] = storage[i];
        }
        return notNull;
    }

    int size() {
        return resCount;
    }
}
