import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Main {
    public static <T> void putPointInBox(Box<T> box, T t) {
        if (!box.isEmpty()) {
            System.out.println("Коробка уже заполнена.");
            return;
        }
        box.put(t);
        System.out.println("Предмет, положен в коробку. " + box);
    }
    public static <T, P> List<P> funct(List<T> list, Function<T, P> function) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> vrotoy) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (vrotoy.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> T reduce(List<T> list, BinaryOperator<T> operator, T defaultValue) {
        if (list == null || list.isEmpty()) {
            return defaultValue;
        }

        T result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result = operator.apply(result, list.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Box<Integer> integerBox = new Box<>();
        Box<Point3D> pointBox = new Box<>();

        while (true) {
            System.out.println("\nЧто вы хотите сделать с коробкой?:");
            System.out.println("1. Положить предмет в коробку");
            System.out.println("2. Достать предмет из коробки");
            System.out.println("3. Проверить, пуста ли коробка");
            System.out.println("4. Завершить");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите номер.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    if (!integerBox.isEmpty()) {
                        System.out.println("Коробка уже заполнена (может хранить один произвольный объект в один момент времени).");
                        break;
                    }
                    System.out.print("Введите целое число, которое нужно поместить в коробку: ");
                    if (scanner.hasNextInt()) {
                        int value = scanner.nextInt();
                        integerBox.put(value);
                        System.out.println("Предмет, положен в коробку." + integerBox);
                    } else {
                        System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    BoxUtil.processBox(integerBox);
                    break;
                case 3:
                    System.out.println("Коробка пуста: " + integerBox.isEmpty());
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
            if (choice == 4) {
                break;
            }
        }

        Storage<Integer> intStorageNull = new Storage<>(null);
        StorageUtil.processStorage(intStorageNull, 0);

        Storage<Integer> intStorage99 = new Storage<>(99);
        StorageUtil.processStorage(intStorage99, -1);

        Storage<String> stringStorageNull = new Storage<>(null);
        StorageUtil.processStorage(stringStorageNull, "default");

        Storage<String> stringStorageHello = new Storage<>("hello");
        StorageUtil.processStorage(stringStorageHello, "hello world");

        System.out.println("\nВведите координаты точки (x y z): ");
        if (scanner.hasNextDouble()) {
            double x = scanner.nextDouble();
            if (scanner.hasNextDouble()) {
                double y = scanner.nextDouble();
                if (scanner.hasNextDouble()) {
                    double z = scanner.nextDouble();
                    Point3D point = new Point3D(x, y, z);
                    putPointInBox(pointBox, point);
                } else {
                    System.out.println("Неверный ввод. Пожалуйста, введите числа.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите числа.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Неверный ввод. Пожалуйста, введите числа.");
            scanner.nextLine();
        }

        System.out.println("\nВведите строки для нахождения их длины (для завершения введите пустую строку):");
        scanner.nextLine();
        List<String> inputStrings = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            inputStrings.add(input);
        }
        List<Integer> lengths = funct(inputStrings, String::length);
        System.out.println("Длины строк: " + lengths);
        System.out.println("\nВведите числа для преобразования (для завершения введите пустую строку):");
        List<Integer> inputNumbers = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                inputNumbers.add(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }

        List<Integer> absolutes = funct(inputNumbers, Math::abs);
        System.out.println("Модули чисел: " + absolutes);

        System.out.println("\nВведите массивы чисел для преобразования (для завершения введите пустую строку):");
        List<int[]> inputArrays = new ArrayList<>();
        while (true) {
            System.out.println("Введите числа через пробел (для завершения введите пустую строку):");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                int[] array = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    array[i] = Integer.parseInt(parts[i]);
                }
                inputArrays.add(array);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите числа через пробел.");
            }
        }
        List<Integer> maxValues = funct(inputArrays, arr -> {
            int max = Integer.MIN_VALUE;
            for (int num : arr) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        });
        System.out.println("Максимальные значения в массивах: " + maxValues);

        System.out.println("\nВведите строки для фильтрации (для завершения введите пустую строку):");
        List<String> filterStrings = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            filterStrings.add(input);
        }

        List<String> filteredStrings = filter(filterStrings, s -> s.length() >= 3);
        System.out.println("Строки с длиной >= 3: " + filteredStrings);
        System.out.println("\nВведите числа для фильтрации (для завершения введите пустую строку):");
        List<Integer> filterNumbers = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                filterNumbers.add(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }

        List<Integer> filteredNumbers = filter(filterNumbers, n -> n < 0);
        System.out.println("Отрицательные числа: " + filteredNumbers);

        System.out.println("\nВведите массивы чисел для фильтрации (для завершения введите пустую строку):");
        List<int[]> filterArrays = new ArrayList<>();
        while (true) {
            System.out.println("Введите числа через пробел (для завершения введите пустую строку):");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                int[] array = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    array[i] = Integer.parseInt(parts[i]);
                }
                filterArrays.add(array);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите числа через пробел.");
            }
        }

        List<int[]> filteredArrays = filter(filterArrays, arr -> {
            for (int num : arr) {
                if (num > 0) {
                    return false;
                }
            }
            return true;
        });
        System.out.print("Массивы без положительных элементов: ");
        for (int[] arr : filteredArrays) {
            System.out.print(java.util.Arrays.toString(arr) + " ");
        }
        System.out.println();

        System.out.println("\nВведите строки для объединения (для завершения введите пустую строку):");
        List<String> inputStrings1 = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            inputStrings1.add(input);
        }

        String concatenatedString = reduce(inputStrings1, (a, b) -> a + b, "");
        System.out.println("Объединенная строка: " + concatenatedString);

        System.out.println("\nВведите числа для суммирования (для завершения введите пустую строку):");
        List<Integer> inputNumbers1 = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                inputNumbers1.add(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }

        Integer sum = reduce(inputNumbers1, (a, b) -> a + b, 0);
        System.out.println("Сумма чисел: " + sum);

        System.out.println("\nВведите вложенные списки чисел (для завершения введите пустую строку):");
        List<List<Integer>> inputNestedLists = new ArrayList<>();
        while (true) {
            System.out.println("Введите числа через пробел (для завершения введите пустую строку):");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                List<Integer> nestedList = new ArrayList<>();
                for (String part : parts) {
                    nestedList.add(Integer.parseInt(part));
                }
                inputNestedLists.add(nestedList);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите числа через пробел.");
            }
        }

        int totalElements = reduce(inputNestedLists, new BinaryOperator<List<Integer>>() {
            public List<Integer> apply(List<Integer> a, List<Integer> b) {
                List<Integer> result = new ArrayList<>(a);
                result.addAll(b);
                return result;
            }
        }, new ArrayList<>()).size();
        System.out.println("Общее количество элементов: " + totalElements);
    }
}
