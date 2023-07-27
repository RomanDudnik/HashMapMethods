package ru.geekbrains.lesson4;

public class HashMap<K, V> {
    /**
     * В классе `HashMap` существуют вложенные классы `Entity` и `Bucket`.
     * Вложенный класс `Entity` представляет собой объект, содержащий пару ключ-значение.
     * Вложенный класс `Bucket` представляет собой один элемент внутри массива `buckets` и содержит связный список узлов.
     */
    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5; //50%
    private int size;

    private Bucket[] buckets;

    class Entity {
        K key;
        V value;
    }

    /**
     *  В классе `Bucket` существует вложенный класс `Node`, который представляет собой узел связного списка внутри `Bucket`.
     *  Каждый узел содержит ссылку на следующий узел и ссылку на объект `Entity`.
     * @param <K>
     * @param <V>
     */
    class Bucket<K, V> {

        Node head;

        class Node {
            Node next;

            Entity nodeValue;
        }

        /**
         * Метод `add` в классе `Bucket` добавляет новый узел в конец связного списка,
         * если ключ отсутствует, и обновляет значение, если ключ уже существует.
         * Метод возвращает предыдущее значение для данного ключа, если оно существовало,
         * и `null`, если ключ был добавлен в первый раз.
         * @param entity
         * @return
         */
        public V add(Entity entity) {
            Node node = new Node();
            node.nodeValue = entity;

            if (head == null) {
                head = node;
                return null;
            }
            Node currentNode = head;
            while (true) {
                if (currentNode.nodeValue.key.equals(entity.key)) {
                    V buf = (V) currentNode.nodeValue.value;
                    currentNode.nodeValue.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        /**
         * Метод `get` в классе `Bucket` осуществляет поиск значения по ключу в связном списке и возвращает это значение, если ключ найден,
         * или `null`, если ключ отсутствует.
         * @param key
         * @return
         */
        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (node.nodeValue.key.equals(key)) {
                    return (V) node.nodeValue.value;
                }
                node = node.next;
            }
            return null;
        }

        /**
         * Метод `remove` в классе `Bucket` удаляет узел из связного списка по ключу, если он существует, и возвращает его значение.
         * Если ключ не найден, метод возвращает `null`.
         * @param key
         * @return
         */
        public V remove(K key) {
            if (head == null)
                return null;
            if (head.nodeValue.key.equals(key)) {
                V buf = (V) head.nodeValue.value;
                head = head.next;
                return buf;
            } else {
                Node node = head;
                while (node.next != null) {
                    if (node.next.nodeValue.key.equals(key)) {
                        V buf = (V) node.next.nodeValue.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }


    }

    /**
     * Метод `calculateBucketIndex` вычисляет индекс в массиве `buckets` на основе ключа с помощью хэш-функции.
     * @param key
     * @return
     */
    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    /**
     * Метод `recalculate` пересчитывает размер массива `buckets`
     * и перемещает все элементы из прежнего массива в новый массив увеличенного размера.
     */
    private void recalculate(){
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++){
            Bucket<K, V> bucket = old[i];
            if (bucket != null){
                Bucket.Node node = bucket.head;
                while (node != null){
                    put((K)node.nodeValue.key, (V)node.nodeValue.value);
                    node = node.next;
                }
            }
            old[i] = null;
        }
    }

    /**
     * Метод `put` добавляет новую пару ключ-значение в HashMap.
     * Если после добавления элемента размер HashMap превышает заданный коэффициент загрузки,
     * вызывается метод `recalculate` для увеличения размера массива `buckets`.
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {

        if (buckets.length * LOAD_FACTOR <= size)
            recalculate();

        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        V ret = (V)bucket.add(entity);
        if (ret == null){
            size++;
        }
        return ret;
    }

    /**
     * Метод `get` возвращает значение для указанного ключа, или `null`, если ключ отсутствует в HashMap.
     * @param key
     * @return
     */
    public V get(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        return (V) bucket.get(key);
    }

    /**
     *  Метод `remove` удаляет пару ключ-значение для указанного ключа и возвращает удаленное значение,
     *  или `null`, если ключ отсутствует в HashMap.
     * @param key
     * @return
     */
    public V remove(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        V ret = (V) bucket.remove(key);
        if (ret != null){
            size--;
        }
        return ret;
    }

    public HashMap() {
        this(INIT_BUCKET_COUNT);
        //buckets = new Bucket[INIT_BUCKET_COUNT];
    }

    /**
     * Конструктор `HashMap` инициализирует `buckets` массив заданного размера.
     * @param initCount
     */
    public HashMap(int initCount) {
        buckets = new Bucket[initCount];
    }

    /**
     * Этот класс `HashMap` представляет простую реализацию хэш-таблицы с использованием массива и связных списков для разрешения коллизий.
     */


}
