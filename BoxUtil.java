public class BoxUtil {
    public static void processBox(Box<Integer> box) {
        if (!box.isEmpty()) {
            int value = box.get();
            System.out.println("Извлечен из коробки: " + value);
        } else {
            System.out.println("Коробка пуста.");
        }
    }
}