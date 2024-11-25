public class StorageUtil {
    public static <T> void processStorage(Storage<T> storage, T defaultValue) {
        T value = storage.get(defaultValue);
        System.out.println("Извлеченное значение из хранилища: " + value);
    }
}