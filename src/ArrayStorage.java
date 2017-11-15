import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index= getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Сохранить невозможно! Нет свободного места.");
        }
        else if (index != -1){
            System.out.println("Резюме с uuid " + r.getUuid() + " уже есть в базе! Сохранить невозможно.");
        }
        else {
            storage[size] = r;
            size++;
            System.out.println(r.getUuid() + " добавлено в базу.");
        }
    }

    public void delete(String uuid) {
        int index= getIndex(uuid);
        if (index == -1) {
            System.out.println(uuid + " удалить невозможно, в базе отсутствует.");
        } else {
            storage[index] = storage[size-1];
            storage[size-1] = null;
            size--;
        }
    }

    public void update(Resume r) {
        int index= getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("!!!Ошибка обновления, в базе отсутствует!!!");
        } else {
            storage[index] = r;
            System.out.println(r.getUuid() + " Обновлено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
