package com.github.robyndalgleish.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class LRUCache<K, V> {

    LinkedHashMap<K, V> map = new LinkedHashMap<>();

    // The capacity stored from dah constructor comment which is next, just read that...
    private final int capacity;

    // The only thing we really need to define before implementing this fucker, is the capacity. Sew... pass that shit in da constructor.
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    // We essentially want a get method and a put method, so let's do that:

    // Put sumfin' in the cache:
    public void put(K key, V value) {
        // If the map has this key, just update the map
        if(map.containsKey(key)){

            // LinkedList reordering to put the node at the newest position
            map.remove(key, value);

        //delete oldest in map if we are above capacity with this new addition
        }else{
            if(map.size() >= capacity){
                Iterator<K> iterator = map.keySet().iterator();
                iterator.next();
                iterator.remove();
            }
        }
        //add new key/value pair to map
        map.put(key, value);
    }

    // Get that cached value:
    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            map.remove(key, value);
            map.put(key, value);
            return value;
        }
        throw new NoSuchElementException("Naw brah; that ain't a thing.");
    }
}
