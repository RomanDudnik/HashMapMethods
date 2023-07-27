package ru.geekbrains.lesson4;

public class Program {

    public static void main(String[] args) {
        System.out.println(-7 % 4);

        HashMap<String, String> hashMap1 = new HashMap<>(4);

        String ret = hashMap1.put("+79001112233", "Андрей");
        ret = hashMap1.put("+79001112232", "Василий");
        ret = hashMap1.put("+79001112234", "Александр1");
        ret = hashMap1.put("+79001112235", "Александр2");
        ret = hashMap1.put("+79001112236", "Александр3");
        ret = hashMap1.put("+79001112237", "Александр4");
        ret = hashMap1.put("+79001112212", "Александр4");
        ret = hashMap1.put("+79001112213", "Александр4");
        ret = hashMap1.put("+79001112214", "Александр4");



        //ret = hashMap1.get("+79001112233");

        //ret = hashMap1.remove("+79001112233");
        //ret = hashMap1.remove("+79001112233");

        //ret = hashMap1.get("+79001112233");

        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("+79001112233", "Андрей");
        myHashMap.put("+79001112232", "Василий");
        myHashMap.put("+79001112234", "Александр1");
        myHashMap.put("+79001112235", "Александр2");
        myHashMap.put("+79001112236", "Александр3");
        myHashMap.put("+79001112237", "Александр4");
        myHashMap.put("+79001112212", "Александр4");
        myHashMap.put("+79001112213", "Александр4");
        myHashMap.put("+79001112214", "Александр4");

        /**
         * используем цикл foreach для перебора всех значений в нашей структуре данных MyHashMap.
         */
        System.out.println("Элементы в хэш-таблице: ");
        for (String value : myHashMap) {
            System.out.println(value);  // Выводим значение переменной `value` в консоль
        }

        System.out.println();

        String val = myHashMap.get("+79001112232");
        System.out.println(val == null ? "Запись не найдена" : "Запись получена: " + val);
        System.out.println();

        myHashMap.remove("+79001112232");
        System.out.println(val == null ? "Запись не найдена" : "Запись удалена: " + val);
        System.out.println();

       val = myHashMap.get("+79001112232");
       System.out.println(val == null ? "Запись не найдена" : "Запись получена: " + val);
       System.out.println();

       System.out.println("Элементы в хэш-таблице: ");
       for (String value : myHashMap) {
           System.out.println(value);
       }


    }

}
