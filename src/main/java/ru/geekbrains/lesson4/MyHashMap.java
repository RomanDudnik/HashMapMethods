/**
 * Создаем класс MyHashMap, который реализует интерфейс Iterable<String>.
 * В методе iterator(), мы возвращаем итератор значений внутреннего HashMap.
 * Затем, в методе main() класса Program, мы используем цикл foreach для перебора всех значений в нашей структуре данных MyHashMap.
 * Этот код позволит нам перебирать все элементы нашей структуры данных HashMap с помощью цикла foreach.
 */

package ru.geekbrains.lesson4;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Определяем новый класс MyHashMap, который реализует интерфейс Iterable<String>.
 * Интерфейс `Iterable<String>` указывает, что этот класс предоставляет итератор,
 * который может перебирать элементы типа `String`.
 */
public class MyHashMap implements Iterable<String> {
    /**
     * Объявляем приватную переменную `hashMap` типа `HashMap<String, String>`.
     */
    private HashMap<String, String> hashMap;    // ключи и значения в хэш-таблице(hashMap) являются строками.

    /**
     * Конструктор класса `MyHashMap`.
     * Создает новый объект `HashMap` и присваивает его переменной `hashMap`.
     */
    public MyHashMap() {
        hashMap = new HashMap<>();
    }

    /**
     * Определение публичного метода `put`, который принимает две строки в качестве параметров:
     * `key` и `value` == 'ключ' и 'значение'.
     * Метод добавляет в `hashMap` пару ключ-значение.
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        hashMap.put(key, value);
    }

    /**
     * Определение публичного метода `remove`, который принимает строку `key` в качестве параметра.
     * Метод удаляет из `hashMap` запись с указанным ключом.
     * @param key
     */
    public void remove(String key) {
        hashMap.remove(key);
    }

    /**
     * Определение публичного метода `get`, который принимает строку `key` в качестве параметра и возвращает строку.
     * Метод возвращает значение, связанное с указанным ключом в `hashMap`.
     * @param key
     * @return
     */
    public String get(String key) {
        return hashMap.get(key);
    }

    /**
     * Переопределяем метод `iterator`, который возвращает итератор.
     * Определение публичного метода `iterator`, который возвращает объект `Iterator<String>`.
     * `Iterator` - это интерфейс, предоставляющий методы для перебора элементов в коллекциях.
     * @return
     */
    @Override
    public Iterator<String> iterator() {
        /**
         * Возвращаем итератор, который позволяет перебирать все значения (не ключи) в `hashMap`.
         */
        return hashMap.values().iterator(); // .values()` - метод, возвращающий коллекцию всех значений в хеш-таблице.

    }
}
