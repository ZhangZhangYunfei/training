package allone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class AllOne {

    private HashMap<String, Integer> map = new HashMap<>();
    private HashMap<Integer, ValueNode> valueNodeMap = new HashMap<>();
    private ValueNode head, tail;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {

    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Integer oldValue = map.get(key);
            Integer newValue = oldValue + 1;

            ValueNode current = valueNodeMap.get(oldValue);
            current.keys.remove(key);

            if (current.next == null) {
                ValueNode node = new ValueNode();
                node.value = newValue;
                node.keys = new HashSet<>();
                node.keys.add(key);

                current.next = node;
                node.pre = current;
                tail = node;

                valueNodeMap.put(newValue, node);
            } else if (current.next.value.equals(newValue)) {
                current.next.keys.add(key);
            } else if (current.next.value > newValue) {
                // 插入
                ValueNode node = new ValueNode();
                node.value = newValue;
                node.keys = new HashSet<>();
                node.keys.add(key);

                node.next = current.next;
                node.pre = current;

                current.next.pre = node;
                current.next = node;

                valueNodeMap.put(newValue, node);
            }

            if (current.keys.isEmpty()) {
                // 删除
                // header
                if (current.pre == null) {
                    head = current.next;
                    current.next.pre = null;
                } else if (current.next == null) {
                    current.pre.next = null;
                    tail = current.pre;
                } else {
                    // 其他
                    current.pre.next = current.next;
                    current.next.pre = current.pre;
                }
                valueNodeMap.remove(current.value);
            }

            map.replace(key, oldValue, newValue);
        } else {
            map.put(key, 1);
            if (head == null) {
                ValueNode node = new ValueNode();
                node.value = 1;
                node.keys = new HashSet<>();
                node.keys.add(key);

                head = node;
                tail = head;

                valueNodeMap.put(1, node);
            } else if (head.value > 1) {
                ValueNode node = new ValueNode();
                node.value = 1;
                node.keys = new HashSet<>();
                node.keys.add(key);

                node.next = head;
                head.pre = node;
                head = node;

                valueNodeMap.put(1, node);
            } else if (head.value == 1) {
                head.keys.add(key);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Integer oldValue = map.get(key);
            Integer newValue = oldValue - 1;

            ValueNode current = valueNodeMap.get(oldValue);
            current.keys.remove(key);

            if (newValue != 0) {
                if (current.pre == null) {
                    ValueNode node = new ValueNode();
                    node.value = newValue;
                    node.keys = new HashSet<>();
                    node.keys.add(key);

                    current.pre = node;
                    node.next = current;
                    head = node;

                    valueNodeMap.put(newValue, node);
                } else if (current.pre.value.equals(newValue)) {
                    current.pre.keys.add(key);
                } else if (current.pre.value < newValue) {
                    // 插入
                    ValueNode node = new ValueNode();
                    node.value = newValue;
                    node.keys = new HashSet<>();
                    node.keys.add(key);

                    node.next = current;
                    node.pre = current.pre;

                    current.pre.next = node;
                    current.pre = node;

                    valueNodeMap.put(newValue, node);
                }
                map.replace(key, oldValue, newValue);
            } else {
                map.remove(key);
            }

            if (current.keys.isEmpty()) {
                // 删除
                // header
                if (current.pre == null) {
                    head = current.next;
                    current.next.pre = null;
                } else if (current.next == null) {
                    current.pre.next = null;
                    tail = current.pre;
                } else {
                    // 其他
                    current.pre.next = current.next;
                    current.next.pre = current.pre;
                }
                valueNodeMap.remove(current.value);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return tail == null ? "" : tail.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return head == null ? "" : head.keys.iterator().next();
    }

    private static class ValueNode {
        Integer value;

        Set<String> keys;

        ValueNode pre;

        ValueNode next;
    }

    public static void main(String[] args) {
        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.inc("b");
        obj.inc("b");

        obj.dec("b");
        obj.dec("b");

        String param_3 = obj.getMaxKey();
        String param_4 = obj.getMinKey();
    }
}