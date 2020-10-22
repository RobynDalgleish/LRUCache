package com.github.robyndalgleish.cache;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class LRUCache<K, V> {

    // We need to access a particular node at any place in the list, but we only need to keep track of the newest and oldest. So, field them fuckers.
    LinkedList<Map.Entry<K, V>> linkedList = new LinkedList<>();

    // This thing is essentially a map, so like, map.
    Map<K, V> map = new HashMap<>();

    // The capacity stored from dah constructor comment which is next, just read that...
    private final int capacity;
    private int length;

    // The only thing we really need to define before implementing this fucker, is the capacity. Sew... pass that shit in da constructor.
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.length = 0;
    }

    // We essentially want a get method and a put method, so let's do that:

    // Put sumfin' in the cache:
    public void put(K key, V value) {
        // If the map has this key, just update the map
        if(map.containsKey(key)){

            // LinkedList reordering to put the node at the newest position
            linkedList.remove(Map.entry(key, value));

        //delete oldest in map if we are above capacity with this new addition
        }else{
            if(length >= capacity){
                var removed = linkedList.removeLast();
                map.remove(removed.getKey());
                length --;
            }
        }
        //add new key/value pair to map
        map.put(key, value);
        length  ++;
        linkedList.addFirst(Map.entry(key, value));
    }

    // Get that cached value:
    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            linkedList.remove(Map.entry(key, value));
            linkedList.addFirst(Map.entry(key, value));
            return value;
        }
        throw new NoSuchElementException("Naw brah; that ain't a thing.");
    }
}
