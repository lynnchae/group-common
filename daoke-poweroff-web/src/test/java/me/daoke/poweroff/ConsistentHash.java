package me.daoke.poweroff;

import java.util.*;

/**
 * User: chenlong
 * Date: 2015/6/29
 * Time: 13:10
 */
public class ConsistentHash<T> {

    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this .hashFunction = hashFunction;
        this .numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle .put(hashFunction .hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle .remove(hashFunction .hash(node.toString() + i));
        }
    }

    public T get(Object key) {
        if (circle .isEmpty()) {
            return null ;
        }
        int hash = hashFunction .hash(key);
        // System.out.println("hash---: " + hash);
        if (!circle .containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle .tailMap(hash);
            hash = tailMap.isEmpty() ? circle .firstKey() : tailMap.firstKey();
        }
        // System.out.println("hash---: " + hash);
        return circle .get(hash);
    }

    static class HashFunction {
        int hash(Object key) {
            //md5加密后，hashcode
            return 1;
          //  return Md5Encrypt.md5(key.toString()).hashCode();
        }
    }

    public static void main(String [] args) {
        HashSet< String> set = new HashSet< String>();
        set.add( "A" );
        set.add( "B" );
        set.add( "C" );
        set.add( "D" );

        Map< String, Integer> map = new HashMap< String, Integer>();

        ConsistentHash< String> consistentHash = new ConsistentHash<String>( new HashFunction(), 1000, set);

        int count = 10000;

        for (int i = 0; i < count; i++) {
            String key = consistentHash.get(i);
            if (map.containsKey(key)) {
                map.put(consistentHash.get(i), map.get(key) + 1);
            } else {
                map.put(consistentHash.get(i), 1);
            }
            // System.out.println(key);
        }

        showServer(map);
        map.clear();
        consistentHash.remove( "A" );

        System. out .println("------- remove A" );

        for (int i = 0; i < count; i++) {
            String key = consistentHash.get(i);
            if (map.containsKey(key)) {
                map.put(consistentHash.get(i), map.get(key) + 1);
            } else {
                map.put(consistentHash.get(i), 1);
            }
            // System.out.println(key);
        }

        showServer(map);
        map.clear();
        consistentHash.add( "E" );
        System. out .println("------- add E" );

        for (int i = 0; i < count; i++) {
            String key = consistentHash.get(i);
            if (map.containsKey(key)) {
                map.put(consistentHash.get(i), map.get(key) + 1);
            } else {
                map.put(consistentHash.get(i), 1);
            }
            // System.out.println(key);
        }

        showServer(map);
        map.clear();

        consistentHash.add( "F" );
        System. out .println("------- add F服务器  业务量加倍" );
        count = count * 2;
        for (int i = 0; i < count; i++) {
            String key = consistentHash.get(i);
            if (map.containsKey(key)) {
                map.put(consistentHash.get(i), map.get(key) + 1);
            } else {
                map.put(consistentHash.get(i), 1);
            }
            // System.out.println(key);
        }

        showServer(map);

    }

    public static void showServer(Map<String , Integer> map) {
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            System. out .println("服务器 " + m.getKey() + "----" + m.getValue() + "个" );
        }
    }
}
