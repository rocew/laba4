public class Box<T> {
    private T t;

    public Box() {
        this.t = null;
    }

    public Box(T t) {
        this.t = t;
    }

    public void put(T t) {
        if (!isEmpty()) {
            throw new IllegalStateException("Коробка уже содержит элемент.");
        }
        this.t = t;
    }

    public T get() {
        T temp = t;
        t = null;
        return temp;
    }

    public boolean isEmpty() {
        return t == null;
    }

    public String toString() {
        return "Коробка содержит предмет:" + t;
    }
}
