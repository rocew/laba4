public class Storage<T> {
    private final T t;

    public Storage(T t) {
        this.t = t;
    }

    public T get(T defaultValue) {
        return t != null ? t : defaultValue;
    }

    public String toString() {
        return "Предмет в хранилище:" + t;
    }
}