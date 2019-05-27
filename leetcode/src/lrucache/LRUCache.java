package lrucache;

import java.util.LinkedHashMap;

public class LRUCache {

    private LinkedHashMap<Integer, Integer> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Integer value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        } else if (map.size() == capacity) {
            map.remove(map.entrySet().iterator().next().getKey());
            map.put(key, value);
        } else {
            map.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1);

        cache.put(2, 1);
        cache.get(1);
    }
}