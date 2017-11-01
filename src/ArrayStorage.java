import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int resCount = 0;

    void clear() {
        Arrays.fill(storage, 0, resCount,null);
        resCount = 0;
    }

    void save(Resume r) {
        if (getIndexUUID(r) == -1 && resCount != storage.length) {
            storage[resCount] = r;
            resCount++;
            System.out.println(r.uuid + " добавлено в базу.");
        }
        else {
            System.out.println("Резюме с uuid " + r.uuid + " уже есть в базе, или в базе нет места. Сохранение невозможно.");
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
        if (getIndexUUID(get(uuid)) == -1) {
            System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
        } else {
            storage[getIndexUUID(get(uuid))] = storage[resCount-1];
            storage[resCount-1] = null;
            resCount--;
        }
    }

    void update(Resume r) {
        if (getIndexUUID(r) == -1) {
            System.out.println("!!!Ошибка обновления, в базе отсутствует!!!");
        } else {
            storage[getIndexUUID(r)] = r;
            System.out.println(r.uuid + " Обновлено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNull = new Resume[resCount];

        System.arraycopy(storage, 0, notNull, 0, resCount);
        return notNull;
    }

    int size() {
        return resCount;
    }

    private int getIndexUUID(Resume r) {
        int index = -1;
        for (int i = 0; i < resCount; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                return i;
            }
        }
        return index;
    }
}
